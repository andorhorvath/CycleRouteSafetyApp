package hu.crs.cycleroutesafetymaven.view;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.lynden.gmapsfx.service.geocoding.GeocodingServiceCallback;
import hu.crs.cycleroutesafetymaven.model.Route;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


/**
 *
 * @author ahorvath
 * According to the example code.
 */
public class ShowRouteMapController implements Initializable, MapComponentInitializedListener {
    
    @FXML
    private GoogleMapView mapView;
    
    @FXML
    private TextField addressTextField;
    @FXML
    private Button searchButton;
    
    private GoogleMap map;
    private GeocodingService geocodingService;
    private StringProperty addressToSearch = new SimpleStringProperty();
    
    private LatLong latLon;
    private Route route;
    
    /**
     * set the MapViewÃ¢â‚¬â„¢s initialization listener to the FXMLController as well
     * as bind the address property to the address TextFieldÃ¢â‚¬â„¢s text property.
     * 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
        addressToSearch.bind(addressTextField.textProperty());
    }    

    @Override
    public void mapInitialized() {
        geocodingService = new GeocodingService();
        MapOptions mapOptions = new MapOptions();
        
        mapOptions.center(new LatLong(47.6097, -122.3331))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
//                .scrollWheel(false)
                .zoom(12);
                   
        map = mapView.createMap(mapOptions);

        
        
       
// start address geocoding & putting marker on map
        geocodingService.geocode(route.getStart(), (GeocodingResult[] results, GeocoderStatus status) -> {
            LatLong latLong = null;
            if( status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching start address found. Address is: " + route.getStart());
                alert.show();
                return;
            } else if( results.length > 1 ) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple start address results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                System.out.println("### DEBUG START ### geocodePoint lambda ELSE ága");
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            }
            this.latLon = latLong;
            System.out.println("### DEBUG START ### geocodePoint lambda utolsó utasítása ez");
    //++TODO: private void addStartMarker(startMarkerOptions, LatLong position, String infoWindowContent);
            
            
            MarkerOptions startMarkerOptions = new MarkerOptions();
            startMarkerOptions.position(latLong);
            Marker startMarker = new Marker(startMarkerOptions);
            map.addMarker( startMarker );
            System.out.println("### DEBUG START ###  marker added");

            InfoWindowOptions startMarkerInfoWindowOptions = new InfoWindowOptions();
            startMarkerInfoWindowOptions.content("<h2>Start Point FFFFS</h2>");
            InfoWindow startMarkerInfoWindow = new InfoWindow(startMarkerInfoWindowOptions);
            startMarkerInfoWindow.open(map, startMarker);
            System.out.println("### DEBUG START ###  before centering&zoom");
            map.setCenter(latLong);

        });
        
        GeocodingServiceCallback callback = null;
        GeocoderStatus status = null;
        GeocodingResult[] results = null;
        geocodingService.geocode(route.getFinish(), callback); 
            LatLong latLong = null;
            if( status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching start address found. Address is: " + route.getFinish());
                alert.show();
                return;
            } else if( results.length > 1 ) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple finish address results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                System.out.println("### DEBUG FINISH ### geocodePoint lambda ELSE ága");
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            }
            this.latLon = latLong;
            System.out.println("### DEBUG FINISH ### geocodePoint lambda utolsó utasítása ez");
    //++TODO: private void addStartMarker(startMarkerOptions, LatLong position, String infoWindowContent);
            
            
            MarkerOptions finishMarkerOptions = new MarkerOptions();
            finishMarkerOptions.position(latLong);
            Marker finishMarker = new Marker(finishMarkerOptions);
            map.addMarker( finishMarker );
            System.out.println("### DEBUG FINISH ###  finish marker added");

            InfoWindowOptions finishMarkerInfoWindowOptions = new InfoWindowOptions();
            finishMarkerInfoWindowOptions.content("<h2>Finish Line</h2>");
            InfoWindow finishMarkerInfoWindow = new InfoWindow(finishMarkerInfoWindowOptions);
            finishMarkerInfoWindow.open(map, finishMarker);
            System.out.println("### DEBUG FINISH ###  before centering&zoom");
            //map.setCenter(latLong);

//        });
        /*
//finish address geocoding & putting marker on map
        geocodingService.geocode(route.getFinish(), (GeocodingResult[] results, GeocoderStatus status) -> {
            LatLong latLong = null;

            if( status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching finish addresses found. Address is: " + route.getFinish());
                alert.show();
                return;
            } else if( results.length > 1 ) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple finish addresses results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            }
//++TODO: private void addFinishMarker(startMarkerOptions, LatLong position, String infoWindowContent);
            MarkerOptions finishMarkerOptions = new MarkerOptions();
            finishMarkerOptions.position(latLong);
            Marker finishMarker = new Marker(finishMarkerOptions);
            map.addMarker( finishMarker );

            InfoWindowOptions finishInfoWindowOptions = new InfoWindowOptions();
            finishInfoWindowOptions.content("<h2>Finish Line</h2>");
            //InfoWindow finishMarkerInfoWindow = new InfoWindow(finishInfoWindowOptions);
            //finishMarkerInfoWindow.open(map, finishMarker);

        });
*/        
//TODO: when SHOWMARKERS button pushed, geocode every marker from DB thats 
//connected to this route
                   
        //TODO: onMapRightClick() ===> show MapContextMenu /addNewMarker(), what is here?---getGeoLocationInfo() + displayGeoLocationInfo(), centerMapHere(LatLong ll), +++addWaypointToRoute()/
        //TODO: onMarkerRightClick() ===> show MarkerContextMenu /editMarker(), deleteMarker(), +++show streetView(), +++/
        //TODO: onMarkerClick() ===> showMarkerInfoWindow();
        //TODO: MenufejlÃ©c + HelpTextBox helpTextBox;
        //TODO: ADD searchField + CenterMap()
        
            /* ADD MARKER EXAMPLE
        MarkerOptions markerOptions5 = new MarkerOptions();
        LatLong fredWilkieLocation = new LatLong(47.6597, -122.3357);
        markerOptions5.position(fredWilkieLocation);
        Marker fredWilkieMarker = new Marker(markerOptions5);
        map.addMarker( fredWilkieMarker );
        
        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("<h2>Fred Wilkie</h2>"
                                + "Current Location: Safeway<br>"
                                + "ETA: 45 minutes" );
        InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
        fredWilkeInfoWindow.open(map, fredWilkieMarker);
*/
    }
    
    @FXML
    public void addressTextFieldAction(ActionEvent event) {
        System.out.println("User wants to search!");
        
        geocodingService.geocode(addressToSearch.get(), (GeocodingResult[] results, GeocoderStatus status) -> {
            
            LatLong latLong = null;
            
            if( status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if( results.length > 1 ) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            }

            map.setCenter(latLong);
            map.setZoom(17);
            
            

        });
        
    }
    
    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    
    public void geocodePoint(String address) {
            System.out.println("### DEBUG ### geocodePoint meghívva");

        System.out.println("### DEBUG ### geocodePoint lambda után");

    }
}
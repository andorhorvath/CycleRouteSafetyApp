package hu.crs.cycleroutesafetymaven.view;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
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
    @FXML
    private Button startButton;
    @FXML
    private Button finishButton;
    
    private GoogleMap map;
    private GeocodingService geocodingService;
    private final StringProperty addressToSearch = new SimpleStringProperty();
    
    private Route route;
    private Boolean foundMarkerExists;
    private LatLong startLatLong;
    private LatLong finishLatLong;
    
    private MarkerOptions startMarkerOptions;
    private Marker startMarker;
    private InfoWindowOptions startMarkerInfoWindowOptions;
    private InfoWindow startMarkerInfoWindow;
    
    private MarkerOptions finishMarkerOptions;
    private Marker finishMarker;
    private InfoWindowOptions finishMarkerInfoWindowOptions;
    private InfoWindow finishMarkerInfoWindow;
    
    private Marker findMarker;
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

        foundMarkerExists = false;
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
        
        startMarkerOptions = new MarkerOptions();
        startMarkerInfoWindowOptions = new InfoWindowOptions();
        startMarkerInfoWindowOptions.content("<h2>Start point</h2>");
        
        finishMarkerOptions = new MarkerOptions();
        finishMarkerInfoWindowOptions = new InfoWindowOptions();
        finishMarkerInfoWindowOptions.content("<h2>Finish line</h2>");

       
//Lat: 47.5041761 Long: 19.072502699999973
        geocodeStart();
        geocodeFinish();

//TODO: when SHOWMARKERS button pushed, geocode every marker from DB thats 
//connected to this route
            
            //TODO: onMapRightClick() ===> show MapContextMenu /addNewMarker(), what is here?---getGeoLocationInfo() + displayGeoLocationInfo(), centerMapHere(LatLong ll), +++addWaypointToRoute()/
            //TODO: onMarkerRightClick() ===> show MarkerContextMenu /editMarker(), deleteMarker(), +++show streetView(), +++/
            //TODO: onMarkerClick() ===> showMarkerInfoWindow();
            //TODO: MenuHeader + HelpTextBox helpTextBox;
            //DONE: ADD searchField + CenterMap()
    }
    
    @FXML
    public void addressTextFieldAction(ActionEvent event) {
        System.out.println("User wants to search!");
        if (foundMarkerExists)
            map.removeMarker(findMarker);
        
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
//for curiousity, let's see wether this function can put a marker or not...
            MarkerOptions findMarkerOptions = new MarkerOptions();
            findMarkerOptions.position(latLong);
            findMarker = new Marker(findMarkerOptions);
            map.addMarker( findMarker );
            foundMarkerExists = true;
            System.out.println("### DEBUG FIND ###  marker added");

            InfoWindowOptions findMarkerInfoWindowOptions = new InfoWindowOptions();
            findMarkerInfoWindowOptions.content("<h2>Found " + addressToSearch.get() + " </h2>");
            InfoWindow findMarkerInfoWindow = new InfoWindow(findMarkerInfoWindowOptions);
            findMarkerInfoWindow.open(map, findMarker);
            System.out.println("### DEBUG FIND ###  done about the marker...");

        });
        
    }
    
    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    
    public void geocodeStart() {
//        LatLong ll = new LatLong(47.516558,19.09047099999998);
        
        
        geocodingService.geocode(route.getFinish(), (GeocodingResult[] results, GeocoderStatus status) -> {

            if( status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching start address found. Address is: " + route.getStart());
                alert.show();
                return;
            } else if( results.length > 1 ) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple start address results found, showing the first one.");
                alert.show();
                startLatLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                System.out.println("### DEBUG START 1) ### geocodePoint lambda ELSE ága");
                startLatLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            }

            
//++TODO: private void addStartMarker(startMarkerOptions, LatLong position, String infoWindowContent);
// put marker on map
            System.out.println("### DEBUG START 2) ### put marker on map");
            startMarkerOptions.position(startLatLong);
            map.setCenter(startLatLong);
            startMarker = new Marker(startMarkerOptions);
            map.addMarker( startMarker );
            System.out.println("### DEBUG START 3) ###  marker added, infowindow next");
            startMarkerInfoWindow = new InfoWindow(startMarkerInfoWindowOptions);        
            startMarkerInfoWindow.open(map, startMarker);
            System.out.println("### DEBUG START 4) ###  put marker on map END");
// put marker on map END
        });

        
        
    }
    
    public void geocodeFinish() {
/*        geocodingService.geocode(route.getFinish(), (GeocodingResult[] resultsFinish, GeocoderStatus statusFinish) -> {
            LatLong latLong = null;
            if( statusFinish == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching finish address found. Address is: " + route.getFinish());
                alert.show();
                return;
            } else if( resultsFinish.length > 1 ) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple finish address results found, showing the first one.");
                alert.show();
                latLong = new LatLong(resultsFinish[0].getGeometry().getLocation().getLatitude(), resultsFinish[0].getGeometry().getLocation().getLongitude());
            } else {
                System.out.println("### DEBUG FINISH 1) ### geocodePoint lambda ELSE ága");
                latLong = new LatLong(resultsFinish[0].getGeometry().getLocation().getLatitude(), resultsFinish[0].getGeometry().getLocation().getLongitude());
            }
        });
*/
            LatLong ll = new LatLong(47.5041761,19.072502699999973);

//++TODO: private void addStartMarker(startMarkerOptions, LatLong position, String infoWindowContent);
//put marker on map
            System.out.println("### DEBUG FINISH 2) ### put marker on map");
            finishMarkerOptions.position(ll);
            //map.setCenter(ll);
            finishMarker = new Marker(finishMarkerOptions);
            map.addMarker( finishMarker );
            System.out.println("### DEBUG FINISH 3) ###  marker added");
            finishMarkerInfoWindow = new InfoWindow(finishMarkerInfoWindowOptions);        
            finishMarkerInfoWindow.open(map, finishMarker);
            System.out.println("### DEBUG FINISH 4) ### put marker on map END");
//put marker on map END

    }
}
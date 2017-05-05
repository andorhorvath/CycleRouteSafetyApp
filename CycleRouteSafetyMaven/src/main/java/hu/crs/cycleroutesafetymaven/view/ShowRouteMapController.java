package hu.crs.cycleroutesafetymaven.view;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.directions.*;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import hu.crs.cycleroutesafetymaven.model.Route;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


/**
 *
 * @author ahorvath
 * According to the example code.
 */
public class ShowRouteMapController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback  {
    
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
    @FXML
    private Button routeButton;
    
    private GoogleMap map;
    private GeocodingService geocodingService;
    private final StringProperty addressToSearch = new SimpleStringProperty();
    protected DirectionsService directionsService;
    protected DirectionsPane directionsPane;
    
    private Route route;
    private Boolean foundMarkerExists;
    private LatLong startLatLong;
    private LatLong finishLatLong;
    
//    private MarkerOptions startMarkerOptions;
//    private Marker startMarker;
//    private InfoWindowOptions startMarkerInfoWindowOptions;
//    private InfoWindow startMarkerInfoWindow;
    
//    private MarkerOptions finishMarkerOptions;
//    private Marker finishMarker;
//    private InfoWindowOptions finishMarkerInfoWindowOptions;
//    private InfoWindow finishMarkerInfoWindow;
    
    private Marker findMarker;
    
//    @FXML
//    private VBox vbox;
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
//        vbox = new VBox(8); // spacing = 8
//        vbox.getChildren().addAll(startButton, finishButton, new Button("Paste"));
    }    
    
    @Override
    public void directionsReceived(DirectionsResult results, DirectionStatus status) {
    }
    
    @Override
    public void mapInitialized() {
        geocodingService = new GeocodingService();
           
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(47.5041761, 19.072502699999973))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);
                   
        map = mapView.createMap(mapOptions);
        directionsService = new DirectionsService();
        directionsPane = mapView.getDirec();

        computeAndDrawRoute();
        /* itt kell inicializálni a MAP-et:
MANUAL...        - put startPOI
MANUAL...        - put finishPOI
        - draw route?
        - put every related POI to the route
        */
        
//Lat: 47.5041761 Long: 19.072502699999973
        //mapOptions.center(new LatLong(47.6097, -122.3331))
//TODO: when SHOWMARKERS button pushed, geocode every marker from DB thats 
//connected to this route
            //TODO: onMapRightClick() ===> show MapContextMenu /addNewMarker(), what is here?---getGeoLocationInfo() + displayGeoLocationInfo(), centerMapHere(LatLong ll), +++addWaypointToRoute()/
            //TODO: onMarkerRightClick() ===> show MarkerContextMenu /editMarker(), deleteMarker(), +++show streetView(), +++/
            //TODO: onMarkerClick() ===> showMarkerInfoWindow();
            //TODO: MenuHeader + HelpTextBox helpTextBox;
            //DONE: ADD searchField + CenterMap()

            
            
/*STARTMARKER - geocode, save it, and put it;
                geocodingService.geocode(route.getStart().getAddressText(), (GeocodingResult[] results, GeocoderStatus status) -> {
                if( status == GeocoderStatus.ZERO_RESULTS) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "No matching start address found. Address is: " + route.getStart());
                    alert.show();
                } else if( results.length > 1 ) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple start address results found, showing the first one.");
                    alert.show();

                    this.startLatLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                } else {

                    //System.out.println("echo ll: " + ll.getLatitude() + " -- " + ll.getLongitude());
                    this.startLatLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                    System.out.println("### DEBUG: startLatLong értéket nyert");
                    //System.out.println("### DEBUG START 1) echo startLatLong: " + this.startLatLong.getLatitude() + " -- " + this.startLatLong.getLongitude());

                    //TODO set route.StartPOI... vagy új POI-t létrehozni és beletölteni

                }
        //ide eljut, tovább viszont nem...
            });
        
        
            MarkerOptions findMarkerOptions = new MarkerOptions();
            findMarkerOptions.position(latLong);
            findMarkerOptions.animation(Animation.DROP);

            findMarker = new Marker(findMarkerOptions);
            map.addMarker( findMarker );
            foundMarkerExists = true;

            InfoWindowOptions findMarkerInfoWindowOptions = new InfoWindowOptions();
            findMarkerInfoWindowOptions.content("<h2>Found " + addressToSearch.get() + " </h2>");
            InfoWindow findMarkerInfoWindow = new InfoWindow(findMarkerInfoWindowOptions);
            findMarkerInfoWindow.open(map, findMarker);
*/
        
/*/FINISHMARKER - geocode, save it, and put it;   

        finishMarkerOptions = new MarkerOptions();
        finishMarkerInfoWindowOptions = new InfoWindowOptions();
        finishMarkerInfoWindowOptions.content("<h2>Finish line</h2>");
*/

/* Nem kell az egész anomaly, és GEOCODE(), mert draw route, oszt heló
    System.out.println("JAAAAAAAAAAAAAAJ getIsDirectionsUsed() is " + route.getIsDirectionsUsed().getValue());        
        if (route.getIsDirectionsUsed().getValue()) {
            startLatLong = new LatLong(route.getStart().getLat(), route.getStart().getLon());
            finishLatLong = new LatLong(route.getFinish().getLat(), route.getFinish().getLon());
        }
        else {
            System.out.println("first run... geocode anomaly");
            //first run
            //geocodeStart();
           

            System.out.println("### DEBUG: PLACEHOLDER");
            Double d = this.startLatLong.getLongitude();
            System.out.println("### DEBUG: Geocode inline előtt; startLatLong.getLat() " + d);
            
            System.out.println("after geoCodeStart, MAIN BODY");
            route.getStart().setLat(startLatLong.getLatitude());
            route.getStart().setLon(startLatLong.getLongitude());
            route.setIsDirectionsUsed(true);

            geocodeFinish();
            route.getFinish().setLat(finishLatLong.getLatitude());
            route.getFinish().setLon(finishLatLong.getLongitude());
            //finishLatLong = geocodeFinish();
        }
        
        // startMarker init + put on map
        startMarkerOptions = new MarkerOptions();
        startMarkerOptions.label("Start");
        startMarkerOptions.position(startLatLong);
        startMarkerOptions.title("start title");
        startMarkerOptions.visible(true);

        startMarkerInfoWindowOptions = new InfoWindowOptions();
        startMarkerInfoWindowOptions.content("Start point infowindow"); // <h2> ... </h2>

        startMarker = new Marker(startMarkerOptions);
        map.addMarker(startMarker);
        startMarkerInfoWindow = new InfoWindow(startMarkerInfoWindowOptions);        
        startMarkerInfoWindow.open(map, startMarker);

        System.out.println("marker added...");
        map.setCenter(startLatLong);
*/
        
/* finish... last try
        finishPos = new LatLong(route.getFinish().getLat(), route.getFinish().getLon());
        finishMarkerOptions.position(finishPos);
        map.setCenter(finishPos);
        finishMarker = new Marker(finishMarkerOptions);
        map.addMarker( finishMarker );
        finishMarkerInfoWindow = new InfoWindow(finishMarkerInfoWindowOptions);        
        finishMarkerInfoWindow.open(map, finishMarker);            
*/


    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    /**
     *
     * @return LatLong of geocoded position by addressText
     */
    @FXML
    public void geocodeStart() {
        geocodingService.geocode(route.getStart().getAddressText(), (GeocodingResult[] results, GeocoderStatus status) -> {
            if( status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching start address found. Address is: " + route.getStart());
                alert.show();
            } else if( results.length > 1 ) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple start address results found, showing the first one.");
                alert.show();
                
                this.startLatLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                System.out.println("### DEBUG START 1) ### geocodeSTART: startLatLong is set");
                
                //System.out.println("echo ll: " + ll.getLatitude() + " -- " + ll.getLongitude());
                this.startLatLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                System.out.println("echo startLatLong: " + this.startLatLong.getLatitude() + " -- " + this.startLatLong.getLongitude());


            }
//TODO set route.StartPOI... vagy új POI-t létrehozni és beletölteni
            System.out.println("### DEBUG STARTMARKER 2) ### put marker on map");
            
            MarkerOptions startMarkerOptions = new MarkerOptions();
            startMarkerOptions.position(new LatLong(this.startLatLong.getLatitude(), this.startLatLong.getLongitude()));
            startMarkerOptions.animation(Animation.DROP);
            Marker startMarker = new Marker(startMarkerOptions);
            map.addMarker(startMarker);
            map.setCenter(new LatLong(this.startLatLong.getLatitude(), this.startLatLong.getLongitude()));
            InfoWindowOptions startMarkerInfoWindowOptions = new InfoWindowOptions();
            startMarkerInfoWindowOptions.content("ez a RAJT marker");
            InfoWindow startMarkerInfoWindow = new InfoWindow(startMarkerInfoWindowOptions);
            startMarkerInfoWindow.open(map, startMarker);
        });

            System.out.println("### DEBUG: geocodeStart() AFTER GEOCODE");        
    }
    
    @FXML
    public void geocodeFinish() {
        geocodingService.geocode(route.getFinish().getAddressText(), (GeocodingResult[] resultsFinish, GeocoderStatus statusFinish) -> {
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
                latLong = new LatLong(resultsFinish[0].getGeometry().getLocation().getLatitude(), resultsFinish[0].getGeometry().getLocation().getLongitude());
                System.out.println("### DEBUG FINISH 1) ### geocodeFinish lambda ELSE ága");
            }

//put marker on map
            System.out.println("### DEBUG FINISH 2) ### put marker on map");
            
            MarkerOptions finishMarkerOptions = new MarkerOptions();
            finishMarkerOptions.position(latLong);
            finishMarkerOptions.animation(Animation.DROP);
            Marker finishMarker = new Marker(finishMarkerOptions);
            map.addMarker(finishMarker);
            map.setCenter(latLong);
            InfoWindowOptions finishMarkerInfoWindowOptions = new InfoWindowOptions();
            finishMarkerInfoWindowOptions.content("ez a finishmarker");
            InfoWindow finishMarkerInfoWindow = new InfoWindow(finishMarkerInfoWindowOptions);
            finishMarkerInfoWindow.open(map, finishMarker);
// marker put to map END
        });
    }
    
    @FXML
    public void computeAndDrawRoute() {
        DirectionsRequest request = new DirectionsRequest(route.getStart().getAddressText(), route.getFinish().getAddressText(), TravelModes.DRIVING);
        directionsService.getRoute(request, this, new DirectionsRenderer(true, mapView.getMap(), directionsPane));
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

            MarkerOptions findMarkerOptions = new MarkerOptions();
            findMarkerOptions.position(latLong);
            findMarkerOptions.animation(Animation.DROP);

            findMarker = new Marker(findMarkerOptions);
            map.addMarker( findMarker );
            foundMarkerExists = true;

            InfoWindowOptions findMarkerInfoWindowOptions = new InfoWindowOptions();
            findMarkerInfoWindowOptions.content("<h2>Found " + addressToSearch.get() + " </h2>");
            InfoWindow findMarkerInfoWindow = new InfoWindow(findMarkerInfoWindowOptions);
            findMarkerInfoWindow.open(map, findMarker);

        });
        
    }

}
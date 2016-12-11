package hu.crs.cycleroutesafetymaven.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import hu.crs.cycleroutesafetymaven.MainApp;
import hu.crs.cycleroutesafetymaven.model.Route;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.scene.control.Button;

public class RouteOverviewController {
    @FXML
    private TableView<Route> routeTable;
    @FXML
    private TableColumn<Route, String> nameColumn;
    @FXML
    private TableColumn<Route, String> authorColumn;

    @FXML
    private Label nameLabel;
    @FXML
    private Label authorLabel;
    @FXML
    private Label startLabel;
    @FXML
    private Label finishLabel;
    @FXML
    private Label lengthLabel;
    @FXML
    private Label lastUpdateTimeLabel;
    
    // TRY disable delete btn
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public RouteOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the route table with the two columns.
        nameColumn.setCellValueFactory(
                cellData -> cellData.getValue().nameProperty());
        authorColumn.setCellValueFactory(
                cellData -> cellData.getValue().authorProperty());

        // Clear route details.
        showRouteDetails(null);

        // Listen for selection changes and show the route details when changed.
        routeTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showRouteDetails(newValue));
    }

    /**
    * Fills all text fields to show details about the route.
    * If the specified route is null, all text fields are cleared.
    * 
    * @param route the route or null
    */
    private void showRouteDetails(Route route) {
        if (route != null) {
            // Fill the labels with info from the route object.
            nameLabel.setText(route.getName());
            authorLabel.setText(route.getAuthor());
            startLabel.setText(route.getStart());
            lengthLabel.setText(Integer.toString(route.getLength()));
            finishLabel.setText(route.getFinish());

//DateUtil-el            lastUpdateTimeLabel.setText(DateUtil.format(route.getLastUpdateTime()));
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            route.getLastUpdateTime();
            String displayablelastUpdateTime = df.format(route.getLastUpdateTime());
            lastUpdateTimeLabel.setText(displayablelastUpdateTime);

            deleteButton.setDisable(false);
            editButton.setDisable(false);
            
        } else {
            // Route is null, remove all the text.
            nameLabel.setText("");
            authorLabel.setText("");
            startLabel.setText("");
            lengthLabel.setText("");
            finishLabel.setText("");
            lastUpdateTimeLabel.setText("");
            deleteButton.setDisable(true);
            editButton.setDisable(true);

        }
    }


    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        routeTable.setItems(mainApp.getRouteData());
    }
    
    
    /**
    * Called when the user clicks on the delete button.
    */
    @FXML
    private void handleDeleteRoute() {
        int selectedIndex = routeTable.getSelectionModel().getSelectedIndex();
        /*if (selectedIndex >= 0) {
            routeTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Route Selected");
            alert.setContentText("Please select a route in the table.");

            alert.showAndWait();
        }
        */
        routeTable.getItems().remove(selectedIndex);
    }
    
    /*
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected route.
     */
    @FXML
    private void handleEditRoute() {
        Route selectedRoute = routeTable.getSelectionModel().getSelectedItem();

        boolean okClicked = mainApp.showRouteEditDialog(selectedRoute);
        if (okClicked) {
            showRouteDetails(selectedRoute);
        }
    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewRoute() {
        Route tempRoute = new Route();
        boolean okClicked = mainApp.showRouteEditDialog(tempRoute);
        if (okClicked) {
            mainApp.getRouteData().add(tempRoute);
        }
    }
}
/*
 * this is a change... we will see how it fares 
*/
package hu.crs.cycleroutesafetymaven;

import static javafx.application.Application.launch;

import hu.crs.cycleroutesafetymaven.model.Route;
import hu.crs.cycleroutesafetymaven.model.RouteDataAccessor;
import hu.crs.cycleroutesafetymaven.view.RouteEditDialogController;
import hu.crs.cycleroutesafetymaven.view.RouteOverviewController;
import hu.crs.cycleroutesafetymaven.view.ShowRouteMapController;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.event.DocumentEvent;


public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    /**
     * The data as an observable list of Routes.
     */
    private final ObservableList<Route> routeData = FXCollections.observableArrayList();
    private RouteDataAccessor dataAccessor;
    /**
     * Constructor
     */
    public MainApp() {
//REMOVABLE            // Add some sample data
//REMOVABLE            routeData.add(new Route("munkába", "ahorvath"));
    }

    /**
     * Returns the data as an observable list of Routes. 
     * @return
     */
    public ObservableList<Route> getRouteData() {
        return routeData;
    }
    
    @Override
    public void start(Stage primaryStage) throws SQLException, ClassNotFoundException {
        //TODO: ask into the DB w the user that is accessing the APP after login ofc...
        dataAccessor = new RouteDataAccessor("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/cycleroutes?zeroDateTimeBehavior=convertToNull", "ahorvath", "A\"brakadabra87");
        try {
            
            routeData.addAll(dataAccessor.getRouteList());
        } catch (SQLException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL exception...");
        }
/*
REMOVABLE        TableView<Route> personTable = new TableView<>();
REMOVABLE        TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
REMOVABLE        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
REMOVABLE        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
REMOVABLE        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
REMOVABLE        TableColumn<Person, String> emailCol = new TableColumn<>("Email");
REMOVABLE        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
REMOVABLE
REMOVABLE        personTable.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
REMOVABLE
REMOVABLE        personTable.getItems().addAll(dataAccessor.getPersonList());
REMOVABLE*/  
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("CycleRouteSafetyApp");

        initRootLayout();
        showRouteOverview();
    }
    
    @Override
    public void stop() throws Exception {
        if (dataAccessor != null) {
            dataAccessor.shutdown();
        }
    }

    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            /*
            scene.setOnScroll(new EventHandler() {
                @Override public void handle(ScrollEvent event) {
                    node.setTranslateX(node.getTranslateX() + event.getDeltaX());
                    node.setTranslateY(node.getTranslateY() + event.getDeltaY());
                }
            });
              */  
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Shows the route overview inside the root layout.
     */
    public void showRouteOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/RouteOverview.fxml"));
            AnchorPane routeOverview = (AnchorPane) loader.load();

            // Give the controller access to the main app.
            RouteOverviewController controller = loader.getController();
            controller.setMainApp(this);
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(routeOverview);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    /**
     * Opens a dialog to edit details for the specified route. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     * 
     * @param route the route object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showRouteEditDialog(Route route) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/RouteEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Route");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            RouteEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setRoute(route);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showMapDialog(Route selectedRoute) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/ShowRouteMap.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Show Route on Map");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the route into the controller.
            ShowRouteMapController controller = loader.getController();
            //controller.setDialogStage(dialogStage);
            controller.setRoute(selectedRoute);

            
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            //return controller.isOkClicked();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
    }
    
    
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    
    
    /**TEMPLATE-TEMPLATE-TEMPLATE
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //ENTRY POINT OF APP
        launch(args);
    }

}

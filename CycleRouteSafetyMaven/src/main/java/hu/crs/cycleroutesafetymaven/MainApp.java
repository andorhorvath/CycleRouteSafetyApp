package hu.crs.cycleroutesafetymaven;

import static javafx.application.Application.launch;

import hu.crs.cycleroutesafetymaven.model.Route;
import hu.crs.cycleroutesafetymaven.view.RouteEditDialogController;
import hu.crs.cycleroutesafetymaven.view.RouteOverviewController;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
//DELETABLE     import javafx.scene.Parent;


public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    /**
     * The data as an observable list of Routes.
     */
    private final ObservableList<Route> routeData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public MainApp() {
        // Add some sample data
        routeData.add(new Route("munkába", "ahorvath"));
        routeData.add(new Route("munkából", "ahorvath"));
        routeData.add(new Route("rendelőbe", "kfoldi"));
        routeData.add(new Route("Renihez", "kfoldi"));

    }

    /**
     * Returns the data as an observable list of Routes. 
     * @return
     */
    public ObservableList<Route> getRouteData() {
        return routeData;
    }
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("CycleRouteSafetyApp");

        initRootLayout();

        showRouteOverview();
    }
    
    /*TEMPLATE-TEMPLATE-TEMPLATE
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }
    */
    
    
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.crs.cycleroutesafetymaven.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import hu.crs.cycleroutesafetymaven.model.Route;
import hu.crs.cycleroutesafetymaven.utils.DateUtil;


/**
 * FXML Controller class
 *
 * @author ahorvath
 */
public class RouteEditDialogController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField startField;
    @FXML
    private TextField finishField;
    @FXML
    private TextField lengthField;
    @FXML
    private TextField lastUpdateTimeField;


    private Stage dialogStage;
    private Route route;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    }
    
    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the route to be edited in the dialog. If editing is done, the given
     * object is updated.
     * 
     * @param route
     */
    public void setRoute(Route route) {
        this.route = route;

        nameField.setText(route.getName());
        authorField.setText(route.getAuthor());
        startField.setText(route.getStart());
        finishField.setText(route.getFinish());
        lengthField.setText(Integer.toString(route.getLength()));
        
        lastUpdateTimeField.setText(DateUtil.format(route.getLastUpdateTime()));
        lastUpdateTimeField.setPromptText("yyyy.mm.dd");
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok button.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            route.setName(nameField.getText());
            route.setAuthor(authorField.getText());
            route.setStart(startField.getText());
            route.setFinish(finishField.getText());
            route.setLength(Integer.parseInt(lengthField.getText()));
            route.setLastUpdateTime(DateUtil.parse(lastUpdateTimeField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
    /**
     * Validates the user input in the text corresponding fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid name given!\n"; 
        }
        if (authorField.getText() == null || authorField.getText().length() == 0) {
            errorMessage += "No valid author!\n"; 
        }
        if (startField.getText() == null || startField.getText().length() == 0) {
            errorMessage += "No valid start location!\n"; 
        }

        if (lengthField.getText() == null || lengthField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(lengthField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid length (must be an integer)!\n"; 
            }
        }

        if (finishField.getText() == null || finishField.getText().length() == 0) {
            errorMessage += "No valid finish location!\n"; 
        }

        if (lastUpdateTimeField.getText() == null || lastUpdateTimeField.getText().length() == 0) {
            errorMessage += "No valid ...!\n";
        } else {
            if (!DateUtil.validDate(lastUpdateTimeField.getText())) {
                errorMessage += "No valid lastUpdateTimeField. Use the format yyyy.mm.dd!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
    
}


//public class PersonEditDialogController {
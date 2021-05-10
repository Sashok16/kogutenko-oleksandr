package ua.khpi.oop.kogutenko16.src.sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ua.khpi.oop.kogutenko16.src.sample.model.Shops;
import ua.khpi.oop.kogutenko16.src.sample.util.DateUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Dialog to edit details of a shops.
 * 
 * @author Marco Jakob
 */
public class ProductEditDialogController {

    Map<String,String> description = new HashMap<>();

    @FXML
    private TextField idField;

    @FXML
    private TextField unitField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField dateField;

    @FXML
    private TextField countField;

    @FXML
    private TextField descrField;

    @FXML
    private Button btn_add_descr;


    private Stage dialogStage;
    private Shops shops;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
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
        
        // Set the dialog icon.
        this.dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));
    }

    /**
     * Sets the shops to be edited in the dialog.
     * 
     * @param shops
     */
    public void setPerson(Shops shops) {
        this.shops = shops;
        idField.setText(Integer.toString(shops.getId()));
        nameField.setText(shops.getName());
        countField.setText(Integer.toString(shops.getCount()));
        unitField.setText(shops.getUnit());
        priceField.setText(Integer.toString(shops.getPrice()));
        dateField.setText(DateUtil.format(shops.getDate()));
        descrField.setText(shops.getDescriptionInfo());
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
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            shops.setId(Integer.parseInt(idField.getText()));
            shops.setName(nameField.getText());
            shops.setCount(Integer.parseInt(countField.getText()));
            shops.setPrice(Integer.parseInt(priceField.getText()));
            shops.setDate(DateUtil.parse(dateField.getText()));
            shops.setUnit(unitField.getText());
            shops.setDescription(description);
            System.out.println(shops.toString());
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
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";



        if (idField.getText() == null || idField.getText().length() == 0) {
            errorMessage += "No valid id!\n";
        }
        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid name!\n";
        }
        if (countField.getText() == null || countField.getText().length() == 0) {
            errorMessage += "No valid count!\n";
        } else
        {
            try {
                Integer.parseInt(countField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid count (must be an integer)!\n";
            }
        }
        if (priceField.getText() == null || priceField.getText().length() == 0) {
            errorMessage += "No valid price!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(priceField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid price (must be an integer)!\n";
            }
        }

        if (unitField.getText() == null || unitField.getText().length() == 0) {
            errorMessage += "No valid unit!\n";
        }

        if (dateField.getText() == null || dateField.getText().length() == 0) {
            errorMessage += "No valid date!\n";
        } else {
            if (!DateUtil.validDate(dateField.getText())) {
                errorMessage += "No valid date. Use the format dd.mm.yyyy!\n";
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
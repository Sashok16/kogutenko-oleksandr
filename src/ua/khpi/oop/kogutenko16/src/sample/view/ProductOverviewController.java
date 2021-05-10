package ua.khpi.oop.kogutenko16.src.sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ua.khpi.oop.kogutenko16.src.sample.MainApp;
import ua.khpi.oop.kogutenko16.src.sample.model.Shops;
import ua.khpi.oop.kogutenko16.src.sample.util.DateUtil;

public class ProductOverviewController {
    @FXML
    private TableView<Shops> personTable;
    @FXML
    private TableColumn<Shops, String> idColumn;
    @FXML
    private TableColumn<Shops, String> nameColumn;

    @FXML
    private Label idLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label unitLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label countLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label descrLabel;


    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public ProductOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
//        // Initialize the person table with the two columns.
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
//
//        // Clear person details.
        showPersonDetails(null);
//
//        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }
    
    /**
     * Fills all text fields to show details about the shops.
     * If the specified shops is null, all text fields are cleared.
     * 
     * @param shops the shops or null
     */
    private void showPersonDetails(Shops shops) {
        if (shops != null) {
            // Fill the labels with info from the shops object.
            idLabel.setText(Integer.toString(shops.getId()));
            nameLabel.setText(shops.getName());
            unitLabel.setText(shops.getUnit());
            countLabel.setText(Integer.toString(shops.getCount()));
            priceLabel.setText(Integer.toString(shops.getPrice()));
            dateLabel.setText(DateUtil.format(shops.getDate()));
            descrLabel.setText(shops.getDescriptionInfo());
        } else {
            // Shops is null, remove all the text.
            idLabel.setText("");
            nameLabel.setText("");
            unitLabel.setText("");
            countLabel.setText("");
            priceLabel.setText("");
            dateLabel.setText("");
            descrLabel.setText("");
        }
    }
    
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Shops Selected");
            alert.setContentText("Please select a person in the table.");
            
            alert.showAndWait();
        }
    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewPerson() {
        Shops tempShops = new Shops();
        boolean okClicked = mainApp.showPersonEditDialog(tempShops);
        if (okClicked) {
            mainApp.getPersonData().add(tempShops);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Shops selectedShops = personTable.getSelectionModel().getSelectedItem();
        if (selectedShops != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedShops);
            if (okClicked) {
                showPersonDetails(selectedShops);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Shops Selected");
            alert.setContentText("Please select a person in the table.");
            
            alert.showAndWait();
        }
    }
}
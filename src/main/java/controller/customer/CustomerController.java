package controller.customer;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import model.tm.CustomerTM;
import service.custom.CustomerService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;



public class CustomerController implements Initializable {
    @Inject
    @SuppressWarnings("unused")
    private CustomerService serviceType;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set up table columns with proper property mappings
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnContactNo.setCellValueFactory(new PropertyValueFactory<>("contact"));
        columnGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        columnDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));

        // Set up gender combo box
        cmbGender.setItems(
                FXCollections.observableArrayList(
                        Arrays.asList("Male", "Female")
                )
        );

        // Load data from database and display in table
        try {
            loadTable();
        } catch (Exception e) {
            //noinspection UseOfSystemOutOrSystemErr
            System.err.println("Error loading customer data: " + e.getMessage());
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }

        // Add listener for table row selection
        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            if (t1 != null) {
                Customer customer = new Customer(
                        t1.getId(),
                        t1.getName(),
                        t1.getContact(),
                        t1.getGender(),
                        t1.getAddress(),
                        t1.getDob()
                );
                setTextToValues(customer);
            }
        });

    }

    private void setTextToValues(Customer customer) {
        txtId.setText(String.valueOf(customer.getId()));
        txtName.setText(customer.getName());
        txtContactNo.setText(customer.getContact());
        cmbGender.setValue(customer.getGender());
        txtAddress.setText(customer.getAddress());
        dpDOB.setValue(customer.getDob());
    }

    private void clearFields() {
        txtId.clear();
        txtName.clear();
        txtContactNo.clear();
        cmbGender.setValue(null);
        txtAddress.clear();
        dpDOB.setValue(null);
    }

    @SuppressWarnings({"unused", "ThrowInsideCatchBlockWhichIgnoresCaughtException", "UseOfSystemOutOrSystemErr", "CallToPrintStackTrace"})
    public void loadTable() {
        try {
            System.out.println("=== Starting loadTable() ===");
            System.out.println("serviceType: " + serviceType);

            if (serviceType == null) {
                System.err.println("✗ ERROR: serviceType is NULL! Dependency injection failed.");
                return;
            }

            // Fetch all customers from database
            System.out.println("Calling serviceType.getAll()...");
            List<Customer> allCustomers = serviceType.getAll();
            System.out.println("Retrieved " + (allCustomers != null ? allCustomers.size() : "null") + " customers");

            if (allCustomers == null || allCustomers.isEmpty()) {
                System.err.println("✗ No customers found in database");
                tblCustomer.setItems(FXCollections.observableArrayList());
                return;
            }

            ArrayList<CustomerTM> customerTMArrayList = new ArrayList<>();

            // Convert Customer objects to CustomerTM (Table Model) objects
            for (Customer customer : allCustomers) {
                System.out.println("Converting customer: " + customer);
                customerTMArrayList.add(new CustomerTM(
                        customer.getId(),
                        customer.getName(),
                        customer.getContact(),
                        customer.getGender(),
                        customer.getAddress(),
                        customer.getDob()
                ));
            }

            System.out.println("Converted to " + customerTMArrayList.size() + " CustomerTM objects");

            // Display data in table
            System.out.println("Setting table items...");
            tblCustomer.setItems(FXCollections.observableArrayList(customerTMArrayList));
            System.out.println("✓ Successfully loaded " + customerTMArrayList.size() + " customers from database");
            System.out.println("=== Finished loadTable() ===");

        } catch (SQLException e) {
            System.err.println("✗ Database error while loading customers: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to load customers from database", e);
        } catch (Exception e) {
            System.err.println("✗ Unexpected error while loading customers: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Unexpected error while loading customers", e);
        }
    }

    @FXML
    private JFXComboBox<String> cmbGender;

    @FXML
    private TableColumn<CustomerTM, String> columnAddress;

    @FXML
    private TableColumn<CustomerTM, String> columnContactNo;

    @FXML
    private TableColumn<CustomerTM, String> columnDOB;

    @FXML
    private TableColumn<CustomerTM, String> columnGender;

    @FXML
    private TableColumn<CustomerTM, String> columnId;

    @FXML
    private TableColumn<CustomerTM, String> columnName;

    @FXML
    private DatePicker dpDOB;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private JFXTextArea txtAddress;

    @FXML
    private JFXTextField txtContactNo;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    @SuppressWarnings("unused")
    void btnAddNewOnAction(ActionEvent event) {
        try {
            // Validate inputs
            if (txtName.getText().isEmpty() || txtContactNo.getText().isEmpty() ||
                cmbGender.getValue() == null || txtAddress.getText().isEmpty() ||
                dpDOB.getValue() == null) {
                System.err.println("✗ Please fill all fields");
                return;
            }

            // Create new customer
            Customer customer = new Customer(
                    0, // ID will be auto-generated
                    txtName.getText(),
                    txtContactNo.getText(),
                    cmbGender.getValue(),
                    txtAddress.getText(),
                    dpDOB.getValue()
            );

            // Add to database
            boolean isAdded = serviceType.addCustomer(customer);

            if (isAdded) {
                System.out.println("✓ Customer added successfully");
                clearFields();
                loadTable(); // Refresh table
            } else {
                System.err.println("✗ Failed to add customer");
            }
        } catch (SQLException e) {
            System.err.println("✗ Error adding customer: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    @SuppressWarnings("unused")
    void btnDeleteOnAction(ActionEvent event) {
        try {
            // Validate ID field is filled
            if (txtId.getText().isEmpty()) {
                System.err.println("✗ Please select a customer to delete");
                return;
            }

            int customerId = Integer.parseInt(txtId.getText());

            // Delete from database
            boolean isDeleted = serviceType.deleteCustomer(customerId);

            if (isDeleted) {
                System.out.println("✓ Customer deleted successfully");
                clearFields();
                loadTable(); // Refresh table
            } else {
                System.err.println("✗ Failed to delete customer");
            }
        } catch (Exception e) {
            System.err.println("✗ Error deleting customer: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    @SuppressWarnings("unused")
    void btnReloadOnAction(ActionEvent event) {
        try {
            clearFields();
            loadTable();
            System.out.println("✓ Table reloaded successfully");
        } catch (Exception e) {
            System.err.println("✗ Error reloading table: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    @SuppressWarnings("unused")
    void btnSearchOnAction(ActionEvent event) {
        try {
            // Validate ID field is filled
            if (txtId.getText().isEmpty()) {
                System.err.println("✗ Please enter a customer ID to search");
                return;
            }

            int customerId = Integer.parseInt(txtId.getText());

            // Search in database
            Customer customer = serviceType.searchCustomerById(customerId);

            if (customer != null) {
                System.out.println("✓ Customer found: " + customer);
                setTextToValues(customer);

                // Highlight the customer in table
                ArrayList<CustomerTM> customerList = new ArrayList<>();
                customerList.add(new CustomerTM(
                        customer.getId(),
                        customer.getName(),
                        customer.getContact(),
                        customer.getGender(),
                        customer.getAddress(),
                        customer.getDob()
                ));
                tblCustomer.setItems(FXCollections.observableArrayList(customerList));
            } else {
                System.err.println("✗ Customer not found");
                clearFields();
                tblCustomer.setItems(FXCollections.observableArrayList());
            }
        } catch (SQLException e) {
            System.err.println("✗ Error searching customer: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("✗ Invalid customer ID format");
        }
    }

    @FXML
    @SuppressWarnings("unused")
    void btnUpdateOnAction(ActionEvent event) {
        try {
            // Validate ID field is filled
            if (txtId.getText().isEmpty()) {
                System.err.println("✗ Please select a customer to update");
                return;
            }

            // Validate inputs
            if (txtName.getText().isEmpty() || txtContactNo.getText().isEmpty() ||
                cmbGender.getValue() == null || txtAddress.getText().isEmpty() ||
                dpDOB.getValue() == null) {
                System.err.println("✗ Please fill all fields");
                return;
            }

            // Create customer object with existing ID
            Customer customer = new Customer(
                    Integer.parseInt(txtId.getText()),
                    txtName.getText(),
                    txtContactNo.getText(),
                    cmbGender.getValue(),
                    txtAddress.getText(),
                    dpDOB.getValue()
            );

            // Update in database
            boolean isUpdated = serviceType.updateCustomer(customer);

            if (isUpdated) {
                System.out.println("✓ Customer updated successfully");
                clearFields();
                loadTable(); // Refresh table
            } else {
                System.err.println("✗ Failed to update customer");
            }
        } catch (Exception e) {
            System.err.println("✗ Error updating customer: " + e.getMessage());
            e.printStackTrace();
        }
    }

}


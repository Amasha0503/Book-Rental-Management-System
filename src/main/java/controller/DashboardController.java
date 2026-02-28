package controller;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXComboBox;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import service.custom.BookService;
import service.custom.CustomerService;
import service.custom.RentalService;

import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbReport;

    @FXML
    private Label lblActiveRentals;

    @FXML
    private Label lblBooks;

    @FXML
    private Label lblCustomers;

    @FXML
    private Label lblFines;

    @FXML
    private Label lblOverdues;

    @Inject
    private BookService bookService;

    @Inject
    private CustomerService customerService;

    @Inject
    private RentalService rentalService;

    @FXML
    void btnPrintReportOnAction(ActionEvent actionEvent) {
        try {

            String selectedReport = cmbReport.getValue();

            String reportPath;
            String reportFileName;

            switch (selectedReport) {
                case "Customer Report":
                    reportPath = "src/main/resources/report/customer-report.jrxml";
                    reportFileName = "customer-report.pdf";
                    break;

                case "Book Report":
                    reportPath = "src/main/resources/report/book-report.jrxml";
                    reportFileName = "book-report.pdf";
                    break;

                case "Rental Report":
                    reportPath = "src/main/resources/report/rental-report.jrxml";
                    reportFileName = "rental-report.pdf";
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + selectedReport);
            }

            JasperDesign design = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    null,
                    DbConnection.getInstance().getConnection()
            );

            JasperExportManager.exportReportToPdfFile(jasperPrint, reportFileName);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            System.err.println("✗ JasperReports error: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("✗ Database error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("✗ Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cmbReport.setItems(
                FXCollections.observableArrayList(
                        Arrays.asList("Customer Report",
                                "Book Report",
                                "Rental Report"
                        ))
        );

        loadTotalBookQuantity();
        loadTotalCustomers();
        loadTotalFines();
    }

    private void loadTotalBookQuantity() {
        try {
            int totalQuantity = bookService.getTotalQuantity();
            lblBooks.setText(String.valueOf(totalQuantity));
        } catch (SQLException e) {
            lblBooks.setText("0");
        }
    }
    private void loadTotalCustomers() {
        try {
            int totalCustomers = customerService.getTotalCustomers();
            lblCustomers.setText(String.valueOf(totalCustomers));
        } catch (SQLException e) {
            lblCustomers.setText("0");
        }
    }

    private void loadTotalFines() {
        try {
            double totalFines = rentalService.getTotalFines();
            lblFines.setText(String.format("Rs. %.2f", totalFines));
        } catch (SQLException e) {
            System.err.println("✗ Error loading total fines: " + e.getMessage());
            e.printStackTrace();
            lblFines.setText("Rs. 0.00");
        }
    }


}

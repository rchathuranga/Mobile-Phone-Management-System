package lk.ijse.projectmp.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import lk.ijse.projectmp.bo.BOFactory;
import lk.ijse.projectmp.bo.bo.custom.CustomBO;
import lk.ijse.projectmp.dao.DAOFactory;
import lk.ijse.projectmp.db.DBConnection;
import lk.ijse.projectmp.dto.CustomDTO;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ReportController implements Initializable {

    @FXML
    private JFXButton btnCustomer;

    @FXML
    private JFXButton btnSales;

    @FXML
    private JFXButton btnIncome;

    @FXML
    private JFXButton btnPhone;

    @FXML
    private JFXButton btnItemD;

    @FXML
    private TextArea txtCredit;

    @FXML
    private JFXButton btnMostSelling;

    private CustomBO customBO= (CustomBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOM);

    @FXML
    void repBtnAction(ActionEvent event) {
        JFXButton btn = (JFXButton) event.getSource();
        switch (btn.getId()) {
            case "btnCustomer":
                CustomerRep();
                break;
            case "btnSales":
                SalesInvoiceRep();
                break;
            case "btnIncome":
                IncomeRep();
                break;
            case "btnItemD":
                ItemDRep();
                break;
            case "btnMostSelling":
                MostSellingRep();
                break;
            case "btnPhone":
                PhonesRep();
                break;
        }
    }

    private void PhonesRep(){
        Connection connection;
        try {
            connection = DBConnection.getInstance().getConnection();
            InputStream invoice = this.getClass().getResourceAsStream("/lk/ijse/projectmp/reports/Phones.jasper");
            JasperPrint print = JasperFillManager.fillReport(invoice, null, connection);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void MostSellingRep(){
        Connection connection;
        try {
            connection = DBConnection.getInstance().getConnection();
            InputStream invoice = this.getClass().getResourceAsStream("/lk/ijse/projectmp/reports/MostSelling.jasper");
            JasperPrint print = JasperFillManager.fillReport(invoice, null, connection);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void SalesInvoiceRep(){
        try {
            InputStream invoice = this.getClass().getResourceAsStream("/lk/ijse/projectmp/reports/Invoice.jasper");
            HashMap<String, Object> map = new HashMap<>();
            map.put("OrderId", 000000);
            map.put("CustomerName", ".........");
            map.put("SubTotal", 0.0);
            map.put("Discount", 0.0);
            map.put("Total", 0.0);
            JasperPrint print = JasperFillManager.fillReport(invoice, map,new JREmptyDataSource());
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ItemDRep() {
        Connection connection ;
        try {
            connection = DBConnection.getInstance().getConnection();
            InputStream invoice = this.getClass().getResourceAsStream("/lk/ijse/projectmp/reports/Items.jasper");
            JasperPrint print = JasperFillManager.fillReport(invoice, null, connection);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void CustomerRep() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            InputStream invoice = this.getClass().getResourceAsStream("/lk/ijse/projectmp/reports/Customer.jasper");
            JasperPrint print = JasperFillManager.fillReport(invoice, null, connection);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void IncomeRep() {
        try {
            CustomDTO profitDetail = customBO.getProfitDetail();
            InputStream invoice = this.getClass().getResourceAsStream("/lk/ijse/projectmp/reports/Profit.jasper");
            HashMap<String, Object> map = new HashMap<>();
            map.put("Total", profitDetail.getTotIncome());
            map.put("Phones", profitDetail.getPhoneExpense());
            map.put("Items", profitDetail.getItemExpence());
            map.put("parts", profitDetail.getRepairPartExpence());
            map.put("TotExpences", profitDetail.getTotExpence());
            map.put("Profit", profitDetail.getProfit());
            map.put("SalesIncome", profitDetail.getOrderIncome());
            map.put("RepairIncome", profitDetail.getRepairIncome());

            JasperPrint print = JasperFillManager.fillReport(invoice, map, new JREmptyDataSource());
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtCredit.setText("Software Solution By IJSE \n" +
                "Developed By RAVINDU CHATHURANGA \n"+
                "Tested By NAVEEN MADUSHA \n"+
                "Implement In PANDORA PVT LMT \n");
    }
}

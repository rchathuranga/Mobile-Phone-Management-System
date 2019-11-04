package lk.ijse.projectmp.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.projectmp.bo.BOFactory;
import lk.ijse.projectmp.bo.bo.custom.*;
import lk.ijse.projectmp.dto.*;
import lk.ijse.projectmp.entity.RepairDetail;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Label lblTotCustomer;

    @FXML
    private Label lblSalesToday;

    @FXML
    private Label lblRepair;

    @FXML
    private Label lblPhoneStock;

    @FXML
    private Label lblItemStock;

    @FXML
    private TableView<CustomDTO> tblMostSelling;

    @FXML
    private TableView<CustomDTO> tblLeastSelling;

    @FXML
    private TableView<ItemDTO> tblLowStock;

    @FXML
    private Label lblReturns;

    @FXML
    private TableView<OrdersDTO> tblOrdersToday;

    @FXML
    private TableView<RepairDetailDTO> tblRepairDetails;

    private ItemBO boItem= (ItemBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);
    private CustomerBO boCust= (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
    private OrdersBO boOrders= (OrdersBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDERS);
    private ReturnsBO boReturns= (ReturnsBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.RETURNS);
    private CustomBO customBO= (CustomBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOM);
    private PhoneBO phoneBO= (PhoneBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PHONE);
    private RepairBO repairBO= (RepairBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.REPAIR);
    private RepairDetailBO repairDetailBO= (RepairDetailBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.REPAIRDETAIL);
    private OrdersBO ordersBO= (OrdersBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDERS);

    @Override
    public void initialize(URL location, ResourceBundle resources){
        initiateTables();
        loadTable();
        setLabels();
    }

    private  void initiateTables(){
        tblLowStock.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblLowStock.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblLowStock.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));

        tblMostSelling.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblMostSelling.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblMostSelling.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));

        tblRepairDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("repairId"));
        tblRepairDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblRepairDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("customerContact"));
        tblRepairDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("phone"));
        tblRepairDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        tblRepairDetails.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("amount"));
        tblRepairDetails.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("completed"));

        tblOrdersToday.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("time"));
        tblOrdersToday.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblOrdersToday.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblOrdersToday.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("customerContact"));
        tblOrdersToday.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblOrdersToday.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("discount"));
        tblOrdersToday.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("amount"));

        tblLeastSelling.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblLeastSelling.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblLeastSelling.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
    }


    private void loadTable(){
        try{
            tblLowStock.setItems(FXCollections.observableArrayList(boItem.getLeastItem()));
            tblMostSelling.setItems(FXCollections.observableArrayList(customBO.getMostSelling()));
            tblRepairDetails.setItems(FXCollections.observableArrayList(repairDetailBO.getUnfinishRepair()));
            tblOrdersToday.setItems(FXCollections.observableArrayList(ordersBO.getAllOrdersToday()));
            tblLeastSelling.setItems(FXCollections.observableArrayList(customBO.getLeastSelling()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setLabels(){
        try {
            lblRepair.setText(String.valueOf(repairBO.repairToDo()));
            lblItemStock.setText(String.valueOf(boItem.getTotItemQty()));
            lblPhoneStock.setText(String.valueOf(phoneBO.getTotPhoneQty()));
            setLblTotCustomer();
            selLblSaleToday();
            setLblReturns();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLblTotCustomer() throws Exception {
        int count=0;
            ArrayList<CustomerDTO> arrayList=boCust.getAllCustomer();
            for (CustomerDTO c: arrayList) {
                count++;
            }
        lblTotCustomer.setText(String.valueOf(count));
    }

    private  void selLblSaleToday() throws Exception {
        int count=0;
            ArrayList<OrdersDTO> arrayList=boOrders.getAllOrdersToday();
            for (OrdersDTO o :arrayList) {
                count++;
            }
        lblSalesToday.setText(String.valueOf(count));
    }

    private  void setLblReturns() throws Exception {
        int count=0;
            ArrayList<ReturnsDTO> allReturns = boReturns.getAllReturns();
            for (ReturnsDTO r :
                    allReturns) {
                count++;
            }
        lblReturns.setText(String.valueOf(count));
    }
}

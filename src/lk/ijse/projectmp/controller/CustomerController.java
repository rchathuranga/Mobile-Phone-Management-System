package lk.ijse.projectmp.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.projectmp.bo.BOFactory;
import lk.ijse.projectmp.bo.bo.custom.CustomBO;
import lk.ijse.projectmp.bo.bo.custom.CustomerBO;
import lk.ijse.projectmp.dto.CustomDTO;
import lk.ijse.projectmp.dto.CustomerDTO;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class CustomerController implements Initializable {
    @FXML
    private TextField txtCid;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtNic;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private TableView<CustomerDTO> tblCustomer;

    @FXML
    private TableView<CustomDTO> tblODetails;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private Label lblTotCustomer;

    @FXML
    private Label lblOrderCustomer;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private Label lblAddCustomer;

    @FXML
    private TableView<CustomDTO> tblRDetails;

    private CustomerBO boCustomer= (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
    private CustomBO boCustom= (CustomBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOM);
    private CustomerDTO customerDTO;

    @FXML
    void btnAddAction(ActionEvent event) {
        String name=txtName.getText();
        int contact= Integer.parseInt(txtContact.getText());
        String nic=txtNic.getText();

        customerDTO=new CustomerDTO(name,contact,nic);
        try {
            showAlert(boCustomer.addCustomer(customerDTO),"Add");
        } catch (Exception e) {
            e.printStackTrace();
        }

        loadTblCustomer();
        customerCount();
        getTodayCustomers();
        loadSearchBar();
    }

    @FXML
    void btnDelete(ActionEvent event) {
        try {
            showAlert(boCustomer.deleteCustomer(Integer.parseInt(txtCid.getText())),"Delete");
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadTblCustomer();
        customerCount();
        getTodayCustomers();
        loadSearchBar();
    }

    @FXML
    void btnUpdateAction(ActionEvent event) {
        customerDTO=new CustomerDTO(Integer.parseInt(txtCid.getText()),txtName.getText(),Integer.parseInt(txtContact.getText()),txtNic.getText());
        try {
            showAlert(boCustomer.updateCustomer(customerDTO),"Update");
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadTblCustomer();
        loadSearchBar();
    }

    public void showAlert(boolean b,String name){
        if(b){
            new Alert(AlertType.CONFIRMATION,name+" Successfully").show();
        }else{
            new Alert(AlertType.CONFIRMATION,name+" Failed").show();
        }
    }

    public void setCustomerInteract(){
        int count=0;
        try {
            ArrayList<CustomDTO> customerInteract = boCustom.getCustomerInteract();

            for (CustomDTO c :customerInteract) {
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        lblOrderCustomer.setText(String.valueOf(count));
    }

    public void getTodayCustomers(){
        int count=0;
        try {
            ArrayList<CustomerDTO> arrayList = boCustomer.getAllCustomer(LocalDate.now().toString());
            for (CustomerDTO c :arrayList) {
                count++;
            }
            lblAddCustomer.setText(String.valueOf(count));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void customerCount(){
        int count=0;
        try {
            ArrayList<CustomerDTO> arrayList=boCustomer.getAllCustomer();
            for (CustomerDTO c:arrayList) {
                count++;
            }

            lblTotCustomer.setText(String.valueOf(count));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerCount();
        getTodayCustomers();
        setCustomerInteract();
        setTables();
        loadTblCustomer();
        loadSearchBar();
    }

    void loadSearchBar(){
        try {
            ArrayList<CustomerDTO> allCustomer = boCustomer.getAllCustomer();
            TextFields.bindAutoCompletion(txtSearch,FXCollections.observableArrayList(allCustomer));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setTables(){
        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblCustomer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("nic"));

        tblODetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblODetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tblODetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("orderId"));
        tblODetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("orderAmount"));

        tblRDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblRDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tblRDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("orderId"));
        tblRDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("orderAmount"));
    }

    public void loadTblCustomer(){
        try {
            ArrayList<CustomerDTO> allCustomer = boCustomer.getAllCustomer();
            tblCustomer.setItems(FXCollections.observableArrayList(allCustomer));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTblDetail(int id) {

        try {
            tblRDetails.setItems(FXCollections.observableArrayList(boCustom.getRepairsofCustomer(id)));
            tblODetails.setItems(FXCollections.observableArrayList(boCustom.getOrdersofCustomer(id)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void btnResetAction(ActionEvent event) {
        txtCid.setText("");
        txtName.setText("");
        txtContact.setText("");
        txtNic.setText("");
    }

    @FXML
    void tblDetailsAction(MouseEvent event) {
//        not used
    }

    @FXML
    void tbtCustomerAction(MouseEvent event) {
        txtCid.setText(String.valueOf(tblCustomer.getSelectionModel().selectedItemProperty().get().getId()));
        txtName.setText(tblCustomer.getSelectionModel().selectedItemProperty().get().getName());
        txtContact.setText(String.valueOf(tblCustomer.getSelectionModel().selectedItemProperty().get().getContact()));
        txtNic.setText(tblCustomer.getSelectionModel().selectedItemProperty().get().getNic());
        loadTblDetail(tblCustomer.getSelectionModel().selectedItemProperty().get().getId());
    }
    @FXML
    void txtContactAction(ActionEvent event) {
        String contact = txtContact.getText();
        if (Pattern.compile("^[0-9]{10}$").matcher(contact).matches()) {
            setTxtFieldColor(txtContact,true);
            txtNic.requestFocus();
        }else {
            setTxtFieldColor(txtContact,false);
            txtContact.requestFocus();
        }
    }

    @FXML
    void txtNameAction(ActionEvent event) {
        String name = txtName.getText();
        if (Pattern.compile("[A-z]{3,15}").matcher(name).matches()) {
            setTxtFieldColor(txtName,true);
            txtContact.requestFocus();
        }else {
            setTxtFieldColor(txtName,false);
            txtName.requestFocus();
        }
    }

    void setTxtFieldColor(JFXTextField tf,boolean b){
        if(b){
            tf.setUnFocusColor(Paint.valueOf("#1900ff"));
            tf.setFocusColor(Paint.valueOf("black"));
        }else {
            tf.setUnFocusColor(Paint.valueOf("red"));
            tf.setFocusColor(Paint.valueOf("red"));
        }
    }

    @FXML
    void txtNicAction(ActionEvent event) {
        String nic = txtNic.getText();
        if (Pattern.compile("[0-9]{12}|[0-9]{9}(v)").matcher(nic).matches()) {
            setTxtFieldColor(txtNic,true);
            btnAdd.fire();
        }else {
            setTxtFieldColor(txtNic,false);
            txtNic.requestFocus();
        }
    }

    @FXML
    void txtSearchAction(ActionEvent event) {
        String name=txtSearch.getText();
        try {
            CustomerDTO customerDTO = boCustomer.searchCustomer(name);
            txtCid.setText(String.valueOf(customerDTO.getId()));
            txtName.setText(customerDTO.getName());
            txtContact.setText(String.valueOf(customerDTO.getContact()));
            txtNic.setText(String.valueOf(customerDTO.getNic()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



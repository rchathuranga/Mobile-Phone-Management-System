package lk.ijse.projectmp.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.fxml.Initializable;
import javafx.scene.paint.Paint;
import lk.ijse.projectmp.bo.BOFactory;
import lk.ijse.projectmp.bo.bo.custom.*;
import lk.ijse.projectmp.dao.dao.custom.ItemDAO;
import lk.ijse.projectmp.dto.*;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddStockController implements Initializable {

    @FXML
    private TableView<CustomDTO> tblStockList;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXComboBox<BrandDTO> cbxBrand;

    @FXML
    private JFXComboBox<CatagoryDTO> cbxCatagory;

    @FXML
    private JFXTextField txtRam;

    @FXML
    private JFXTextField txtStorage;

    @FXML
    private JFXTextField txtNetwork;

    @FXML
    private JFXTextField txtWarranty;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtBuyingPrice;

    @FXML
    private JFXTextField txtSellingPrice;

    @FXML
    private JFXRadioButton rdbPhone;

    @FXML
    private ToggleGroup TGstockType;

    @FXML
    private JFXRadioButton rdbItem;

    @FXML
    private TextField txtId;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnReset;

    @FXML
    private JFXTextField txtNewBrand;

    @FXML
    private JFXButton btnNewBrand;

    @FXML
    private JFXTextField txtNewCatagory;

    @FXML
    private JFXButton btnNewCatagory;


    @FXML
    private Label lblTotItem;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblTotQty;

    @FXML
    private JFXTextField txtSearch;

    CustomBO customBO= (CustomBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOM);
    PhoneBO phoneBO= (PhoneBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PHONE);
    BrandBO brandBO= (BrandBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BRAND);
    CatagoryBO cataBO= (CatagoryBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CATAGORY);
    ItemBO itemBO= (ItemBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);
    @FXML
    void rdbAction(ActionEvent event) {
        JFXRadioButton rdb= (JFXRadioButton) event.getSource();
        if(rdb.getId()==rdbItem.getId()){
            txtName.setPromptText("Item Name");
            txtRam.setDisable(true);
            txtStorage.setDisable(true);
            txtNetwork.setDisable(true);
            cbxCatagory.setDisable(false);
            loadTable();
        }else {
            txtName.setPromptText("Phone Name");
            txtRam.setDisable(false);
            txtStorage.setDisable(false);
            txtNetwork.setDisable(false);
            cbxCatagory.setDisable(true);
            loadTable();
        }
    }

    @FXML
    void subBtnAction(ActionEvent event) {
        JFXButton btn= (JFXButton) event.getSource();
        switch (btn.getId()){
            case "btnAdd": addStock();loadTable();
                break;
            case "btnUpdate":updateStock();loadTable();
                break;
            case "btnDelete":deleteStock();loadTable();
                break;
            case "btnReset": clearFields();
                break;
            case "btnNewBrand":addNewBrand();setCbx();
                break;
            case "btnNewCatagory":addNewCata();setCbx();
                break;
        }
    }

    void loadTable(){
        try {
            ArrayList<CustomDTO> arrayList=customBO.getAllItemandPhone();
            tblStockList.setItems(FXCollections.observableArrayList(arrayList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void addStock(){
        int brandId=cbxBrand.getSelectionModel().getSelectedItem().getId();
        String name=txtName.getText();

        int qty= Integer.parseInt(txtQty.getText());
        int warranty= Integer.parseInt(txtWarranty.getText());
        double buyingPrice= Double.parseDouble(txtBuyingPrice.getText());
        double sellingPrice= Double.parseDouble(txtSellingPrice.getText());
        try {
            JFXRadioButton rdb = (JFXRadioButton) TGstockType.getSelectedToggle();
            if (rdb.getId() == rdbItem.getId()) {
                int cataid = cbxCatagory.getSelectionModel().getSelectedItem().getId();
                if(itemBO.addItem(new ItemDTO(brandId,cataid,name,warranty,qty,buyingPrice,sellingPrice))){
                    new Alert(Alert.AlertType.CONFIRMATION,"Item Added Successfully").show();
                }else {
                    new Alert(Alert.AlertType.CONFIRMATION,"Item Failed to Add").show();
                }
            } else {
                int ram= Integer.parseInt(txtRam.getText());
                int storage= Integer.parseInt(txtStorage.getText());
                String network=txtNetwork.getText();
                if(phoneBO.addPhone(new PhoneDTO(brandId, name, ram, storage, network,qty,warranty, buyingPrice, sellingPrice))){
                    new Alert(Alert.AlertType.CONFIRMATION,"Phone Added Successfully").show();
                }else {
                    new Alert(Alert.AlertType.CONFIRMATION,"Phone Failed to Add").show();
                }
            }
            setLabels();
        }catch (Exception e){
            e.getMessage();
        }
    }


    void updateStock(){
        int id= Integer.parseInt(txtId.getText());
        int brandId=cbxBrand.getSelectionModel().getSelectedItem().getId();
        String name=txtName.getText();

        int qty= Integer.parseInt(txtQty.getText());
        int warranty= Integer.parseInt(txtWarranty.getText());
        double buyingPrice= Double.parseDouble(txtBuyingPrice.getText());
        double sellingPrice= Double.parseDouble(txtSellingPrice.getText());
        try {
            JFXRadioButton rdb = (JFXRadioButton) TGstockType.getSelectedToggle();
            if (rdb.getId() == rdbItem.getId()) {
                int cataid=cbxCatagory.getSelectionModel().getSelectedItem().getId();
                if(itemBO.updateItem(new ItemDTO(id,brandId,cataid,name,warranty,qty,buyingPrice,sellingPrice))){
                    new Alert(Alert.AlertType.CONFIRMATION,"Item Updated Successfully").show();
                }else {
                    new Alert(Alert.AlertType.CONFIRMATION,"Item Fail to Update").show();
                }
            } else {
                int ram= Integer.parseInt(txtRam.getText());
                int storage= Integer.parseInt(txtStorage.getText());
                String network=txtNetwork.getText();
                if(phoneBO.updatePhone(new PhoneDTO(id,brandId,name,ram,storage,network,qty,warranty,sellingPrice,buyingPrice))){
                    new Alert(Alert.AlertType.CONFIRMATION,"Phone Updated Successfully").show();
                }else {
                    new Alert(Alert.AlertType.CONFIRMATION,"Phone Fail to Update").show();
                }
            }
        }catch (Exception e){
            e.getMessage();
        }
    }

    void deleteStock(){
        try {
            JFXRadioButton rdb = (JFXRadioButton) TGstockType.getSelectedToggle();
            if (Pattern.compile("^[0-9]{0,8}$").matcher(txtId.getText()).matches()) {
                if (rdb.getId() == rdbItem.getId()) {
                    if(itemBO.deleteItem(Integer.parseInt(txtId.getText()))){
                        new Alert(Alert.AlertType.CONFIRMATION,"Item Deleted Successfully").show();
                    }else {
                        new Alert(Alert.AlertType.CONFIRMATION,"Item Fail to Delete").show();
                    }
                }else {
                   if(phoneBO.deletePhone(Integer.parseInt(txtId.getText()))){
                       new Alert(Alert.AlertType.CONFIRMATION,"Phone Deleted Successfully").show();
                   }else {
                       new Alert(Alert.AlertType.CONFIRMATION,"Item Fail to Delete").show();
                   }
                }
            } else {
                new Alert(Alert.AlertType.CONFIRMATION,"Invailed Input near 'id' ").show();
                txtId.setText("");
                txtId.requestFocus();
            }
            setLabels();
        }catch (Exception e){
            e.getMessage();
        }
    }

    void addNewBrand(){
        BrandDTO brandDTO=new BrandDTO(txtNewBrand.getText());
        try {
            if(brandBO.addBrand(brandDTO)){
                new Alert(Alert.AlertType.INFORMATION,"Brand Added Successfully").show();
            }else {
                new Alert(Alert.AlertType.INFORMATION,"Brand Fail to Add").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtNewBrand.setText("");
    }

    void addNewCata(){
        CatagoryDTO catagoryDTO=new CatagoryDTO(txtNewCatagory.getText());
        try {
            if(cataBO.addCatagory(catagoryDTO)){
                new Alert(Alert.AlertType.INFORMATION,"Catagory Added Successfully").show();
            }else {
                new Alert(Alert.AlertType.INFORMATION,"Catagory Fail to Add ").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtNewCatagory.setText("");
    }

    void clearFields(){
        txtId.setText("");
        txtName.setText("");
        txtRam.setText("");
        txtStorage.setText("");
        txtNetwork.setText("");
        txtWarranty.setText("");
        txtQty.setText("");
        txtBuyingPrice.setText("");
        txtSellingPrice.setText("");
        cbxBrand.getSelectionModel().clearSelection();
        cbxCatagory.getSelectionModel().clearSelection();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCbx();
        loadTable();
        loadSearchBar();

        tblStockList.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblStockList.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStockList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("brandName"));
        tblStockList.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("catagoryName"));
        tblStockList.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("ram"));
        tblStockList.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("storage"));
        tblStockList.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("network"));
        tblStockList.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("warranty"));
        tblStockList.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblStockList.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("buyingPrice"));
        tblStockList.getColumns().get(10).setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));

        setLabels();
    }

    void loadSearchBar(){
        try {
            TextFields.bindAutoCompletion(txtSearch,customBO.getAllItemandPhone());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setCbx(){
        try {
            cbxCatagory.setItems(FXCollections.observableArrayList(cataBO.getAllCatagory()));
            cbxBrand.setItems(FXCollections.observableArrayList(brandBO.getAllBrand()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void txtSearchAction(ActionEvent event) throws Exception {
        String name=txtSearch.getText();
        String sId="";
        for (int i = 0; i <6; i++) {
            sId+=name.charAt(i);
        }
        int id= Integer.parseInt(sId);

        if(TGstockType.getSelectedToggle()==rdbItem){
            if(id>=800000) {
                ItemDTO itemDTO = itemBO.searchItem(id);
                txtId.setText(String.valueOf(itemDTO.getId()));
                txtName.setText(itemDTO.getName());
                cbxBrand.getSelectionModel().select(getSelectedbrand(itemDTO.getBrandid()));
                cbxCatagory.getSelectionModel().select(getSelectedCatagory(itemDTO.getCataid()));
                txtWarranty.setText(String.valueOf(itemDTO.getWarranty()));
                txtQty.setText(String.valueOf(itemDTO.getQty()));
                txtBuyingPrice.setText(String.valueOf(itemDTO.getSellingPrice()));
                txtSellingPrice.setText(String.valueOf(itemDTO.getSellingPrice()));
            }else {
                new Alert(Alert.AlertType.INFORMATION,"Wrong Item. Change the RadioButton and Try Again.").show();
            }
        }else {
            if(id>=800000) {
                new Alert(Alert.AlertType.INFORMATION,"Wrong Item. Change the RadioButton and Try Again.").show();
            }else {
                PhoneDTO phoneDTO = phoneBO.searchPhone(id);
                txtId.setText(String.valueOf(phoneDTO.getId()));
                txtName.setText(phoneDTO.getName());
                cbxBrand.getSelectionModel().select(getSelectedbrand(phoneDTO.getBrandid()));
                txtRam.setText(String.valueOf(phoneDTO.getRam()));
                txtStorage.setText(String.valueOf(phoneDTO.getStorage()));
                txtNetwork.setText(phoneDTO.getNetwork());
                txtWarranty.setText(String.valueOf(phoneDTO.getWarranty()));
                txtQty.setText(String.valueOf(phoneDTO.getQty()));
                txtBuyingPrice.setText(String.valueOf(phoneDTO.getSellingPrice()));
                txtSellingPrice.setText(String.valueOf(phoneDTO.getSellingPrice()));
            }
        }
    }

    @FXML
    void tblStockMouseAction(MouseEvent event) {
        CustomDTO customDTO = tblStockList.getSelectionModel().selectedItemProperty().get();
        JFXRadioButton rdb = (JFXRadioButton) TGstockType.getSelectedToggle();
        if (rdbItem.getId()==rdb.getId()) {
            if(customDTO.getId()>=800000) {
                fillTextField();
                cbxBrand.getSelectionModel().select(getSelectedbrand(tblStockList.getSelectionModel().getSelectedItem().getBrandid()));
                cbxCatagory.getSelectionModel().select(getSelectedCatagory(tblStockList.getSelectionModel().getSelectedItem().getCataid()));
            }else {
                new Alert(Alert.AlertType.INFORMATION,"Wrong Item. Change the RadioButton and Try Again.").show();
//                rdbPhone.setSelected(true);
            }
        }else {
            if(customDTO.getId()<800000) {
                txtRam.setText(String.valueOf(customDTO.getRam()));
                txtStorage.setText(String.valueOf(customDTO.getStorage()));
                txtNetwork.setText(customDTO.getNetwork());
                fillTextField();
                cbxBrand.getSelectionModel().select(getSelectedbrand(tblStockList.getSelectionModel().getSelectedItem().getBrandid()));
            }else {
                new Alert(Alert.AlertType.INFORMATION,"Wrong Item. Change the RadioButton and Try Again.").show();
//                rdbItem.setSelected(true);
            }
        }
    }

    int getSelectedCatagory(int id){
        int count=0;
        int catagoryid=id;
        try {
            for (CatagoryDTO c:cataBO.getAllCatagory()) {
                if(c.getId()==catagoryid){
                    return count;
                }
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    int getSelectedbrand(int id){
        int count=0;
        int brandid = id;
        try {
            for (BrandDTO b:brandBO.getAllBrand()) {
                if(b.getId()==brandid){
                    return count;
                }
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    void setLabels(){
        int totQty=0;
        double total=0.0;
        int count=0;
        ObservableList<CustomDTO> items = tblStockList.getItems();
        for (CustomDTO c : items) {
            total+=c.getSellingPrice();
            totQty+=c.getQty();
            count++;
        }
        lblTotItem.setText(String.valueOf(count));
        lblTotQty.setText(String.valueOf(totQty));
        lblTotal.setText(String.valueOf(total));
    }


    public void fillTextField(){
        CustomDTO customDTO = tblStockList.getSelectionModel().selectedItemProperty().get();
        txtId.setText(String.valueOf(customDTO.getId()));
        txtName.setText(customDTO.getName());
        txtQty.setText(String.valueOf(customDTO.getQty()));
        txtWarranty.setText(String.valueOf(customDTO.getWarranty()));
        txtBuyingPrice.setText(String.valueOf(customDTO.getBuyingPrice()));
        txtSellingPrice.setText(String.valueOf(customDTO.getSellingPrice()));
    }

}

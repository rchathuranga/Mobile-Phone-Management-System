package lk.ijse.projectmp.controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Paint;
import lk.ijse.projectmp.bo.BOFactory;
import lk.ijse.projectmp.bo.bo.custom.AccountBO;
import lk.ijse.projectmp.bo.bo.custom.BrandBO;
import lk.ijse.projectmp.bo.bo.custom.CatagoryBO;
import lk.ijse.projectmp.bo.bo.custom.SDiscountBO;
import lk.ijse.projectmp.dto.*;
import lk.ijse.projectmp.entity.SDiscount;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class SettingsController implements Initializable {

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXPasswordField pfCurPassword;

    @FXML
    private JFXPasswordField pfNewPassword;

    @FXML
    private JFXButton btnNew;

    @FXML
    private JFXButton btnChange;

    @FXML
    private JFXDatePicker dtpStartDate;

    @FXML
    private JFXDatePicker dtpEndDate;

    @FXML
    private JFXRadioButton rdbPercentage;

    @FXML
    private ToggleGroup discountType;


    @FXML
    private JFXRadioButton rdbBrand;

    @FXML
    private ToggleGroup TGBrandorCata;

    @FXML
    private JFXRadioButton rdbCatagory;

    @FXML
    private JFXTextField txtBrandorCata;

    @FXML
    private TextField txtId;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private TableView<SuperBrandCata> tblBrandorCata;

    @FXML
    private JFXRadioButton rdbAmount;

    @FXML
    private JFXTextField txtDiscount;

    @FXML
    private JFXTextField txtNoofDays;

    @FXML
    private JFXComboBox<CatagoryDTO> cmbCatagory;

    @FXML
    private JFXComboBox<BrandDTO> cmbBrand;

    @FXML
    private JFXButton btnAddSeasonal;

    @FXML
    private JFXButton btnReset;

    @FXML
    private TableView<SDiscountDTO> tblSdiscount;

    private AccountDTO user;

    SDiscountBO boSDisc = (SDiscountBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SDISCOUNT);
    AccountBO boAcc = (AccountBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ACCOUNT);
    BrandBO boBrand = (BrandBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BRAND);
    CatagoryBO boCata = (CatagoryBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CATAGORY);


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblBrandorCata.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblBrandorCata.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));

        tblSdiscount.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblSdiscount.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("startDate"));
        tblSdiscount.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("endDate"));
        tblSdiscount.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("brandName"));
        tblSdiscount.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("catagoryName"));
        tblSdiscount.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("percentage"));

        loadTblDisc();
        loadBrandtoTbl();
        loadcbxBrand();
    }

    int getSelectedCatagory() {
        int count = 0;
        int catagoryid = tblSdiscount.getSelectionModel().getSelectedItem().getCata();
        try {
            for (CatagoryDTO c : boCata.getAllCatagory()) {
                if (c.getId() == catagoryid) {
                    return count;
                }
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    int getSelectedbrand() {
        int count = 0;
        int brandid = tblSdiscount.getSelectionModel().getSelectedItem().getBrand();
        try {
            for (BrandDTO b : boBrand.getAllBrand()) {
                if (b.getId() == brandid) {
                    return count;
                }
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    void loadTblDisc() {
        try {
            tblSdiscount.setItems(FXCollections.observableArrayList(boSDisc.getAllSDiscount()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnNewAccountAction(ActionEvent event) {
        String name = txtName.getText();
        if (validAccName(name)) {
            String username = txtUsername.getText();
            if (validAccUsername(username)) {
                String password = pfCurPassword.getText();
                if (validAccPassword(password)) {
                    AccountDTO account = new AccountDTO(name, username, pfCurPassword.getText());
                    try {
                        if (boAcc.addAccount(account)) {
                            new Alert(Alert.AlertType.CONFIRMATION, "New User Added", ButtonType.OK).show();
                        } else {
                            new Alert(Alert.AlertType.CONFIRMATION, "New User cannot be added", ButtonType.OK).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    clearAccountField();
                } else {
                    invalidError("Password");
                    pfCurPassword.setUnFocusColor(Paint.valueOf("RED"));
                    pfCurPassword.setFocusColor(Paint.valueOf("RED"));
                }
            } else {
                invalidError("Username");
                txtUsername.setFocusColor(Paint.valueOf("RED"));
                txtUsername.setUnFocusColor(Paint.valueOf("RED"));
            }
        } else {
            invalidError("Name");
            txtName.setFocusColor(Paint.valueOf("RED"));
            txtName.setUnFocusColor(Paint.valueOf("RED"));
        }
    }


    void clearAccountField() {
        txtName.setText("");
        txtUsername.setText("");
        pfCurPassword.setText("");
        pfNewPassword.setText("");
        dtpEndDate.setValue(null);
        dtpStartDate.setValue(null);
    }

    @FXML
    void btnChangePasswordAction(ActionEvent event) {
        String name = txtName.getText();
        if (validAccName(name)) {
            String username = txtUsername.getText();
            if (validAccUsername(username)) {
                String password = pfCurPassword.getText();
                if (validAccPassword(password)) {
                    AccountDTO accountDTO = new AccountDTO(txtName.getText(), txtUsername.getText(), pfNewPassword.getText());
                    try {
                        if (boAcc.updateAccount(accountDTO)){
                            new Alert(Alert.AlertType.CONFIRMATION, "Changed Done", ButtonType.OK).show();
                        } else {
                            new Alert(Alert.AlertType.CONFIRMATION, "Changed Failed", ButtonType.OK).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    clearAccountField();
                } else {
                    invalidError("Password");
                    pfCurPassword.setUnFocusColor(Paint.valueOf("RED"));
                    pfCurPassword.setFocusColor(Paint.valueOf("RED"));
                }
            } else {
                invalidError("Username");
                txtUsername.setFocusColor(Paint.valueOf("RED"));
                txtUsername.setUnFocusColor(Paint.valueOf("RED"));
            }
        } else {
            invalidError("Name");
            txtName.setFocusColor(Paint.valueOf("RED"));
            txtName.setUnFocusColor(Paint.valueOf("RED"));
        }
    }

    @FXML
    void rdbBrandCataAction(ActionEvent event) {
        JFXRadioButton rdb = (JFXRadioButton) event.getSource();
        if (rdb.getId() == rdbBrand.getId()) {
            txtBrandorCata.setPromptText(rdbBrand.getText());
            loadBrand();
            txtId.setText("");
            txtName.setText("");
        } else {
            txtBrandorCata.setPromptText(rdbCatagory.getText());
            loadCata();
            txtId.setText("");
            txtName.setText("");
        }
    }

    @FXML
    void btnUpdateAction(ActionEvent event) {
        try {
            if (BrandOrCata()) {
                if (boBrand.updateBrand(new BrandDTO(Integer.parseInt(txtId.getText()), txtBrandorCata.getText()))) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Brand Updated Successfully").show();
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "Brand Update Failed").show();
                }
                loadBrandtoTbl();
            } else {
                if (boCata.updateCatagory(new CatagoryDTO(Integer.parseInt(txtId.getText()), txtBrandorCata.getText()))) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Catagory Updated Successfully").show();
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "Catagory Update Failed").show();
                }
                loadBrandtoTbl();
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }


    boolean BrandOrCata() {
        JFXRadioButton rdb = (JFXRadioButton) TGBrandorCata.getSelectedToggle();
        if (rdb.getId() == rdbBrand.getId()) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    void btnDeleteAction(ActionEvent event) {
        try {
            if (BrandOrCata()) {
                if (boBrand.deleteBrand(Integer.parseInt(txtId.getText()))) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Brand Deleted Successfully").show();
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "Brand Delete Failed").show();
                }
                loadBrandtoTbl();
            } else {
                if (boCata.deleteCatagory(Integer.parseInt(txtId.getText()))) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Catagory Deleted Successfully").show();
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "Catagory Delete Failed").show();
                }
                loadBrandtoTbl();
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }


    @FXML
    void tblBrandorCataMouseAction(MouseEvent event) {
        SuperBrandCata selectedItem = tblBrandorCata.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            txtId.setText(String.valueOf(selectedItem.getId()));
            txtBrandorCata.setText(selectedItem.getName());
        }
    }

    @FXML
    void rdbDicountAction(ActionEvent event) {
        JFXRadioButton rdb = (JFXRadioButton) event.getSource();
        if (rdb.getId() == rdbPercentage.getId()) {
            txtDiscount.setPromptText("Percentage");
        } else {
            txtDiscount.setPromptText("Amount");
        }
    }

    @FXML
    void btnAddAction(ActionEvent event) {
        if (Pattern.compile("[0-9]{0,}[.0-9]{0,2}").matcher(txtDiscount.getText()).matches()) {
            if (!cmbBrand.getSelectionModel().isEmpty() && !cmbCatagory.getSelectionModel().isEmpty()) {
                double discount = 0.0;
                if (rdbAmount == discountType.getSelectedToggle()) {
                    if (Pattern.compile("^[0-9]{0,}[.0-9]{0,3}$").matcher(txtDiscount.getText()).matches()) {
                        txtDiscount.setUnFocusColor(Paint.valueOf("#1900ff"));
                        txtDiscount.setFocusColor(Paint.valueOf("black"));
                        discount = Double.parseDouble(txtDiscount.getText());
                    } else {
                        new Alert(Alert.AlertType.CONFIRMATION, "Invalid Amount").show();
                    }
                } else {
                    if (Pattern.compile("^[0-9]{2}[.0-9]{0,2}$").matcher(txtDiscount.getText()).matches()) {
                        txtDiscount.setUnFocusColor(Paint.valueOf("#1900ff"));
                        txtDiscount.setFocusColor(Paint.valueOf("black"));
                        discount = Double.parseDouble(txtDiscount.getText()) / 100;
                    } else {
                        new Alert(Alert.AlertType.CONFIRMATION, "Invalid Percentage").show();
                    }
                }
                int brandid = cmbBrand.getSelectionModel().getSelectedItem().getId();
                int catagoryid = cmbCatagory.getSelectionModel().getSelectedItem().getId();

                String startDate = String.valueOf(dtpStartDate.getValue());
                String endDate = String.valueOf(dtpEndDate.getValue());

                SDiscountDTO discountDTO = new SDiscountDTO(startDate, endDate, catagoryid, brandid, discount);
                try {
                    if (boSDisc.addSDisount(discountDTO)) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Discount Added").show();
                    } else {
                        new Alert(Alert.AlertType.CONFIRMATION, "Discount Fail to Add").show();
                    }
                    loadTblDisc();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Please Select Brand And Catagory").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Invalid Discount Input. Please Enter Valid Input.").show();
        }

    }

    @FXML
    void btnResetAction(ActionEvent event) {
        dtpStartDate.setValue(null);
        dtpEndDate.setValue(null);
        txtDiscount.setText("");
        txtNoofDays.setText("");
        rdbAmount.setSelected(true);
        cmbCatagory.getSelectionModel().clearSelection();
        cmbBrand.getSelectionModel().clearSelection();
    }

    void invalidError(String s) {
        new Alert(Alert.AlertType.CONFIRMATION, "Invalid " + s + " Input.").show();
    }

    void loadcbxBrand() {
        try {
            cmbBrand.setItems(FXCollections.observableArrayList(boBrand.getAllBrand()));
            cmbCatagory.setItems(FXCollections.observableArrayList(boCata.getAllCatagory()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void loadBrandtoTbl() {
        if (BrandOrCata()) {
            loadBrand();
        } else {
            loadCata();
        }
    }

    void loadBrand() {
        ArrayList<BrandDTO> allBrand = null;
        try {
            allBrand = boBrand.getAllBrand();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tblBrandorCata.setItems(FXCollections.observableArrayList(allBrand));
    }

    void loadCata() {
        ArrayList<CatagoryDTO> allCata = null;
        try {
            allCata = boCata.getAllCatagory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tblBrandorCata.setItems(FXCollections.observableArrayList(allCata));
    }

    @FXML
    void tblDiscountAction(MouseEvent event) {
        SDiscountDTO discountDTO = tblSdiscount.getSelectionModel().selectedItemProperty().get();
        if (discountDTO != null) {
            dtpStartDate.setValue(LocalDate.parse(discountDTO.getStartDate()));
            dtpEndDate.setValue(LocalDate.parse(discountDTO.getEndDate()));
            txtDiscount.setText(String.valueOf(discountDTO.getAmount()));
            txtNoofDays.setText(String.valueOf(discountDTO.getDays()));
            cmbCatagory.getSelectionModel().select(getSelectedCatagory());
            cmbBrand.getSelectionModel().select(getSelectedbrand());

            if (discountDTO.getAmount() < 1) {
                rdbPercentage.setSelected(true);
            } else {
                rdbAmount.setSelected(true);
            }
        }
    }

    boolean validAccName(String s) {
        return Pattern.compile("^[A-z]{0,15}").matcher(s).matches();
    }

    boolean validAccUsername(String s) {
        return Pattern.compile("^[A-z0-9]{0,10}$").matcher(s).matches();
    }

    boolean validAccPassword(String s) {
        return Pattern.compile("^[A-z0-9]{4,15}$").matcher(s).matches();
    }
}

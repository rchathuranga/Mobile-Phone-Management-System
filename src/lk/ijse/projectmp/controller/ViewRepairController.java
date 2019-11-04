package lk.ijse.projectmp.controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import lk.ijse.projectmp.bo.BOFactory;
import lk.ijse.projectmp.bo.bo.custom.CustomerBO;
import lk.ijse.projectmp.bo.bo.custom.RepairBO;
import lk.ijse.projectmp.bo.bo.custom.RepairDetailBO;
import lk.ijse.projectmp.dto.CustomerDTO;
import lk.ijse.projectmp.dto.RepairDTO;
import lk.ijse.projectmp.dto.RepairDetailDTO;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewRepairController implements Initializable {

    @FXML
    private TextField txtRepairId;

    @FXML
    private JFXTextField txtCname;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtNic;

    @FXML
    private JFXTextField txtPhone;

    @FXML
    private JFXTextField txtPhoneDesc;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDeleteRepair;

    @FXML
    private Label lblNoofService;

    @FXML
    private Label lblApproxiTot;

    @FXML
    private TextField txtServiceId;

    @FXML
    private JFXButton btnDone;

    @FXML
    private JFXButton btnDeleteService;

    @FXML
    private TableView<RepairDTO> tblRepair;

    @FXML
    private TableView<RepairDetailDTO> tblRepairDetail;

    @FXML
    private JFXButton btnFinish;

    @FXML
    private HBox hBox;

    @FXML
    private Label lblServiceTotal;

    @FXML
    private Label lblPartTotal;

    @FXML
    private Label lblTotal;

    private  RepairBO repairBO = (RepairBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.REPAIR);
    private RepairDetailBO repairDetailBO = (RepairDetailBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.REPAIRDETAIL);
    private CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);

    @FXML
    void btnDeleteRepairAction(ActionEvent event) {
        if (tblRepair.getSelectionModel().getSelectedItem() != null) {
            try {
                boolean b = repairBO.deleteRepair(Integer.parseInt(txtRepairId.getText()));
                if (b) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "Failed").show();
                }
                loadTable();
                loadRepairDetailTbl(tblRepair.getSelectionModel().getSelectedItem());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void btnDeleteServiceAction(ActionEvent event) throws Exception {
        if (tblRepairDetail.getSelectionModel().getSelectedItem() != null) {
            boolean b = repairDetailBO.deleteRepairDetail(Integer.parseInt(txtServiceId.getText()));
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Failed").show();
            }
            loadTable();
            loadRepairDetailTbl(tblRepair.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void btnDoneAction(ActionEvent event) {
        try {
            boolean b = repairDetailBO.setCompleted(Integer.parseInt(txtServiceId.getText()));
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Done").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Failed").show();
            }
            loadRepairDetailTbl(tblRepair.getSelectionModel().getSelectedItem());
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @FXML
    void btnUpdateAction(ActionEvent event) {
        if(tblRepair.getSelectionModel().getSelectedItem()!=null) {
            RepairDTO repairDTO = new RepairDTO(Integer.parseInt(txtRepairId.getText()),
                    txtPhoneDesc.getText());
            try {
                boolean b = repairBO.updateRepair(repairDTO);
                if (b) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "Failed").show();
                }
                loadTable();
                loadRepairDetailTbl(tblRepair.getSelectionModel().getSelectedItem());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initiateTable();
        loadTable();
    }

    void initiateTable() {
        tblRepair.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("repid"));
        tblRepair.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("cid"));
        tblRepair.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblRepair.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("phone"));
        tblRepair.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblRepair.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("noofRepair"));
        tblRepair.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("total"));


        tblRepairDetail.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblRepairDetail.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        tblRepairDetail.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("servicePrice"));
        tblRepairDetail.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("partId"));
        tblRepairDetail.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("partName"));
        tblRepairDetail.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        tblRepairDetail.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("amount"));
        tblRepairDetail.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("completed"));
    }

    void loadTable() {
        try {
            ArrayList<RepairDTO> allRepair = repairBO.getAllRepair();
            tblRepair.setItems(FXCollections.observableArrayList(allRepair));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void tblRepairMouseAction(MouseEvent event) {
        try {
            RepairDTO selectedItem = tblRepair.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                txtRepairId.setText(String.valueOf(selectedItem.getRepid()));
                CustomerDTO customerDTO = customerBO.searchCustomer(selectedItem.getCid());
                txtCname.setText(customerDTO.getName());
                txtContact.setText(String.valueOf(customerDTO.getContact()));
                txtNic.setText(customerDTO.getNic());
                lblNoofService.setText(String.valueOf(selectedItem.getNoofRepair()));
                lblApproxiTot.setText(String.valueOf(selectedItem.getTotal()));
                txtPhone.setText(selectedItem.getPhone());
                txtPhoneDesc.setText(selectedItem.getDescription());
                loadRepairDetailTbl(selectedItem);
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    void loadRepairDetailTbl(RepairDTO selectedItem) {
        if (selectedItem != null) {
            try {
                tblRepairDetail.setItems(FXCollections.observableArrayList(repairDetailBO.getAllDetailOf(selectedItem.getRepid())));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void tblRepairDetailMouseAction(MouseEvent event) {
        RepairDetailDTO selectedItem = tblRepairDetail.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            txtServiceId.setText(String.valueOf(selectedItem.getId()));
        }

    }

    @FXML
    void btnFinishAction(ActionEvent event) {
        try {
            repairBO.finish(Integer.parseInt(txtRepairId.getText()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package lk.ijse.projectmp.controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
//import dao.SuperDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.fxml.Initializable;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.ijse.projectmp.bo.BOFactory;
import lk.ijse.projectmp.bo.bo.custom.CustomerBO;
import lk.ijse.projectmp.bo.bo.custom.RepairBO;
import lk.ijse.projectmp.bo.bo.custom.RepairServiceBO;
import lk.ijse.projectmp.dao.dao.custom.RepairPartDAO;
import lk.ijse.projectmp.dto.*;
import lk.ijse.projectmp.entity.RepairPart;
import lk.ijse.projectmp.entity.RepairService;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddRepairController implements Initializable {

    @FXML
    private AnchorPane anchor;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private TextField txtCid;

    @FXML
    private JFXTextField txtCname;

    @FXML
    private JFXTextField txtCcontact;

    @FXML
    private JFXTextField txtCnic;

    @FXML
    private JFXButton btnSaveDraft;

    @FXML
    private JFXButton btnFillDraft;

    @FXML
    private TextField txtServiceId;

    @FXML
    private JFXComboBox<RepairServiceDTO> cbxService;

    @FXML
    private JFXButton btnAddService;

    @FXML
    private JFXTextField txtPhone;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private Label lblNoofServices;

    @FXML
    private TableView<RepairDetailDTO> tblRepairServiceList;

    @FXML
    private AnchorPane achNewService;

    @FXML
    private ImageView imgClear;

    @FXML
    private JFXButton btnRemoveService;

    @FXML
    private JFXComboBox<CustomerDTO> cbxSelectCustomer;

    @FXML
    private Label lblNewService;

    @FXML
    private JFXComboBox<RepairServiceDTO> cbxNewService;

    @FXML
    private JFXTextField txtServiceName;

    @FXML
    private JFXTextField txtServicePrice;

    @FXML
    private JFXComboBox<RepairPartDTO> cbxPartName;

    @FXML
    private JFXTextField txtPartName;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtBuyingPrice;

    @FXML
    private JFXTextField txtSellingPrice;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdateService;

    @FXML
    private JFXButton btnDeleteService;

    @FXML
    private JFXButton btnCancelService;

    @FXML
    private JFXButton btnViewRepair;

    @FXML
    private JFXButton btnAddNewService;

    @FXML
    private Label lblServiceTotal;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblPartTotal;

    @FXML
    private JFXButton btnAddRepair;

    @FXML
    private JFXButton btnDelete;

    RepairServiceBO repairServiceBO = (RepairServiceBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.REPAIRSERVICE);
    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
    RepairBO repairBO = (RepairBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.REPAIR);

    CustomerDTO draft = new CustomerDTO();

    @FXML
    void btnAddAction(ActionEvent event) throws Exception {
        RepairPartDTO selectedPart = cbxPartName.getSelectionModel().getSelectedItem();
        RepairServiceDTO service = null;

        boolean isAdded = false;

        if (selectedPart == null) {

            service = new RepairServiceDTO(txtServiceName.getText(), Double.parseDouble(txtServicePrice.getText()));
            RepairPartDTO part = new RepairPartDTO(
                    txtPartName.getText(),
                    Double.parseDouble(txtBuyingPrice.getText()),
                    Double.parseDouble(txtServicePrice.getText()),
                    Integer.parseInt(txtQty.getText())
            );

            service.setPart(part);
            try {
                isAdded = repairServiceBO.addService(service);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            service = new RepairServiceDTO(txtServiceName.getText(), Double.parseDouble(txtServicePrice.getText()));
            service.setRpid(selectedPart.getId());
            repairServiceBO.addService(service);
        }
        if (isAdded) {
            new Alert(Alert.AlertType.CONFIRMATION, "Added").show();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "Failed").show();
        }
    }

    @FXML
    void btnRemoveServiceAction(ActionEvent event) {
        if (tblRepairServiceList.getSelectionModel().getSelectedItem() != null) {
            int selectedIndex = tblRepairServiceList.getSelectionModel().getSelectedIndex();
            repairList.remove(selectedIndex);
            tblRepairServiceList.setItems(FXCollections.observableArrayList(repairList));
            setLbls();
        }
    }

    void setLbls() {
        int count = 0;
        double serviceTot = 0.0;
        double partTot = 0.0;
        double total = 0.0;
        for (RepairDetailDTO rd :
                repairList) {
            count++;
            serviceTot += rd.getServicePrice();
            partTot += rd.getPartPrice();
            total += rd.getAmount();
        }
        lblNoofServices.setText(String.valueOf(count));
        lblServiceTotal.setText(String.valueOf(serviceTot));
        lblPartTotal.setText(String.valueOf(partTot));
        lblTotal.setText(String.valueOf(total));

    }

    ArrayList<RepairDetailDTO> repairList = new ArrayList<>();

    @FXML
    void btnAddServiceAction(ActionEvent event) {
        try {
            int serviceId = Integer.parseInt(txtServiceId.getText());
            RepairDetailDTO service = repairServiceBO.searchService(serviceId);
            service.setAmount(service.getServicePrice() + service.getPartPrice());
            repairList.add(service);
            tblRepairServiceList.setItems(FXCollections.observableArrayList(repairList));
        } catch (Exception e) {
            e.getMessage();
        }
        setLbls();
    }


    @FXML
    void btnDeleteAction(ActionEvent event) {
    }

    @FXML
    void btnAddNewServiceAction(ActionEvent event) {
        achNewService.setDisable(false);
        btnAddRepair.setDisable(true);
    }

    @FXML
    void btnCancelServiceAction(ActionEvent event) {
        achNewService.setDisable(true);
        btnAddRepair.setDisable(false);
    }

    @FXML
    void btnFillDraftAction(ActionEvent event) {
        txtCname.setText(draft.getName());
        txtCcontact.setText(String.valueOf(draft.getContact()));
        txtCnic.setText(draft.getNic());
    }

    @FXML
    void btnSaveDraftAction(ActionEvent event) {
        draft.setName(txtCname.getText());
        if (!txtCcontact.getText().equals("")) {
            draft.setContact(Integer.parseInt(txtCcontact.getText()));
        }
        draft.setNic(txtCnic.getText());
        txtCname.setText("");
        txtCcontact.setText("");
        txtCnic.setText("");
    }

    @FXML
    void btnUpdateAction(ActionEvent event) {

    }

    @FXML
    void btnViewRepairAction(ActionEvent event) {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("/lk/ijse/projectmp/view/ViewRepair.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        anchor.getChildren().clear();
        anchor.getChildren().add(anchorPane);
    }


    @FXML
    void btnDiscardAction(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure ????", ButtonType.OK, ButtonType.CANCEL);
        Optional<ButtonType> action = a.showAndWait();

        if (action.get() == ButtonType.OK) {
            clearCustomerA();
            clearNewServiceA();
            clearRepairA();
        } else {
            System.out.println("cancel");
        }
    }

    void clearCustomerA() {
        txtCid.setText("");
        txtCname.setText("");
        txtCcontact.setText("");
        txtCnic.setText("");
    }

    void clearNewServiceA() {
        txtServiceName.setText("");
        txtServicePrice.setText("");
        txtPartName.setText("");
        txtQty.setText("");
        txtBuyingPrice.setText("");
        txtSellingPrice.setText("");
    }

    void clearRepairA() {
        txtPhone.setText("");
        txtDescription.setText("");
        txtServiceId.setText("");
    }

    void loadCMB() {
        try {
            cbxService.setItems(FXCollections.observableArrayList(repairServiceBO.getAllService()));
            cbxSelectCustomer.setItems(FXCollections.observableArrayList(customerBO.getAllCustomer()));
            cbxNewService.setItems(FXCollections.observableArrayList(repairServiceBO.getAllService()));
            cbxPartName.setItems(FXCollections.observableArrayList(repairServiceBO.getAllPart()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void imgClearMouseAction(MouseEvent event) {
        cbxSelectCustomer.getSelectionModel().clearSelection();
    }


    int getCustomer() {
        int customerId = 0;
        try {
            CustomerDTO customer;
            if (cbxSelectCustomer.getSelectionModel().isEmpty()) {
                String name = txtCname.getText();
                if (Pattern.compile("[A-z]{3,15}").matcher(name).matches()) {
                    txtCname.setUnFocusColor(Paint.valueOf("#1900ff"));
                    txtCname.setFocusColor(Paint.valueOf("black"));
                    String contact = txtCcontact.getText();
                    if (Pattern.compile("^[0-9]{10}$").matcher(contact).matches()) {
                        txtCcontact.setUnFocusColor(Paint.valueOf("#1900ff"));
                        txtCcontact.setFocusColor(Paint.valueOf("black"));
                        String nic = txtCnic.getText();
                        if (Pattern.compile("[0-9]{12}|[0-9]{9}(v)").matcher(nic).matches()) {
                            txtCnic.setUnFocusColor(Paint.valueOf("#1900ff"));
                            txtCnic.setFocusColor(Paint.valueOf("black"));
                            customer = new CustomerDTO(name, Integer.parseInt(contact), nic);
                            customerBO.addCustomer(customer);
                            customerId = customerBO.searchCustomer(txtCname.getText()).getId();
                        } else {
                            invalidMassage(txtCnic);
                        }
                    } else {
                        invalidMassage(txtCcontact);
                    }
                } else {
                    invalidMassage(txtCname);
                }
            } else {
                customer = cbxSelectCustomer.getValue();
                customerId = customer.getId();
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return customerId;
    }

    public void invalidMassage(JFXTextField tf) {
        tf.setUnFocusColor(Paint.valueOf("red"));
        tf.setFocusColor(Paint.valueOf("red"));
        tf.requestFocus();
        new Alert(Alert.AlertType.CONFIRMATION, "Invalid Input. Try again...").show();
    }

    @FXML
    void cbxServiceAction(ActionEvent event) {
        txtServiceId.setText(String.valueOf(cbxService.getSelectionModel().getSelectedItem().getRsid()));
    }

    @FXML
    void btnAddRepairAction(ActionEvent event) {
        if(!tblRepairServiceList.getItems().isEmpty()) {
            int customerid = getCustomer();
            double amount = Double.parseDouble(lblTotal.getText());
            String phone = txtPhone.getText();
            String desc = txtDescription.getText();

            RepairDTO repairDTO = new RepairDTO(customerid, amount, phone, desc);
            ArrayList<RepairDetailDTO> arrayList = new ArrayList<>();
            for (RepairDetailDTO rd :
                    tblRepairServiceList.getItems()) {
                arrayList.add(new RepairDetailDTO(
                        rd.getRepairServiceId(),
                        rd.getAmount()
                ));
            }
            repairDTO.setRepairDetail(arrayList);

            try {
                boolean b = repairBO.addRepair(repairDTO);
                if(b){
                    new Alert(Alert.AlertType.INFORMATION,"Repair Added").show();

                    InputStream invoice = this.getClass().getResourceAsStream("/lk/ijse/projectmp/reports/Repair.jasper");
                    HashMap map = new HashMap();

                    map.put("CustomerName", customerBO.searchCustomer(customerid).getName());
                    map.put("RepairId", repairBO.getAllRepair().get(repairBO.getAllRepair().size()-1).getRepid());
                    map.put("Phone", repairDTO.getPhone());
                    map.put("Description", repairDTO.getDescription());
                    map.put("Total", repairDTO.getTotal());

                    map.put("buyes",tblRepairServiceList.getItems());

                    JasperPrint print = JasperFillManager.fillReport(invoice, map,new JREmptyDataSource());
                    JasperViewer.viewReport(print, false);


                }else {
                    new Alert(Alert.AlertType.INFORMATION,"Failed to Add Repair").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadCMB();

        tblRepairServiceList.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("repairServiceId"));
        tblRepairServiceList.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        tblRepairServiceList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("servicePrice"));
        tblRepairServiceList.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("partName"));
        tblRepairServiceList.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        tblRepairServiceList.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("amount"));

        try {
            TextFields.bindAutoCompletion(txtSearch, repairServiceBO.getAllService());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int getSelectedCustomer(int id){
        int count=0;
        int catagoryid=id;
        try {
            for (CustomerDTO c:customerBO.getAllCustomer()) {
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

    @FXML
    void txtContactAction(ActionEvent event) {
        try {
            String text = txtCcontact.getText();
            if (Pattern.compile("^[0-9]{10}$").matcher(text).matches()) {
                txtCcontact.setUnFocusColor(Paint.valueOf("#1900ff"));
                txtCcontact.setFocusColor(Paint.valueOf("black"));
                CustomerDTO customerDTO = customerBO.searchCustomerByContact(Integer.parseInt(txtCcontact.getText()));
                cbxSelectCustomer.getSelectionModel().select(getSelectedCustomer(customerDTO.getId()));
            } else {
                invalidMassage(txtCcontact);
            }
        }catch (Exception e){
            e.getMessage();
        }
    }

    int getSelectedCatagory(int id) {
        int count = 0;
        int rServiceId = id;
        try {
            for (RepairServiceDTO c : repairServiceBO.getAllService()) {
                if (c.getRsid() == rServiceId) {
                    return count;
                }
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @FXML
    void txtSearchAction(ActionEvent event) {
        String text = txtSearch.getText();
        String Sid = "0";
        for (int i = 0; i < 3; i++) {
            Sid += text.charAt(i);
        }
        int id = Integer.parseInt(Sid);
        cbxService.getSelectionModel().select(getSelectedCatagory(id));
    }
}

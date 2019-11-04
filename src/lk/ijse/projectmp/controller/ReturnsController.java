package lk.ijse.projectmp.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import lk.ijse.projectmp.bo.BOFactory;
import lk.ijse.projectmp.bo.bo.custom.*;
import lk.ijse.projectmp.dto.*;
import lk.ijse.projectmp.entity.OrderDetail;
import lk.ijse.projectmp.entity.Phone;

import javax.naming.Name;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ReturnsController implements Initializable {

    private OrderDetailBO orderDetailBO = (OrderDetailBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDERDETAIL);
    private ReturnsBO returnsBO = (ReturnsBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.RETURNS);
    private OrdersBO ordersBO = (OrdersBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDERS);
    private ItemBO itemBO = (ItemBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);
    private PhoneBO phoneBO = (PhoneBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PHONE);


    @FXML
    private TableView<ReturnsDTO> tblReturns;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TableView<OrderDetailDTO> tblOrderDetail;

    @FXML
    private TextField txtItemId;

    @FXML
    private TextField txtWarranty;

    @FXML
    private JFXTextField txtReason;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private Label lblCustomer;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblAmount;

    @FXML
    private Label lblDiscount;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtItemName;

    @FXML
    private JFXButton btnAddToItem;

    @FXML
    private JFXButton btnAddReturn;

    @FXML
    void btnAddAction(ActionEvent event) {
        int itemId = Integer.parseInt(txtItemId.getText());
        String reason = txtReason.getText();
        if (Pattern.compile("[0-9]{0,}[.0-9]{0,2}").matcher(txtPrice.getText()).matches()) {
            double amount = Double.parseDouble(txtPrice.getText());
            ReturnsDTO returnsDTO;

            if (itemId >= 800000) {
                returnsDTO = new ReturnsDTO(orders.getCid(), itemId, 0, reason, amount);
            } else {
                returnsDTO = new ReturnsDTO(orders.getCid(), 0, itemId, reason, amount);
            }
            try {
                boolean b = returnsBO.addReturns(returnsDTO);
                if (b) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Added").show();
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "Failed").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid Price Input. Please Enter Valid Input").show();
        }
        loadTblReturns();
    }

    private OrdersDTO orders = null;

    @FXML
    void txtOrderIdAction(ActionEvent event) {
        if (Pattern.compile("^[0-9]{6}$").matcher(txtOrderId.getText()).matches()) {
            String text = txtOrderId.getText();
            int id = Integer.parseInt(text);
            try {
                ArrayList<OrderDetailDTO> all = orderDetailBO.getAll(id);
                if (all.isEmpty()) {
                    new Alert(Alert.AlertType.ERROR, "ERROR 404: Order Not Found").show();
                } else {
                    orders = ordersBO.searchOrders(id);
                    lblCustomer.setText(orders.getCustomerName());
                    lblDate.setText(orders.getDate());
                    lblTime.setText(orders.getTime());
                    lblAmount.setText(String.valueOf(orders.getAmount()));
                    lblDiscount.setText(String.valueOf(orders.getDiscount()));
                    lblTotal.setText(String.valueOf(orders.getTotal()));
                    tblOrderDetail.setItems(FXCollections.observableArrayList(all));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid Order Id Input. Please Enter Valid Input").show();
        }
    }


    @FXML
    void btnAddToItemAction(ActionEvent event) {
//        ReturnsDTO returnsDTO=new ReturnsDTO(lblCustomer.getText(),);
//        returnsBO.addReturnstoItem(id);
    }

    @FXML
    void tblOrderDetailMouseAction(MouseEvent event) throws Exception {
        OrderDetailDTO selectedItem = tblOrderDetail.getSelectionModel().getSelectedItem();
        txtItemId.setText(String.valueOf(selectedItem.getItemid()));
        txtItemName.setText(selectedItem.getItemName());
        txtPrice.setText(String.valueOf(selectedItem.getAmount()));

        txtPrice.setText(String.valueOf(selectedItem.getAmount()));

        int warranty;
        if (selectedItem.getItemid() >= 800000) {
            txtItemId.setText(String.valueOf(selectedItem.getItemid()));
            warranty = itemBO.searchItem(selectedItem.getItemid()).getWarranty();
        } else {
            txtItemId.setText(String.valueOf(selectedItem.getPhoneid()));
            warranty = phoneBO.searchPhone(selectedItem.getPhoneid()).getWarranty();
        }

        LocalDate warrExpireDate = LocalDate.parse(lblDate.getText()).plusMonths(warranty);

        if (warrExpireDate.isAfter(LocalDate.now())) {
            txtWarranty.setText("Warranty Valid");
            btnAddReturn.setDisable(false);
        } else {
            txtWarranty.setText("Warranty Expire");
            btnAddReturn.setDisable(true);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblOrderDetail.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblOrderDetail.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tblOrderDetail.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblOrderDetail.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("amount"));
        tblOrderDetail.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("discount"));
        tblOrderDetail.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("total"));

        tblReturns.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblReturns.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("cid"));
        tblReturns.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblReturns.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("itemid"));
        tblReturns.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tblReturns.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("reason"));
        tblReturns.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("price"));
        loadTblReturns();
    }

    private void loadTblReturns() {
        try {
            tblReturns.setItems(FXCollections.observableArrayList(returnsBO.getAllReturns()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package lk.ijse.projectmp.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.projectmp.bo.BOFactory;
import lk.ijse.projectmp.bo.bo.custom.*;
import lk.ijse.projectmp.dto.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

public class POSController implements Initializable {

    @FXML
    private JFXTextField txtSearch;

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
    private TextField txtItemid;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXButton btnAddQty;

    @FXML
    private JFXRadioButton rdbAmount;

    @FXML
    private ToggleGroup discountType;

    @FXML
    private JFXRadioButton rdbPercentage;

    @FXML
    private JFXTextField txtOrderDiscount;

    @FXML
    private JFXTextField txtItemDiscount;

    @FXML
    private JFXButton btnAddDiscount;

    @FXML
    private TableView<CustomDTO> tblPos;

    @FXML
    private JFXButton btnAcceptReturn;

    @FXML
    private Label lblSubTotal;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblDiscount;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXButton btnDiscard;


    @FXML
    private AnchorPane anchor;

    @FXML
    private JFXButton btnRemoveDiscount;

    @FXML
    private JFXComboBox<CustomerDTO> cmbSelectCustomer;

    @FXML
    private JFXButton btnRemoveItem;

    @FXML
    private ImageView imgClear;

    PhoneBO phoneBO = (PhoneBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PHONE);
    ItemBO itemBO = (ItemBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);
    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
    CustomBO customBO = (CustomBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOM);
    BrandBO brandBO = (BrandBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BRAND);
    CatagoryBO catagoryBO = (CatagoryBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CATAGORY);
    OrdersBO ordersBO = (OrdersBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDERS);

    private CustomerDTO draftCustomer = new CustomerDTO();


    @FXML
    void subBtnAction(ActionEvent event) {
        JFXButton btn = (JFXButton) event.getSource();
        switch (btn.getId()) {
            case "btnSaveDraft":
                saveAndfillDraft(true);
                clearCustomerField();
                break;
            case "btnFillDraft":
                saveAndfillDraft(false);
                break;
            case "btnRemoveItem":
                removeItem();
                break;
            case "btnAddQty":
                setQty();
                break;
            case "btnAddDiscount":
                setDiscount();
                break;
            case "btnRemoveDiscount":
                lblDiscount.setText("0.0");
                setTotal();
                break;
        }
    }

    @FXML
    void btnAcceptReturnAction(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/lk/ijse/projectmp/view/Returns.fxml"));
            anchor.getChildren().clear();
            anchor.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    int getCustomer() {
        int customerId = 0;
        try {
            CustomerDTO customer;
            if (cmbSelectCustomer.getSelectionModel().isEmpty()) {
                String name = txtCname.getText();
                if (Pattern.compile("[A-z]{3,15}").matcher(name).matches()) {
                    String contact = txtCcontact.getText();
                    if (Pattern.compile("^[0-9]{10}$").matcher(contact).matches()) {
                        String nic = txtCnic.getText();
                        if (Pattern.compile("[0-9]{12}|[0-9]{9}(v)").matcher(nic).matches()) {
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
                customer = cmbSelectCustomer.getValue();
                customerId = customer.getId();
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return customerId;
    }

    @FXML
    void btnPlaceOrderAction(ActionEvent event) {
        int customerId = getCustomer();
        if (customerId != 0) {
            OrdersDTO orders = new OrdersDTO(customerId,
                    Double.parseDouble(lblTotal.getText()),
                    Double.parseDouble(lblDiscount.getText()),
                    Double.parseDouble(lblTotal.getText())
            );
            ArrayList<OrderDetailDTO> orderlist = new ArrayList<>();

            for (CustomDTO c : tblPos.getItems()) {
                if (c.getId() >= 800000) {
                    orderlist.add(new OrderDetailDTO(0, c.getId(), 0, c.getQty(), c.getSellingPrice(), c.getDiscount(), c.getTotal()));
                } else {
                    orderlist.add(new OrderDetailDTO(0, c.getId(), 0, c.getQty(), c.getSellingPrice(), c.getDiscount(), c.getTotal(), 0));
                }
            }
            orders.setOrderDetails(orderlist);

            try {
                boolean b = ordersBO.addOrders(orders);
                if (true) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Order Placed.").show();
                    InputStream invoice = this.getClass().getResourceAsStream("/lk/ijse/projectmp/reports/Invoice.jasper");
                    HashMap map = new HashMap();

                    if(cmbSelectCustomer.getSelectionModel().isEmpty()){
                        map.put("CustomerName", txtCname.getText());
                    }else {
                        map.put("CustomerName", cmbSelectCustomer.getSelectionModel().getSelectedItem().getName());
                    }
                    map.put("OrderId", ordersBO.getLastOrderId());
                    map.put("SubTotal", orders.getAmount());                                 //jasper Report
                    map.put("Discount", orders.getDiscount());
                    map.put("Total", orders.getTotal());



                    ArrayList<CustomDTO> array=new ArrayList<>();
                    for (CustomDTO cus:
                         tblPos.getItems()) {
                        array.add(cus);
                    }

                    JRBeanCollectionDataSource jr=new JRBeanCollectionDataSource(array);

                    map.put("POSItems",jr);
                    JasperPrint print = JasperFillManager.fillReport(invoice, map,new JREmptyDataSource());
                    JasperViewer.viewReport(print, false);
                } else {
                    new Alert(Alert.AlertType.ERROR, "Something Goes Wrong. Please Try Again").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void btnDiscardAction(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure ????", ButtonType.OK, ButtonType.CANCEL);
        Optional<ButtonType> action = a.showAndWait();
        if (action.get() == ButtonType.OK) {
            clearFields();
            custom = null;
            arrayList = new ArrayList<>();
            tblPos.setItems(FXCollections.observableArrayList(arrayList));
        }
    }

    void saveAndfillDraft(boolean b) {
        if (b) {
            draftCustomer.setName(txtCname.getText());
            if (!txtCcontact.getText().equals("")) {
                draftCustomer.setContact(Integer.parseInt(txtCcontact.getText()));
            }
            draftCustomer.setNic(txtCnic.getText());
        } else {
            txtCname.setText(draftCustomer.getName());
            txtCcontact.setText(String.valueOf(draftCustomer.getContact()));
            txtCnic.setText(draftCustomer.getNic());
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
                cmbSelectCustomer.getSelectionModel().select(getSelectedCustomer(customerDTO.getId()));
            } else {
                invalidMassage(txtCcontact);
            }
        }catch (Exception e){
            e.getMessage();
        }
    }

    @FXML
    void rdbDiscountAction(ActionEvent event) {
        JFXRadioButton rdb = (JFXRadioButton) event.getSource();
        if (rdb.getId() == rdbPercentage.getId()) {
            txtOrderDiscount.setPromptText(rdb.getText());
        } else {
            txtOrderDiscount.setPromptText(rdb.getText());
        }
    }

    void clearCustomerField() {
        cmbSelectCustomer.getSelectionModel().clearSelection();
        txtCname.setText("");
        txtCcontact.setText("");
        txtCnic.setText("");
    }

    void clearFields() {
        clearCustomerField();
        txtItemid.setText("");
        txtOrderDiscount.setText("");
        txtQty.setText("");
        lblSubTotal.setText("0.0");
        lblDiscount.setText("0.0");
        lblTotal.setText("0.0");
    }


    void removeItem() {
        int selectedIndex = tblPos.getSelectionModel().getSelectedIndex();
        if (tblPos.getSelectionModel().getSelectedItem() != null) {
            arrayList.remove(selectedIndex);
            tblPos.setItems(FXCollections.observableArrayList(arrayList));
            setSubTotal();
            setTotal();
        }
    }

    @FXML
    void tblPOSMouseAction(MouseEvent event) {
        CustomDTO selectedItem = tblPos.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            txtItemid.setText(String.valueOf(tblPos.getSelectionModel().selectedItemProperty().get().getId()));
            txtQty.setText(String.valueOf(tblPos.getSelectionModel().selectedItemProperty().get().getQty()));
        }
    }

    void setQty() {
        if (custom != null) {
            if (Pattern.compile("^[0-9]{0,3}$").matcher(txtQty.getText()).matches()) {
                txtQty.setUnFocusColor(Paint.valueOf("#1900ff"));
                txtQty.setFocusColor(Paint.valueOf("black"));
                custom.setQty(Integer.parseInt(txtQty.getText()));
                custom.setTotal(custom.getQty() * custom.getSellingPrice());
                String discount = txtItemDiscount.getText();

                if (Pattern.compile("^[0-9]{0,}(.)[0-9]{0,3}$").matcher(discount).matches()) {
                    txtItemDiscount.setUnFocusColor(Paint.valueOf("#1900ff"));
                    txtItemDiscount.setFocusColor(Paint.valueOf("black"));
                    custom.setDiscount(Double.parseDouble(discount));
                    custom.setTotal((custom.getQty() * custom.getSellingPrice()) - custom.getDiscount());
                    arrayList.add(custom);
                    tblPos.setItems(FXCollections.observableArrayList(arrayList));
                    setSubTotal();
                    setTotal();
                } else {
                    invalidMassage(txtItemDiscount);
                }
            } else {
                invalidMassage(txtQty);
            }
        } else {
            txtSearch.requestFocus();
        }

//        CustomDTO customDTO=null;
//
//        if (custom != null) {
//            if (count == 1) {
//                custom.setQty(Integer.parseInt(txtQty.getText()));
//                arrayList.add(custom);
//                tblPos.setItems(FXCollections.observableArrayList(arrayList));
//                System.out.println("1");
//            } else {
//                boolean b=false;
//                for (CustomDTO c : tblPos.getItems()) {
//                    if (c.getId() == Integer.parseInt(txtItemid.getText())){
//                        b=true;
//                        break;
//                    }
//                    customDTO=c;
//                }
//                if(b){
//                    customDTO.setQty(customDTO.getQty() + Integer.parseInt(txtQty.getText()));
//                    ObservableList<CustomDTO> items = tblPos.getItems();
//                    items.remove(customDTO);
//                    items.add(customDTO);
//                    tblPos.setItems(items);
//                    System.out.println("if");
//                } else {
//                    custom.setQty(Integer.parseInt(txtQty.getText()));
//                    arrayList.add(custom);
//                    tblPos.setItems(FXCollections.observableArrayList(arrayList));
//                    System.out.println("else");
//                }
//            }
//        }
//        count++;
    }
//    int count = 1;



    void setSubTotal() {
        double total = 0.0;
        for (CustomDTO c :
                arrayList) {
            total += c.getTotal();
        }
        lblSubTotal.setText(String.valueOf(total));
    }

    void setDiscount() {
        if (rdbAmount == discountType.getSelectedToggle()) {
            if (Pattern.compile("^[0-9]{0,}[.0-9]{0,3}$").matcher(txtOrderDiscount.getText()).matches()) {
                txtOrderDiscount.setUnFocusColor(Paint.valueOf("#1900ff"));
                txtOrderDiscount.setFocusColor(Paint.valueOf("black"));
                lblDiscount.setText(txtOrderDiscount.getText());
                setTotal();
            } else {
                invalidMassage(txtOrderDiscount);
            }
        } else {
            if (Pattern.compile("^[0-9]{2}[.0-9]{0,2}$").matcher(txtOrderDiscount.getText()).matches()) {
                txtOrderDiscount.setUnFocusColor(Paint.valueOf("#1900ff"));
                txtOrderDiscount.setFocusColor(Paint.valueOf("black"));

                double subTot = Double.parseDouble(lblSubTotal.getText());
                double discount = subTot * (Double.parseDouble(txtOrderDiscount.getText()) / 100);
                double total = subTot - discount;

                lblDiscount.setText(String.valueOf(discount));
                lblTotal.setText(String.valueOf(total));

            } else {
                invalidMassage(txtOrderDiscount);
            }
        }
        setTotal();
    }

    public void invalidMassage(JFXTextField tf) {
        tf.setUnFocusColor(Paint.valueOf("red"));
        tf.setFocusColor(Paint.valueOf("red"));
        tf.requestFocus();
        new Alert(Alert.AlertType.CONFIRMATION, "Invalid Input. Try again...").show();
    }

    public void setTotal() {
        double subTot = Double.parseDouble(lblSubTotal.getText());
        double discount = Double.parseDouble(lblDiscount.getText());
        lblTotal.setText(String.valueOf(subTot - discount));
    }

    private ArrayList<CustomDTO> arrayList = new ArrayList<>();
    private CustomDTO custom = null;

    @FXML
    void txtSearchAction(ActionEvent event) {
        try {
            String text = txtSearch.getText();
            String id = "";

            for (int i = 0; i < 6; i++) {
                id += String.valueOf(text.charAt(i));
            }
            int stockId = Integer.parseInt(id);
            if (stockId >= 800000) {
                ItemDTO itemDTO = itemBO.searchItem(stockId);
                CustomDTO customDTO = new CustomDTO(itemDTO.getId(), itemDTO.getBrandid(),
                        itemDTO.getCataid(), itemDTO.getName(), itemDTO.getQty(),
                        itemDTO.getWarranty(), itemDTO.getSellingPrice(),
                        itemDTO.getBuyingPrice());
                customDTO.setBrandName(brandBO.searchBrand(itemDTO.getBrandid()).getName());
                customDTO.setCatagoryName(catagoryBO.searchCatagory(itemDTO.getCataid()).getName());
                txtItemid.setText(String.valueOf(itemDTO.getId()));
                txtQty.requestFocus();
                custom = customDTO;
            } else {
                PhoneDTO pDTO = phoneBO.searchPhone(stockId);
                CustomDTO customDTO = new CustomDTO(pDTO.getId(), pDTO.getBrandid(), 0,
                        pDTO.getName(), pDTO.getRam(), pDTO.getStorage(),
                        pDTO.getNetwork(), pDTO.getQty(), pDTO.getWarranty(),
                        pDTO.getSellingPrice(), pDTO.getBuyingPrice());
                customDTO.setBrandName(brandBO.searchBrand(pDTO.getBrandid()).getName());
                customDTO.setCatagoryName("Mobile Phone");
                txtItemid.setText(String.valueOf(pDTO.getId()));
                txtQty.requestFocus();
                custom = customDTO;
            }
            txtQty.setText(String.valueOf(custom.getQty()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void loadcmbSelectCustomer() {
        try {
            cmbSelectCustomer.setItems(FXCollections.observableArrayList(customerBO.getAllCustomer()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadcmbSelectCustomer();

        tblPos.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblPos.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblPos.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("brandName"));
        tblPos.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("catagoryName"));
        tblPos.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblPos.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        tblPos.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("discount"));
        tblPos.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("total"));


        try {
            ArrayList<CustomDTO> allItemandPhone = customBO.getAllItemandPhone();
//            for (CustomerDTO c :
//                    customerBO.getAllCustomer()) {
//                allItemandPhone.add(new CustomDTO(c.getId(),c.getName()));
//            }
            TextFields.bindAutoCompletion(txtSearch, allItemandPhone);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @FXML
    void imgClearMouseClick(MouseEvent event) {
        cmbSelectCustomer.getSelectionModel().clearSelection();
    }

}

package lk.ijse.projectmp.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lk.ijse.projectmp.bo.BOFactory;
import lk.ijse.projectmp.bo.bo.custom.AccountBO;
import lk.ijse.projectmp.dto.AccountDTO;
import lk.ijse.projectmp.main.StartUp;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class MainController implements Initializable {

    @FXML
    private BorderPane border;

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnPos;

    @FXML
    private JFXButton btnRepair;

    @FXML
    private JFXButton btnStock;

    @FXML
    private JFXButton btnReturn;

    @FXML
    private JFXButton btnCustomer;

    @FXML
    private JFXButton btnReport;

    @FXML
    private JFXButton btnSettings;

    @FXML
    private ImageView image;

    @FXML
    private Label lblUser;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblUIname;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXPasswordField pfPassword;

    @FXML
    private JFXButton btnSwitchUser;

    @FXML
    private JFXButton btnLogout;

    @FXML
    private AnchorPane anchor;

    private Stage mainStage=LoginController.mainStage;

    private AccountDTO user;

    public void clearAndLoadRoot(String fxmlFileName) throws IOException {
        Parent root=FXMLLoader.load(getClass().getResource("/lk/ijse/projectmp/view/"+fxmlFileName+".fxml"));
        anchor.getChildren().clear();
        anchor.getChildren().add(root);
        setAnimation();
    }

    @FXML
    void MenuBtnAction(ActionEvent event) throws IOException {
        JFXButton btn = (JFXButton) event.getSource();

        switch (btn.getId()) {
            case "btnHome":
                clearAndLoadRoot("Home");
                lblUIname.setText("H O M E");
                break;
            case "btnPos":
                clearAndLoadRoot("POS");
                lblUIname.setText("P O S");
                break;
            case "btnRepair":
                clearAndLoadRoot("AddRepair");
                lblUIname.setText("R E P A I R");
                break;
            case "btnStock":
                clearAndLoadRoot("AddStock");
                lblUIname.setText("S T O C K");
                break;
            case "btnReturn":
                clearAndLoadRoot("Returns");
                lblUIname.setText("R E T U R N S");
//                btnSettings.setStyle("-fx-background-color: #f3ff00");
                break;
            case "btnCustomer":
                clearAndLoadRoot("Customer");
                lblUIname.setText("C U S T O M E R");
//                btnSettings.setStyle("-fx-background-color: #f3ff00");
                break;
            case "btnReport":
                clearAndLoadRoot("Report");
                lblUIname.setText("R E P O R T");
                break;
            case "btnSettings":
                clearAndLoadRoot("Settings");
                lblUIname.setText("S E T T I N G S");
//                    btn.setStyle("-fx-background-color: RED");
                break;
            default:clearAndLoadRoot("ErrorView");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDateAndTime();
        try {
            clearAndLoadRoot("Home");
            lblUIname.setText("H O M E");
        } catch (IOException e) {
            e.printStackTrace();
        }
        setAnimation();
    }

//======================================== Animation Start =============================================================
    Timeline rotate=new Timeline();

    public void setAnimation(){
            DoubleProperty r = image.rotateProperty();
            rotate.getKeyFrames().addAll(
                    new KeyFrame(new Duration(0), new KeyValue(r, 0)),
                    new KeyFrame(new Duration(1000), new KeyValue(r, -180))
            );
            rotate.play();
    }
//============================================ Animation End  ==========================================================

    private void loadDateAndTime() {

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY");
        String txtDate = simpleDateFormat.format(date);
        lblDate.setText(txtDate);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                Calendar time = Calendar.getInstance();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss a");
                                lblTime.setText(simpleDateFormat.format(time.getTime()));
                            }
                        }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @FXML
    void btnLogoutAction(ActionEvent event) {
        Parent root;
        try {
            root= FXMLLoader.load(getClass().getResource("/lk/ijse/projectmp/view/Login.fxml"));
            Scene scene=new Scene(root);
            Stage stage=new Stage();
            stage.initStyle(StageStyle.DECORATED.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSwitchUserAction(ActionEvent event) throws IOException {
        String uname = txtUsername.getText();

        if(Pattern.compile("^[\\S][A-z-0-9]{0,10}$").matcher(uname).matches()) {

            String upass=pfPassword.getText();
        System.out.println("Password"+upass);
            new Alert(Alert.AlertType.CONFIRMATION,upass).show();


            boolean b=Pattern.compile("^\\S[A-z-0-9]{4,10}$").matcher(upass).matches();
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION,"Pass").show();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"fail").show();
            }


        }else {
            new Alert(Alert.AlertType.CONFIRMATION,"Failed").show();
        }

    }

    public void setAnchor() throws IOException {
        Parent root=FXMLLoader.load(getClass().getResource("/lk/ijse/projectmp/view/ViewRepair.fxml"));
        anchor.getChildren().clear();
        anchor.getChildren().add(root);
        setAnimation();
    }

    public void setUser(AccountDTO user){
        this.user=user;
        lblUser.setText(this.user.getName());
    }

    public AccountDTO getUser(){
        return user;
    }
}


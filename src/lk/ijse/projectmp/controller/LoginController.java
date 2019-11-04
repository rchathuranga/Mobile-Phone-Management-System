package lk.ijse.projectmp.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
//import dao.SuperDAO;
//import dao.custom.impl.AccountDAOImpl;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.projectmp.bo.BOFactory;
import lk.ijse.projectmp.bo.bo.custom.AccountBO;
import lk.ijse.projectmp.dto.AccountDTO;
import lk.ijse.projectmp.main.StartUp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class LoginController implements Initializable {

    @FXML
    private AnchorPane Up;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private AnchorPane down;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXPasswordField pfPassword;

    @FXML
    private AnchorPane anchorClose;


    @FXML
    private Label lblWelcome;

    public static Stage mainStage;

    AccountBO bo = (AccountBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ACCOUNT);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtUsername.requestFocus();

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(-1);
        dropShadow.setOffsetY(3);
        dropShadow.setColor(Color.BLACK);
        Up.setEffect(dropShadow);
        down.setEffect(dropShadow);
        anchorClose.setEffect(dropShadow);

    }


    @FXML
    void pfPasswordKeyAction(KeyEvent event) {
        String uname = txtUsername.getText();
        AccountDTO account = null;
        try {
            account = bo.searchAccount(uname);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (account != null) {
            if (uname.equalsIgnoreCase(account.getUsername())) {
                lblWelcome.setText("Welcome " + account.getName() + "...!");
            }
        }
    }

    @FXML
    void btnLoginAction(ActionEvent event) throws IOException {
        String uname = txtUsername.getText();

        if (Pattern.compile("^[\\S][A-z-0-9]{0,10}$").matcher(uname).matches()) {
            String upass = pfPassword.getText();

            if (Pattern.compile("^[A-z-0-9]{4,15}$").matcher(upass).matches()) {

                uname = txtUsername.getText();
                AccountDTO account = null;
                try {
                    account = bo.searchAccount(uname);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (account != null) {
                    if (uname.equalsIgnoreCase(account.getUsername()) && upass.equals(account.getPassword())) {
                        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/lk/ijse/projectmp/view/Main.fxml"));
                        Parent root = fxml.load();
                        MainController mainC = fxml.getController();
                        System.out.println(mainC);
                        mainC.setUser(account);

                        Scene scene = new Scene(root);


                        Stage stage = new Stage();
                        mainStage = stage;
                        stage.setMaximized(true);
                        stage.setScene(scene);
                        stage.getIcons().add(new Image("/lk/ijse/projectmp/assets/image/logo-att-color-trans-200x200.png"));
                        stage.setTitle("pandora pvt lmt");
                        stage.show();

                        StartUp.stageLogin.close();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "You Have Entered Wrong Password. And Try Again !", ButtonType.OK).show();
                        pfPassword.requestFocus();
                    }
                } else {
                    new Alert(Alert.AlertType.WARNING, "You Have Entered Wrong User Name", ButtonType.OK).show();
                    txtUsername.requestFocus();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Invalid password..!", ButtonType.OK).show();
                pfPassword.requestFocus();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Invalid username..!", ButtonType.OK).show();
            txtUsername.requestFocus();
        }
    }


    @FXML
    void btnLoginMouseEntered(MouseEvent event) {
        btnLogin.setTextFill(Paint.valueOf("White"));
    }

    @FXML
    void btnLoginMouseExited(MouseEvent event) {
        btnLogin.setTextFill(Paint.valueOf("#4de98f"));
    }

    @FXML
    void closeAction(MouseEvent event) {
        System.exit(1);
    }

    @FXML
    void closeMouseEntered(MouseEvent event) {
        ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), anchorClose);
        scaleT.setToX(1.2);
        scaleT.setToY(1.2);
        scaleT.play();
    }

    @FXML
    void closeMouseExited(MouseEvent event) {
        ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), anchorClose);
        scaleT.setToX(1);
        scaleT.setToY(1);
        scaleT.play();
    }


}

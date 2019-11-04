package lk.ijse.projectmp.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StartUp extends Application {
    public static Stage stageLogin;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stageLogin=primaryStage;
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/projectmp/view/Login.fxml"));
        Scene scene=new Scene(root);
        primaryStage.initStyle(StageStyle.DECORATED.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("/lk/ijse/projectmp/assets/image/logo-att-color-trans-200x200.png"));
        primaryStage.show();
    }
}

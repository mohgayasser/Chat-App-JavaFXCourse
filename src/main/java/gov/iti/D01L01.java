package gov.iti;

import java.sql.SQLException;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

public class D01L01 extends Application {

    @Override
    public void start(Stage primaryStage){
        FXMLLoader loader = new FXMLLoader();
        LoginFXMLController loginController = new LoginFXMLController();
        loader.setController(loginController);
        try {
            Parent root = loader.load(getClass().getClassLoader().getResource("login_scene.fxml").openStream());
            
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
           
            FXMLLoader loader2 = new FXMLLoader();
            Parent secondParent  = loader2.load(getClass().getClassLoader().getResource("chat_room_scene.fxml").openStream());
            ChatRoomFXMLController chatRoomFXMLController = new ChatRoomFXMLController();
            loader2.setController(chatRoomFXMLController);
            
            Scene secondScene = new Scene(secondParent);
            
            loginController.setSecondScene(secondScene , loader2);

            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){

        launch(args);

    }
}
//javac D01L02.java

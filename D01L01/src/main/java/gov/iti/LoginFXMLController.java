package gov.iti;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class LoginFXMLController implements Initializable{

    @FXML
    private Button select_picture_button;
    @FXML
    private Button enter_chat_room_Button;
    @FXML
    private ImageView picture_imageView;
    @FXML
    private Image userImage;
    @FXML
    private TextField username_textField;
    private Scene secondScene;
    private FXMLLoader firstPaneLoader;

    public Image getUserImage() {
        return userImage;
    }

    public LoginFXMLController() {
    }


    @Override
    public void initialize (URL location, ResourceBundle resources){

        select_picture_button.setOnAction((ActionEvent action)->{
            
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Image Files", "*.png","*.jpg")
            );
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            File OpendFile = fileChooser.showOpenDialog(null);
            if(OpendFile != null){
            
                userImage = new Image(OpendFile.toURI().toString(),picture_imageView.getFitWidth(),picture_imageView.getFitHeight(),false,true);
              
                // userImage = new Image(OpendFile.getPath());
                Circle cir = new Circle(75, 75, 75);
                
                picture_imageView.setImage(userImage);
                picture_imageView.setClip(cir);
            }
            
        });
        enter_chat_room_Button.setOnAction((ActionEvent actionEvent) ->OpenSecondScene(actionEvent));

    }


    private void OpenSecondScene(ActionEvent actionEvent) {
        ChatRoomFXMLController chatRoomFXMLController =(ChatRoomFXMLController) firstPaneLoader.getController();
        chatRoomFXMLController.getUserImage(userImage);
        chatRoomFXMLController.getUserName(username_textField.getText());
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(secondScene);
        
    }
    public void setSecondScene(Scene scene ,FXMLLoader firstPaneLoader) {
        secondScene = scene;
        this.firstPaneLoader = firstPaneLoader;

    }

}

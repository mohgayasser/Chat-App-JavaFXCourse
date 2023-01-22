package gov.iti;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginFXMLController implements Initializable{

    @FXML
    private BorderPane main_border_pane;
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
    @FXML
    private VBox messages_vbox;
    
    private Scene secondScene;
    private File OpendFile;
    private boolean hasName=false;

    public Image getUserImage() {
        return userImage;
    }

    public LoginFXMLController() {
    }


    @Override
    public void initialize (URL location, ResourceBundle resources){
        try {
            OpendFile = new File(getClass().getClassLoader().getResource("default_users.png").toURI());
           
        } catch (URISyntaxException e) {
            
            e.printStackTrace();
        }

        select_picture_button.setOnAction((ActionEvent action)->{
            
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Image Files", "*.png","*.jpg")
            );
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            OpendFile = fileChooser.showOpenDialog(null);
            if(OpendFile != null){
            
                userImage = new Image(OpendFile.toURI().toString(),picture_imageView.getFitWidth(),picture_imageView.getFitHeight(),false,true);
              
                Circle cir = new Circle(75, 75, 75);
                
                picture_imageView.setImage(userImage);
                picture_imageView.setClip(cir);
            } else{
                try {
                    OpendFile = new File(getClass().getClassLoader().getResource("default_users.png").toURI());
                   
                } catch (URISyntaxException e) {
                  
                    e.printStackTrace();
                }
            }
            
        });
        
        enter_chat_room_Button.setOnAction((ActionEvent actionEvent) ->OpenSecondScene(actionEvent));
        
    }


    private void OpenSecondScene(ActionEvent actionEvent) {
       //check  user name
         String userNamde = username_textField.getText();
         String regex = "^.*[a-zA-Z0-9]+.*$";
      
        Pattern pattern = Pattern.compile(regex);
      
        Matcher matcher = pattern.matcher(userNamde);
        if (!username_textField.getText().isEmpty() && matcher.matches())
        {
           userNamde = username_textField.getText();
        }else {
             userNamde = "Guest";
        }
        //
        FXMLLoader loader = new FXMLLoader();
        ChatRoomFXMLController chaLoginFXMLController = new ChatRoomFXMLController();
        loader.setController(chaLoginFXMLController);
        try {
            
          chaLoginFXMLController.getUserImage(OpendFile.toURI().toString());
          chaLoginFXMLController.getUserName(userNamde);
          Stage primaryStage = (Stage)enter_chat_room_Button.getScene().getWindow();
          
          Parent secondParent  = loader.load(getClass().getClassLoader().getResource("chat_room_scene.fxml").openStream());
          Scene scene = new Scene(secondParent);
          scene.getStylesheets().add(String.valueOf(getClass().getClassLoader().getResource("styles/style.css" )));
          primaryStage.close();

          primaryStage.setScene(scene);
          primaryStage.setResizable(true);
          primaryStage.setMinHeight(650);
          primaryStage.setMinWidth(430);
          primaryStage.setTitle("JETS Chat Room");
          primaryStage.show();
        }catch(IOException exception){}
        
        
        
    }
   
}

package gov.iti;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ChatRoomFXMLController implements Initializable{
    private String userName;
    
    private Image userImage;
    
    @FXML
    private TextField typing_textField;
    @FXML
    private VBox messages_vbox;
    @FXML
    private ImageView user_picture_imageView;
    @FXML
    private TextField username_label;
    //getImage()
    public ChatRoomFXMLController() {
    }
    
    public void getUserImage(Image I){
        userImage = I;
    }
     public void getUserName(String userName){
        this.userName = userName;
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("hello");

        System.out.println(userImage.toString());   
        System.out.println(userName);

        userImage = new Image(userImage.toString(),user_picture_imageView.getFitWidth(),user_picture_imageView.getFitHeight(),false,true);
              
        Circle cir = new Circle(75, 75, 75);
                
        user_picture_imageView.setImage(userImage);
        user_picture_imageView.setClip(cir);
        username_label.setText(userName);
        // TODO Auto-generated method stub
        typing_textField.setOnAction((ActionEvent action)->{
            HBox newMessage = new HBox();
            ImageView imageIcon = new ImageView();
            imageIcon.setImage(userImage); 
            imageIcon.resize(20, 20);
            newMessage.getChildren().add(imageIcon);
            
            TextFlow textFlow = new TextFlow();
            Text text = new Text(typing_textField.getText());
            textFlow.getChildren().add(text);
            newMessage.getChildren().add(textFlow);
            messages_vbox.getChildren().add(newMessage);
            
        });
    }
}

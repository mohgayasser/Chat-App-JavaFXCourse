package gov.iti;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ChatRoomFXMLController implements Initializable{
    private String userName;
    private Image image;
    private String userImage;
    
    @FXML
    private TextField typing_textField;
    @FXML
    private VBox messages_vbox;
    @FXML
    private ImageView user_picture_imageView;
    @FXML
    private Label username_label;
    @FXML
    private ScrollPane messages_scroll_pane;
    //getImage()
    public ChatRoomFXMLController() {
    }
    
    public void getUserImage(String I){
        userImage = I;
    }
     public void getUserName(String userName){
        this.userName = userName;
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        image = new Image(userImage,120,120,false,true);
              
        Circle cir = new Circle(60, 60, 60);
                
        user_picture_imageView.setImage(image);
        user_picture_imageView.setClip(cir);
        username_label.setText(userName);
       
        typing_textField.setOnAction((ActionEvent action)->{
            
            String userNamde = typing_textField.getText();
            String regex = "^.*[a-zA-Z0-9]+.*$";

            Pattern pattern = Pattern.compile(regex);

            Matcher matcher = pattern.matcher(userNamde);
           if (!typing_textField.getText().isEmpty() && matcher.matches())
           {
           
           
            
               
            HBox newMessage = new HBox();
            ImageView imageIcon = new ImageView();
            
            imageIcon.setImage(new Image(userImage,30,30,false,true)); 
            Circle circle = new Circle(15, 15, 15);
            imageIcon.setClip(circle);
            
            newMessage.getChildren().add(imageIcon);
            
            TextFlow textFlow = new TextFlow();
            textFlow.setStyle("-fx-background-color: orange; -fx-padding: 5px;-fx-border-insets: 5px;-fx-background-insets: 5px; -fx-border-radius: 15 15 15 0; -fx-background-radius: 15 15 15 0;");

            Text text = new Text(typing_textField.getText());
            typing_textField.setText("");
            text.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
            
            textFlow.getChildren().add(text);
            
            newMessage.getChildren().add(textFlow);
            newMessage.setFillHeight(true);
            System.out.println(textFlow.getHeight());
           
            messages_vbox.getChildren().add(newMessage);
           
            System.out.println("Hello in enter");
           }
           else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("You need to enter a message");
                alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
           }
            
        });
    }
}

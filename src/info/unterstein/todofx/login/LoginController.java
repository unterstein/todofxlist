package info.unterstein.todofx.login;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

  @FXML
  private Button login;

  @FXML
  private TextField userName;

  @FXML
  private PasswordField password;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    login.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent mouseEvent) {
        System.out.println(userName.getText() + " : " + password.getText());
      }
    });
  }
}

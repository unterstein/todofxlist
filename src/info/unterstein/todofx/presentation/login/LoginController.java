package info.unterstein.todofx.presentation.login;

import info.unterstein.todofx.business.boundary.TodoListService;
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
        String userNameValue = userName.getText();
        String passwordValue = password.getText();
        // validation
        if (validate(userNameValue, passwordValue) == false) {
          // TODO signalize errors
          return;
        }
        // persistence
        TodoListService.instance().login(userNameValue, passwordValue);
      }
    });
  }

  private boolean validate(String userName, String password) {
    if (userName == null || userName.isEmpty() || userName.length() < 4) {
      return false;
    }
    if (password == null || password.isEmpty() || userName.length() < 4) {
      return false;
    }
    return true;
  }
}

package info.unterstein.todofx;

import info.unterstein.todofx.login.LoginView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    // start View
    LoginView view = new LoginView();
    primaryStage.setTitle("Hello World");
    primaryStage.setScene(new Scene(view.getView(), 300, 275));
    primaryStage.show();
  }
}

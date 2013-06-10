package info.unterstein.todofx;

import info.unterstein.todofx.presentation.login.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    // start View
    LoginView view = new LoginView();
    primaryStage.setTitle("Hello World");
    primaryStage.setScene(new Scene(view.getView()));
    primaryStage.show();
  }
}

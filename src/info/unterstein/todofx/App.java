package info.unterstein.todofx;

import info.unterstein.todofx.presentation.AbstractView;
import info.unterstein.todofx.presentation.login.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

  private static Stage primaryStage;

  public static void main(String[] args) {
    launch(args);
  }

  public static void initAndStartView(AbstractView view) {
    // start View
    primaryStage.setTitle(view.getTitle());
    Scene loginScene = new Scene(view.getView());
    primaryStage.setScene(loginScene);
    primaryStage.show();
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    this.primaryStage = primaryStage;
    initAndStartView(new LoginView());
  }
}

package info.unterstein.todofx;

import info.unterstein.todofx.business.boundary.TodoListService;
import info.unterstein.todofx.presentation.AbstractView;
import info.unterstein.todofx.presentation.login.LoginView;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class App extends Application {

  private static Stage primaryStage;

  public static void main(String[] args) {
    launch(args);
  }

  public static void initAndStartView(AbstractView view) {
    if (primaryStage != null) {
      // start View
      primaryStage.setTitle(view.getTitle());
      Scene scene = new Scene(view.getView());
      primaryStage.setScene(scene);
      primaryStage.show();
    }
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    this.primaryStage = primaryStage;
    this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

      @Override
      public void handle(WindowEvent windowEvent) {
        TodoListService.instance().storeDatabase();
      }
    });
    TodoListService.instance().loadDatabase();
    initAndStartView(new LoginView());
  }
}

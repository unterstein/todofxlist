package info.unterstein.todofx.presentation;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import java.net.URL;

public abstract class AbstractView {

  private final Class<? extends Initializable> controller;

  protected FXMLLoader loader;

  private String title;

  protected AbstractView(Class<? extends Initializable> controller, String title) {
    this.controller = controller;
    this.title = title;
    init(getClass(), getFXMLName());
  }

  private void init(Class<? extends AbstractView> aClass, String fxmlName) {
    final URL resource = aClass.getResource(fxmlName);
    loader = new FXMLLoader(resource);
    try {
      loader.setController(controller.newInstance());
      loader.load();
    } catch (Exception e) {
      e.printStackTrace(); // TODO
    }
  }

  public Parent getView() {
    return this.loader.getRoot();
  }

  private String getFXMLName() {
    String name = this.getClass().getSimpleName().toLowerCase();
    name = name.replace("view", "") + ".fxml";
    return name;
  }

  public String getTitle() {
    return title;
  }
}

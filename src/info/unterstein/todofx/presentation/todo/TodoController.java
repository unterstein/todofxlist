package info.unterstein.todofx.presentation.todo;

import info.unterstein.todofx.business.boundary.TodoListService;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class TodoController implements Initializable {

  @FXML
  private Button create;

  @FXML
  private TextField newList;

  @FXML
  private ListView taskList;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    updateList();
    create.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent mouseEvent) {
        String newListName = newList.getText();
        TodoListService.instance().getLoggedInUser().addList(newListName);
        updateList();
        newList.setText("");
      }
    });
  }

  private void updateList() {
    taskList.setItems(FXCollections.observableList(TodoListService.instance().getLoggedInUser().getLists()));
  }

}

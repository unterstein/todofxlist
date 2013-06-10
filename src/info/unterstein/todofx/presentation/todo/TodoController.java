package info.unterstein.todofx.presentation.todo;

import com.sun.javafx.scene.control.ReadOnlyUnbackedObservableList;
import info.unterstein.todofx.business.boundary.TodoListService;
import info.unterstein.todofx.business.entity.TodoList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
    ObservableList<TodoList> todoLists = FXCollections.observableList(TodoListService.instance().getLoggedInUser().getLists());
    taskList.setItems(todoLists);
  }


}

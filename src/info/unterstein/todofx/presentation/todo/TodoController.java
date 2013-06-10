package info.unterstein.todofx.presentation.todo;

import info.unterstein.todofx.business.boundary.TodoListService;
import info.unterstein.todofx.business.entity.TodoList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
  private Button createList;

  @FXML
  private TextField newList;

  @FXML
  private ListView<TodoList> taskList;

  @FXML
  private Button deleteList;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    updateList();

    createList.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent mouseEvent) {
        String newListName = newList.getText();
        TodoListService.instance().getLoggedInUser().addList(newListName);
        updateList();
        newList.setText("");
      }
    });
    deleteList.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent mouseEvent) {
        ObservableList<TodoList> selectedItems = taskList.getSelectionModel().getSelectedItems();
        for (TodoList todoList : selectedItems) {
          if (TodoListService.instance().getLoggedInUser().getDefaultList().equals(todoList) == false) {
            TodoListService.instance().getLoggedInUser().removeList(todoList);
          }
        }
        updateList();
      }
    });
  }

  private void updateList() {
    taskList.setItems(FXCollections.observableList(TodoListService.instance().getLoggedInUser().getLists()));
  }

}

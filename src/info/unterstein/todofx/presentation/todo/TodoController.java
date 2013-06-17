package info.unterstein.todofx.presentation.todo;

import info.unterstein.todofx.App;
import info.unterstein.todofx.business.boundary.TodoListService;
import info.unterstein.todofx.business.entity.Task;
import info.unterstein.todofx.business.entity.TodoList;
import info.unterstein.todofx.presentation.login.LoginView;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class TodoController implements Initializable {

  @FXML
  private Button logout;

  @FXML
  private Button createList;

  @FXML
  private TextField newList;

  @FXML
  private ListView<TodoList> todoListList;

  @FXML
  private ListView<Task> taskList;

  @FXML
  private Button deleteList;

  @FXML
  private Button createTask;

  @FXML
  private TextField newTask;

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
        TodoList selectedItem = todoListList.getSelectionModel().getSelectedItem();
        TodoList defaultList = TodoListService.instance().getLoggedInUser().getDefaultList();
        if (defaultList.equals(selectedItem) == false) {
          TodoListService.instance().setSelectedList(defaultList);
          todoListList.getSelectionModel().select(defaultList);
          TodoListService.instance().getLoggedInUser().removeList(selectedItem);
        }
        updateList();
      }
    });
    logout.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent mouseEvent) {
        TodoListService.instance().logout();
        App.initAndStartView(new LoginView());
      }
    });
    todoListList.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent mouseEvent) {
        updateList();
      }
    });
    todoListList.setOnKeyReleased(new EventHandler<KeyEvent>() {

      @Override
      public void handle(KeyEvent keyEvent) {
        updateList();
      }
    });
    createTask.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent mouseEvent) {
        String newTaskText = newTask.getText();
        TodoListService.instance().getSelectedList().addTask(newTaskText);
        updateList();
      }
    });
  }

  private void updateList() {
    TodoList defaultList = TodoListService.instance().getLoggedInUser().getDefaultList();
    TodoList selectedItem = todoListList.getSelectionModel().getSelectedItem();
    if (selectedItem == null) {
      TodoListService.instance().setSelectedList(defaultList);
    } else {
      TodoListService.instance().setSelectedList(selectedItem);
    }
    if (TodoListService.instance().getLoggedInUser().getDefaultList().equals(selectedItem)) {
      deleteList.setDisable(true);
    } else {
      deleteList.setDisable(false);
    }

    taskList.setItems(FXCollections.observableList(TodoListService.instance().getSelectedList().getTasks()));
    todoListList.setItems(FXCollections.observableList(TodoListService.instance().getLoggedInUser().getLists()));
    todoListList.getSelectionModel().select(TodoListService.instance().getSelectedList());

  }

}

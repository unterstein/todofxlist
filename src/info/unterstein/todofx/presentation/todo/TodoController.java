package info.unterstein.todofx.presentation.todo;

import info.unterstein.todofx.App;
import info.unterstein.todofx.business.boundary.TodoListService;
import info.unterstein.todofx.business.entity.Task;
import info.unterstein.todofx.business.entity.TaskList;
import info.unterstein.todofx.presentation.login.LoginView;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

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
  private ListView<TaskList> todoListList;

  @FXML
  private ListView<Task> taskList;

  @FXML
  private Button deleteList;

  @FXML
  private Button createTask;

  @FXML
  private TextField newTask;

  @FXML
  private Button deleteTask;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    updateList();

    initTaskList();

    initTaskArea();
  }

  private void initTaskArea() {
    createTask.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent mouseEvent) {
        String newTaskText = newTask.getText();
        TodoListService.instance().getSelectedList().addTask(newTaskText);
        updateList();
      }
    });
    taskList.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {

      @Override
      public ListCell<Task> call(ListView<Task> taskListView) {
        TaskCell cell = new TaskCell();
        cell.setOnMouseClicked(new EventHandler<MouseEvent>() {

          @Override
          public void handle(MouseEvent mouseEvent) {
            // TODO
          }
        });
        return cell;
      }
    });
    deleteTask.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent mouseEvent) {
        Task selectedItem = taskList.getSelectionModel().getSelectedItem();
        TodoListService.instance().getSelectedList().removeTask(selectedItem);
        updateList();
      }
    });
  }

  private void initTaskList() {
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
        TaskList selectedItem = todoListList.getSelectionModel().getSelectedItem();
        TaskList defaultList = TodoListService.instance().getLoggedInUser().getDefaultList();
        if (defaultList.equals(selectedItem) == false) {
          TodoListService.instance().setSelectedList(defaultList);
          todoListList.getSelectionModel().select(defaultList);
          TodoListService.instance().getLoggedInUser().removeList(selectedItem);
        }
        updateList();
      }
    });
    // todoListList selection changes
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
    // logout
    logout.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent mouseEvent) {
        TodoListService.instance().logout();
        App.initAndStartView(new LoginView());
      }
    });
  }

  private void updateList() {
    TaskList defaultList = TodoListService.instance().getLoggedInUser().getDefaultList();
    TaskList selectedItem = todoListList.getSelectionModel().getSelectedItem();
    if (selectedItem == null) {
      selectedItem = defaultList;
    }

    TodoListService.instance().setSelectedList(selectedItem);
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

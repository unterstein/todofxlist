package info.unterstein.todofx.presentation.todo;

import info.unterstein.todofx.App;
import info.unterstein.todofx.business.boundary.TodoListService;
import info.unterstein.todofx.business.entity.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class TaskDetailController implements Initializable {

  @FXML
  private TextField title;

  @FXML
  private TextArea description;

  @FXML
  private TextField dueDate;

  @FXML
  private CheckBox finished;

  @FXML
  private CheckBox prioritized;

  @FXML
  private Button back;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    final Task currentTask = TodoListService.instance().getSelectedTask();
    title.setText(currentTask.getTitle());
    description.setText(currentTask.getDescription());
    if (currentTask.getDueDate() != null) {
      dueDate.setText(TodoListService.SDF.format(currentTask.getDueDate()));
    }
    finished.setSelected(currentTask.isFinished());
    prioritized.setSelected(currentTask.isPrioritized());
    back.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent mouseEvent) {
        currentTask.setDescription(description.getText());
        currentTask.setTitle(title.getText());
        currentTask.setFinished(finished.isSelected());
        currentTask.setPrioritized(prioritized.isSelected());
        if (dueDate.getText() != null && "".equals(dueDate.getText()) == false) {
          try {
            currentTask.setDueDate(TodoListService.SDF.parse(dueDate.getText()));
          } catch (Exception e) {
            e.printStackTrace(); // TODO
          }
        }
        App.initAndStartView(new TodoView());
      }
    });
  }
}

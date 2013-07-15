package info.unterstein.todofx.presentation.todo;

import info.unterstein.todofx.business.boundary.TodoListService;
import info.unterstein.todofx.business.entity.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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

  private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    Task currentTask = TodoListService.instance().getSelectedTask();
    title.setText(currentTask.getTitle());
    description.setText(currentTask.getDescription());
    if (currentTask.getDueDate() != null) {
      dueDate.setText(sdf.format(currentTask.getDueDate()));
    }
    finished.setSelected(currentTask.isFinished());
    prioritized.setSelected(currentTask.isPrioritized());
  }
}

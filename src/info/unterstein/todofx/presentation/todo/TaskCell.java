package info.unterstein.todofx.presentation.todo;

import info.unterstein.todofx.business.entity.Task;
import javafx.scene.control.ListCell;

public class TaskCell extends ListCell<Task> {

  @Override
  protected void updateItem(Task item, boolean empty) {
    super.updateItem(item, empty);
    if (item != null) {
      setText(item.getTitle());
    }
  }
}

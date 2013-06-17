package info.unterstein.todofx.presentation.todo;

import info.unterstein.todofx.business.entity.Task;
import javafx.scene.control.ListCell;
import javafx.scene.control.OverrunStyle;

public class TaskCell extends ListCell<Task> {

  public TaskCell() {
    setTextOverrun(OverrunStyle.ELLIPSIS);
  }

  @Override
  protected void updateItem(Task item, boolean empty) {
    super.updateItem(item, empty);
    if (empty == false) {
      setText(item.getTitle());
    }
  }
}

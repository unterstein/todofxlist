package info.unterstein.todofx.presentation.todo;

import info.unterstein.todofx.business.entity.Task;
import javafx.beans.binding.StringBinding;
import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.control.OverrunStyle;
import javafx.scene.input.MouseEvent;

public class TaskCell extends ListCell<Task> {

  public TaskCell() {
    setTextOverrun(OverrunStyle.ELLIPSIS);
  }

  @Override
  protected void updateItem(final Task item, boolean empty) {
    super.updateItem(item, empty);
    if (empty == false) {
      textProperty().bind(new StringBinding() {

        @Override
        protected String computeValue() {
          return item.getTitle() + ", finished: " + item.isFinished() + ", prioritized: " + item.isPrioritized();
        }
      });
    }
  }
}

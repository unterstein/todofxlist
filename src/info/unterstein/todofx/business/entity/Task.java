package info.unterstein.todofx.business.entity;


import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {

  private String title;

  private String description;

  private boolean finished;

  private boolean prioritized;

  private Date dueDate;


  public Task(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isFinished() {
    return finished;
  }

  public void setFinished(boolean finished) {
    this.finished = finished;
  }

  public boolean isPrioritized() {
    return prioritized;
  }

  public void setPrioritized(boolean prioritized) {
    this.prioritized = prioritized;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }
}

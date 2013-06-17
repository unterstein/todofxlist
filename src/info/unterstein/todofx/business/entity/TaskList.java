package info.unterstein.todofx.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements Serializable {

  private String name;

  private List<Task> tasks;

  public TaskList(String name) {
    this.name = name;
    tasks = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Task> getTasks() {
    return tasks;
  }

  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }

  public Task addTask(String taskName) {
    Task task = new Task(taskName);
    tasks.add(task);
    return task;
  }

  @Override
  public String toString() {
    return getName();
  }
}

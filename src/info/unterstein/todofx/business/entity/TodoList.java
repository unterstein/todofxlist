package info.unterstein.todofx.business.entity;

import java.io.Serializable;
import java.util.List;

public class TodoList implements Serializable {

  private String name;

  private List<Task> tasks;
}

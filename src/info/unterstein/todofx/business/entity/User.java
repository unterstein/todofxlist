package info.unterstein.todofx.business.entity;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

  private String name;

  private String password;

  private List<TodoList> lists;

  public User() {
  }
}

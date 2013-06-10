package info.unterstein.todofx.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

  private static final String DEFAULT_LIST_NAME = "Inbox";

  private String name;

  private String password;

  private List<TodoList> lists;

  public User() {
    lists = new ArrayList<TodoList>();
    lists.add(new TodoList(DEFAULT_LIST_NAME));
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<TodoList> getLists() {
    return lists;
  }

  public void setLists(List<TodoList> lists) {
    this.lists = lists;
  }
}

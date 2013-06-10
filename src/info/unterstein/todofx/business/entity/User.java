package info.unterstein.todofx.business.entity;

import info.unterstein.todofx.business.boundary.I18nService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

  private String name;

  private String password;

  private List<TodoList> lists;

  public User(String name, String password) {
    this.name = name;
    this.password = password;
    lists = new ArrayList();
    lists.add(new TodoList(I18nService.getTodoListDefaultName()));
  }

  /**
   * password compare, could be hashed as well!
   *
   * @param password
   * @return
   */
  public boolean comparePassword(String password) {
    if (this.password == null || password == null) {
      return false;
    }
    return this.password.equals(password);
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

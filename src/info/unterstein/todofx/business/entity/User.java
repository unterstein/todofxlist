package info.unterstein.todofx.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

  private static final String DEFAULT_LIST_NAME = "Inbox";

  private String name;

  private String password;

  private List<TodoList> lists;


  public User(String name, String password) {
    this.name = name;
    this.password = password;
    lists = new ArrayList();
  }

  public static User createUser(String name, String password) {
    User result = new User(name, password);
    result.lists.add(new TodoList(DEFAULT_LIST_NAME));
    return result;
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

  public TodoList addList(String listName) {
    TodoList todoList = new TodoList(listName);
    lists.add(todoList);
    return todoList;
  }

  public void removeList(TodoList item) {
    if (lists.get(0).equals(item) == false) {
      lists.remove(item);
    }
  }

  public TodoList getDefaultList() {
    return lists.get(0);
  }
}

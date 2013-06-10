package info.unterstein.todofx.business.boundary;

import info.unterstein.todofx.business.entity.User;

import java.util.Map;

/**
 *
 */
public class TodoListService {

  private static final TodoListService instance = new TodoListService();

  private Map<String, User> users;

  private TodoListService() {
    users = PersistenceService.instance().loadUsers();
  }

  public static TodoListService instance() {
    return instance;
  }

  public User register(String userName, String password) {

    return null;
  }
}

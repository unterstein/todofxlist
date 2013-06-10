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

  public boolean userNameExists(String userName) {
    if (userName == null || userName.isEmpty()) {
      return false;
    }
    return users.containsKey(userName);
  }

  public User login(String userName, String password) {
    User dbUser = users.get(userName);
    if (dbUser == null) { // semantically equals to users.containsKey(userName)
      return null;
    }
    if (dbUser.comparePassword(password) == true) {
      return dbUser;
    }
    return null;
  }

  public User register(String userName, String password) throws IllegalArgumentException {
    if (users.containsKey(userName) == true) {
      throw new IllegalArgumentException();
    }
    User user = new User(userName, password);
    users.put(userName, user);
    return user;
  }
}

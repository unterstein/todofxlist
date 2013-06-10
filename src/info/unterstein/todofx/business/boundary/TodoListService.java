package info.unterstein.todofx.business.boundary;

import info.unterstein.todofx.business.entity.User;

import java.util.Map;

/**
 * TodoListService as singleton<br/>
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

  public static boolean validateEquals(String passwordValue, String rePasswordValue) {
    if (passwordValue == null || rePasswordValue == null) {
      return false;
    }
    return passwordValue.equals(rePasswordValue);
  }

  public static boolean validateInputs(String userName, String password) {
    if (userName == null || userName.isEmpty() || userName.length() < 4) {
      return false;
    }
    if (password == null || password.isEmpty() || userName.length() < 4) {
      return false;
    }
    return true;
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

  public User register(String userName, String password, String rePassword) throws IllegalArgumentException {
    if (users.containsKey(userName) == true) {
      throw new IllegalArgumentException();
    }
    User user = new User(userName, password);
    users.put(userName, user);
    return user;
  }
}

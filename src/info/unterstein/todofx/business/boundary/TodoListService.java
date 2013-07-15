package info.unterstein.todofx.business.boundary;

import info.unterstein.todofx.business.entity.TaskList;
import info.unterstein.todofx.business.entity.User;

import java.util.Map;

/**
 * TodoListService as singleton<br/>
 */
public class TodoListService {

  private static final TodoListService instance = new TodoListService();

  private Map<String, User> users;

  private TaskList selectedList;

  private User loggedInUser;

  private TodoListService() {
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

  public void storeDatabase() {
    PersistenceService.instance().saveUsers(users);
  }

  public void loadDatabase() {
    users = PersistenceService.instance().loadUsers();
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
      loggedInUser = dbUser;
      selectedList = dbUser.getDefaultList();
      return dbUser;
    }
    return null;
  }

  public User register(String userName, String password, String rePassword) throws IllegalArgumentException {
    if (users.containsKey(userName) == true || validateEquals(password, rePassword) == false) {
      throw new IllegalArgumentException();
    }
    User user = User.createUser(userName, password);
    users.put(userName, user);
    loggedInUser = user;
    selectedList = user.getDefaultList();
    return user;
  }

  public void logout() {
    loggedInUser = null;
  }

  public User getLoggedInUser() {
    return loggedInUser;
  }

  public TaskList getSelectedList() {
    return selectedList;
  }

  public void setSelectedList(TaskList selectedList) {
    this.selectedList = selectedList;
  }
}

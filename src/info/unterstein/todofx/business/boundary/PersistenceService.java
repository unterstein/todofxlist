package info.unterstein.todofx.business.boundary;

import info.unterstein.todofx.business.entity.Task;
import info.unterstein.todofx.business.entity.TaskList;
import info.unterstein.todofx.business.entity.User;

import java.io.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Persistence service as singleton.<br/>
 * This service should be only visible to package classes.
 */
class PersistenceService {

  private static final PersistenceService instance = new PersistenceService();

  private static final String USER = "User;";

  private static final String LIST = "List;";

  private static final String TASK = "Task;";

  private static final String SEP = System.getProperty("line.separator");

  private static final File FILE = new File(System.getProperty("user.home") + "/todo.db");

  private PersistenceService() {
  }

  public static PersistenceService instance() {
    return instance;
  }

  public void saveUsers(Map<String, User> users) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE))) {
      for (User user : users.values()) {
        writer.write(USER + " " + user.getName() + ", " + user.getPassword() + SEP);
        for (TaskList list : user.getLists()) {
          writer.write(LIST + " " + list.getName() + SEP);
          for (Task task : list.getTasks()) {
            writer.write(TASK + " " + task.toSerializeString() + SEP);
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Map<String, User> loadUsers() {
    Map<String, User> result = new HashMap<>();
    String line = null;
    String[] split = null;
    User currentUser = null;
    TaskList currentList = null;
    try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
      while ((line = reader.readLine()) != null) {
        if (line.startsWith(USER)) {
          split = line.replace(USER + " ", "").split(",");
          currentUser = new User(split[0].trim(), split[1].trim());
          result.put(currentUser.getName(), currentUser);
        } else if (line.startsWith(LIST)) {
          if (currentUser == null) {
            throw new Exception(); // TODO
          }
          currentList = currentUser.addList(line.replace(LIST + " ", "").trim());
        } else if (line.startsWith(TASK)) {
          if (currentUser == null || currentList == null) {
            throw new Exception(); // TODO
          }
          split = line.replace(TASK + " ", "").split(",");
          Task task = currentList.addTask(split[0].trim());
          task.setDescription(split[1].trim());
          task.setFinished(Boolean.valueOf(split[2].trim()));
          task.setPrioritized(Boolean.valueOf(split[3].trim()));
          String date = split[4].trim();
          if (date != null && "null".equals(date) == false) {
            try {
              task.setDueDate(TodoListService.SDF.parse(date));
            } catch (Exception e) {
              e.printStackTrace(); // TODO
            }
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }
}

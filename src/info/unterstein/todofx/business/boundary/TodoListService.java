package info.unterstein.todofx.business.boundary;

/**
 *
 */
public class TodoListService {

  private static final TodoListService instance = new TodoListService();

  private TodoListService() {
  }

  public static TodoListService instance() {
    return instance;
  }
}

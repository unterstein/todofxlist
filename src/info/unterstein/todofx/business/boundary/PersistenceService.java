package info.unterstein.todofx.business.boundary;

import info.unterstein.todofx.business.entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Persistence service as singleton.<br/>
 * This service should be only visible to package classes.
 */
class PersistenceService {

  private static final PersistenceService instance = new PersistenceService();

  private PersistenceService() {
  }

  public static PersistenceService instance() {
    return instance;
  }

  public Map<String, User> loadUsers() {
    return new HashMap<>();
  }
}

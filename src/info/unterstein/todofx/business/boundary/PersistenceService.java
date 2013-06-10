package info.unterstein.todofx.business.boundary;

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
}

package integration.store;

/** Simple storage abstraction for informational log messages. */
public interface InfoStore {
  void save(String message);
}

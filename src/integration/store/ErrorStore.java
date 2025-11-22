package integration.store;

/** Simple storage abstraction for error log messages. */
public interface ErrorStore {
  void save(String message);
}

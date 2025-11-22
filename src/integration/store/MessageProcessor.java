package integration.store;

import java.util.Objects;

/** Processes log messages and routes them to either the info or error store based on the prefix. */
public class MessageProcessor {
  private final InfoStore infoStore;
  private final ErrorStore errorStore;

  public MessageProcessor(final InfoStore infoStore, final ErrorStore errorStore) {
    this.infoStore = Objects.requireNonNull(infoStore, "infoStore cannot be null");
    this.errorStore = Objects.requireNonNull(errorStore, "errorStore cannot be null");
  }

  /**
   * Routes the message to the appropriate store based on the prefix.
   *
   * @param message the log message to process
   */
  public void process(final String message) {
    if (message == null || message.isBlank()) {
      throw new IllegalArgumentException("Message cannot be blank");
    }

    if (message.startsWith("[INFO]")) {
      infoStore.save(message);
    } else if (message.startsWith("[ERROR]")) {
      errorStore.save(message);
    }
    // WARN/other messages are ignored for this example
  }
}

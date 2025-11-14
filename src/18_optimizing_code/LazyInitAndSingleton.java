import java.util.HashMap;
import java.util.Map;

/**
 * Recaps the DataCamp lesson on lazy initialization and singletons.
 *
 * <ul>
 *   <li>Delay expensive creation until something actually needs it (lazy database client below).
 *   <li>Centralize shared resources behind thread-safe singleton accessors (logging config).
 * </ul>
 */
public final class LazyInitAndSingleton {

  private LazyInitAndSingleton() {}

  /**
   * Simple demo: touch the database (triggering lazy init) and grab the singleton log manager.
   *
   * @param args ignored command-line arguments
   */
  public static void main(String[] args) {
    Database database = new Database();
    database.ensureConnection();

    LogManager logManager = LogManager.getInstance();
    logManager.setConfig("level", "INFO");
    System.out.println("LogManager config snapshot: " + logManager.describeConfig());
  }

  /**
   * Represents the "lazy Redis cache" portion of the lesson. Connecting is assumed to cost ~500 ms,
   * so we defer it until someone calls {@link #getClient}. The synchronized getter keeps multiple
   * services (User, Payment, Order) from creating redundant clients.
   */
  private static final class Database {
    private DatabaseClient client;

    public synchronized DatabaseClient getClient() {
      if (client == null) {
        client = new DatabaseClient();
        client.connect("https://our-database.com");
      }
      return client;
    }

    public void ensureConnection() {
      getClient();
    }
  }

  /**
   * Stubbed-out remote client; imagine the real Redis/Jedis client. `connect` stands in for the
   * expensive network handshake described in the transcript.
   */
  private static final class DatabaseClient {

    public void connect(final String connectionUrl) {
      System.out.println("Connecting to " + connectionUrl);
    }
  }

	//////////////////////////////////

  /**
   * Singleton log manager mirrors the "one shared Redis cache" solution. Key takeaways:
   *
   * <ul>
   *   <li>Private constructor prevents direct instantiation (everyone must call getInstance()).
   *   <li>Synchronized accessor guarantees only one instance even under concurrency.
   *   <li>Shared config lives centrally so services reuse it instead of reloading.
   * </ul>
   */
  private static final class LogManager {
    private static LogManager instance;
    private final Map<String, String> logConfig;

    private LogManager() {
      logConfig = new HashMap<>();
    }

    public static synchronized LogManager getInstance() {
      if (instance == null) {
        instance = new LogManager();
      }
      return instance;
    }

    public synchronized void setConfig(final String key, final String value) {
      logConfig.put(key, value);
    }

    public synchronized String describeConfig() {
      return logConfig.toString();
    }
  }
}

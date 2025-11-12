import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Shows two common thread-handling patterns you actually use in production: a fixed thread pool for
 * chunking work and a CompletableFuture pipeline for async orchestration. Both highlight that you
 * almost never hand-roll Thread instances once the platform can manage lifecycles for you.
 *
 * <p>Layperson comparison vs. JavaScript:
 *
 * <ul>
 *   <li>JS is single-threaded at the language level; async code cooperatively yields back to the
 *       event loop so nothing blocks. Workers exist but are opt-in—much like Java’s parallel
 *       streams/executors.
 *   <li>Java’s model is multi-threaded by default, so blocking calls are fine as long as you use
 *       managed pools. The demos below are the “real-world” equivalents of JS event-loop tasks: one
 *       uses an executor (like handing work to worker threads), the other chains async tasks via
 *       CompletableFuture (similar to JS promises).
 * </ul>
 */
public class ThreadExecutorExample {

  public static void main(String[] args) throws InterruptedException {
    runFixedThreadPoolExample();
    runCompletableFutureExample();
  }

  // Takeaway: use ExecutorService for batches of independent tasks; it reuses a bounded set of
  // threads so you don’t oversubscribe the CPU.
  private static void runFixedThreadPoolExample() throws InterruptedException {
    List<String> documents = List.of("Doc1", "Doc2", "Doc3", "Doc4", "Doc5");
    List<Future<String>> futures = new ArrayList<>();

    // Executors.newFixedThreadPool(..) to create a fixed thread pool
    ExecutorService executor = Executors.newFixedThreadPool(3);

    for (String doc : documents) {
      // Submit the processDocument() method to the executor
      futures.add(executor.submit(() -> processDocument(doc)));
    }

    // executor.shutdown() and .awaitTermination() to shutdown the executor and wait for termination
    executor.shutdown();
    executor.awaitTermination(10, TimeUnit.SECONDS);

    for (Future<String> future : futures) {
      try {
        System.out.println(future.get());
      } catch (ExecutionException executionException) {
        System.out.println("Error processing documents: " + executionException.getMessage());
      }
    }
  }

  private static String processDocument(final String docId) throws InterruptedException {
    System.out.println("Processing " + docId + " on thread " + Thread.currentThread().getName());
    Thread.sleep((long) (Math.random() * 1000));
    return docId + " Processed";
  }

  // Takeaway: CompletableFuture pipelines shine when you need asynchronous orchestration without
  // blocking a thread for the entire call chain.
  private static void runCompletableFutureExample() {
    CompletableFuture<UserSummary> userSummaryFuture =
        CompletableFuture.supplyAsync(() -> fetchUserData("user123"))
            .thenApply(ThreadExecutorExample::createSummary)
            .exceptionally(
                ex -> {
                  System.out.println("Falling back due to: " + ex.getMessage());
                  return new UserSummary(new UserData("error", "Error User"));
                });

    UserSummary summary = userSummaryFuture.join();
    System.out.println(summary.describe());
  }

  private static UserData fetchUserData(final String userId) {
    simulateNetworkLatency(300);
    return new UserData(userId, "John Doe");
  }

  private static UserSummary createSummary(final UserData userData) {
    simulateNetworkLatency(200);
    return new UserSummary(userData);
  }

  private static void simulateNetworkLatency(final int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException interruptedException) {
      Thread.currentThread().interrupt();
    }
  }

  /** Simple user record fetched from downstream services. */
  private static final class UserData {
    private final String id;
    private final String name;

    private UserData(final String id, final String name) {
      this.id = id;
      this.name = name;
    }

    private String describe() {
      return id + " (" + name + ")";
    }
  }

  /** Result object combining multiple pieces of user info. */
  private static final class UserSummary {
    private final UserData userData;

    private UserSummary(final UserData userData) {
      this.userData = userData;
    }

    private String describe() {
      return "Summary for " + userData.describe();
    }
  }
}

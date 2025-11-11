import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** Minimal manual threading demo to show how CPU-bound work can be split across cores. */
public class MultiThreadExample {
  private static double cpuSink;

  public static void main(String[] args) throws InterruptedException {
    List<Transaction> transactions = multiThreadDemo();
    // Summarize the work so SpotBugs sees the results consumed, and we can sanity-check the math.
    double totalProcessed = transactions.stream().mapToDouble(tx -> tx.result).sum();
    System.out.println("Processed transactions total result: " + totalProcessed);

    multiThreadDemo2();
    System.out.println("Synthetic CPU sink (keeps work from being optimized away): " + cpuSink);
  }

  // Why threads? Splitting CPU-heavy work across cores can shorten end-to-end time.
  // We wire them up manually here purely to show the moving parts—
  // production code would almost always lean on an ExecutorService, ForkJoinPool,
  // virtual threads, etc., so the platform manages the thread pool lifecycle for us.
  @SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
  private static List<Transaction> multiThreadDemo() throws InterruptedException {
    List<Transaction> transactions = generateTransactions(1000);
    List<Thread> threads = new ArrayList<>();
    int threadCount = 4;
    int chunkSize = (int) Math.ceil(transactions.size() / (double) threadCount);
    for (int t = 0; t < threadCount; t++) {
      int start = t * chunkSize;
      int end = Math.min(transactions.size(), start + chunkSize);

      // Create thread that will run processTransaction()
      Thread thread =
          new Thread(
              () -> {
                for (int i = start; i < end; i++) {
                  processTransaction(transactions.get(i));
                }
              });

      threads.add(thread);
      // Start the thread
      thread.start();
    }

    // Wait for all threads to complete
    for (Thread thread : threads) {
      thread.join();
    }
    return transactions;
  }

  // CPU-bound toy workload that simulates cryptographic checks or pricing logic.
  private static void processTransaction(final Transaction tx) {
    double result = 0;
    for (int i = 0; i < 1000; i++) {
      result += Math.sqrt(tx.amount * i);
    }
    tx.result = result;
  }

  private static List<Transaction> generateTransactions(final int n) {
    List<Transaction> list = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      list.add(new Transaction(Math.random() * 1000));
    }
    return list;
  }

  private static final class Transaction {
    private final double amount;
    private double result;

    private Transaction(final double amount) {
      this.amount = amount;
    }
  }

  //////////////

  /**
   * Same story, but using a parallel stream to show how the JVM handles the thread pool for us.
   * Useful takeaway: parallel streams are opt-in—callers decide when to use them—and they shine for
   * stateless CPU-bound transforms. You still make an explicit choice to call {@code parallel()},
   * and you must keep the per-element work thread-safe.
   */
  private static void multiThreadDemo2() {
    List<Image> images = generateImages(500);
    List<Image> processedImages =
        images.parallelStream().map(MultiThreadExample::applyFilters).collect(Collectors.toList());
    System.out.println("Images processed in parallel: " + processedImages.size());
  }

  private static Image applyFilters(final Image image) {
    Image blurred = blur(image);
    return sharpen(blurred);
  }

  private static Image blur(final Image image) {
    process();
    return new Image(image.id, image.width, image.height);
  }

  private static Image sharpen(final Image image) {
    process();
    return new Image(image.id, image.width, image.height);
  }

  private static void process() {
    double localSum = 0;
    for (int i = 0; i < 100_000; i++) {
      localSum += Math.sin(i) * Math.cos(i);
    }
    cpuSink += localSum;
  }

  /** Utility that creates placeholder images for the parallel stream demo. */
  private static List<Image> generateImages(final int count) {
    List<Image> list = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      list.add(new Image(i, 1920, 1080));
    }
    return list;
  }

  /** Simple immutable image record; in real life this would hold pixels or metadata. */
  private static final class Image {
    private final int id;
    private final int width;
    private final int height;

    private Image(final int id, final int width, final int height) {
      this.id = id;
      this.width = width;
      this.height = height;
    }
  }
}

/**
 * Simple benchmarking helper showing why StringBuilder beats string concatenation in loops and how
 * identical O(n) searches can feel wildly different once constant factors creep in.
 */
public class PerfDemo {
  /**
   * Runs the concatenation benchmark and prints the elapsed time.
   *
   * @param args ignored command-line arguments
   */
  public static void main(String[] args) {
    runConcatenationBenchmark();
    runLinearSearchBenchmark();
  }

  // Key takeaway: StringBuilder keeps repeated appends O(n) by reusing the buffer; a naive
  // concatenation loop would keep allocating new strings and feel dramatically slower. nanoTime
  // just
  // gives us evidence for that story.
  private static void runConcatenationBenchmark() {
    // nanoTime is 1/1,000,000,000 of a second
    long startTime = System.nanoTime();

    StringBuilder result = new StringBuilder();
    for (int i = 0; i < 10_000; i++) {
      // Single builder avoids creating intermediate strings, keeping the O(n) build efficient.
      result.append(i);
    }

    // Get the end time
    long endTime = System.nanoTime();

    // Calculate the duration
    long durationInNanos = endTime - startTime;
    double durationInMillis = durationInNanos / 1_000_000.0;

    System.out.println("String concatenation took: " + durationInMillis + " ms");
    System.out.println("Final string length: " + result.length());
  }

  // Key takeaway: both searches are O(n), but adding even a tiny delay per comparison blows up the
  // constant factor—mirrors how I/O, locks, or network calls can dwarf algorithmic complexity.
  private static void runLinearSearchBenchmark() {
    int[] array = new int[10_000];
    for (int i = 0; i < array.length; i++) {
      // Populate predictable values so we know the target exists.
      array[i] = i;
    }

    int target = array[7_500]; // Target value to search for

    long startRegular = System.nanoTime();
    // New method: plain linear search, O(n) comparisons with minimal constant factor.
    boolean foundRegular = linearSearch(array, target);
    long endRegular = System.nanoTime();

    long startDelay = System.nanoTime();
    // Old method: injects a tiny delay per comparison to mimic blocking I/O.
    boolean foundDelay = linearSearchWithDelay(array, target);
    long endDelay = System.nanoTime();

    if (!foundRegular || !foundDelay) {
      throw new IllegalStateException("Target value was not located by one of the searches.");
    }

    long regularDuration = endRegular - startRegular;
    long delayDuration = endDelay - startDelay;

    double ratio =
        regularDuration == 0 ? Double.POSITIVE_INFINITY : (double) delayDuration / regularDuration;

    System.out.printf("Regular linear search: %.3f ms%n", regularDuration / 1_000_000.0);
    System.out.printf("Delayed linear search: %.3f ms%n", delayDuration / 1_000_000.0);
    System.out.printf(
        "Delay-injected linear search is %.2fx slower despite both being O(n).%n", ratio);
  }

  private static boolean linearSearch(final int[] data, final int target) {
    boolean found = false;
    for (int value : data) {
      if (value == target) {
        found = true;
        break;
      }
    }
    return found;
  }

  private static boolean linearSearchWithDelay(final int[] data, final int target) {
    boolean found = false;
    for (int value : data) {
      try {
        Thread.sleep(0, 1000); // Fixed 1 μs delay per comparison = larger constant factor.
      } catch (InterruptedException interruptedException) {
        Thread.currentThread().interrupt();
        throw new IllegalStateException("Search interrupted", interruptedException);
      }
      if (value == target) {
        found = true;
        break;
      }
    }
    return found;
  }
}

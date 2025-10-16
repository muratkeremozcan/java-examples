import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Streams 101 for a TypeScript/JavaScript mindset.
 *
 * <p>Think of {@link java.util.stream.Stream} as lazy, composable pipes: <br>
 * - Similar to chaining {@code map/filter/reduce} in TypeScript, but the work only happens when you
 * terminally consume the stream (e.g., {@code forEach}, {@code count}, {@code collect}). <br>
 * - Pipelines stay readable even when data sets grow or when you flip to parallel execution. <br>
 * - They shine when transforming, filtering, and aggregating large collections without hand-written
 * loops.
 */
public class StreamExamples {

  private static final Logger LOGGER = Logger.getLogger(StreamExamples.class.getName());

  /**
   * Runs each miniature stream scenario so you can compare sequential, filtered, collected, and
   * parallel pipelines in one go.
   */
  public static void main(String[] args) {
    demoForEach();
    demoFilterAndCount();
    demoParallelStream();
    demoMapAndCollect();
    demoMapAndReduce();
  }

  /**
   * Streams feel like TypeScript's array chains, but the work happens only at the terminal step.
   * Here {@code forEach} triggers the iteration and logging.
   */
  private static void demoForEach() {
    var usernames = new ArrayList<String>();
    usernames.add("Alice123");
    usernames.add("BobTheCoder");
    usernames.add("CharlieDev");
    usernames.add("David99");

    usernames.stream().forEach(name -> LOGGER.info("User: " + name));
  }

  /**
   * Filters a list lazily and counts matches. No intermediate arrays are created—unlike {@code
   * emails.filter(...).length} in TypeScript—which keeps things memory friendly.
   */
  private static void demoFilterAndCount() {
    var emails = new ArrayList<String>();
    emails.add("alice@company.com");
    emails.add("bob@gmail.com");
    emails.add("charlie@company.com");
    emails.add("david@yahoo.com");

    long count = emails.stream().filter(email -> email.endsWith("@company.com")).count();

    LOGGER.info("Total company emails: " + count);
  }

  /**
   * Runs the same pipeline in parallel. The code matches demoForEach(), but ordering is unspecified
   * and the JVM may split the work across threads—handy for CPU-bound transformations.
   */
  private static void demoParallelStream() {
    var numbers = new ArrayList<Integer>();
    for (int i = 0; i < 10; i++) {
      numbers.add(i);
    }

    numbers.parallelStream()
        .map(StreamExamples::expensiveComputation)
        .forEach(result -> LOGGER.info("Result: " + result));
  }

  private static int expensiveComputation(final int value) {
    try {
      TimeUnit.MILLISECONDS.sleep(100); // simulate cost per element
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    return value * value;
  }

  /**
   * Compared to TypeScript's {@code [...new Set(arr.map(...))]}, Java's collector steps avoid
   * building intermediate arrays; the stream feeds the set directly. Swap the collector and you can
   * materialize to a list, map, string, etc., without changing the preceding pipeline.
   */
  private static void demoMapAndCollect() {
    var cities = List.of("paris", "london", "new york", "paris");

    Set<String> uniqueUppercaseCities =
        cities.stream().map(String::toUpperCase).collect(Collectors.toSet());

    LOGGER.info(uniqueUppercaseCities.toString());
  }

  /**
   * Map and reduce are the building blocks of streams. Map transforms each element, and reduce
   * accumulates them into a single result.
   */
  private static void demoMapAndReduce() {
    List<Double> sales = List.of(200.0, 450.0, 700.0, 150.0, 300.0);

    double totalSalesAfterTax =
        sales.stream().map(amount -> amount * 1.1).reduce(0.0, (sum, value) -> sum + value);

    LOGGER.info("Total Sales After Tax: $" + totalSalesAfterTax);
  }
}

import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Logger;

/**
 * Iterator tour for folks coming from languages like TypeScript where iteration is simpler.
 *
 * <p>- Enhanced for-each is the Java "for...of" equivalent: fast read-only traversal.<br>
 * - An {@link java.util.Iterator} works for any collection and lets you remove items while you walk
 * forward.<br>
 * - A {@link java.util.ListIterator} is list-only but adds the extras: set existing values, insert
 * new ones, walk backwards, and surface indexes.
 */
public class IteratorExamples {
  private static final Logger LOGGER = Logger.getLogger(IteratorExamples.class.getName());
  private static final int MINIMUM_VALUE_TO_KEEP = 25;
  private static final String NAME_TO_UPDATE = "Jon";
  private static final String ERROR_TOKEN = "error";

  /** Executes each iterator flavor demo back-to-back for easy comparison. */
  public static void main(String[] args) {
    demoForEach();
    demoIterator();
    demoListIterator();
    demoListIterator2();
  }

  /** Demonstrates Java's for-each loop: iterate, don't mutate. */
  private static void demoForEach() {
    var cities = new HashSet<String>();

    cities.add("New York");
    cities.add("Los Angeles");
    cities.add("Chicago");

    for (final String city : cities) {
      LOGGER.info(city);
    }
  }

  /**
   * Uses {@link java.util.Iterator}: works on any collection, removal is allowed, but forward-only
   * and no edits.
   */
  private static void demoIterator() {
    var numbers = new HashSet<Integer>();

    numbers.add(10);
    numbers.add(20);
    numbers.add(30);
    numbers.add(40);
    numbers.add(50);

    var it = numbers.iterator();

    while (it.hasNext()) {
      int current = it.next();
      if (current > MINIMUM_VALUE_TO_KEEP) {
        it.remove();
      }
    }

    LOGGER.info(numbers.toString());
  }

  /** Walks a list with {@link java.util.ListIterator} to mutate the current element. */
  private static void demoListIterator() {
    var contacts = new ArrayList<String>();
    contacts.add("Alice");
    contacts.add(NAME_TO_UPDATE);
    contacts.add("Charlie");

    // KEY: ListIterator has set, size, previous, hasPrevious
    var it = contacts.listIterator();

    while (it.hasNext()) {
      String name = it.next();
      if (NAME_TO_UPDATE.equals(name)) {
        // KEY: mutation with set
        it.set("John");
      }
    }

    LOGGER.info(contacts.toString());
  }

  /** Replays a list in reverse to insert items while iterating. */
  private static void demoListIterator2() {
    var textHistory = new ArrayList<String>();
    textHistory.add("Hello");
    textHistory.add("error");
    textHistory.add("world");

    var it = textHistory.listIterator(textHistory.size());

    while (it.hasPrevious()) {
      String word = it.previous();
      if (ERROR_TOKEN.equals(word)) {
        it.add("correction");
      }
    }

    LOGGER.info(textHistory.toString());
  }
}

import java.util.HashSet;
import java.util.logging.Logger;

/**
 * Compares enhanced for-each iteration with manual Iterator control.
 *
 * <p>for-each is great for read-only traversals an explicit Iterator lets us remove elements safely
 * during the walk.
 */
public class CityDisplay {
  private static final Logger LOGGER = Logger.getLogger(CityDisplay.class.getName());
  private static final int MINIMUM_VALUE_TO_KEEP = 25;

  public static void main(String[] args) {
    demoForEach();
    demoIterator();
  }

  private static void demoForEach() {
    var cities = new HashSet<String>();

    cities.add("New York");
    cities.add("Los Angeles");
    cities.add("Chicago");

    for (final String city : cities) {
      LOGGER.info(city);
    }
  }

  private static void demoIterator() {
    var numbers = new HashSet<Integer>();

    numbers.add(10);
    numbers.add(20);
    numbers.add(30);
    numbers.add(40);
    numbers.add(50);

    // Create a new Iterator object
    var it = numbers.iterator();

    // Check if more elements exist
    while (it.hasNext()) {
      // Retrieve next element
      int current = it.next();
      if (current > MINIMUM_VALUE_TO_KEEP) {
        // Remove the retrieved element
        it.remove();
      }
    }

    LOGGER.info(numbers.toString());
  }
}

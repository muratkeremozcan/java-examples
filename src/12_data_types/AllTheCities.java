import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Demonstrates common collection utilities by juggling city names between arrays and collections.
 */
public class AllTheCities {

  /**
   * Entry point that showcases bulk add, array-backed list views, and in-place collection
   * utilities.
   */
  public static void main(String[] args) {
    String[] euCapitals = {"Paris", "London", "Prague"};
    // ArrayList<String> capitals = new ArrayList<String>(); // NOPMD - example needs concrete type
    var capitals = new ArrayList<String>(); // preferred newer way

    // Bulk add: push multiple elements into the list in a single call instead of repeating
    // add(...).
    Collections.addAll(capitals, "Lima", "Bogota", "Santiago");

    // Arrays.asList returns a fixed-size list view backed by the original array (not a
    // java.util.ArrayList).
    // Element updates reflect in euCapitals, but add/remove throw UnsupportedOperationException.
    // final List<String> euCapitalsList = Arrays.asList(euCapitals);
    var euCapitalsList = Arrays.asList(euCapitals); // preferred newer way

    // In-place sort and reverse on the same list to highlight mutating utility methods.
    Collections.sort(capitals);
    Collections.reverse(capitals);

    System.out.println(capitals);
    System.out.println(euCapitalsList);

    // Printing the bare array would call Object.toString and show its memory reference;
    // Arrays.toString produces a readable list instead.
    System.out.println(Arrays.toString(euCapitals));
  }
}

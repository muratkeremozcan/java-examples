// JVM manages memory through (mainly) stack & heap areas
// Stack memory - fast, stores primitives and references
// Heap memory -> Stores objects
/**
 * Quick demo showing how sharing immutable objects saves heap allocations compared to rebuilding
 * clones needlessly.
 */
public class JvmMemoryDemo {

  /**
   * Initializes two arrays with identical values using both inefficient and efficient strategies.
   *
   * @param args ignored command-line arguments
   */
  public static void main(String[] args) {
    int size = 10_000;

    String[] inefficientArray = populateArrayInefficient(size, "DataCamp");
    String[] efficientArray = populateArrayEfficient(size, "DataCamp");

    System.out.println(
        "Arrays have same length: " + (inefficientArray.length == efficientArray.length));
  }

  /**
   * Fills an array by cloning the provided value every time (similar to the “inefficient” path in
   * {@code GarbageCollectorExample}).
   *
   * @param size number of slots to fill
   * @param value string to store in each slot
   * @return array containing the duplicated references
   */
  public static String[] populateArrayInefficient(final int size, final String value) {
    // Create a brand-new String instance for each slot (mimics eager cloning).
    String[] array = new String[size];

    for (int i = 0; i < size; i++) {
      array[i] = value.concat("");
    }

    return array;
  }

  /**
   * Fills an array while reusing the same string reference to avoid redundant allocations.
   *
   * @param size number of slots to fill
   * @param value shared string reference for each slot
   * @return array containing the shared reference
   */
  public static String[] populateArrayEfficient(final int size, final String value) {
    // Reuse the original immutable String instance for every slot (cheap, GC-friendly).
    String[] array = new String[size];

    for (int i = 0; i < size; i++) {
      array[i] = value;
    }

    // Return the final array
    return array;
  }
}

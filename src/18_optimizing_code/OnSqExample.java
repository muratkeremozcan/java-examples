import java.util.ArrayList;
import java.util.List;

/**
 * Quadratic-time sort check demo.
 *
 * <ul>
 *   <li>Nested loops mean O(n^2) comparisons; list size doubles → work quadruples.
 *   <li>Great for visualizing why you replace double loops with smarter algorithms.
 * </ul>
 */
public final class OnSqExample {
  private OnSqExample() {}

  /** Populate a list and run the quadratic sortedness check. */
  public static void main(String[] args) {
    int arraySize = 1000;

    List<Integer> unsortedNumbers = new ArrayList<>();
    for (int i = 0; i < arraySize; i++) {
      unsortedNumbers.add(i);
    }

    // Force a single inversion near the end of the list.
    int temp = unsortedNumbers.get(arraySize - 1);
    unsortedNumbers.set(arraySize - 1, unsortedNumbers.get(arraySize - 2));
    unsortedNumbers.set(arraySize - 2, temp);

    boolean result = SortChecker.isSorted(unsortedNumbers);
    System.out.println("Unsorted array result: " + result);
  }

  /** Utility for quadratic-order sortedness checks. */
  private static final class SortChecker {

    private static boolean isSorted(final List<Integer> numbers) {
      boolean sorted = true;
      for (Integer left : numbers) {
        for (Integer right : numbers) {
          if (left > right) {
            sorted = false;
            break;
          }
        }
        if (!sorted) {
          break;
        }
      }
      return sorted;
    }
  }
}

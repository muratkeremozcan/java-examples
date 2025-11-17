import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Quadratic-time sort check demo.
 *
 * <ul>
 *   <li>Nested loops mean O(n^2) comparisons; list size doubles → work quadruples.
 *   <li>Great for visualizing why you replace double loops with smarter algorithms.
 *   <li>Switch to a single-pass scan when you only need to verify non-decreasing order.
 * </ul>
 */
public final class OnSqExample {
  private OnSqExample() {}

  /** Populate a list and run the quadratic vs linear sortedness checks. */
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

    boolean quadraticResult = SortChecker.isSortedQuadratic(unsortedNumbers);
    boolean linearResult = SortChecker.isSortedLinear(unsortedNumbers);
    System.out.println("Unsorted array, quadratic check: " + quadraticResult);
    System.out.println("Unsorted array, linear check: " + linearResult);

    List<Integer> sortedNumbers = new ArrayList<>(unsortedNumbers);
    Collections.sort(sortedNumbers);
    System.out.println("Sorted array, linear check: " + SortChecker.isSortedLinear(sortedNumbers));
  }

  /** Utility for quadratic vs linear sorted-ness checks. */
  private static final class SortChecker {

    // O(n^2) double loop: compares every pair.
    private static boolean isSortedQuadratic(final List<Integer> numbers) {
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

    // O(n) single pass: bail on first inversion.
    private static boolean isSortedLinear(final List<Integer> numbers) {
      boolean sorted = true;
      Integer previous = null;
      for (Integer current : numbers) {
        if (previous != null && previous > current) {
          sorted = false;
          break;
        }
        previous = current;
      }
      return sorted;
    }
  }
}

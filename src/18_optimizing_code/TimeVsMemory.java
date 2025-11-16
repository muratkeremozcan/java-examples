/**
 * Two tiny demos: single-pass max (O(n), small memory) vs generating all pairs (O(n^2), big mem).
 */
public class TimeVsMemory {

  public static void main(String[] args) {
    dataAnalyzerDemo();
    connectionAnalyzerDemo();
  }

  private static void dataAnalyzerDemo() {
    int[] smallData = {5, 3, 9, 1, 7};
    int maxSmall = DataAnalyzer.findMaxValue(smallData);
    System.out.println("Maximum value in small dataset: " + maxSmall);

    int[] largeData = new int[1000];
    for (int i = 0; i < largeData.length; i++) {
      // Populating the array scales linearly because storing the values costs O(n) space.
      largeData[i] = java.util.concurrent.ThreadLocalRandom.current().nextInt(10_000);
    }

    int maxLarge = DataAnalyzer.findMaxValue(largeData);
    System.out.println("Maximum value in large dataset: " + maxLarge);
  }

  private static final class DataAnalyzer {
    private static int findMaxValue(final int[] data) {
      int max = data[0];

      for (int value : data) {
        // Single pass keeps O(n) time and O(1) extra space by reusing the running maximum.
        if (value > max) {
          max = value;
        }
      }

      return max;
    }
  }

  private static void connectionAnalyzerDemo() {

    ConnectionAnalyzer analyzer = new ConnectionAnalyzer();

    String[] users = {"Alice", "Bob", "Charlie", "Diana"};

    String[][] allPairs = analyzer.generateAllPairs(users);
    System.out.println("All possible connections:");
    for (String[] pair : allPairs) {
      System.out.println(pair[0] + " - " + pair[1]);
    }
  }

  private static final class ConnectionAnalyzer {
    public String[][] generateAllPairs(final String[] elements) {
      int n = elements.length;

      int numPairs = n * (n - 1) / 2;
      String[][] pairs = new String[numPairs][2];

      int pairIndex = 0;
      for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
          // Nested loops touch every pair: O(n^2) time and O(n^2) space to store them all.
          // Add the first user to our pair
          pairs[pairIndex][0] = elements[i];
          // Add the second user to our pair
          pairs[pairIndex][1] = elements[j];
          pairIndex++;
        }
      }

      // Return the result
      return pairs;
    }
  }
}

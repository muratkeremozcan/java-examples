/**
 * A simple program that demonstrates array iteration and modification using different loop types.
 * It processes an array of weights, prints them, and then replaces any weight over 220 with 198.
 */
public class WeightWatcher {
  /** Maximum allowed weight before it gets reset. */
  private static final int MAX_ALLOWED_WEIGHT = 220;

  /** Default weight to use when resetting an over-limit weight. */
  private static final int DEFAULT_WEIGHT = 198;

  /**
   * Main method that demonstrates array processing with different loop types.
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {
    final int[] weights = {
      198, 190, 188, 187, 190, 198, 201, 250, 203, 210, 205, 170, 180, 200, 203, 210, 180
    };

    // KEY: use a foreach loop when you don't need the index
    for (final int weight : weights) {
      System.out.println(weight);
    }

    // KEY: use a regular for loop when you need the index
    System.out.println("\nuse a regular for loop when you need the index\n");

    for (int i = 0; i < weights.length; i++) {
      final int weight = weights[i];
      if (weight > MAX_ALLOWED_WEIGHT) {
        weights[i] = DEFAULT_WEIGHT;
      }
      System.out.println(weights[i]);
    }
  }
}

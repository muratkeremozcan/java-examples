/**
 * A utility class for performing price calculations, such as applying multipliers to values.
 * Demonstrates the use of helper methods and array iteration.
 */
public class PriceCalculator {
  /**
   * Main method that demonstrates price adjustment calculations. Applies a 1.5x multiplier to each
   * price in an array and prints the results.
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {
    final double[] numbers = {76, 38.3, 10, 42, 98.5, 84, 50, 72.2, 98, 96};

    for (int i = 0; i < numbers.length; i++) {
      numbers[i] = oneAndaHalf(numbers[i]);
      System.out.println(numbers[i]);
    }
  }

  /**
   * Multiplies a number by 1.5, typically used for price adjustments.
   *
   * @param a the input value to be multiplied
   * @return the input value multiplied by 1.5
   */
  private static double oneAndaHalf(final double a) {
    return a * 1.5;
  }
}

/** Utility class for speed calculations. */
public final class SpeedCalculator {
  // Prevent instantiation
  private SpeedCalculator() {}

  /**
   * Calculates the speed given distance and time.
   *
   * @param distance the distance traveled
   * @param time the time taken (must be positive)
   * @return the calculated speed as distance per time unit
   * @throws IllegalArgumentException if time is not positive
   */
  public static double calculateSpeed(final double distance, final double time) {
    if (time <= 0) {
      throw new IllegalArgumentException("Time must be positive");
    }
    return distance / time;
  }

  /**
   * Main method to demonstrate speed calculation.
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {
    try {
      final double speed = calculateSpeed(100, 60);
      System.out.printf("Speed: %.2f units per time%n", speed);
    } catch (IllegalArgumentException e) {
      System.err.println("Error calculating speed: " + e.getMessage());
    }
  }
}

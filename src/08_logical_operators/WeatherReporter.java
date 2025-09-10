/** A simple weather reporting application that demonstrates switch-case statements. */
public class WeatherReporter {
  /**
   * Main method to demonstrate weather reporting.
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {
    final String weather = "Sunny";

    switch (weather) {
      case "Windy":
        System.out.println("It's windy");
        break;
      case "Rainy":
        System.out.println("It's rainy");
        break;
      default:
        System.out.println("It's sunny");
    }
  }
}

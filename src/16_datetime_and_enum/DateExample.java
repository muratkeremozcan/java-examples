import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Lightweight intro to {@link java.time.LocalDate}, the modern replacement for the legacy {@code
 * java.util.Date} API.
 */
public class DateExample {

  /**
   * Kicks off the demo—Chrono API calls are static, so keeping main tiny makes the example easy to
   * copy.
   */
  public static void main(String[] args) {
    printDate();
  }

  /**
   * Grabs today's date, formats it with a pattern, and shows parsing. These are the
   * bread-and-butter operations when migrating from string-based date handling.
   */
  public static void printDate() {
    LocalDate date = LocalDate.now();
    System.out.println("current date in default format: " + date);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    System.out.println("ofPattern() + format(): " + date.format(formatter));

    LocalDate parseDate = LocalDate.parse("2025-02-10");
    System.out.println("parse(): " + parseDate);
  }

  /**
   * Shows common date math—no mutable calendars, just fluent calls. Handy when porting code from
   * imperative date arithmetic.
   */
  public static void dateArithmetic() {
    LocalDate today = LocalDate.now();

    LocalDate futureDate = today.plusDays(10);
    LocalDate pastDate = today.minusDays(5);

    System.out.println(futureDate);
    System.out.println(pastDate);
  }
}

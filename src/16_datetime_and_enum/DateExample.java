import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Lightweight intro to {@link java.time.LocalDate}. */
public class DateExample {

  /** Kickstarts the sample by calling {@link #printDate()}. */
  public static void main(String[] args) {
    printDate();
  }

  /** Shows formatting and parsing without the old {@code java.util.Date} API. */
  public static void printDate() {
    LocalDate date = LocalDate.now();
    System.out.println("current date in default format: " + date);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    System.out.println("ofPattern() + format(): " + date.format(formatter));

    LocalDate parseDate = LocalDate.parse("2025-02-10");
    System.out.println("parse(): " + parseDate);
  }

  /** Performs simple date arithmetic with immutable {@link LocalDate}. */
  public static void dateArithmetic() {
    LocalDate today = LocalDate.now();

    LocalDate futureDate = today.plusDays(10);
    LocalDate pastDate = today.minusDays(5);

    System.out.println(futureDate);
    System.out.println(pastDate);
  }
}

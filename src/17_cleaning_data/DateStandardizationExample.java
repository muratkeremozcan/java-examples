import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/** Normalizes date strings and time zones into consistent formats. */
public class DateStandardizationExample {
  /** Run the formatting and time-zone demonstrations. */
  public static void main(String[] args) {
    formatDatesExample();
    zonedDatesExample();
    formatDatesExample2();
  }

  private static void formatDatesExample() {
    String[] dates = {"3/1/25", "04-01-2025", "2025.06.01"};
    // use DateTimeFormatter.ofPattern() to specify the date format
    DateTimeFormatter[] formatters = {
      DateTimeFormatter.ofPattern("M/d/yy"),
      DateTimeFormatter.ofPattern("MM-dd-yyyy"),
      DateTimeFormatter.ofPattern("yyyy.MM.dd")
    };

    System.out.println("Standardized manufacturing dates:");
    for (int i = 0; i < dates.length; i++) {
      // use LocalDate.parse() to parse the date string into the specified format
      LocalDate productDate = LocalDate.parse(dates[i], formatters[i]);
      System.out.println("Date " + dates[i] + " is standardized as " + productDate);
    }

    LocalDate manufacturingDate = LocalDate.parse("2023-01-01");
    // Display date as full month name, day without leading zeros, and full year
    DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMMM d, yyyy");
    String formattedDate = manufacturingDate.format(displayFormat);

    System.out.println("\nFormatted manufacturing date: " + formattedDate);
  }

  private static void zonedDatesExample() {
    LocalDate date = LocalDate.parse("2023-01-01");

    // date.atStartOfDay(zone) anchors the local date to midnight in that time zone.
    ZonedDateTime nyTime = date.atStartOfDay(ZoneId.of("America/New_York"));
    // withZoneSameInstant keeps the instant but expresses it in the target time zone.
    ZonedDateTime laTime = nyTime.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));

    System.out.println("New York time: " + nyTime);
    System.out.println("Los Angeles time: " + laTime);
  }

  private static void formatDatesExample2() {
    LocalDate date1 = LocalDate.parse("1/1/23", DateTimeFormatter.ofPattern("M/d/yy"));
    // Specify the format of date2 with zero-padded month/day
    LocalDate date2 = LocalDate.parse("07-01-2023", DateTimeFormatter.ofPattern("MM-dd-yyyy"));
    // Extract the month and year from date1 and date2
    System.out.println(
        "Month and year of " + date1 + ": " + date1.getMonth() + " " + date1.getYear());
    System.out.println(
        "Month and year of " + date2 + ": " + date2.getMonth() + " " + date2.getYear());
  }
}

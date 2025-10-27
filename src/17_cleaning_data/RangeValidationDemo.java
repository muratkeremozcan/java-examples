import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.Range;

/**
 * Key takeaways.
 *
 * <ul>
 *   <li>Collections.min/max reveal the observed bounds before we validate.</li>
 *   <li>Range.of captures inclusive thresholds so the checks stay readable.</li>
 *   <li>Parse strings to LocalDate, then compare within the allowed launch window.</li>
 * </ul>
 */
public class RangeValidationDemo {
  /** Run the quantity and date range validation demos. */
  public static void main(String[] args) {
    rangeValidationExample();
  }

  private static void rangeValidationExample() {
    List<Double> quantities = Arrays.asList(41.49, 29.08, 15.85);

    // Collections.min/max surface the observed limits before we apply business rules.
    Double minQuantity = Collections.min(quantities);
    Double maxQuantity = Collections.max(quantities);

    System.out.println("Quantity range: " + minQuantity + " - " + maxQuantity);

    // Range.of() captures the acceptable bounds in one place (inclusive on both ends here).
    Range<Double> lowQuantities = Range.of(0.0, 25.0);
    Range<Double> highQuantities = Range.of(25.0, 50.0);

    for (Double quantity : quantities) {
      // Check if the quantity is low
      if (lowQuantities.contains(quantity)) {
        System.out.println(quantity + " - Low quantity");
        // Check if the quantity is high
      } else if (highQuantities.contains(quantity)) {
        System.out.println(quantity + " - High quantity");
      } else {
        System.out.println(quantity + " - Out of expected range");
      }
    }
  }

  /** Validate release dates sit inside the approved launch window. */
  public static void rangeValidationExample2() {
    List<String> releaseDates = Arrays.asList("11/19/2006", "11/13/1985", "4/10/2008");

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
    LocalDate startDate = LocalDate.of(1985, 1, 1);
    LocalDate endDate = LocalDate.of(2024, 12, 31);

    for (String dateStr : releaseDates) {
      LocalDate releaseDate = LocalDate.parse(dateStr, formatter);
      // Check that the release date occurs after startDate
      boolean isValid =
          releaseDate.isAfter(startDate)
              // Guard against future launches outside the vetted product window.
              && releaseDate.isBefore(endDate);
      System.out.println(dateStr + " is valid: " + isValid);
    }
  }
}

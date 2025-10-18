// Summary: detecting nulls and blanks

// Null values indicate missing or undefined data
// We need to prevent NullPointerException by checking nulls before use

// import java.util.Objects, import java.util.Optional
// Objects.isNull() : check for nulls
// Optional.ofNullable() : provide defaults for nulls

// Blank values indicate empty or whitespace-only text

// import org.apache.commons.lang3.StringUtils
// StringUtils.isBlank() : detect "", null, or whitespace
// Objects.toString() : provide defaults for blanks

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;

public class NullAndBlankDemo {
  
  public static void main(String[] args) {
    nullDemo();
    blankDemo();
  }
  
  // Record is just a compact, immutable data bundle—think readonly TS object with auto getters.
  private record CoffeeSales(
      LocalDate date, String coffeeName, String paymentMethod, Integer quantity, Double amount) {}
      
  private static void nullDemo() {
    CoffeeSales sale = new CoffeeSales(LocalDate.of(2024, 3, 1), "Latte", "cash", null, null);

    // use Objects.isNull() to check for nulls
    if (Objects.isNull(sale.quantity())) {
      System.out.println("Missing quantity");
    }
    if (Objects.isNull(sale.amount())) {
      System.out.println("Missing amount");
    }

    // use Optional.ofNullable() to provide defaults for nulls
    int defaultQuantity = Optional.ofNullable(sale.quantity()).orElse(1);
    System.out.println("Default quantity: " + defaultQuantity);
  }

  private static void blankDemo() {
    CoffeeSales sale = new CoffeeSales(LocalDate.of(2024, 3, 1), null, " ", 1, 6.26);

    // use Objects.toString() to provide defaults for blanks
    String coffeeName = Objects.toString(sale.coffeeName(), "[No coffee name]");
    System.out.println("coffeeName: " + coffeeName);

    // use StringUtils.isBlank() to check for blanks
    if (StringUtils.isBlank(sale.paymentMethod())) {
      System.out.println("Payment method cannot be empty or whitespace");
    }
  }
}

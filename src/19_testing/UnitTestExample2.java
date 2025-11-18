import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import testing.support.TestLauncher;

/**
 * Mirrors the "Unit testing with JUnit" transcript: we define units, show assertions, and highlight
 * the naming convention methodUnderTest_expectedBehavior_conditions.
 */
@SuppressWarnings("checkstyle:abbreviationaswordinname")
public class UnitTestExample2 {
  public static void main(String[] args) {
    TestLauncher.launchTestsAndPrint(
        CurrencyConverterTest.class, CollectionUtilitiesTest.class, ExceptionFlowsTest.class);
  }

  /** Tiny unit under test used across the snippets. */
  private static final class CurrencyConverter {

    /* default */ static double convert(final double currency, final double exchangeRate) {
      return currency * exchangeRate;
    }

    /* default */ static double rateByDay(final double[] rates, final int dayIndex) {
      return rates[dayIndex];
    }
  }

  /** Demonstrates assertEquals + naming convention methodUnderTest_expectedBehavior_conditions. */
  @SuppressWarnings("PMD.MethodNamingConventions")
  /* default */ static class CurrencyConverterTest {

    @Test
    void convert_returnsConvertedCurrency_whenValidInputs() {
      double currency = 100;
      double exchangeRate = 1.2;

      double convertedCurrency = CurrencyConverter.convert(currency, exchangeRate);

      assertEquals(120.0, convertedCurrency, 0.0001, "Currency should convert correctly");
    }
  }

  /** Shows assertTrue/assertFalse/assertNull/assertNotNull on collections and maps. */
  @SuppressWarnings("PMD.MethodNamingConventions")
  /* default */ static class CollectionUtilitiesTest {
    @Test
    void inventory_isEmpty_whenNoOrders() {
      List<String> inventory = new ArrayList<>();
      assertTrue(inventory.isEmpty(), "Inventory should be empty");
    }

    @Test
    void inventory_isNotEmpty_whenItemsAdded() {
      List<String> inventory = new ArrayList<>();
      inventory.add("USD");
      assertFalse(inventory.isEmpty(), "Inventory should not be empty");
    }

    @Test
    void currencyRate_isNotNull_whenMappingExists() {
      Map<String, Double> rates = new HashMap<>();
      rates.put("USD", 1.0);

      Double usdRate = rates.get("USD");
      assertNotNull(usdRate, "USD rate should be present");
    }

    @Test
    void currencyRate_matchesExpectedValue_whenMappingExists() {
      Map<String, Double> rates = new HashMap<>();
      rates.put("USD", 1.0);

      Double usdRate = rates.get("USD");
      assertEquals(1.0, usdRate, "USD rate should equal 1.0");
    }

    @Test
    void currencyRate_isNull_whenMissingMapping() {
      Map<String, Double> rates = new HashMap<>();
      Double cadRate = rates.get("CAD");
      assertNull(cadRate, "Missing rate should be null");
    }
  }

  /** Verifies exception scenarios using assertInstanceOf per the lesson transcript. */
  @SuppressWarnings("PMD.MethodNamingConventions")
  /* default */ static class ExceptionFlowsTest {
    @Test
    void rateByDay_throwsArrayIndexOutOfBounds_whenIndexInvalid() {
      boolean threw = false;
      try {
        CurrencyConverter.rateByDay(new double[] {1.1, 1.2, 1.3}, 5);
      } catch (ArrayIndexOutOfBoundsException ex) {
        threw = true;
      }
      assertTrue(threw, "Should throw ArrayIndexOutOfBoundsException for invalid index");
    }
  }
}

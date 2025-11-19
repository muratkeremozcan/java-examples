package integration.forex;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Simplified representation of the European Central Bank reference server with October 2025 rates.
 */
public class EuropeanCentralBankServer {
  private final Map<String, Double> referenceRates;

  /** Preloads a few daily reference rates. */
  public EuropeanCentralBankServer() {
    Map<String, Double> rates = new HashMap<>();
    rates.put("USD", 1.07);
    rates.put("GBP", 0.86);
    rates.put("TRY", 37.15);
    rates.put("CHF", 0.95);
    referenceRates = Collections.unmodifiableMap(rates);
  }

  /**
   * Returns the EUR -> target currency rate.
   *
   * @param currencyCode ISO-4217 code like USD, TRY, etc.
   * @return multiplier from EUR to the requested currency
   */
  public double getRateEuroTo(final String currencyCode) {
    Double rate = referenceRates.get(currencyCode);
    if (rate == null) {
      throw new IllegalArgumentException("Currency not in ECB list: " + currencyCode);
    }
    return rate;
  }
}

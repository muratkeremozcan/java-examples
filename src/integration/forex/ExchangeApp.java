package integration.forex;

import java.util.Objects;

/** Currency exchange application that depends on the EuropeanCentralBankServer. */
public class ExchangeApp {
  private final EuropeanCentralBankServer ecbServer;

  public ExchangeApp(final EuropeanCentralBankServer ecbServer) {
    this.ecbServer = Objects.requireNonNull(ecbServer, "ECB server cannot be null.");
  }

  public double convertEuroTo(final double amountInEuro, final String targetCurrency) {
    double rate = ecbServer.getRateEuroTo(targetCurrency);
    return amountInEuro * rate;
  }
}

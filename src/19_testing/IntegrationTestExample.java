import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import integration.forex.EuropeanCentralBankServer;
import integration.forex.ExchangeApp;
import org.junit.jupiter.api.Test;
import testing.support.TestLauncher;

/** Demonstrates an integration test that wires ExchangeApp with its ECB dependency. */
@SuppressWarnings("checkstyle:abbreviationaswordinname")
public class IntegrationTestExample {
  public static void main(String[] args) {
    TestLauncher.launchTestsAndPrint(ExchangeAppTest.class);
  }

  @SuppressWarnings({
    "PMD.MethodNamingConventions",
    "PMD.AtLeastOneConstructor",
    "PMD.JUnitTestContainsTooManyAsserts"
  })
  /* default */ static class ExchangeAppTest {

    @Test
    void convertEuroTo_returnsPositiveAmount() {
      // here Exchange app uses the ECB server to get the exchange rate; hence the integration test
      EuropeanCentralBankServer bank = new EuropeanCentralBankServer();
      ExchangeApp exchangeApp = new ExchangeApp(bank);

      double euroAmount = 100;
      double tryAmount = exchangeApp.convertEuroTo(euroAmount, "TRY");

      System.out.println("Converted " + euroAmount + " EUR to " + tryAmount + " TRY.");
      assertTrue(tryAmount > euroAmount, "TRY amount should exceed EUR amount");
    }

    @Test
    void convert_throwsException_whenGetRateThrowsException() {
      EuropeanCentralBankServer bank = new EuropeanCentralBankServer();
      ExchangeApp exchangeApp = new ExchangeApp(bank);
      IllegalArgumentException ex =
          assertThrows(
              IllegalArgumentException.class,
              () -> exchangeApp.convertEuroTo(1000, "Invalid Currency"),
              "Conversion should fail for unknown currencies");
      assertEquals(
          "Currency not in ECB list: Invalid Currency",
          ex.getMessage(),
          "Exception should mention the missing currency");
    }

    // mocking example
    @Test
    void convertEuroTo_convertsTST_usingMock() {
      // Mock the ECB server to return a fixed rate for TST
      // This isolates the test from network dependencies and ECB server availability
      EuropeanCentralBankServer mockedBank = mock(EuropeanCentralBankServer.class);
      ExchangeApp exchangeApp = new ExchangeApp(mockedBank);
      // stub the mock to return a fixed rate
      when(mockedBank.getRateEuroTo("TST")).thenReturn(123.45);

      double euroAmount = 100;
      double tstAmount = exchangeApp.convertEuroTo(euroAmount, "TST");

      System.out.println("Converted " + euroAmount + " EUR to " + tstAmount + " TST using a mock.");
      assertEquals(12_345.0, tstAmount, "Mocked conversion should match stubbed rate");
    }

    // mock an exception scenario
    @Test
    void convertEuroTo_throwsException_whenBankUnavailable() {
      EuropeanCentralBankServer mockedBank = mock(EuropeanCentralBankServer.class);
      ExchangeApp exchangeApp = new ExchangeApp(mockedBank);
      // stub the mock to throw an exception
      when(mockedBank.getRateEuroTo("TST"))
          .thenThrow(new RuntimeException("Bank server is unavailable."));

      double euroAmount = 450;
      double tstAmount = exchangeApp.convertEuroTo(euroAmount, "TST");

      // Assert on the return value of the method
      assertEquals(-1.0, tstAmount, "Should return -1 when bank is unavailable");
    }

    
  }
}

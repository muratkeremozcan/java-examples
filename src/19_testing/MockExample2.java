import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import integration.alert.AlertService;
import integration.alert.DurationMonitor;
import org.junit.jupiter.api.Test;
import testing.support.TestLauncher;

/** Shows how to stub and verify alert flows with Mockito. */
@SuppressWarnings("checkstyle:abbreviationaswordinname")
public class MockExample2 {
  public static void main(String[] args) {
    TestLauncher.launchTestsAndPrint(DurationMonitorTest.class);
  }

  @SuppressWarnings("PMD.MethodNamingConventions")
  /* default */ static class DurationMonitorTest {

    @Test
    void recordDuration_triggersAlert_whenAboveLimit() {
      AlertService alertService = mock(AlertService.class);
      DurationMonitor monitor = new DurationMonitor(alertService);
      monitor.recordDuration(1500);

      verify(alertService).trigger("Slow execution detected: 1500ms");
    }

    @Test
    void recordDuration_doesNotTriggerAlert_whenUnderLimit() {
      AlertService alertService = mock(AlertService.class);
      DurationMonitor monitor = new DurationMonitor(alertService);
      monitor.recordDuration(500);

      verify(alertService, never()).trigger(org.mockito.ArgumentMatchers.anyString());
    }
  }
}

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import integration.alert.AlertService;
import integration.alert.DurationMonitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testing.support.TestLauncher;

/**
 * Shows two Mockito styles side by side: a vanilla approach and a version with shared setup.
 */
@SuppressWarnings("checkstyle:abbreviationaswordinname")
public class MockExample2 {
  public static void main(String[] args) {
    TestLauncher.launchTestsAndPrint(
        DurationMonitorTestVanilla.class, DurationMonitorTestRefined.class);
  }

  /** Vanilla version: each test builds its own mocks. */
  @SuppressWarnings("PMD.MethodNamingConventions")
  /* default */ static class DurationMonitorTestVanilla {

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

      verify(alertService, never()).trigger(anyString());
    }
  }

  /** Refined version: uses shared setup and stronger verification. */
  @SuppressWarnings("PMD.MethodNamingConventions")
  /* default */ static class DurationMonitorTestRefined {
    private AlertService alertService;
    private DurationMonitor monitor;

    @BeforeEach
    void setUp() {
      alertService = mock(AlertService.class);
      monitor = new DurationMonitor(alertService);
    }

    @Test
    void recordDuration_triggersAlert_whenAboveLimit() {
      monitor.recordDuration(1500);

      verify(alertService).trigger("Slow execution detected: 1500ms");
    }

    @Test
    void recordDuration_doesNotTriggerAlert_whenUnderLimit() {
      monitor.recordDuration(500);

      verifyNoInteractions(alertService);
    }
  }
}

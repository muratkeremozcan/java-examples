package integration.alert;

import java.util.Objects;

/** Records execution time and notifies the {@link AlertService} when it exceeds a limit. */
public class DurationMonitor {
  private static final long MAX_DURATION_MS = 1_000;

  private final AlertService alertService;

  /** Creates a monitor that publishes alerts via the provided service. */
  public DurationMonitor(final AlertService alertService) {
    this.alertService = Objects.requireNonNull(alertService, "alertService cannot be null");
  }

  /**
   * Records a duration and triggers the alert when it exceeds {@link #MAX_DURATION_MS}.
   *
   * @param durationMillis duration in milliseconds
   */
  public void recordDuration(final long durationMillis) {
    if (durationMillis <= 0) {
      throw new IllegalArgumentException("Duration must be positive");
    }
    if (durationMillis > MAX_DURATION_MS) {
      alertService.trigger("Slow execution detected: " + durationMillis + "ms");
    }
  }
}

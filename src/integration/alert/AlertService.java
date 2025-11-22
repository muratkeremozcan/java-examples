package integration.alert;

/** Sends alerts when monitored durations exceed acceptable thresholds. */
public interface AlertService {
  void trigger(String message);
}

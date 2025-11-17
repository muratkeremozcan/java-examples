package testing.support;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

/**
 * Small utility to run JUnit tests from the CLI without IDE integrations. Lessons invoke the static
 * helper so that each snippet can stay focused on the code under test.
 */
@SuppressWarnings("PMD.TestClassWithoutTestCases")
public final class TestLauncher {
  private TestLauncher() {}

  /**
   * Launches one or more test classes and prints a concise summary of the execution results.
   *
   * @param testClasses the test classes to execute (must contain at least one non-null entry)
   * @throws IllegalArgumentException if no classes are provided
   */
  public static void launchTestsAndPrint(final Class<?>... testClasses) {
    if (testClasses == null || testClasses.length == 0) {
      throw new IllegalArgumentException("Provide at least one test class to execute.");
    }

    LauncherDiscoveryRequestBuilder builder = LauncherDiscoveryRequestBuilder.request();
    for (Class<?> testClass : testClasses) {
      if (testClass == null) {
        continue;
      }
      builder.selectors(selectClass(testClass));
    }

    LauncherDiscoveryRequest request = builder.build();
    SummaryGeneratingListener listener = new SummaryGeneratingListener();

    Launcher launcher = LauncherFactory.create();
    launcher.registerTestExecutionListeners(listener);
    launcher.execute(request);

    printSummary(listener.getSummary());
  }

  private static void printSummary(final TestExecutionSummary summary) {
    long total = summary.getTestsFoundCount();
    long succeeded = summary.getTestsSucceededCount();
    long failed = summary.getTestsFailedCount();
    System.out.printf("Tests run: %d, passed: %d, failed: %d%n", total, succeeded, failed);

    if (failed > 0) {
      for (TestExecutionSummary.Failure failure : summary.getFailures()) {
        Throwable exception = failure.getException();
        String message = exception == null ? "no details available" : exception.getMessage();
        System.out.printf(
            "Test %s failed: %s%n", failure.getTestIdentifier().getDisplayName(), message);
      }
    } else {
      System.out.println("All tests passed!");
    }
  }
}

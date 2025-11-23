import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import testing.support.TestLauncher;

/*
 * Quick takeaways: JUnit is the go-to Java testing library, @Test marks discovery, and AAAs keep
 * tests readable.
 */
/** Minimal JUnit demo plus CLI tips for running tests with standard tooling. */
@SuppressWarnings("checkstyle:abbreviationaswordinname")
public class JUnitExamples {
  /** Runs the Datacamp helper and shows the equivalent real-world commands. */
  public static void main(String[] args) {
    System.out.println("=== Datacamp helper ===");
    TestLauncher.launchTestsAndPrint(LastDigitTest.class);

    System.out.println();
    System.out.println("=== Real-world runner ===");
    /*
     * Copy/paste from repo root:
     *
     * # Gradle (preferred)
     * ./gradlew test --tests "19_testing.JUnitExamples$LastDigitTest"
     *
     * # ConsoleLauncher (download + run)
     * curl -L -o junit-platform-console.jar \
     *   https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.2/junit-platform-console-standalone-1.10.2.jar
     * ./gradlew classes
     * java -jar junit-platform-console.jar \
     *   --classpath build/classes/java/main \
     *   -c 19_testing.JUnitExamples$LastDigitTest
     */
  }

  /** Simple test verifying that {@link LastDigit} returns the final digit of the input number. */
  /* default */ static class LastDigitTest {
    @Test
    void testLastDigit() {
      // arrange
      final int number = 2025;
      final int expected = 5;

      // act
      final int actual = LastDigit.lastDigit(number);

      // assert
      assertEquals(expected, actual, "Returns the last digit");
    }

    // same thing with ValueSource
    /**
     * `@ValueSource` supplies a single argument, so we check a common invariant (result is 0-9).
     */
    @ParameterizedTest(name = "Digit of ''{0}'' should be between 0 and 9")
    @ValueSource(ints = {2025, -123, 0})
    void testLastDigitParameterizedValueSource(final int number) {
      final int actual = LastDigit.lastDigit(number);
      assertTrue(actual >= 0 && actual <= 9, "Last digit must stay within 0-9");
    }

    /** `@CsvSource` lets us pass multiple arguments (input + expected output). */
    @ParameterizedTest(name = "Last digit of ''{0}'' is ''{1}''")
    @CsvSource({"2025, 5", "-123, 3", "0, 0"})
    void testLastDigitParameterized(final int number, final int expected) {
      final int actual = LastDigit.lastDigit(number);
      assertEquals(expected, actual, "Returns the last digit");
    }
  }

  /** Utility holder for digit-related helpers used by the lesson. */
  private static final class LastDigit {
    private static int lastDigit(final int number) {
      return Math.abs(number % 10);
    }
  }
}

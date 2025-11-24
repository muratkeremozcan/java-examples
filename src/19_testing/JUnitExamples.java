import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import testing.support.TestLauncher;

/*
 * Quick takeaways: JUnit is the go-to Java testing library, @Test marks discovery, and AAAs keep
 * tests readable. Use @ParameterizedTest with @MethodSource for complex test data, and @BeforeEach
 * to reduce setup duplication.
 */
/**
 * Comprehensive JUnit demo covering: - Basic @Test and AAA pattern. - @ParameterizedTest
 * with @ValueSource, @CsvSource, and @MethodSource. - Arguments class for passing complex objects
 * to parameterized tests. - @BeforeEach for shared test setup.
 */
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

    /**
     * `@MethodSource` points to a static method that provides Arguments objects. Useful for complex
     * test data or when you need more flexibility than CSV strings.
     */
    @ParameterizedTest(name = "Last digit of ''{0}'' is ''{1}''")
    @MethodSource("getArgs")
    void testLastDigitWithMethodSource(final int number, final int expected) {
      final int actual = LastDigit.lastDigit(number);
      assertEquals(expected, actual, "Returns the last digit");
    }

    /**
     * Provider method for @MethodSource. Must be static and return a collection of Arguments.
     * Arguments.of() wraps multiple values into a single test case.
     */
    @SuppressWarnings("PMD.UnusedPrivateMethod") // Used by @MethodSource
    private static List<Arguments> getArgs() {
      return List.of(Arguments.of(2025, 5), Arguments.of(-2025, 5), Arguments.of(2020, 0));
    }
  }

  /** Utility holder for digit-related helpers used by the lesson. */
  private static final class LastDigit {
    private static int lastDigit(final int number) {
      return Math.abs(number % 10);
    }
  }

  /**
   * Demonstrates `@BeforeEach` and testing complex objects with `@MethodSource`. The `@BeforeEach`
   * method runs before each test, reducing duplication when multiple tests share setup logic.
   */
  /* default */ static class PersonTest {
    // Shared fields for all tests (initialized in @BeforeEach)
    private Person alice;
    private Person bob;

    /** Sets up test data before each test method runs. */
    @BeforeEach
    void setUp() {
      alice = new Person("Alice", "Smith");
      bob = new Person("Bob", "Jones");
    }

    /** Simple test using the shared Person objects from @BeforeEach. */
    @Test
    void testFullNameFromBeforeEach() {
      assertEquals("Alice Smith", alice.fullName(), "Alice's full name");
    }

    /** Another test using the shared Person objects from @BeforeEach. */
    @Test
    void testFullNameFromBeforeEachForBob() {
      assertEquals("Bob Jones", bob.fullName(), "Bob's full name");
    }

    /**
     * Parameterized test with complex objects. Each Arguments contains a Person and the expected
     * full name String.
     */
    @ParameterizedTest(name = "{0} should have full name ''{1}''")
    @MethodSource("personProvider")
    void testFullNameParameterized(final Person person, final String expectedFullName) {
      assertEquals(expectedFullName, person.fullName(), "Full name should match");
    }

    /**
     * Provider method supplying Person objects and their expected full names. Demonstrates how
     * Arguments can hold any object types.
     */
    @SuppressWarnings("PMD.UnusedPrivateMethod") // Used by @MethodSource
    private static List<Arguments> personProvider() {
      return List.of(
          Arguments.of(new Person("Alice", "Smith"), "Alice Smith"),
          Arguments.of(new Person("Bob", "Jones"), "Bob Jones"),
          Arguments.of(new Person("Charlie", "Brown"), "Charlie Brown"));
    }
  }

  /** Simple Person record for demonstrating object-based parameterized tests. */
  private record Person(String firstName, String lastName) {
    /* default */ String fullName() {
      return firstName + " " + lastName;
    }
  }
}

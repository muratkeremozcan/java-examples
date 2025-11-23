import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import testing.support.TestLauncher;

/** Focused example that exercises boolean assertions against a username validator. */
@SuppressWarnings("checkstyle:abbreviationaswordinname")
public class UnitTestExample3 {
  public static void main(String[] args) {
    TestLauncher.launchTestsAndPrint(
        UsernameValidatorTest.class, UsernameValidatorParameterizedTest.class);
  }

  /** Tiny utility class that represents a unit under test. */
  private static final class UsernameValidator {

    /* default */ static boolean isValidUsername(final String username) {
      return username != null
          && !username.isEmpty()
          && !username.contains(" ")
          && username.length() >= 3;
    }
  }

  /** Demonstrates assertTrue/assertFalse variations with descriptive names. */
  @SuppressWarnings("PMD.MethodNamingConventions")
  /* default */ static class UsernameValidatorTest {

    @Test
    void isValidUsername_returnsTrue_whenValidUsername() {
      boolean actual = UsernameValidator.isValidUsername("john_doe");
      assertTrue(actual, "Valid username should pass validation");
    }

    @Test
    void isValidUsername_returnsFalse_whenSpaces() {
      boolean actual = UsernameValidator.isValidUsername("john doe");
      assertFalse(actual, "Usernames with spaces should fail");
    }

    @Test
    void isValidUsername_returnsFalse_whenShortUsername() {
      boolean actual = UsernameValidator.isValidUsername("ab");
      assertFalse(actual, "Too-short usernames should fail");
    }

    @Test
    void isValidUsername_returnsFalse_whenNull() {
      boolean actual = UsernameValidator.isValidUsername(null);
      assertFalse(actual, "Null usernames should fail");
    }
  }

  /**
   * Shows the same scenarios using parameterized tests to reduce duplication: `@NullSource` +
   * `@ValueSource` for a single argument, `@CsvSource` when multiple values are needed.
   */
  @SuppressWarnings("PMD.MethodNamingConventions")
  /* default */ static class UsernameValidatorParameterizedTest {

    @ParameterizedTest(name = "Invalid username ''{0}'' should fail validation")
    @NullSource
    @ValueSource(strings = {"john doe", "", "jd"})
    void isValidUsername_returnsFalse_forInvalidInputs(final String username) {
      assertFalse(UsernameValidator.isValidUsername(username), "Invalid usernames should fail");
    }

    @ParameterizedTest(name = "Username ''{0}'' expected valid={1}")
    @CsvSource({
      "john_doe, true",
      "'john doe', false",
      "'ab', false",
      "validUser, true",
      "'', false"
    })
    void isValidUsername_matchesExpectedResult(final String username, final boolean expected) {
      boolean actual = UsernameValidator.isValidUsername(username);
      assertEquals(expected, actual, "Validation result should match expected value");
    }
  }
}

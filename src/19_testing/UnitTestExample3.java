import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import testing.support.TestLauncher;

/** Focused example that exercises boolean assertions against a username validator. */
@SuppressWarnings("checkstyle:abbreviationaswordinname")
public class UnitTestExample3 {
  public static void main(String[] args) {
    TestLauncher.launchTestsAndPrint(UsernameValidatorTest.class);
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
      String username = "john_doe";
      boolean actual = UsernameValidator.isValidUsername(username);
      assertTrue(actual, "Valid username should pass validation");
    }

    @Test
    void isValidUsername_returnsFalse_whenSpaces() {
      String username = "john doe";
      boolean actual = UsernameValidator.isValidUsername(username);
      assertFalse(actual, "Usernames with spaces should fail");
    }

    @Test
    void isValidUsername_returnsFalse_whenShortUsername() {
      String username = "ab";
      boolean actual = UsernameValidator.isValidUsername(username);
      assertFalse(actual, "Too-short usernames should fail");
    }

    @Test
    void isValidUsername_returnsFalse_whenNull() {
      String username = null;
      boolean actual = UsernameValidator.isValidUsername(username);
      assertFalse(actual, "Null usernames should fail");
    }
  }
}

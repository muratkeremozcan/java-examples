import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;
import org.junit.jupiter.api.Test;
import testing.support.TestLauncher;

/** Simple message processor showcasing assertEquals + exception assertions. */
@SuppressWarnings("checkstyle:abbreviationaswordinname")
public class UnitTestExample4 {

  public static void main(String[] args) {
    TestLauncher.launchTestsAndPrint(MessageProcessorTest.class);
  }

  /** Unit under test: uppercases messages and rejects null input. */
  private static final class MessageProcessor {

    /* default */ static String processMessage(final String message) {
      if (message == null) {
        throw new IllegalArgumentException("Message cannot be null.");
      }
      return message.toUpperCase(Locale.ROOT);
    }
  }

  @SuppressWarnings("PMD.MethodNamingConventions")
  /* default */ static class MessageProcessorTest {

    @Test
    void processMessage_returnsUppercase() {
      String message = "error!";
      String expected = "ERROR!";

      String actual = MessageProcessor.processMessage(message);

      assertEquals(expected, actual, "Message should be uppercased");
    }

    @Test
    void processMessage_throwsException_whenMessageIsNull() {
      try {
        MessageProcessor.processMessage(null);
        throw new AssertionError("Expected IllegalArgumentException when message is null.");
      } catch (IllegalArgumentException e) {
        assertEquals("Message cannot be null.", e.getMessage(), "Message mismatch");
      }
    }
  }
}

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import integration.store.ErrorStore;
import integration.store.InfoStore;
import integration.store.MessageProcessor;
import org.junit.jupiter.api.Test;
import testing.support.TestLauncher;

/** Demonstrates Mockito stubbing and verification. */
@SuppressWarnings("checkstyle:abbreviationaswordinname")
public class MockExample {
  public static void main(String[] args) {
    TestLauncher.launchTestsAndPrint(MessageProcessorTest.class);
  }

  @SuppressWarnings({"PMD.MethodNamingConventions", "PMD.JUnitTestContainsTooManyAsserts"})
  /* default */ static class MessageProcessorTest {

    @Test
    void process_savesNowhere_whenWarnMessage() {
      InfoStore infoStore = mock(InfoStore.class);
      ErrorStore errorStore = mock(ErrorStore.class);
      MessageProcessor processor = new MessageProcessor(infoStore, errorStore);
      String message = "[WARN] The search is slow.";

      processor.process(message);

      verify(infoStore, never()).save(message);
      verify(errorStore, never()).save(message);
    }

    @Test
    void process_savesInfo_whenInfoMessage() {
      InfoStore infoStore = mock(InfoStore.class);
      ErrorStore errorStore = mock(ErrorStore.class);
      MessageProcessor processor = new MessageProcessor(infoStore, errorStore);
      String message = "[INFO] Request finished.";

      processor.process(message);

      verify(infoStore).save(message);
      verify(errorStore, never()).save(message);
    }

    @Test
    void process_throwsException_whenMessageBlank() {
      InfoStore infoStore = mock(InfoStore.class);
      ErrorStore errorStore = mock(ErrorStore.class);
      MessageProcessor processor = new MessageProcessor(infoStore, errorStore);

      assertThrows(IllegalArgumentException.class, () -> processor.process("   "));
    }
  }
}

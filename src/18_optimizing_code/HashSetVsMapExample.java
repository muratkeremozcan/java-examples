import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * HashSet: store unique elements with O(1) membership checks—great when you only care if something
 * exists. HashMap: still O(1) get/put, but it associates each key with a value (counts, metadata,
 * etc.). Same Big-O, different purpose: sets answer “do I have X?”, maps answer “what data is tied
 * to X?”.
 */
public class HashSetVsMapExample {
  public static void main(String[] args) {
    hashSetDemo();
    mapDemo();
  }

  /** Shows how HashSet keeps duplicate detection O(n) by giving average O(1) membership checks. */
  private static void hashSetDemo() {
    // Linear scan through the transactions—overall O(n) time in this demo.
    TransactionProcessor processor = new TransactionProcessor();

    String[] transactions = {
      "TXN001", "TXN002", "TXN003", "TXN001",
      "TXN004", "TXN005", "TXN003", "TXN006"
    };

    boolean hasDuplicates = processor.hasDuplicateTransactions(transactions);
    if (hasDuplicates) {
      System.out.println("Duplicate transactions detected!");
    } else {
      System.out.println("All transactions are unique.");
    }
  }

  // HashSet is a collection of unique elements, allows a single null
  // HashSet’s superpower is giving you constant-time membership checks and inserts
  // in the average case O(1)
  private static final class TransactionProcessor {
    public boolean hasDuplicateTransactions(final String[] transactionIds) {
      // HashSet lookups/inserts average O(1), so the whole duplicate check stays linear.
      Set<String> seen = new HashSet<>();

      boolean duplicateFound = false;
      for (String transactionId : transactionIds) {
        if (seen.contains(transactionId)) {
          duplicateFound = true;
          break;
        } else {
          // First time seeing this transaction, add it to the set
          // We pay O(1) time and O(n) cumulative space to remember what we have seen.
          seen.add(transactionId);
        }
      }

      // Returning false means we never hit an O(1) repeat; worst-case hashing still O(n^2).
      return duplicateFound;
    }
  }

  private static void mapDemo() {
    TextAnalyzer analyzer = new TextAnalyzer();

    List<String> words =
        Arrays.asList(
            "Java",
            "is",
            "a",
            "programming",
            "language",
            "Java",
            "is",
            "widely",
            "used",
            "for",
            "building",
            "applications",
            "Many",
            "programmers",
            "use",
            "Java",
            "for",
            "web",
            "development",
            "and",
            "Android",
            "apps");

    Map<String, Integer> wordFrequency = analyzer.buildWordFrequencyMap(words);

    System.out.println("Word frequency analysis:");
    // HashMap lets us turn streaming input into counts in linear time and space
    // perfect for key/value lookups and overwrites
    for (Entry<String, Integer> entry : wordFrequency.entrySet()) {
      // Key/value pairing: the word is the key, its frequency is the value we update in O(1).
      System.out.println(entry.getKey() + ": " + entry.getValue() + " occurrences");
    }
  }

  private static final class TextAnalyzer {
    public Map<String, Integer> buildWordFrequencyMap(final List<String> words) {
      // Map updates stay amortized O(1), so building the histogram is O(n).
      Map<String, Integer> frequencyMap = new HashMap<>();

      for (String word : words) {
        if (word.isEmpty()) {
          continue;
        }

        String normalizedWord = word.toLowerCase(Locale.ROOT);

        if (frequencyMap.containsKey(normalizedWord)) {
          // Increment happens in-place: still O(1) time for the update.
          int currentCount = frequencyMap.get(normalizedWord);
          frequencyMap.put(normalizedWord, currentCount + 1);
        } else {
          // First occurrence adds a new key, growing space usage linearly with unique words.
          frequencyMap.put(normalizedWord, 1);
        }
      }

      return frequencyMap;
    }
  }
}

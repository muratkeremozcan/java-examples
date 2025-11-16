import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Benchmark-friendly demo contrasting O(n) list scans vs. O(1) hash lookups. */
public class OnExample {
  /** Populate a list of contacts and perform a simple linear search. */
  public static void main(String[] args) {
    ContactManager manager = new ContactManager();

    // Edit numberOfContacts to see how it affects execution time.
    int numberOfContacts = 1000;
    for (int i = 0; i < numberOfContacts; i++) {
      manager.addContact("Contact_" + i);
    }

    String target = "Contact_" + (numberOfContacts - 1);

    Contact linear = manager.findContactLinear(target);
    Contact viaMap = manager.findContactMap(target);
    System.out.println(
        "Linear search result: " + (linear != null ? linear.getName() : "not found"));
    System.out.println(
        "HashMap search result: " + (viaMap != null ? viaMap.getName() : "not found"));
  }

  /**
   * Manages contacts in both structures: an ArrayList (for O(n) demonstrations) and a HashMap (for
   * typical-case O(1) lookups).
   */
  private static final class ContactManager {
    private final List<Contact> contacts;
    // HashMap provides typical O(1) lookups for known names.
    private final Map<String, Contact> index;

    private ContactManager() {
      contacts = new ArrayList<>();
      index = new HashMap<>();
    }

    private void addContact(final String name) {
      // ArrayList part: O(1) append so we can later demonstrate linear scans.
      Contact contact = new Contact(name);
      contacts.add(contact);
      // HashMap part: O(1) insert so keyed lookups skip the linear scan entirely.
      index.put(name, contact);
    }

    private Contact findContactLinear(final String name) {
      // O(n): we may inspect every contact until we find the match (or finish the list).
      Contact match = null;
      for (Contact contact : contacts) {
        if (contact.getName().equals(name)) {
          match = contact;
          break;
        }
      }
      return match;
    }

    // HashMap lookup avoids the linear walk for subsequent queries.
    private Contact findContactMap(final String name) {
      // Average-case O(1): direct hash bucket lookup by key.
      return index.get(name);
    }
  }

  /** Simple immutable contact record used by the demo. */
  private static final class Contact {
    private final String name;

    private Contact(final String name) {
      this.name = name;
    }

    private String getName() {
      return name;
    }
  }
}

import java.util.ArrayList;
import java.util.List;

/**
 * Benchmark-friendly contact search example for O(n) lookups.
 *
 * <ul>
 *   <li>Linear search in an ArrayList is O(n); every lookup scans the whole list until it finds a
 *       match.
 *   <li>Push more rows through `numberOfContacts` to feel how the runtime grows linearly.
 * </ul>
 */
public class OnExample {
  /** Populate a list of contacts and perform a simple linear search. */
  public static void main(String[] args) {
    ContactManager manager = new ContactManager();

    // Edit numberOfContacts to see how it affects execution time.
    int numberOfContacts = 1000;
    for (int i = 0; i < numberOfContacts; i++) {
      manager.addContact("Contact_" + i);
    }

    Contact result = manager.findContact("Contact_" + (numberOfContacts - 1));
    System.out.println("Found: " + (result != null ? result.getName() : "not found"));
  }

  /** Manages contacts in an ArrayList and exposes simple add/find operations. */
  private static final class ContactManager {
    private final List<Contact> contacts;

    private ContactManager() {
      contacts = new ArrayList<>();
    }

    private void addContact(final String name) {
      contacts.add(new Contact(name));
    }

    private Contact findContact(final String name) {
      Contact match = null;
      for (Contact contact : contacts) {
        if (contact.getName().equals(name)) {
          match = contact;
          break;
        }
      }
      return match;
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

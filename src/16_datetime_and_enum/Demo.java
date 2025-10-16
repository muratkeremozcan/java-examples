import java.time.LocalDate;

/** Tiny subscription demo showing nested classes and a static helper workflow. */
@SuppressWarnings("PMD.ShortClassName")
public class Demo {

  /** Kicks off the subscription example. */
  public static void main(String[] args) {
    Subscription.main();
    UserStateManager.main();
  }

  /** Simple nested type so multiple classes can live in one file for quick demos. */
  public static class Subscription {

    /** Calculates an expiration date and prints it. */
    public static void main() {
      LocalDate start = LocalDate.now();
      LocalDate expiration = calculateExpiration(start, 60);
      System.out.println("Expiration date: " + expiration);
    }

    private static LocalDate calculateExpiration(final LocalDate date, final int days) {
      return date.plusDays(days);
    }
  }

  /** Quick enum demo to show state checking. */
  public static class UserStateManager {

    /** Possible states a user can be in for this mini demo. */
    private enum UserState {
      NEW,
      ACTIVE,
      SUSPENDED;

      public boolean isActiveUser() {
        return this == ACTIVE;
      }
    }

    /** Prints whether the sample users are active. */
    public static void main() {
      UserState suspendUser = UserState.SUSPENDED;
      UserState activeUser = UserState.ACTIVE;

      System.out.println(suspendUser.isActiveUser());
      System.out.println(activeUser.isActiveUser());
    }
  }
}

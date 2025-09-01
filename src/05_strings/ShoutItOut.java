import java.util.Locale;

class ShoutItOut {
  public static void main(String[] args) {
    final String message = "It's a good day to have a good day!";

    System.out.println(message.toUpperCase(Locale.ROOT));

    System.out.println(message.toLowerCase(Locale.ROOT));
  }
}

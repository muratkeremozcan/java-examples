/**
 * Quick tour of enums: directional constants and HTTP status codes in a single place.
 *
 * <p>Why it matters: enums give type-safe labels instead of loose strings or ints, making switch
 * statements and domain checks a lot safer.
 */
public class EnumDemo {

  /** demo */
  public static void main(String[] args) {
    printDirection(Direction.EAST);
    System.out.println(HttpStatus.OK.getCode());
  }

  /** simple enum */
  private enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST
  }

  private static void printDirection(final Direction direction) {
    System.out.println("Direction: " + direction);
  }

  /////////

  /** Enums with custom state and methods. */
  private enum HttpStatus {
    OK(200),
    NOT_FOUND(404);

    private final int code;

    HttpStatus(final int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }
  }
}

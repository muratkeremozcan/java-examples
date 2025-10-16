/** Simple enum demo: directional labels plus HTTP status codes. */
@SuppressWarnings("PMD.CommentDefaultAccessModifier")
public class EnumDemo {

  /** Entry point that prints a direction and status code. */
  public static void main(String[] args) {
    printDirection(Direction.EAST);
    System.out.println(HttpStatus.OK.getCode());
  }

  /** Compass directions as an enum. */
  private enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST
  }

  /** Logs whichever direction was supplied. */
  private static void printDirection(final Direction direction) {
    System.out.println("Direction: " + direction);
  }

  /////////

  /** Minimal HTTP status catalog to show enums with state. */
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

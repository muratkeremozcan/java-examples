public class Squarer {
  public static void main(String[] args) {
    final int hours = squareOf5();
    System.out.println("I'm working " + hours + " hours a week");
  }

  public static int squareOf5() {
    return 5 * 5;
  }
}

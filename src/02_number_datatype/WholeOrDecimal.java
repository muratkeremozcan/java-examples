// enter a note about when to use which numeric data type
// whole numbers: byte, short, int, long
// decimal numbers: double, float

class WholeOrDecimal {
  public static void main(String[] args) {

    final byte dressSize = 8; // use byte for small whole numbers
    final float shoeSize = 11.5F; // use float for long decimal numbers
    final int dressPrice = 55; // use int for medium whole numbers
    final double shoePrice = 49.9; // use double for short decimal numbers

    System.out.println(dressSize + " " + shoeSize + " " + dressPrice + " " + shoePrice);
  }
}

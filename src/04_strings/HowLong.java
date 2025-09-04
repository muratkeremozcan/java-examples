class HowLong {
  public static void main(String[] args) {
    final String bookName = "Good Omens: The Nice and Accurate Prophecies of Agnes Nutter, Witch";

    final int bookNameLength = bookName.length();
    final int authorNameLength = "Terry Pratchett".length();

    System.out.println("The length of the book title is " + bookNameLength);
    System.out.println("The length of the author's name is " + authorNameLength);
  }
}

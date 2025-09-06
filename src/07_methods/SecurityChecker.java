class SecurityChecker {
  public static void main(String[] args) {
    final String url = "https://www.datacamp.com";
    final int charPosition = 4;

    System.out.println("s means secure : ");
    System.out.println(url.charAt(charPosition));
  }
}

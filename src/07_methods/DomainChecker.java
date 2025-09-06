class DomainChecker {
  public static void main(String[] args) {
    final String toFind = ".gov";
    final String url = "https://www.usa.gov/holidays";

    System.out.println(url.contains(toFind));
  }
}

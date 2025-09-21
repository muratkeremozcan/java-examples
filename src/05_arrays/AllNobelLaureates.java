class AllNobelLaureates {
  public static void main(String[] args) {

    final String[] physicsLaureates = {
      "Curie", "Einstein", "Fermi"
    }; // objects are considered String[] in Java
    final String[] literatureLaureates = {"Kipling", "Shaw", "Hemingway", "Seifert"};
    final String[] chemistryLaureates = {"Curie", "Heyrovsky"};

    System.out.println(physicsLaureates.length + " physics laureates");
    System.out.println(literatureLaureates.length + " literature laureates");
    System.out.println(chemistryLaureates.length + " chemistry laureates");
  }
}

class Speller {
  public static void main(String[] args) {
    final String toSpell = "conscientious";
    final int wordLength = toSpell.length();

    for (int i = 0; i < wordLength; i++) {
      System.out.println(toSpell.charAt(i));
    }
  }
}

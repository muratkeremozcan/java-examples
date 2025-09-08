public class GradeChecker {
  private static final int FAILING_GRADE = 10;
  private static final int PASSING_GRADE = 14;
  private static final int DISTINCTION_GRADE = 18;

  @SuppressWarnings("unused")
  public static void main(String[] args) {
    final int grade = 14;

    if (grade < FAILING_GRADE) {
      System.out.println("Failing grade");
    } else if (grade < PASSING_GRADE) {
      System.out.println("Passing grade");
    } else if (grade < DISTINCTION_GRADE) {
      System.out.println("With distinction");
    } else {
      System.out.println("With highest distinction");
    }
  }
}

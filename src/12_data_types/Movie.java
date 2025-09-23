// Plain old Java object (POJO)
// POJO class and getters/setters should be public
// POJO fields should be private
// POJOs should extend no class, and implement no interfaces.
// POJOs have a no-argument constructor

@SuppressWarnings({
  "checkstyle:OneTopLevelClass",
  "PMD.ShortClassName",
  "PMD.TestClassWithoutTestCases",
  "PMD.LocalVariableCouldBeFinal"
})
class Test2 {

  /**
   * Demonstrates basic Movie POJO usage.
   *
   * @param args command line arguments (unused)
   */
  public static void main(final String[] args) {
    Movie jaws = new Movie();
    jaws.setTitle("Jaws");
    jaws.setDirector("Spielberg");
    jaws.setRating("PG");
    System.out.println(jaws.getRating());
  }
}

/** Simple POJO representing a movie record. */
@SuppressWarnings({"PMD.DataClass", "PMD.AtLeastOneConstructor"})
public class Movie {
  private String title;
  private String director;
  private String rating;

  public String getTitle() {
    return title;
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  public String getDirector() {
    return director;
  }

  public void setDirector(final String director) {
    this.director = director;
  }

  public String getRating() {
    return rating;
  }

  public void setRating(final String rating) {
    this.rating = rating;
  }
}

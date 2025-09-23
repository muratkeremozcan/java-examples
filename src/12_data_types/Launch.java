import java.math.BigDecimal;

/** Demonstrates invoking behavior on a simple rocket POJO. */
public class Launch {

  /**
   * Sets up a rocket instance and triggers a mock launch sequence.
   *
   * @param args command line arguments (unused)
   */
  public static void main(final String[] args) {
    // Create an instance of the Rocket POJO
    final Rocket rocket = new Rocket();
    rocket.setName("Saturn");
    // Set the thrust field to as a BigDecimal
    rocket.setThrust(new BigDecimal("7770000"));
    // Set the rocket's manned Boolean wrapper field to true
    rocket.setManned(true);
    fire(rocket);
  }

  /**
   * Performs a simple calculation before printing launch status.
   *
   * @param r rocket instance configured by the caller
   */
  @SuppressWarnings("PMD.LawOfDemeter")
  public static void fire(final Rocket r) {
    final BigDecimal newtons = r.getThrust().divide(BigDecimal.valueOf(224));
    System.out.println("We have liftoff of: " + r.getName());
    System.out.print("Thrust is: " + newtons + " newtons of energy");
  }

  /** Simple POJO representing the properties of a rocket launch vehicle. */
  @SuppressWarnings("PMD.DataClass")
  public static class Rocket {

    private String name;
    private BigDecimal thrust;
    private Boolean manned;

    public String getName() {
      return name;
    }

    public void setName(final String name) {
      this.name = name;
    }

    public BigDecimal getThrust() {
      return thrust;
    }

    public void setThrust(final BigDecimal thrust) {
      this.thrust = thrust;
    }

    public Boolean isManned() {
      return manned;
    }

    public void setManned(final Boolean manned) {
      this.manned = manned;
    }
  }
}

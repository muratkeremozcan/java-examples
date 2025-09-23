// Plain old Java object (POJO)
// POJO class and getters/setters should be public
// POJO fields should be private
// POJOs should extend no class, and implement no interfaces.
// POJOs have a no-argument constructor

@SuppressWarnings({
  "checkstyle:OneTopLevelClass",
  "PMD.ShortClassName",
  "PMD.TestClassWithoutTestCases",
  "PMD.LocalVariableCouldBeFinal",
  "PMD.UseUnderscoresInNumericLiterals"
})
class Test {
  /**
   * Demonstrates simple access to the SavingsAccount POJO.
   *
   * @param args command line arguments (unused)
   */
  public static void main(final String[] args) {
    SavingsAccount account = new SavingsAccount();
    account.setAccountNo("12345");
    account.setBalance(50000.00);
    System.out.println(
        "Account " + account.getAccountNo() + " has balance of: " + account.getBalance());
  }
}

/** Simple POJO with getters and setters for account details. */
@SuppressWarnings({"PMD.DataClass", "PMD.AtLeastOneConstructor"})
public class SavingsAccount {
  private String accountNo;
  private double balance;

  public String getAccountNo() {
    return accountNo;
  }

  public void setAccountNo(final String accountNo) {
    this.accountNo = accountNo;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(final double balance) {
    this.balance = balance;
  }
}

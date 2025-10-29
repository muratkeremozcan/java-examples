import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

/**
 * Demonstrates Jakarta Bean Validation on a sales record.
 *
 * <ul>
 *   <li>Annotate fields with constraints (e.g., {@code @NotNull}, {@code @Min}) instead of manual
 *       if-checks.
 *   <li>Ask the shared {@link Validator} for violations; it returns a set describing every broken
 *       rule.
 *   <li>Surface problems early—log them, throw, or map them to an HTTP 400—before dirty data keeps
 *       flowing.
 * </ul>
 */
public class ValidatorDemo {
  /** Run the validation demo and throw if constraints fail. */
  public static void main(String[] args) {
    GameSale invalidSale = new GameSale("Super Mario Bros.", null, 1950, 29.08);
    Set<ConstraintViolation<GameSale>> violations = SalesValidator.validateSale(invalidSale);

    violations.forEach(v -> System.out.println(v.getPropertyPath() + ": " + v.getMessage()));

    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  /** Simple value object representing a game sale with validation annotations. */
  private static final class GameSale {
    @NotNull(message = "Name cannot be empty")
    private final String name;

    // Annotated constraints replace manual null/empty checks.
    @NotNull(message = "Platform cannot be empty")
    private final String platform;

    // Declarative minimum keeps legacy years from slipping through.
    @Min(value = 1960, message = "Year must be greater than 1960")
    private final Integer year;

    // Reject negative sales without an imperative if/throw block.
    @Min(value = 0, message = "Sales amount must be positive")
    private final Double sales;

    private GameSale(
        final String name, final String platform, final Integer year, final Double sales) {
      this.name = name;
      this.platform = platform;
      this.year = year;
      this.sales = sales;
    }
  }

  /** Wrapper around the Jakarta Validator. */
  private static final class SalesValidator {
    private static final ValidatorFactory FACTORY = Validation.buildDefaultValidatorFactory();
    private static final Validator VALIDATOR = FACTORY.getValidator();

    public static Set<ConstraintViolation<GameSale>> validateSale(final GameSale sale) {
      // ask the Validator; it returns everything that broke a constraint in one pass
      return VALIDATOR.validate(sale);
    }
  }
}

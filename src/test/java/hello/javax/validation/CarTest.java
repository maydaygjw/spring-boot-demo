package hello.javax.validation;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by Junwen on 6/17/15.
 */
public class CarTest {
    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testLicensePlateNotUpperCase() {

        Car car = new Car("Morris", "dd-ab-123", 4, "F");

        Set<ConstraintViolation<Car>> constraintViolations =
                validator.validate(car);
        assertEquals(1, constraintViolations.size());
        assertEquals(
                "Case mode must be UPPER.",
                constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void carIsValid() {

        Car car = new Car("Morris", "DD-AB-123", 4, "F");

        Set<ConstraintViolation<Car>> constraintViolations =
                validator.validate(car);

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void testLombokNonNull() {
        try {
            Car car = new Car("Morris", "DD-AB-123", 4, null);
            car.setSex(null);
        }catch (NullPointerException e) {
            System.out.println("THis is expected");
        }

    }
}

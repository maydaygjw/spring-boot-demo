package hello.javax.validation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Created by Junwen on 6/17/15.
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = CheckCaseValidator.class)
@Documented
public @interface CheckCase {

    String message() default "{hello.javax.validation.checkcase}";  //check ValidationMessage.properties under classpath out

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    CaseMode caseMode();

}

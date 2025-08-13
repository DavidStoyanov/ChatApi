package org.myapp.chat_api.util.validator.field_qnique;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

//@FieldMismatch(first = "email", second = "username", message = "Email and username must not be the same")

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldMismatchValidator.class)
@Documented
public @interface FieldMismatch {
    String message() default "Fields must not match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String first();
    String second();

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        FieldMismatch[] value();
    }
}
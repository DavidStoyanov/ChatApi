package org.myapp.chat_api.util.validator.field_qnique;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;

public class FieldMismatchValidator implements ConstraintValidator<FieldMismatch, Object> {

    private String firstFieldName;
    private String secondFieldName;
    private String message;

    @Override
    public void initialize(FieldMismatch constraintAnnotation) {
        this.firstFieldName = constraintAnnotation.first();
        this.secondFieldName = constraintAnnotation.second();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            Object firstValue = BeanUtils.getProperty(value, firstFieldName);
            Object secondValue = BeanUtils.getProperty(value, secondFieldName);

            if (firstValue != null && firstValue.equals(secondValue)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(message)
                        .addPropertyNode(firstFieldName)
                        .addConstraintViolation();
                return false;
            }
        } catch (Exception e) {
            // Handle reflection exceptions
        }
        return true;
    }
}
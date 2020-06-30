package com.dev.testquest.model.validator;

import com.dev.testquest.lib.FieldMatchConstraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldMatchValidator implements ConstraintValidator<FieldMatchConstraint, Object> {
    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldMatchConstraint passwordMatches) {
        this.field = passwordMatches.field();
        this.fieldMatch = passwordMatches.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
        Object fieldValueMatch = new BeanWrapperImpl(value).getPropertyValue(fieldMatch);

        return fieldValue != null && fieldValue.equals(fieldValueMatch);
    }
}

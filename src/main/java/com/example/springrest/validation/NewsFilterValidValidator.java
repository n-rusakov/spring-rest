package com.example.springrest.validation;

import com.example.springrest.web.filter.NewsFilter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class NewsFilterValidValidator implements
        ConstraintValidator<NewsFilterValid, NewsFilter> {

    @Override
    public boolean isValid(NewsFilter filter,
                           ConstraintValidatorContext constraintValidatorContext) {

        if (filter.getPageSize() == null || filter.getPageNumber() == null) {
            return false;
        }

        return true;
    }

}

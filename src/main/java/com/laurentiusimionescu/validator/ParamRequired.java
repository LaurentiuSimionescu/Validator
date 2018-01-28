package com.laurentiusimionescu.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ParamRequired {

    /* Default is without regex. */
    String regex() default "";

    /**
     * Default rule is without a rule.
     * Available rules: {@link ValidatorRule}
     */
    ValidatorRule rule() default ValidatorRule.NO_RULE;

}

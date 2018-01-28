package com.laurentiusimionescu.validator;

import android.support.annotation.NonNull;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class ValidationProcessor {

    private static final String VALIDATION_FAIL_NULL = "is required and is null";
    private static final String VALIDATION_FAIL_EMPTY = "is required and is empty";
    private static final String VALIDATION_FAIL_REGEX = "doesn't match regex";
    private static final String VALIDATION_ERROR = "validation error";

    public static boolean isValid(@NonNull Object object) {
        if (object == null) {
            throw new RuntimeException("Object cannot be null");
        }
        try {
            return checkIfValid(object, object.getClass().getSimpleName()) == null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getMessage(@NonNull Object object) {
        try {
            return checkIfValid(object, object.getClass().getSimpleName());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return VALIDATION_ERROR;
        }
    }

    /* This method returns the message if the object is not valid, else will return null if the object is valid. */
    private static String checkIfValid(@NonNull Object object, String path) throws IllegalAccessException {
        String errorMessage = null;

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(object);

            if (field.isAnnotationPresent(ParamRequired.class) && field.isAnnotationPresent(ParamOptional.class)) {
                throw new RuntimeException("A field cannot be annotated with both ParamRequired and ParamOptional. Please remove one.");
            }

            if (value != null && field.getClass().getDeclaredFields() != null && field.getClass().getDeclaredFields().length > 0 && !getWrapperTypes().contains(value.getClass())) {
                String message = checkIfValid(value, path + "." + value.getClass().getSimpleName());
                if (message != null) {
                    errorMessage = message;
                    return errorMessage;
                }
            }

            if (field.isAnnotationPresent(ParamRequired.class)) {
                errorMessage = requiredCheck(object, field, path);
                if (errorMessage != null) {
                    return errorMessage;
                }
            }

            if (field.isAnnotationPresent(ParamOptional.class)) {
                errorMessage = optionalCheck(object, field, path);
                if (errorMessage != null) {
                    return errorMessage;
                }
            }

        }

        return errorMessage;
    }

    private static String requiredCheck(@NonNull Object object, Field field, @NonNull String path) throws IllegalAccessException {
        Object value = field.get(object);

        if (value == null) {
            return path + "." + field.getName() + " " + VALIDATION_FAIL_NULL;
        }

        if (value instanceof String && ((String) value).isEmpty()) {
            return path + "." + field.getName() + " " + VALIDATION_FAIL_EMPTY;
        }

        if (value instanceof String && !((String) value).isEmpty()) {
            return regexCheckRequired((String) value, field, path);
        }

        return null;

    }

    private static String optionalCheck(@NonNull Object object, Field field, @NonNull String path) throws IllegalAccessException {
        Object value = field.get(object);

        if (value instanceof String && !((String) value).isEmpty()) {
            return regexCheckOptional((String) value, field, path);
        }

        return null;

    }


    private static String regexCheckRequired(@NonNull String value, Field field, @NonNull String path) throws IllegalAccessException {
        ParamRequired paramRequired = field.getAnnotation(ParamRequired.class);

        if (paramRequired.rule() != ValidatorRule.NO_RULE && !paramRequired.regex().isEmpty()) {
            throw new RuntimeException("Choose only a regex or a predefined rule.");
        }

        if (paramRequired.rule() != ValidatorRule.NO_RULE) {
            if (!Pattern.compile(paramRequired.rule().getRegex(), Pattern.CASE_INSENSITIVE).matcher(value).find()) {
                return path + "." + field.getName() + " is required and " + VALIDATION_FAIL_REGEX + " : " + paramRequired.rule().getRegex();
            }
        } else {
            if (!Pattern.compile(paramRequired.regex(), Pattern.CASE_INSENSITIVE).matcher(value).find()) {
                return path + "." + field.getName() + " is required and " + VALIDATION_FAIL_REGEX + " : " + paramRequired.regex();
            }
        }

        return null;
    }

    private static String regexCheckOptional(@NonNull String value, Field field, @NonNull String path) throws IllegalAccessException {
        ParamOptional paramOptional = field.getAnnotation(ParamOptional.class);

        if (paramOptional.rule() != ValidatorRule.NO_RULE && !paramOptional.regex().isEmpty()) {
            throw new RuntimeException("Choose only a regex or a predefined rule.");
        }

        if (paramOptional.rule() != ValidatorRule.NO_RULE) {
            if (!Pattern.compile(paramOptional.rule().getRegex(), Pattern.CASE_INSENSITIVE).matcher(value).find()) {
                return path + "." + field.getName() + " is optional but " + VALIDATION_FAIL_REGEX + " : " + paramOptional.rule().getRegex();
            }
        } else {
            if (!Pattern.compile(paramOptional.regex(), Pattern.CASE_INSENSITIVE).matcher(value).find()) {
                return path + "." + field.getName() + " is optional but " + VALIDATION_FAIL_REGEX + " : " + paramOptional.regex();
            }
        }

        return null;
    }

    private static Set<Class<?>> getWrapperTypes() {
        Set<Class<?>> ret = new HashSet<Class<?>>();
        ret.add(Boolean.class);
        ret.add(Character.class);
        ret.add(Byte.class);
        ret.add(Short.class);
        ret.add(Integer.class);
        ret.add(Long.class);
        ret.add(Float.class);
        ret.add(Double.class);
        ret.add(Void.class);
        ret.add(String.class);
        return ret;
    }


}

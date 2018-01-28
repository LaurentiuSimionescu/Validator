package com.laurentiusimionescu.validator;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class ValidatorTest {

    @Test
    public void testNullString() {

        Example example = new Example();

        assertFalse(ValidationProcessor.isValid(example));
    }

    @Test
    public void testEmptyString() {

        Example example = new Example();

        assertFalse(ValidationProcessor.isValid(example));
    }


    @Test
    public void testAllGood() {
        Example example = new Example();
        example.dog = "Dog";
        example.boo = 12;
        example.innerClass = new InnerClass();
        example.innerClass.innerString = "i1";

        example.innerClass3 = new InnerClass();
        example.innerClass3.innerString = "i3";

        example.castClass = new CastClass();
        example.castClass.castClass = new CastClass();


        assertTrue(ValidationProcessor.getMessage(example), ValidationProcessor.isValid(example));

    }

    @Test
    public void testInteger() {
        Example example = new Example();


        assertFalse(ValidationProcessor.isValid(example));

    }

    @Test
    public void testDouble() {
        Example example = new Example();

        assertFalse(ValidationProcessor.isValid(example));

    }


    public class Example {

        @ParamRequired
        String dog;

        @ParamOptional(rule = ValidatorRule.EMAIL)
        String email = "abc@gmail.com";

        String foo;

        @ParamRequired
        Integer boo;

        @ParamRequired
        Integer boo2 = 90;

        @ParamRequired
        InnerClass innerClass;

        InnerClass innerClass2;

        @ParamRequired
        InnerClass innerClass3 = new InnerClass();

        @ParamOptional
        CastClass castClass;

    }

    class InnerClass {

        @ParamRequired
        String innerString;

    }

    class CastClass {

        @ParamOptional(rule = ValidatorRule.EMAIL)
        String name = "asdf@sdf.com";

        @ParamOptional
        CastClass castClass;

    }

    class Foo {

        @ParamRequired
        String title;

        @ParamOptional
        String description;

    }


}

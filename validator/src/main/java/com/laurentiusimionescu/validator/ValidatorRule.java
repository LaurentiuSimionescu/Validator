package com.laurentiusimionescu.validator;

public enum ValidatorRule {

    /* A rule represents a predefined regex. The most common regex are for: email and password.
    To use a custom regex type it in the Annotation Eg. @ParamOptional(regex = "abc") */

    NO_RULE(null), // default rule

    CREDIT_CARD("^(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14}|6011[0-9]{12}|3(?:0[0-5]|[68][0-9])[0-9]{11}|3[47][0-9]{13})$"), // This regular expression will validate all major credit cards: American Express (Amex), Discover, Mastercard, and Visa. Note that it is not quite as precise as its counterpart Perl and PHP regex.
    CREDIT_CARD_VISA("^(4[0-9]{12}(?:[0-9]{3})?)*$"),
    CREDIT_CARD_MASTERCARD("^(5[1-5][0-9]{14})*$"),
    CREDIT_CARD_AMEX("^(3[47][0-9]{13})*$"),

    DATE_DD_MM_YYYY("^([1-9]|0[1-9]|[12][0-9]|3[01])\\D([1-9]|0[1-9]|1[012])\\D(19[0-9][0-9]|20[0-9][0-9])$"), // usual date format (dd mm yyyy, d/m/yyyy, etc.)
    DATE_MM_DD_YYYY("^(0?[1-9]|1[012])[- /.](0?[1-9]|[12][0-9]|3[01])[- /.](19|20)?[0-9]{2}$"), // Validate the calendar date in MM/DD/YYYY format with this regex. Optional separators are spaces, hyphens, forward slashes, and periods. The year is limited between 1900 and 2099.
    DATE_YYYY_MM_DD("^(19|20)?[0-9]{2}[- /.](0?[1-9]|1[012])[- /.](0?[1-9]|[12][0-9]|3[01])$"), // Validate the calendar date in YYYY/MM/DD format with this regex. Optional separators are spaces, hyphens, forward slashes, and periods. The year is limited between 1900 and 2099.

    EMAIL("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$"),
    USERNAME("^[\\w\\d_.]{4,}$"), // Common username

    PASSWORD("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"), // complex password, look below for explanation
    PASSWORD_6_CHAR("^.{6,}$"), // Simple password, enter 6 or more chars

    PHONE_NUMBER("^\\+?[\\d\\s]{3,}$"),
    PHONE_NUMBER_WITH_CODE("^\\+?[\\d\\s]+\\(?[\\d\\s]{10,}$"),
    PHONE_NUMBER_NORTH_AMERICA("^(([0-9]{1})*[- .(]*([0-9]{3})[- .)]*[0-9]{3}[- .]*[0-9]{4})+$"), // This regex will validate a 10-digit North American telephone number. Separators are not required, but can include spaces, hyphens, or periods. Parentheses around the area code are also optional.

    DIGITS_ONLY("^[0-9]+$"),
    NUMBER_POSITIVE("^\\d*\\.?\\d+$"),
    NUMBER_NEGATIVE("^-\\d*\\.?\\d+$"),

    ALPHA_NUMERIC("^[a-zA-Z0-9]*$"), // Alpha-numeric characters only
    ALPHA_NUMERIC_WITH_SPACES("^[a-zA-Z0-9 ]*$"), // Alpha-numeric characters with spaces only
    ALPHABETIC("^[a-zA-Z]*$"), // Alphabetic characters only
    ALPHABETIC_UPPERCASE("^([A-Z])*$"), // Uppercase letters only
    ALPHABETIC_LOWERCASE("^([a-z])*$"), // Lowercase letters only


    DOMAIN("^([a-z][a-z0-9-]+(\\.|-*\\.))+[a-z]{2,6}$"),
    URL("^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$"),
    IP_V4("^(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]){3}$"),

    STATES_US("^(?:A[KLRZ]|C[AOT]|D[CE]|FL|GA|HI|I[ADLN]|K[SY]|LA|M[ADEINOST]|N[CDEHJMVY]|O[HKR]|PA|RI|S[CD]|T[NX]|UT|V[AT]|W[AIVY])*$"), // Validate all 2-letter US State abbreviates with this regular expression.
    ZIP_CODE_US("^[0-9]{5}(?:-[0-9]{4})?$"), // This regexp verifies US ZIP Codes, with an optional 4 number ZIP code extension.

    POSTAL_CODE_CANADA("^[ABCEGHJKLMNPRSTVXY][0-9][A-Z] [0-9][A-Z][0-9]$"), // Canadian Postal Codes
    POSTAL_CODE_UK("^([A-Z]{1,2}[0-9][A-Z0-9]? [0-9][ABD-HJLNP-UW-Z]{2})*$"), // This regexp verifies UK Postal Codes.
    POSTAL_CODE_AUSTRALIA("^((0[289][0-9]{2})|([1345689][0-9]{3})|(2[0-8][0-9]{2})|(290[0-9])|(291[0-4])|(7[0-4][0-9]{2})|(7[8-9][0-9]{2}))*$"); // If you need to verify Australian Postal Codes, use this regular expression.

    /**
     * ^                 # start-of-string
     * (?=.*[0-9])       # a digit must occur at least once
     * (?=.*[a-z])       # a lower case letter must occur at least once
     * (?=.*[A-Z])       # an upper case letter must occur at least once
     * (?=.*[@#$%^&+=])  # a special character must occur at least once
     * (?=\S+$)          # no whitespace allowed in the entire string
     * .{8,}             # anything, at least eight places though
     * $                 # end-of-string
     */

    private String regex;

    ValidatorRule(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }

}

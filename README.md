# Validator
<p align="center">
  <img src="https://github.com/LaurentiuSimionescu/Validator/blob/master/v.png" width="150"/>
</p>

This library is written in pure Java and can be used in Java projects or Android projects. It offers easy validation for credit cards, email, password, zip code, url, date etc.

# Download
  - Gradle
``` gradle
repositories {
  maven { url 'https://jitpack.io' }
}
    
dependencies {
  implementation 'com.github.LaurentiuSimionescu:Validator:1.0'
}
```
  - Maven
``` xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependency>
  <groupId>com.github.LaurentiuSimionescu</groupId>
  <artifactId>Validator</artifactId>
  <version>-1.0</version>
</dependency>
```


# Table of Contents:
 - Credit Cards
    - CREDIT_CARD
    - CREDIT_CARD_VISA
    - CREDIT_CARD_MASTERCARD
    - CREDIT_CARD_AMEX
 - Date
    - DATE_DD_MM_YYYY
    - DATE_MM_DD_YYYY
    - DATE_YYYY_MM_DD
 - Credentials
    - EMAIL
    - USERNAME
    - PASSWORD
    - PASSWORD_6_CHAR
 - Phone Numbers
    - PHONE_NUMBER
    - PHONE_NUMBER_WITH_CODE
    - PHONE_NUMBER_NORTH_AMERICA
 - Numbers
    - DIGITS_ONLY
    - NUMBER_POSITIVE
    - NUMBER_NEGATIVE
 - Url
    - DOMAIN
    - URL
    - IP_V4
 - Zip Code & Postal Code
    - STATES_US
    - ZIP_CODE_US
    - POSTAL_CODE_CANADA
    - POSTAL_CODE_UK
    - POSTAL_CODE_AUSTRALIA
 - Misc
    - ALPHA_NUMERIC
    - ALPHA_NUMERIC_WITH_SPACES
    - ALPHABETIC
    - ALPHABETIC_UPPERCASE
    - ALPHABETIC_LOWERCASE

Annotations availabe:
 1. @ParamRequired - annotated field must be present (null or empty = invalid)
 2. @ParamOptional - annotated field is optional (null or empty = valid) else must match regex or rule

# Simple usage

``` java
class Foo {

    @ParamRequired
    String title;

    @ParamOptional
    String description;
    
}

  Foo foo = new Foo();
  ValidationProcessor.isValid(foo); // fail
  
  foo.title = "title";
  ValidationProcessor.isValid(foo); // success


```
# Using regex and ValidatorRule

``` java
class Credentials {

    @ParamRequired(rule = ValidatorRule.EMAIL)
    String email;

    @ParamRequired(rule = ValidatorRule.PASSWORD_6_CHAR)
    String password;
    
    @ParamOptional(rule = ValidatorRule.DATE_DD_MM_YYYY)
    String date;
    
    @ParamOptional(regex = "^\+?[\d\s]{3,}$")
    String phoneNumber;
    
}

  Credentials credentials = new Credentials();
  credentials.email = "abcd@mail.com";    // valid
  credentials.password = "123456"; // valid
  credentials.date = "02/12/2018"; // valid
  // credentials.date = "1234" // invalid
  ValidationProcessor.isValid(credentials); // success
```
# Contribute
First off, thanks for taking the time to contribute! Now, take a moment to be sure your contributions make sense to everyone else. When creating a new rule make sure it doesn't exists already and test it before.
# License
```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

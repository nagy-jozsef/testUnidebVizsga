# language: en
Feature: YorLogo Account processes

  Background:
    Given YourLogo home page opened
    And The My Account link is clicked
    # And The Sign in link is clicked

  Scenario: False login
    Given The Sign In button is clicked
    Then An email address required error message is shown

  Scenario Outline: Succesfull login
    Given The <field> is filled <value>
    And The <field1> is filled <value1>
    And The Sign In button is clicked
    Then A MY ACCOUNT title is shown
    Examples:
      | field     | value      | field1     | value1     |
      | 'email' | 'nj@unideb.com' | 'passwd' | 'PWD123@' |


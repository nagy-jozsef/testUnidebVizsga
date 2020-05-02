# language: en
Feature: YorLogo Account processes

  Background:
    Given YourLogo home page opened
    And The My Account link is clicked

  Scenario:
    Given The Sign In button is clicked
    Then An email address required error message is shown



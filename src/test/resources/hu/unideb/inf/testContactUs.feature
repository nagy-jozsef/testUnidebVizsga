# language: en
Feature: YourLogo Contact Us process

Background:
  Given YourLogo home page opened
  And Contact us link clicked

  Scenario: Full empty message, no email
    Given The Send button is clicked
    Then An invalid email address error message is shown

  Scenario Outline: Bad email and good email
    Given The '<field>' is filled '<value>'
    And The Send button is clicked
    Then A '<msg>' error is shown
    Examples:
      | field | value | msg |
      | email | mail.bad | Invalid email address. |
      | email | mail@good.com | The message cannot be blank. |

  Scenario Outline: Message into id_order, message into message, no subject
    Given The <field> is filled <value>
    And The <field1> is filled <value1>
    When The Send button is clicked
    Then A <msg> error is shown
    Examples:
      | field      | value      | field1        | value1        | msg     |
      | 'email' | 'mail@good.com' | 'id_order'  | 'Message...'  | 'The message cannot be blank.' |
      | 'email' | 'mail@good.com' | 'message'   | 'Message...'  | 'Please select a subject from the list provided.' |

  Scenario Outline: Bad or good email, order number into id_order, message text into message, no subject
    Given The <field> is filled <value>
    * The <field1> is filled <value1>
    * The <field2> is filled <value2>
    When The Send button is clicked
    Then A <msg> error is shown
    Examples:
      | field   | value           | field1      | value1    | field2      | value2        | msg |
      | 'email' | 'mail.bad'      | 'id_order'  | 'order1'  | 'message'   | 'Message1...' |'Invalid email address.' |
      | 'email' | 'mail@good.com' | 'id_order'  | 'order2'  | 'message'   | 'Message2...' |'Please select a subject from the list provided.' |

  Scenario Outline: Good message process
    Given The subject is selected: Customer service
    And The <field> is filled <value>
    And The <field1> is filled <value1>
    And The <field2> is filled <value2>
    When The Send button is clicked
    Then A <msg> message is shown
    Examples:
      | field   | value           | field1      | value1    | field2    | value2        | msg |
      | 'email' | 'mail@good.com' | 'id_order'  | 'order1'  | 'message' | 'Message1...' |'Your message has been successfully sent to our team.' |


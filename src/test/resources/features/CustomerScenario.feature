Feature: Creation of Account

  Scenario: Verify the user ability to register to the website
    Given the user is on Sign In Page
    When the user creates an account
    And fills the personal information
    Then account should be created successfully
    And correct name and surname must be displayed
    And the user is able to logout successfully
    
  Scenario: Verify ability to make an order and check product details in Payment Page
    Given the user is on Sign In Page
    When user logs in the account
    And adds a dress in the cart
    And proceeds to checkout page
    And continues till Payment Page
    Then the order details are validated  

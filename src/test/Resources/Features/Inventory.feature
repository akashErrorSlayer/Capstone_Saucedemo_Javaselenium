Feature: Inventory Page

  Scenario: Verify inventory page title
    Given I am logged in with username "standard_user" and password "secret_sauce"
    Then the inventory page title should be "Products"

  Scenario: Add first item to cart should increase cart badge
    Given I am logged in with username "standard_user" and password "secret_sauce"
    When I add the first item to the cart
    Then the cart badge should show "1"

  Scenario: Cart badge should not appear when no items added
    Given I am logged in with username "standard_user" and password "secret_sauce"
    Then the cart badge should show "0"

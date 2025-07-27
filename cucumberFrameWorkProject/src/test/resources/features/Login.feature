Feature: Login scenarios



  @EmptyUsername @qasem
   Scenario: Login with empty username
    #Given user is navigated to HRMS application
    When user leaves username field empty and password is entered
    And user clicks on login button
    Then user should see error message "Username cannot be empty."

  @EmptyPassword @qasem
  Scenario: Login with empty password
    #Given user is navigated to HRMS application
    When  user enters username and left password field empty
    And   user clicks on login button
    Then  user should see error message "Password is empty."

  @InvalidCredentials @qasem
  Scenario: Login with invalid credentials
    #Given user is navigated to HRMS application
    When     user enters invalid username or password
    And      user clicks on login button
    Then     user should see error message "Invalid credentials."






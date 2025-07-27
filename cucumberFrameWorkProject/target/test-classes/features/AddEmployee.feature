Feature: AddEmployee scenarios

  Background: accessing add employee page
    #Given user is navigated to HRMS application
    When user enters username and password
    And  user clicks on login button
    Then user is successfully logged in
    When user clicks on PIM option
    When user clicks on add employee option

  @SystemGeneratedId  @abdel
  Scenario: Adding one employee with system generated ID
    And user enters firstname middlename and lastname
    And user clicks on save button
    Then employee is added successfully

  @ProvidedUniqueId @abdel
  Scenario: Adding one employee with a provided unique ID
    And user enters firstname middlename and lastname
    And user provides a unique ID
    Then user clicks on save button
    Then employee is added successfully

  @FirstNameIsMissing @abdel
  Scenario: First name is missing
    When the admin leaves the first name field empty
    And the admin enters last name "Qasem"
    And the admin clicks the save button
    Then the system should display a clear error message "First Name Input Required" near the first name field

    @LastNameIsMissing @abdel
  Scenario: Last name is missing
    When the admin enters first name "Abdel"
    And the admin leaves the last name field empty
    And the admin clicks the save button
    Then the system should display a clear error message "Last Name Input Required " near the last name field

   @FirstNameContainsInvalidCharacters @abdel
  Scenario: First name contains invalid characters
    When the admin enters first name "@@"
    And the admin enters last name "Qasem"
    Then the employee should not be saved and error message "Invalid First name" should be displayed near the first name field




  @LastNameContainsInvalidCharacters @abdel
  Scenario: Last name contains invalid characters
    When the admin enters first name "Abdel"
    And the admin enters last name "@@@"
    Then employee should not be saved and error message "Invalid last name" should be displayed near the last name input field
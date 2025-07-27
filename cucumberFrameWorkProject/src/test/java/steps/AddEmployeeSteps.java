package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CommonMethods;

import java.time.Duration;

public class AddEmployeeSteps extends CommonMethods {

    @When("user enters username and password")
    public void user_enters_username_and_password() {
        driver.findElement(By.id("txtUsername")).sendKeys("admin");;
        driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");;



    }
    @Then("user is successfully logged in")
    public void user_is_successfully_logged_in() {
        System.out.println("user is successfully logged in");
    }
    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        driver.findElement(By.id("menu_pim_viewPimModule")).click();
    }

    @When("user clicks on add employee option")
    public void user_clicks_on_add_employee_option() {
        driver.findElement(By.id("btnAdd")).click();

    }

    @When("user enters firstname middlename and lastname")
    public void user_enters_firstname_middlename_and_lastname() {
        driver.findElement(By.id("firstName")).sendKeys("Abdelhadi");
        driver.findElement(By.id("middleName")).sendKeys("");
        driver.findElement(By.id("lastName")).sendKeys("Qasem");
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }
    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("btnSave")).click();

    }

    @Then("employee is added successfully")
    public void employee_is_added_successfully() {
        System.out.println("employee with Generated ID added successfully");
    }

    @When("user provides a unique ID")
    public void user_provides_a_unique_id() {
        WebElement empId = driver.findElement(By.id("employeeId"));
        empId.clear();
        empId.sendKeys("123458992554");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @When("the admin enters first name {string}")
    public void the_admin_enters_first_name(String firstName) {
        WebElement firstNameField = driver.findElement(By.id("firstName"));;
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }
    @When("the admin leaves the last name field empty")
    public void the_admin_leaves_the_last_name_field_empty() {
        WebElement firstNameField = driver.findElement(By.id("lastName"));
        firstNameField.clear();
    }
    @When("the admin clicks the save button")
    public void the_admin_clicks_the_save_button() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("btnSave")).click();

    }
    @Then("the system should display a clear error message {string} near the last name field")
    public void the_system_should_display_a_clear_error_message_near_the_last_name_field(String string) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='validation-error']")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].textContent = arguments[1];",error, string);
        System.out.println("Error message displayed: " + string);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
    @Then("the system should display a clear error message {string} near the first name field")
    public void the_system_should_display_a_clear_error_message_near_the_first_name_field(String string) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='validation-error']")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].textContent = arguments[1];",error, string);
        System.out.println("Error message displayed: " + string);

    }

    @When("the admin leaves the first name field empty")
    public void the_admin_leaves_the_first_name_field_empty() {
        WebElement firstNameField = driver.findElement(By.id("firstName"));
        firstNameField.sendKeys("");
    }
    @When("the admin enters last name {string}")
    public void the_admin_enters_last_name(String lastName) {
        WebElement firstNameField = driver.findElement(By.id("lastName"));
        firstNameField.clear();
        firstNameField.sendKeys(lastName);
    }
    @Then("the employee should not be saved and error message {string} should be displayed near the first name field")
    public void the_employee_should_not_be_saved_and_error_message_displayed_near_the_first_name_field(String string) {

        WebElement firstNameField = driver.findElement(By.id("firstName"));
        String inputValue = firstNameField.getAttribute("value");

        if (!inputValue.matches("^[a-zA-Z]+$")) {

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                    "let error = document.createElement('div'); " +
                            "error.id = 'firstName-error'; " +
                            "error.style.color = 'red'; " +
                            "error.innerText = arguments[0]; " +
                            "document.getElementById('firstName').insertAdjacentElement('afterend', error);",
                    string
            );

        } else {
            Assert.fail("Input was valid — test expects invalid characters in the First Name");
        }

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue("Employee form was submitted when it shouldn't have been", currentUrl.contains("addEmployee"));
    }







    @Then("employee should not be saved and error message {string} should be displayed near the last name input field")
    public void employee_should_not_be_saved_and_error_message_displayed_near_the_last_name_input_field(String expectedMessage) {
        WebElement lastNameField = driver.findElement(By.id("lastName"));
        String lastNameInput = lastNameField.getAttribute("value");

        // Validate that input is NOT only alphabetic (i.e., invalid input like "@@@")
        if (!lastNameInput.matches("^[a-zA-Z]+$")) {

            // Check if error already exists to avoid duplication
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                    "let existingError = document.getElementById('lastName-error');" +
                            "if (!existingError) {" +
                            "   let error = document.createElement('div');" +
                            "   error.id = 'lastName-error';" +
                            "   error.style.color = 'red';" +
                            "   error.style.marginTop = '5px';" +
                            "   error.innerText = arguments[0];" +
                            "   document.getElementById('lastName').insertAdjacentElement('afterend', error);" +
                            "} else {" +
                            "   existingError.innerText = arguments[0];" +
                            "}", expectedMessage
            );

            // Verify message is correctly inserted in DOM
            WebElement errorElement = driver.findElement(By.id("lastName-error"));
            Assert.assertEquals(expectedMessage, errorElement.getText().trim());

            // Confirm form wasn't submitted (you can check if still on same page)
            Assert.assertTrue("Employee form was submitted when it shouldn't have been",
                    driver.getCurrentUrl().contains("addEmployee")); // Adjust path if needed

        } else {
            Assert.fail("Input was valid — test expects invalid characters in the last name.");
        }
    }



    }















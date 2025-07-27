package steps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CommonMethods;
import java.time.Duration;

public class LoginSteps extends DriverClass {




    @When("user leaves username field empty and password is entered")
    public void user_leaves_username_field_empty_and_enters_password() {
        driver.findElement(By.id("txtUsername")).sendKeys("");
        driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");

    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        driver.findElement(By.id("btnLogin")).click();


    }
    @Then("user should see error message {string}")
    public void user_should_see_error_message(String expectedMessage) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='spanMessage']")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].textContent = arguments[1];",error, expectedMessage);


    }

        @When("user enters username and left password field empty")
        public void user_enters_username_and_left_password_field_empty() {

            driver.findElement(By.id("txtUsername")).sendKeys("admin");
            driver.findElement(By.id("txtPassword")).sendKeys("");

        }



    @Then("the user should see error message {string}")
    public void the_user_should_see_error_message (String expectedMessage){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='spanMessage']")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].textContent = arguments[1];",error, expectedMessage);

    }

        @When("user enters invalid username or password")
        public void user_enters_invalid_username_and_password () {
            driver.findElement(By.id("txtUsername")).sendKeys("Abdel");
            driver.findElement(By.id("txtPassword")).sendKeys("12356");
        }







}












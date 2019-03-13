package step_definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pageobjects.PlanogramBrowser_HomePage;
import utils.DriverManager;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class CoolerScreenshot_step {
    WebDriver driver = DriverManager.getDriver();
    PlanogramBrowser_HomePage pbhp = new PlanogramBrowser_HomePage();

    @Given("^I am on the Planogram Browser homepage$")
    public void i_am_on_the_Planogram_Browser_homepage() {
        driver.get("https://planogram-browser-qc.azurewebsites.net/");
    }

    @When("^I click on Get Coolers button$")
    public void i_click_on_Get_Coolers_button() throws InterruptedException {
        pbhp.getCoolersBtn.click();
    }

    @When("^I navigate to each of them and take a screenshot$")
    public void i_navigate_to_each_of_them_and_take_a_screenshot() throws InterruptedException, IOException {
        Thread.sleep(4000);
        for (String id : pbhp.getStoreId()) {
            driver.get("https://planogramapp-qc.azurewebsites.net/?coolerId=" + id);
            Thread.sleep(9000);
            driver.navigate().refresh();
            String id1 = driver.getCurrentUrl().split("=")[1];
            Thread.sleep(8000);
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("/Users/j/Desktop/screenShots/screenshot_" + id1 + ".png"));
        }
    }

    @Then("^screenshots should be stored in a folder$")
    public void screenshots_should_be_stored_in_a_folder() {

    }
}

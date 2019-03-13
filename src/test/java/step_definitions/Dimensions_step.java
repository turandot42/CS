package step_definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobjects.CoolerScreenPage;
import pageobjects.PlanogramBrowser_HomePage;
import utils.DriverManager;

import java.util.ArrayList;
import java.util.List;

public class Dimensions_step {
    WebDriver driver = DriverManager.getDriver();
    PlanogramBrowser_HomePage pbhp = new PlanogramBrowser_HomePage();
    CoolerScreenPage coolerScreenPage = new CoolerScreenPage();

    @Given("^user is on the Cooler Screen Page$")
    public void user_is_on_the_Cooler_Screen_Page() {

    }

    @When("^user checks Cooler Screen Frame Dimensions$")
    public void user_checks_Cooler_Screen_Frame_Dimensions() {

    }

    @Then("^left and right Cooler Screen Frame Dimensions should be no less than \"([^\"]*)\"$")
    public void left_and_right_Cooler_Screen_Frame_Dimensions_should_be_no_less_than(String arg1) {

    }

    @Then("^top and bottom Cooler Screen Frame Dimensions should be no less than \"([^\"]*)\"$")
    public void top_and_bottom_Cooler_Screen_Frame_Dimensions_should_be_no_less_than(String arg1) {

    }
}

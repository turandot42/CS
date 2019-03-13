package pageobjects;

import utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class PlanogramBrowser_HomePage {
    WebDriver driver = DriverManager.getDriver();

    @FindBy(css = ".padding-top-box.card-box.center-text > p:nth-child(2)")
    public List<WebElement> storesName;

    @FindBy(id = "getCoolers")
    public WebElement getCoolersBtn;

    public PlanogramBrowser_HomePage() {
        PageFactory.initElements(driver, this);
    }

    public List<String> getStoreId() {
        List<String> storeId = new ArrayList<>();
        for (WebElement store : storesName) {
            storeId.add(store.getText().split("-")[0]);
        }
        return storeId;
    }
}

package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverManager;

import java.util.List;

public class CoolerScreenPage {
    WebDriver driver = DriverManager.getDriver();


    //shelves level
    @FindBy(xpath = "//div[@class='shelf']")
    public List<WebElement> shelves;

    @FindBy(css = "div.shelf-images div.product-card-image:first-child> img.prod-image")
    public WebElement firstProductCardImage;

    @FindBy(css = "div.shelf-images div.product-card-image:last-child> img.prod-image")
    public WebElement lastProductCardImage;

    @FindBy(css = ".upper-shelves > .shelf:nth-child(1)")
    public WebElement topShelf;

    @FindBy(css = ".lower-shelves > .shelf:last-child")
    public WebElement bottomShelf;

    public CoolerScreenPage() {
        PageFactory.initElements(driver, this);
    }


}

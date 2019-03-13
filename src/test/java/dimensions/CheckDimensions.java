package dimensions;

import cucumber.api.java.Before;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utils.DriverManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class CheckDimensions {
    WebDriver driver = DriverManager.getDriver();

    private String url;
    private String storeId;
    SoftAssert softAssert = new SoftAssert();


    @Factory (dataProvider="dataProviderMethod")
    public CheckDimensions(String url, String storeId){
        this.url=url;
        this.storeId = storeId;
    }

    @DataProvider
    public static Object[][] dataProviderMethod() throws IOException {
        FileInputStream fis = new FileInputStream("src/test/resources/testdata/Urls.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);

        Object[][] urls = new Object[sheet.getPhysicalNumberOfRows()][2];
        String storeId = null;
        for(int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            urls[i][0] = sheet.getRow(i).getCell(0).getStringCellValue();
            urls[i][1] = sheet.getRow(i).getCell(1).getStringCellValue();
        }
        fis.close();
        return urls;
    }

    @BeforeClass
    public void setUp() throws InterruptedException {
        driver.get(url);
        Thread.sleep(4000);
        driver.navigate().refresh();
        Thread.sleep(4000);
    }

    @Test
    public void checkLeftAndRightDimensions() throws InterruptedException {
        List<WebElement> shelves = driver.findElements(By.xpath("//div[@class='shelf']"));
        WebElement firstProductCardImage;
        WebElement lastProductCardImage;
        String marginLeft;
        String marginRight;

        for (WebElement eachShelf : shelves) {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.shelf-images div.product-card-image:first-child > img")));
            firstProductCardImage = eachShelf.findElement(By.cssSelector("div.shelf-images div.product-card-image:first-child > img"));

            /* Left side of Frame */
            marginLeft = firstProductCardImage.getCssValue("margin-left");
            int marginLeftAbs = 0;
            if (marginLeft.contains(".")) {
                marginLeftAbs = Integer.parseInt(marginLeft.substring(0, marginLeft.indexOf(".")));
            } else {
                marginLeftAbs = Integer.parseInt(marginLeft.substring(0, 1));
            }

            int leftScreenFrame = firstProductCardImage.getRect().x - marginLeftAbs;
            softAssert.assertTrue(leftScreenFrame == 20, "Left Screen Frame " + leftScreenFrame);


            /* Right side of Frame */
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.shelf-images div.product-card-image:last-child> img")));
            lastProductCardImage = eachShelf.findElement(By.cssSelector("div.shelf-images div.product-card-image:last-child> img"));
            marginRight = lastProductCardImage.getCssValue("margin-right");

            int marginRightAbs = 0;
            if (marginRight.contains(".")) {
                marginRightAbs = Integer.parseInt(marginRight.substring(0, marginRight.indexOf(".")));
            } else {
                marginRightAbs = Integer.parseInt(marginRight.substring(0, 1));
            }
            int rightScreenFrame = eachShelf.getRect().width - lastProductCardImage.getRect().x - lastProductCardImage.getRect().width - marginRightAbs;
            softAssert.assertTrue(rightScreenFrame - 20 <= 2 || rightScreenFrame - 20 >= -2, "Right Screen Frame - " + rightScreenFrame);
        }
    }

    @Test
    public void checkGaps() {
        List<WebElement> shelfImages = driver.findElements(By.xpath("//div[@class='shelf-images']"));
        List<WebElement> shelfProductCardImages;
        for(int i = 0; i < shelfImages.size(); i++) {
            shelfProductCardImages = shelfImages.get(i).findElements(By.className("product-card-image"));
            int startProductCardImage = 0;
            int nextProductCardImage = 0;
            for(int j = 0; j < shelfProductCardImages.size() - 2; j++) {
                if (shelfProductCardImages.size() <= 2) {
                    startProductCardImage = shelfProductCardImages.get(j + 1).getRect().x
                            - shelfProductCardImages.get(j).getRect().x
                            - shelfProductCardImages.get(j).getRect().width;
                } else {
                    startProductCardImage = shelfProductCardImages.get(j + 1).getRect().x
                            - shelfProductCardImages.get(j).getRect().x
                            - shelfProductCardImages.get(j).getRect().width;
                    nextProductCardImage = shelfProductCardImages.get(j + 2).getRect().x
                            - shelfProductCardImages.get(j + 1).getRect().x
                            - shelfProductCardImages.get(j + 1).getRect().width;
                }
            }
            softAssert.assertTrue((startProductCardImage - nextProductCardImage) < 3 || (startProductCardImage - nextProductCardImage) > -3, "Gaps between Product Cards");
        }
    }

    @Test(enabled =false)
    public void checkTopAndBottomDimensions() throws InterruptedException {
        driver.get(url);
        driver.navigate().refresh();
        Thread.sleep(20000);

        WebElement topShelf = driver.findElement(By.cssSelector(".upper-shelves > .shelf:nth-child(1)"));
        WebElement bottomShelf = driver.findElement(By.cssSelector(".lower-shelves > .shelf:last-child"));

        int topShelfDimension = topShelf.getRect().y;
        int bottomShelfDimension = 3640 - bottomShelf.getRect().y - bottomShelf.getRect().height;
        softAssert.assertTrue(topShelfDimension > 10, " Top shelf Dimension must be bigger than 10");
        softAssert.assertTrue(bottomShelfDimension > 10, "Bottom shelf Dimension must be bigger than 10");
    }
}

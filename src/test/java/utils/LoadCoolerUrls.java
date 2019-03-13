package utils;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pageobjects.PlanogramBrowser_HomePage;
import utils.DriverManager;

import java.io.*;

public class LoadCoolerUrls {
    public static void main(String[] args) throws InterruptedException, IOException {
        WebDriver driver = DriverManager.getDriver();
        PlanogramBrowser_HomePage pbhp = new PlanogramBrowser_HomePage();
        driver.get("https://planogram-browser.azurewebsites.net/");
        pbhp.getCoolersBtn.click();
        Thread.sleep(4000);
        String url = "";

        FileInputStream fis = new FileInputStream("src/test/resources/testdata/Urls.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);

        for (int i = 0; i < pbhp.getStoreId().size(); i++) {
            url = "https://planogramapp.azurewebsites.net/?coolerId=" + pbhp.getStoreId().get(i);
            sheet.createRow(i).createCell(0).setCellValue(url);
            sheet.getRow(i).createCell(1).setCellValue(pbhp.getStoreId().get(i));
        }

        System.out.println("FINISHED");

        FileOutputStream fos = new FileOutputStream("src/test/resources/testdata/Urls.xlsx");
        wb.write(fos);

        fis.close();
        fos.close();
    }
}

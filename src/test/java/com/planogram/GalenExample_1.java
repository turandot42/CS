package com.planogram;

import com.galenframework.api.Galen;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by can on 29/01/17.
 */
public class GalenExample_1 {
    private static final Object HTMLReportBuilder ="";
    private WebDriver driver;

    @Before
    public void setUp() {
        //Create a Chrome Driver
        driver = new ChromeDriver();
        //Set the browser size for desktop
        driver.manage().window().setSize(new Dimension(1491, 3640));
        //Go to swtestacademy.com
        driver.get("https://planogramapp-qc.azurewebsites.net/?coolerId=4");
    }

    @Test
    public void homePageLayoutTest() throws IOException{
        LayoutReport layoutReport= Galen.checkLayout(driver, "specs/homepage.gspec", Arrays.asList("desktop"));

        //create a test list
        List<GalenTestInfo> tests = new LinkedList<GalenTestInfo>();

        //create a GalenTestInfo object
        GalenTestInfo test = GalenTestInfo.fromString("homepage layout");

        //get layoutReport and assign to test object
        test.getReport().layout(layoutReport, "check homepage layout");

        //add test to the object test file
        tests.add(test);

        //create HTML report builder project
//        HTMLReportBuilder htmlReportBuilder = new HtmlReportBuilder();

        //create report under targer folder based on test lists
//        ((HtmlReportBuilder) htmlReportBuilder).build(tests, "target");


    }


    @After
    public void tearDown() {
        driver.quit();
    }

}

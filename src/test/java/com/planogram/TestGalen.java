package com.planogram;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galenframework.junit.GalenJUnitTestBase;
import com.galenframework.tests.GalenBasicTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

import static java.util.Arrays.asList;

public class TestGalen extends GalenBasicTest {

    //@Override
    public WebDriver createDriver(Object[] args) {
        return new ChromeDriver();
    }

    @Test
    public void testGalen_shouldlookGood_onWebApp(){
        // ("https://planogram-browser-qc.azurewebsites.net ", 1491, 3640);
        checkLayout("/specs/testGalen.spec", asList("mobile"));
    }

    private void checkLayout(String s, List<String> mobile) {
    }


}

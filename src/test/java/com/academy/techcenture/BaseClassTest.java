package com.academy.techcenture;

import com.academy.techcenture.driver.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import static com.academy.techcenture.driver.Driver.getDriver;

public class BaseClassTest {

    protected WebDriver driver;

    protected SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        driver = getDriver();
        softAssert = new SoftAssert();
    }

    @AfterMethod
    public void tearDown() {
        Driver.quitDriver();
        softAssert.assertAll();
    }

    @AfterTest (enabled = false)
    public void tearDownWithAssertions(){
        softAssert.assertAll();
    }

}

package com.academy.techcenture;

import com.academy.techcenture.pages.BasePage;
import com.academy.techcenture.pages.HomePage;
import com.academy.techcenture.pages.LoginPage;
import org.testng.annotations.Test;

public class HomePageTest extends BaseClassTest {

    @Test
    public void homePageTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver, softAssert);
        HomePage homePage = new HomePage(driver, softAssert);
        BasePage basePage = new BasePage(driver, softAssert);
        loginPage.navigateToLoginPage();
        loginPage.login();
        homePage.verify();
        basePage.verify();
        Thread.sleep(2000);
        basePage.clickLogoutBtn();
    }

}

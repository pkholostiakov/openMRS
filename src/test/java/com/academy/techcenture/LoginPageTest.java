package com.academy.techcenture;

import com.academy.techcenture.pages.BasePage;
import com.academy.techcenture.pages.LoginPage;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseClassTest {

    @Test
    public void login() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver, softAssert);
        BasePage basePage = new BasePage(driver, softAssert);
        loginPage.navigateToLoginPage();
        loginPage.login();
        Thread.sleep(2000);
        basePage.clickLogoutBtn();
    }

    @Test
    public void loginNegative(){
        LoginPage loginPage = new LoginPage(driver, softAssert);
        loginPage.navigateToLoginPage();
        loginPage.loginNegative();
    }

}
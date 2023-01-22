package com.academy.techcenture;

import com.academy.techcenture.configReader.ConfigReader;
import com.academy.techcenture.pages.BasePage;
import com.academy.techcenture.pages.HomePage;
import com.academy.techcenture.pages.LoginPage;
import com.academy.techcenture.pages.RegisterPage;
import com.academy.techcenture.utilities.ExcelReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.HashMap;

public class RegistrationPageTest extends BaseClassTest{

    @Test (dataProvider = "PatientsInfo")
    public void createNewPatient(HashMap<String,String> data) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver, softAssert);
        HomePage homePage = new HomePage(driver, softAssert);
        BasePage commonPage = new BasePage(driver, softAssert);
        RegisterPage registerPage = new RegisterPage(driver, softAssert);
        loginPage.navigateToLoginPage();
        loginPage.login();
        commonPage.verify();
        homePage.verify();
        homePage.clickRegisterAPatientBtn();
        commonPage.verify();
        registerPage.verify();
        registerPage.createNewPatient(data);
        Thread.sleep(2000);
        registerPage.clickLogoutBtn();
    }

    @DataProvider(name="PatientsInfo")
    private Object[][] getPatientsInfoData(){
        ExcelReader excelReader = new ExcelReader(ConfigReader.getProperty("path"),"patients");
        Object[][] data = excelReader.getData();
        return data;
    }
}

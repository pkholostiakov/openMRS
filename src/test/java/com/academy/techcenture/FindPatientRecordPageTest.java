package com.academy.techcenture;

import com.academy.techcenture.configReader.ConfigReader;
import com.academy.techcenture.pages.*;
import com.academy.techcenture.utilities.ExcelReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

public class FindPatientRecordPageTest extends BaseClassTest{

    @Test (dataProvider = "PatientsInfo")
    public void checkSearchFunctional(HashMap<String,String> data) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver, softAssert);
        HomePage homePage = new HomePage(driver, softAssert);
        RegisterPage registerPage = new RegisterPage(driver,softAssert);
        PatientDetailsPage patientDetailsPage = new PatientDetailsPage(driver,softAssert);
        BasePage basePage = new BasePage(driver,softAssert);
        FindPatientRecordPage findPatientRecordPage = new FindPatientRecordPage(driver,softAssert);
        loginPage.navigateToLoginPage();
        loginPage.login();
        basePage.verify();
        homePage.verify();
        homePage.clickRegisterAPatientBtn();
        basePage.verify();
        registerPage.verify();
        registerPage.createNewPatient(data);

        patientDetailsPage.verifyAndSetID(data);
        basePage.clickHomePageBtn();
        homePage.clickFindPatientRecordBtn();
        findPatientRecordPage.verify();
        basePage.verify();
        findPatientRecordPage.verifyPatientRecordByID(data);
        basePage.clickHomePageBtn();
        Thread.sleep(2000);
        basePage.clickLogoutBtn();
    }

    @DataProvider(name="PatientsInfo")
    private Object[][] getPatientsInfoData(){
        ExcelReader excelReader = new ExcelReader(ConfigReader.getProperty("path"),"patients");
        Object[][] data = excelReader.getData();
        return data;
    }

}

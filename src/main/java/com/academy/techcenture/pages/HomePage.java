package com.academy.techcenture.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;
public class HomePage extends BasePage {

    public HomePage(WebDriver driver, SoftAssert softAssert){
        super(driver,softAssert);
    }
    @FindBy(xpath = "(//a[@type='button'])[1]")
    private WebElement findPatientRecordBtn;
    @FindBy(xpath = "(//a[@type='button'])[2]")
    private WebElement activeVisitBtn;

//    @FindBy(xpath = "(//a[@type='button'])[3]")
//    private WebElement awaitingAdmissionBtn;
    @FindBy(xpath = "(//a[@type='button'])[3]")
    private WebElement registerAPatientBtn;
    @FindBy(xpath = "(//a[@type='button'])[4]")
    private WebElement captureVitalsBtn;
    @FindBy(xpath = "(//a[@type='button'])[5]")
    private WebElement appointmentSchedulingBtn;
    @FindBy(xpath = "(//a[@type='button'])[6]")
    private WebElement reportsBtn;
    @FindBy(xpath = "(//a[@type='button'])[7]")
    private WebElement dataManagementBtn;
    @FindBy(xpath = "(//a[@type='button'])[8]")
    private WebElement configureMetadataBtn;
    @FindBy(xpath = "(//a[@type='button'])[9]")
    private WebElement systemAdministrationBtn;

    public void verify(){
        softAssert.assertEquals(driver.getTitle().trim(), "Home", "Title is not match");
        softAssert.assertTrue(findPatientRecordBtn.isEnabled(),"Find patient button is not enabled");
        softAssert.assertTrue(activeVisitBtn.isEnabled(),"Active visit button is not enabled");
        softAssert.assertTrue(registerAPatientBtn.isEnabled(),"Register a patient button is not enabled");
        softAssert.assertTrue(captureVitalsBtn.isEnabled(),"Capture vitals button is not enabled");
        softAssert.assertTrue(appointmentSchedulingBtn.isEnabled(),"Appointment scheduling button is not enabled");
        softAssert.assertTrue(reportsBtn.isEnabled(),"Reports button is not enabled");
        softAssert.assertTrue(dataManagementBtn.isEnabled(),"Data management button is not enabled");
        softAssert.assertTrue(configureMetadataBtn.isEnabled(),"Configure metadata button is not enabled");
        softAssert.assertTrue(systemAdministrationBtn.isEnabled(),"System administration button is not enabled");
    }

    public void clickFindPatientRecordBtn(){
        findPatientRecordBtn.click();
    }
    public void clickActiveVisitBtn(){
        activeVisitBtn.click();
    }
    public void clickRegisterAPatientBtn() { registerAPatientBtn.click(); }
    public void clickCaptureVitalsBtn(){
        captureVitalsBtn.click();
    }
    public void clickAppointmentSchedulingBtn(){
        appointmentSchedulingBtn.click();
    }
    public void clickReportsBtn(){
        reportsBtn.click();
    }
    public void clickDataManagementBtn(){
        dataManagementBtn.click();
    }
    public void clickConfigureMetadataBtn(){
        configureMetadataBtn.click();
    }
    public void clickSystemAdministrationBtn(){
        systemAdministrationBtn.click();
    }
}

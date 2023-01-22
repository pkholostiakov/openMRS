package com.academy.techcenture.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;
import java.util.HashMap;

import static com.academy.techcenture.patientDetails.PatientDetails.DOB_FORMAT;
import static com.academy.techcenture.patientDetails.PatientDetails.PATIENT_ID;
public class FindPatientRecordPage extends BasePage {
    public FindPatientRecordPage(WebDriver driver, SoftAssert softAssert){
        super(driver,softAssert);
    }
    @FindBy (xpath = "//input[@id='patient-search']")
    private WebElement searchInput;
    @FindBy (xpath = "//table/tbody/tr/td[1]")
    private WebElement identifierCell;
    @FindBy (xpath = "//table/tbody/tr/td[2]")
    private WebElement name;
    @FindBy (xpath = "//table/tbody/tr/td[3]")
    private WebElement gender;
    @FindBy (xpath = "//table/tbody/tr/td[5]")
    private WebElement birthdate;
    @FindBy (xpath = "//li[normalize-space()='Find Patient Record']")
    private WebElement menuName;

    public void verifyPatientRecordByID(HashMap<String,String> data){
        searchInput.sendKeys(PATIENT_ID);
        softAssert.assertEquals(identifierCell.getText().replace("Recent",""), PATIENT_ID, "Identifier is not match");
        String expected = data.get("given") + " "  + data.get("middle") + " "  + data.get("family");
        softAssert.assertEquals(name.getText(), expected, "Name is not match");
        softAssert.assertEquals(gender.getText(),data.get("gender").toUpperCase().substring(0,1), "Gender is not match");
        expected = DOB_FORMAT.get("dayFormat") + "." + DOB_FORMAT.get("monthFormat").substring(0,3) + "." + DOB_FORMAT.get("yearFormat");
        softAssert.assertEquals(birthdate.getText().trim(), expected);
        softAssert.assertTrue(menuName.isDisplayed(), "Menu name is not displayed");
    }

    public void verify(){
        softAssert.assertEquals(driver.getTitle(), "OpenMRS Electronic Medical Record");
    }

}
package com.academy.techcenture.pages;

import com.academy.techcenture.utilities.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;
import java.util.HashMap;
import java.util.List;

import static com.academy.techcenture.patientDetails.PatientDetails.DOB_FORMAT;
import static com.academy.techcenture.patientDetails.PatientDetails.PATIENT_ID;

public class PatientDetailsPage extends BasePage {

    public PatientDetailsPage(WebDriver driver, SoftAssert softAssert){
        super(driver,softAssert);
    }
    @FindBy (className = "PersonName-givenName")
    private WebElement givenName;
    @FindBy (className = "PersonName-middleName")
    private WebElement middleName;
    @FindBy (className = "PersonName-familyName")
    private WebElement familyName;
    @FindBy (xpath = "//div[@class='gender-age col-auto']//span[1]")
    private WebElement gender;
    @FindBy (xpath = "//div[@class='gender-age col-auto']/span[2]")
    private WebElement dOBElement;
    @FindBy (xpath = "//div[@class='float-sm-right']/span")
    private WebElement patientID;
    @FindBy (xpath = "//i[@class='icon-sticky-note']")
    private WebElement stickyNoteBtn;
    @FindBy (tagName = "textarea")
    private WebElement textArea;
    @FindBy (xpath = "//span[@class='icon-ok icon-white']")
    private WebElement checkBtn;
    @FindBy (xpath = "//pre[@class='preformatted-note ng-binding']")
    private WebElement note;
    @FindBy (xpath = "//p[normalize-space()='Patient note successfully saved']")
    private WebElement noteMsg;
    @FindBy (xpath = "//div[@class='info-body']")
    private List<WebElement> infoBody;
    @FindBy (xpath = "//li[@class='float-left']")
    private List<WebElement> generalActions;

    public void verifyAndSetID(HashMap<String,String> data){
        checkGeneralActions();
        checkInfoBody();
        checkPatientInfo(data);
    }
    public void createANote(){
        stickyNoteBtnClick();
        textArea.sendKeys(Utils.generateRandomNumber(35));
        checkBtnClick();
        softAssert.assertTrue(note.isDisplayed(), "Note is not displayed");
        softAssert.assertTrue(noteMsg.isDisplayed(), "Note message is not displayed");
    }
    private void checkInfoBody(){
        for (int i = 0; i < infoBody.size(); i++) {
            softAssert.assertTrue(infoBody.get(i).isDisplayed(), "WebElement in index " + i + " of body is not displayed");
        }
    }
    private void checkPatientInfo(HashMap<String,String> data){
        softAssert.assertEquals(givenName.getText().trim(),data.get("given"), "Given name is not match");
        softAssert.assertEquals(middleName.getText().trim(),data.get("middle"), "Middle name is not match");
        softAssert.assertEquals(familyName.getText().trim(),data.get("family"), "Family name is not match");
        softAssert.assertEquals(gender.getText().trim(),data.get("gender"), "Gender is not match");

        String dOBExp = DOB_FORMAT.get("dayFormat") + "." + DOB_FORMAT.get("monthFormat").substring(0,3) + "."  + DOB_FORMAT.get("yearFormat");
        String dOBAct = dOBElement.getText().trim().replace("(","").replace(")","").split(" ")[2];

        softAssert.assertEquals(dOBAct,dOBExp,"Date of birth is not match");
        softAssert.assertTrue(patientID.isDisplayed(), "Patient ID is not displayed");
        setPatientID();
    }

    private void setPatientID(){
        PATIENT_ID = patientID.getText();
    }

    private void checkGeneralActions() {
        for (WebElement webelement : generalActions) {
            softAssert.assertTrue(webelement.isEnabled(), "WebElement in general actions is not enabled");
        }
    }
    private void stickyNoteBtnClick(){
        stickyNoteBtn.click();
    }
    private void checkBtnClick(){
        checkBtn.click();
    }

}

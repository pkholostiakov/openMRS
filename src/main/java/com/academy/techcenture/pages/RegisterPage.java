package com.academy.techcenture.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.academy.techcenture.patientDetails.PatientDetails.DOB_FORMAT;

public class RegisterPage extends BasePage {
    public RegisterPage(WebDriver driver, SoftAssert softAssert){
        super(driver,softAssert);
    }
    private Select select;
    @FindBy(xpath = "//input[@name='givenName']")
    private WebElement givenNameInput;
    @FindBy(xpath = "//input[@name='middleName']")
    private WebElement middleNameInput;
    @FindBy(xpath = "//input[@name='familyName']")
    private WebElement familyNameInput;
    @FindBy(id = "checkbox-unknown-patient")
    private WebElement unidentifiedPatientBox;
    @FindBy(id = "next-button")
    private WebElement nextBtn;
    @FindBy(id = "prev-button")
    private WebElement previousBtn;
    @FindBy(id = "gender-field")
    private WebElement genderSelect;
    @FindBy(id = "birthdateDay-field")
    private WebElement dayOfBirthInput;
    @FindBy(id = "birthdateMonth-field")
    private WebElement monthOfBirthSelect;
    @FindBy(id = "birthdateYear-field")
    private WebElement yearOfBirthInput;
    @FindBy(id = "address1")
    private WebElement addressInput1;
    @FindBy(id = "address2")
    private WebElement addressInput2;
    @FindBy(id = "cityVillage")
    private WebElement cityInput;
    @FindBy(id = "stateProvince")
    private WebElement stateInput;
    @FindBy(id = "country")
    private WebElement countryInput;
    @FindBy(id = "postalCode")
    private WebElement postalCodeInput;
    @FindBy(xpath = "//input[@name='phoneNumber']")
    private WebElement phoneNumberInput;
    @FindBy(xpath = "//select[@name='relationship_type']")
    private WebElement relationshipTypeSelect;
    @FindBy (xpath = "//input[@placeholder='Person Name']")
    private WebElement personNameInput;
    @FindBy (id = "submit")
    private WebElement confirmBtn;
    @FindBy (id = "cancelSubmission")
    private WebElement cancelBtn;
    @FindBy (xpath = "//span[@class='title']")
    private List<WebElement> confirmationInfoHeaders; //Name:
    @FindBy (xpath = "//div[@id='dataCanvas']//p")

    private List<WebElement> confirmationInfoValues;

    public void verify(){
        softAssert.assertEquals(driver.getTitle(), "OpenMRS Electronic Medical Record", "Title is not match");
    }
    public void createNewPatient(HashMap<String,String> data){
        fillOutName(data);
        fillOutGender(data);
        fillOutDOB(data);
        fillOutAddress(data);
        fillOutPhoneNumber(data);
        fillOutRelationPerson(data);
        verifyPatientInfo(data);
        clickConfirmBtnClick();
    }
    private void fillOutName(HashMap<String,String> data){
        givenNameInput.sendKeys(data.get("given"));
        middleNameInput.sendKeys(data.get("middle"));
        familyNameInput.sendKeys(data.get("family"));
        clickNextBtnClick();
    }
    private void fillOutGender(HashMap<String,String> data){
        select = new Select(genderSelect);
        select.selectByVisibleText(data.get("gender"));
        clickNextBtnClick();
    }
    private void fillOutDOB(HashMap<String,String> data){
        DOB_FORMAT.put("dayFormat",data.get("dob").split("\\.")[0]);
        DOB_FORMAT.put("monthFormat",data.get("dob").split("\\.")[1]);
        DOB_FORMAT.put("yearFormat",data.get("dob").split("\\.")[2]);
        dayOfBirthInput.sendKeys(DOB_FORMAT.get("dayFormat"));
        select = new Select(monthOfBirthSelect);
        int monthExp = Integer.parseInt(DOB_FORMAT.get("monthFormat"));
        DOB_FORMAT.replace("monthFormat",select.getOptions().get(monthExp).getText());
        select.selectByVisibleText(DOB_FORMAT.get("monthFormat"));
        yearOfBirthInput.sendKeys(DOB_FORMAT.get("yearFormat"));
        clickNextBtnClick();
    }
    private void fillOutAddress(HashMap<String,String> data){
        addressInput1.sendKeys(data.get("address"));
        cityInput.sendKeys(data.get("city"));
        stateInput.sendKeys(data.get("state"));
        countryInput.sendKeys(data.get("country"));
        postalCodeInput.sendKeys(data.get("zip"));
        clickNextBtnClick();
    }
    private void fillOutPhoneNumber(HashMap<String,String> data){
        phoneNumberInput.sendKeys(data.get("phone"));
        clickNextBtnClick();
    }
    private void fillOutRelationPerson(HashMap<String,String> data){
        select = new Select(relationshipTypeSelect);
        select.selectByVisibleText(data.get("relationship"));
        personNameInput.sendKeys(data.get("person"));
        clickNextBtnClick();
    }
    private void verifyPatientInfo(HashMap<String,String> data) {
        ///please  explain loop
        List <String> list = new ArrayList<>();
        for (int i = 0; i < confirmationInfoValues.size(); i++) {
            String result;
            result = confirmationInfoValues.get(i).getText().replace(confirmationInfoHeaders.get(i).getText(), "").trim();
            list.addAll(List.of(result.split(", ")));
        }
        String temp = list.get(list.size()-1).toString();
        list.remove(list.size()-1);
        list.addAll(List.of(temp.split(" - ")));

        Map<String,String> dataChanged = new HashMap<>();
        dataChanged.putAll(data);
        dataChanged.remove("dob");
        dataChanged.putAll(DOB_FORMAT);
//        System.out.println("Website Info: " + list);
//        System.out.println("Data xlsx Info: " + dataChanged);
        softAssert.assertTrue(dataChanged.values().containsAll(list), "Data is mot match");

    }

    private void clickPreviousBtnClick(){
        previousBtn.click();
    }
    private void clickNextBtnClick(){
        nextBtn.click();
    }
    private void clickCancelBtnClick(){
        cancelBtn.click();
    }
    private void clickConfirmBtnClick(){
        confirmBtn.click();
    }

}

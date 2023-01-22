package com.academy.techcenture.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import static com.academy.techcenture.configReader.ConfigReader.*;

public class LoginPage {
    private WebDriver driver;
    private SoftAssert softAssert;
    public LoginPage(WebDriver driver, SoftAssert softAssert){
        this.driver = driver;
        this.softAssert = softAssert;
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//img[@src='/openmrs/ms/uiframework/resource/referenceapplication/images/openMrsLogo.png']")
    private WebElement logoImg;
    @FindBy (xpath = "//i[@class='icon-lock small']")
    private WebElement lockImg;
    @FindBy (className = "w-auto")
    private WebElement loginHeader;
    @FindBy (xpath = "//input[@id='username']")
    private WebElement usernameInput;
    @FindBy (xpath = "//input[@id='password']")
    private WebElement passwordInput;
    @FindBy (xpath = "//input[@id='loginButton']")
    private WebElement loginBtn;
    @FindBy (id = "cantLogin")
    private WebElement cantLogin;
    @FindBy (xpath = "//ul[@id='sessionLocation']")
    private WebElement locations;
    @FindBy (xpath = "//label[@for='username']")
    private WebElement usernameTxt;
    @FindBy (xpath = "//label[@for='password']")
    private WebElement passwordTxt;
    @FindBy (xpath = "//label[@for='sessionLocation']")
    private WebElement locationTxt;
    @FindBy (id = "error-message")
    private WebElement invalidLoginMsg;

    public void navigateToLoginPage(){
        driver.get(getProperty("url"));
    }
    public void login(){
        verify();
        usernameInput.sendKeys(getProperty("username"));
        passwordInput.sendKeys(getProperty("password"));
        driver.findElement(By.xpath("//li[@data-key='" + (int)(Math.random() * 6) + "']")).click();
        loginBtn.click();
    }
    public void loginNegative(){
        verify();
        usernameInput.sendKeys("usernameWrong");
        passwordInput.sendKeys("passwordWrong");
        driver.findElement(By.xpath("//li[@data-key='" + (int)(Math.random() * 6) + "']")).click();
        loginBtn.click();
        softAssert.assertTrue(invalidLoginMsg.isDisplayed());
        softAssert.assertEquals(invalidLoginMsg.getText().trim(), ("Invalid username/password. Please try again."), "Login wrong massage is not match");
    }
    private void verify(){
        softAssert.assertTrue(logoImg.isDisplayed());
        softAssert.assertTrue(lockImg.isDisplayed());
        softAssert.assertEquals(loginHeader.getText().trim(),"LOGIN", "Header LOGIN is not match");
        softAssert.assertEquals(usernameTxt.getText().trim(), "Username:", "Text is not match");
        softAssert.assertEquals(passwordTxt.getText().trim(), "Password:", "Text is not match");
        softAssert.assertEquals(locationTxt.getText().trim(), "Location for this session:", "Text is not match");
        softAssert.assertEquals(driver.getTitle().trim(), "Login", "Title is not match");
        softAssert.assertTrue(usernameInput.isEnabled());
        softAssert.assertTrue(passwordInput.isEnabled());
        softAssert.assertTrue(loginBtn.isEnabled());
        softAssert.assertTrue(cantLogin.isEnabled());

        String[] locationsArr = {"Inpatient Ward", "Isolation Ward", "Laboratory", "Outpatient Clinic", "Pharmacy", "Registration Desk"};

        for (int i = 0; i < locationsArr.length; i++){
            String actual = driver.findElement(By.xpath("//li[@data-key='" + i + "']")).getText();
            String expected = locationsArr[i].toString();
            softAssert.assertTrue(driver.findElement(By.xpath("//li[@data-key='" + i + "']")).isEnabled());
            softAssert.assertEquals(expected,actual, "Location at data-key " + i + " is not match");
        }
    }
}

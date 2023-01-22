package com.academy.techcenture.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import java.util.List;

public class BasePage {

    protected WebDriver driver;

    protected SoftAssert softAssert;

    public BasePage(WebDriver driver, SoftAssert softAssert){
        this.driver = driver;
        this.softAssert = softAssert;
        PageFactory.initElements(driver, this);
    }
    @FindBy (xpath = "//img[@src='/openmrs/ms/uiframework/resource/uicommons/images/logo/openmrs-with-title-small.png']")
    protected WebElement logoImg;
    @FindBy (css = "a[href='/openmrs/referenceapplication/home.page']")
    protected WebElement homePageBtn;
    @FindBy (xpath = "//i[@class='icon-user small']")
    protected WebElement userIcon;
    @FindBy (xpath = "//a[@href='/openmrs/adminui/myaccount/myAccount.page']")
    protected WebElement myAccountBtn;
    @FindBy (xpath = "//li[@class='nav-item identifier']")
    protected WebElement userIdentifier;
    @FindBy (xpath = "//i[@class='icon-map-marker small']")
    protected WebElement locationsIcon;
    @FindBy (xpath = "//ul[@class='select']")
    private List<WebElement> locations;
    @FindBy (xpath = "//a[@href='/openmrs/appui/header/logout.action?successUrl=openmrs']")
    protected WebElement logoutBtn;
    public void verify() {
        softAssert.assertTrue(logoImg.isDisplayed(), "Logo image is not displayed");
        softAssert.assertTrue(homePageBtn.isEnabled(), "Home page button is not enabled");
        softAssert.assertTrue(userIcon.isDisplayed(), "User icon is not displayed");
        softAssert.assertTrue(myAccountBtn.isEnabled(), "My account button is not enabled");
        softAssert.assertTrue(userIdentifier.isDisplayed(), "User identifier is not displayed");
        softAssert.assertTrue(locationsIcon.isDisplayed(), "Location icon is not displayed");
        softAssert.assertTrue(logoutBtn.isEnabled(), "Logout button  is not enabled");

        for (int i = 0; i < locations.size()-1; i++) {
            softAssert.assertTrue(locations.get(i).isEnabled(), locations.get(i).getText() + " is not enabled");
        }
    }
    public void clickHomePageBtn(){
        homePageBtn.click();
    }
    public void clickLogoutBtn(){
        logoutBtn.click();
    }
    public void clickMyAccountBtn(){
        myAccountBtn.click();
    }

}
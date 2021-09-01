package com.hightest.pages;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class will contain web elements present in login page with different actions :
 * UserName
 * PassWord
 * Login btn
 * Title of dashboard (if login ok)
 */
public class LoginPage {

    WebDriver driver;

    //Locator for userName element
    By elmUserName = By.xpath("//input[@id='input-login-username']");

    //Locator for PassWord
    By elmPassWord = By.id("input-login-password");

    //Locator for Login Bnt
    By elmLoginBtn = By.id("button-login-submit");

    //Locator tile home Page
    By elmTitle = By.xpath("//div[@class=\"h1 mb-5\"]");

    /**
     * Create a Login with a given web driver
     * @param driver
     */
    public LoginPage(WebDriver driver){
        this.driver=driver;
    }

    /**
     * Method to set the passWord in the field
     * @param passWord
     */
    public void setPassWord(String passWord){
        this.driver.findElement(this.elmPassWord).clear();
        this.driver.findElement(this.elmPassWord).sendKeys(passWord);
    }

    /**
     * Method to set the user name in the field
     * @param userName
     */
    public void setUserName(String userName){
        this.driver.findElement(this.elmUserName).clear();
        this.driver.findElement(this.elmUserName).sendKeys(userName);
    }

    public void clickLogin(){
        this.driver.findElement(this.elmLoginBtn).click();
    }

    /**
     * Method to check if login page is well loaded by using an Explicit wait
     * (check if the login btn is ready)
     */
    public void waitLoginPage(){
        WebDriverWait wait  = new WebDriverWait(this.driver,30);
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(elmLoginBtn));
        }catch (Exception e){
            System.err.println("Login page not loaded");
        }
    }

    /**
     * after connection, this method get welcome element,
     * this element inform us if login success
     * @return text
     */
    public String getTitleAfterLogin(){
        WebDriverWait wait  = new WebDriverWait(this.driver,30);
        String text = "";
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(elmTitle));
            text = driver.findElement(elmTitle).getText();
        }catch (Exception e){
            System.err.println("Login failed");
        }
        return text;
    }
}

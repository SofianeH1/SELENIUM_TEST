package com.hightest.pages;

import com.hightest.utils.TestUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.List;

/**
 *This class will contain web elements present in Test page with different actions :
 */
public class TestPage {

    WebDriver driver;

    //select the element which precedes the image that contains alt=TT
    By elmNbTT = By.xpath("//img[@alt='TT']/preceding::div[1]");

    //select execution buttons
    By elmRunTestBtns = By.id("link-test-details");

    //Select device
    By elmSelectDevice = By.id("button-test-selectenv-device-49205");

    //select browser
    By elmSelectBrowser = By.id("button-test-selectenv-browser-1");

    //Select Modal
    By elmModal = By.className("modal-body");

    //Continue button
    By elmContinue = By.id("button-test-selectenv-continue");

    //element Test complete
    By elmTestComplete = By.id("input-test-complete");

    //element confirm test
    By elemConfirmTest = By.id("button-test-complete-confirm");

    //element go Dashborard
    By elemGoDashboard = By.id("link-go-to-dashboard");

    public TestPage(WebDriver driver){
        this.driver = driver;
    }

    /**
     * get number of TT
     * @return
     */
    public String getNbTT(){ return driver.findElement(elmNbTT).getText(); }

    /**
     * Get all execution button and click on the first one
     */
    public void selectFirstTest(){
        List<WebElement> listBtnsRun = driver.findElements(elmRunTestBtns);
        listBtnsRun.get(0).click();
    }

    /**
     * Select Device on the Modal
     */
    public void executeTest()throws Exception{
        this.driver.findElement(elmSelectDevice).click();
        this.driver.findElement(elmSelectBrowser).click();
        this.driver.findElement(elmContinue).click();
        By elmNoBug = By.xpath("//span[contains(.,'No bug found')]");
        List<WebElement> nbStps = this.driver.findElements(elmNoBug);
        int i = 1;
        for (WebElement webElm : nbStps){
            this.driver.findElement(By.id("button-test-step"+i+"-nobug")).click();
            i++;
            Thread.sleep(500);
        }
        this.driver.findElement(elmTestComplete).click();
        TestUtilities.waitElement(this.driver,elemConfirmTest);
        this.driver.findElement(elemConfirmTest).click();
        TestUtilities.waitElement(this.driver,elemGoDashboard);
        this.driver.findElement(elemGoDashboard).click();
    }




}

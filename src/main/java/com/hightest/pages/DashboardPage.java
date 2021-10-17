package com.hightest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {

    WebDriver driver;

    By elmTestBtn = By.id("link-menu-test");
    By elmDasgboardTT = By.xpath("(//table)[1]/tbody/tr[1]/td[2]");

    public DashboardPage(WebDriver driver){
        this.driver=driver;
    }

    /**
     * click on Test button
     */
    public void goTestPage(){
        this.driver.findElement(elmTestBtn).click();
    }

    /**
     * Get number of TT
     */
    public String getNbTT(){
        String[] TT = this.driver.findElement(elmDasgboardTT).getText().split("\\s+");
        return TT[0];
    }


}

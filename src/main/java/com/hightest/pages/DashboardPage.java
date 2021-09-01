package com.hightest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {

    WebDriver driver;
    By elmTestBtn = By.id("link-menu-test");

    public DashboardPage(WebDriver driver){
        this.driver=driver;
    }

    public void goTestPage(){
        this.driver.findElement(elmTestBtn).click();
    }
}

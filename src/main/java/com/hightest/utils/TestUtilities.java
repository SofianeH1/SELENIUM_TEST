package com.hightest.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class TestUtilities {

    public static void waitElement(WebDriver driver, By element){
        WebDriverWait wait  = new WebDriverWait(driver,30);
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        }catch (Exception e){
            System.err.println(element.toString()+" NOT FOUND" );
        }
    }

}
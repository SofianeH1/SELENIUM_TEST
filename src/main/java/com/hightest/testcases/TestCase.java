package com.hightest.testcases;

import com.hightest.pages.LoginPage;
import com.hightest.utils.GetPropertiesVelues;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class TestCase {

    WebDriver driver;
    LoginPage objLogin;
    GetPropertiesVelues propertiesVelues;
    /**
     * In this method we load the chrome driver,
     * set an implicit time out of 60s,
     * delete all cookies
     * maximize window
     * instantiate GetPropertiesVelues with adequate file that contains our data
     */
    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://recette.testeum.com/login");
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        propertiesVelues = new GetPropertiesVelues("config.properties");
    }

    /**
     * Login page :
     * Getting login values
     * We instantiate LoginPage with webDriver
     * Wait until the page is loaded
     * set login information and click on login button
     * by using {@link GetPropertiesVelues#getPropertyValue(String)} we wait until the welcome message be visible
     * and get the text
     * Assertion:
     *      -Expected Value : "hi 'username,'"
     * @throws Exception
     */
    @Test
    public void test_Login_Page() throws Exception{
        //Get user/password values
        String password = propertiesVelues.getPropertyValue("passWord");
        String userName = propertiesVelues.getPropertyValue("userName");
/**
 * Action 1 : Login
 */
        objLogin = new LoginPage(this.driver);
        objLogin.waitLoginPage();
        objLogin.setUserName(userName);
        objLogin.setPassWord(password);
        objLogin.clickLogin();
        String result = objLogin.getTitleAfterLogin();
        String expected = "hi "+userName+",";
        Assert.assertEquals(result.toLowerCase(),expected.toLowerCase());


    }



    @AfterTest
    public void tearDown(){
        this.driver.quit();
    }

}

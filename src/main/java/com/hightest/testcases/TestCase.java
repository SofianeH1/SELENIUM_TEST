package com.hightest.testcases;

import com.hightest.pages.DashboardPage;
import com.hightest.pages.LoginPage;
import com.hightest.pages.TestPage;
import com.hightest.utils.GetPropertiesVelues;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class TestCase {

    WebDriver driver;
    LoginPage objLoginPage;
    TestPage objTestPage;
    DashboardPage objDashboardPage;
    GetPropertiesVelues propertiesVelues;
    String nbTT;
    String nbTTExpected;
    /**
     * SetUp Method
     * In this method we load the chrome driver,
     * set an implicit time out of 60s,
     * delete all cookies
     * maximize window
     * instantiate GetPropertiesVelues with adequate file that contains login data
     * instantiate Pages
     */
    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=en");
        driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();
        driver.get("https://recette.testeum.com/login");
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        propertiesVelues = new GetPropertiesVelues("config.properties");
        objTestPage = new TestPage(this.driver);
        objLoginPage = new LoginPage(this.driver);
        objDashboardPage = new DashboardPage(this.driver);
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
    @Test(priority = 0)
    public void test_Login_Page() throws Exception{

        //Get user/password values
        String password = propertiesVelues.getPropertyValue("passWord");
        String userName = propertiesVelues.getPropertyValue("userName");
        objLoginPage.waitLoginPage();
        objLoginPage.setUserName(userName);
        objLoginPage.setPassWord(password);
        objLoginPage.clickLogin();
        String result = objLoginPage.getTitleAfterLogin();
        String expected = "hi "+userName+",";
        Assert.assertEquals(result.toLowerCase(),expected.toLowerCase());
    }

    /**
     * Go to dashboard page and store TT in a global variable before execute test,
     * and next execute first test
     * @throws Exception
     */
    @Test (priority = 1)
    public void test_Execution_test() throws Exception{
        objDashboardPage.goTestPage();
        nbTT = objTestPage.getNbTT();
        objTestPage.selectFirstTest();
        objTestPage.executeTest();
        System.out.println("NBTT : "+nbTT);

    }

    /**
     * Ensure that the number of TTs before execution is equal to that obtained
     */
    @Test(priority = 3)
    public void verifyNbTT(){
        nbTTExpected = objDashboardPage.getNbTT();
        Assert.assertEquals(nbTT,nbTTExpected);
    }

    @AfterTest
    public void tearDown(){
        this.driver.quit();
    }

}

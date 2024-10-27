package demo;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.logging.Level;

import java.time.Duration;

import demo.wrappers.Wrappers;
// import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;
    String url = "https://www.scrapethissite.com/pages/";
    JavascriptExecutor js = (JavascriptExecutor) driver;
    WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(10));

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @Test (priority = 1, description = "Test scraping Hockey team data", enabled = true)
    public void testCase01() throws Exception{
     System.out.println("Started : TestCase01");
     //navigating to the website 
     Wrappers.navigateToWebsite(driver,url);
   

     //Clicking on the heading
     Wrappers.headingSelection(driver,"Hockey");
     //printing the Teamname ,year, win %
     Wrappers.teamData(driver);
     System.out.println("End : TestCase01");
    }

    @Test (priority = 2, description = "Test scraping Oscar movie data", enabled = true)
    public void testCase02() throws Exception{
        System.out.println("Started : TestCase02");
     //navigating to the website 
     Wrappers.navigateToWebsite(driver,url);
     
     //Clicking on the  heading
     Wrappers.headingSelection(driver,"Oscar");
     Wrappers.scrapeOscarData(driver);
     Wrappers.verifyFile();
     

    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}
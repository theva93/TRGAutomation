package com.trg.testframework.test;

import com.trg.testframework.trgsites.pageUtils.CommonOperations;
import com.trg.testframework.trgsites.pageUtils.GiveGift;
import com.trg.testframework.trgsites.pageUtils.HomePage;
import com.trg.testframework.trgsites.pageUtils.RedeemGift;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class driver {

    private static String valuesFile = "";
    private static int numOfDataSets;
    private static long testTimeout;
    private String paramScreenShotDir;
    private String paramUrl;
    private String paramBrowser;
    private String paramChromeDriverLocation = "";
    private static boolean paramDebug;
    private HomePage homePage = new HomePage();
    private GiveGift giveGift = new GiveGift();
    private RedeemGift redeemGift = new RedeemGift();
    private CommonOperations commonOperations = null;

    private static String landingProperties = "";

    private WebDriver driver = null;

    /**
     * This method get parameters from property file
     */
    public void getParametersFromFile() {
        try {
            InputStream props = this.getClass().getClassLoader()
                    .getResourceAsStream("trgsite.properties");
            Properties prop = new Properties();
            prop.load(props);
            int counter = 0;

            paramBrowser = new String(prop.getProperty("browser_" + String.valueOf(counter + 1)));
            paramChromeDriverLocation = new String(prop.getProperty("chromedriverlocation_" + String.valueOf(counter + 1)));
            paramUrl = new String(prop.getProperty("url_" + String.valueOf(counter + 1)));
            paramDebug = new Boolean(prop.getProperty("debug_" + String.valueOf(counter + 1)));
            paramScreenShotDir = new String(prop.getProperty("screenshotdir"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method creates the webdriver for a specific browser
     *
     * @param browser: name of the browser, could be iexplorer, firefox, chrome, safari
     * @return WebDriver object for the specified browser
     */
    public WebDriver createDriver(String browser) {
        WebDriver driver = null;
        if (paramBrowser.compareTo("firefox") == 0) {
            driver = new FirefoxDriver();
        } else if (paramBrowser.compareTo("chrome") == 0) {
            try {
                File file = new File(paramChromeDriverLocation);
                System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
                driver = new ChromeDriver();
            } catch (Exception e) {
                e.printStackTrace();
                assertTrue(false);
            }
        } else {
            driver = new FirefoxDriver();
        }
        return driver;
    }

    /**
     * Preconditions which need to run before scenario starts.
     */
    @Before
    public void startTest() {
        getParametersFromFile();
        driver = createDriver(paramBrowser);
        driver.get(paramUrl);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        commonOperations = new CommonOperations(driver);
        homePage.recieveDriver(driver, commonOperations);
        giveGift.receiveDriver(driver, commonOperations);
        redeemGift.recieveDriver(driver, commonOperations);

    }

    public void verifyHomePage() {

    }


    public void giveAGift() {

        try {
            Assert.assertTrue(homePage.navigatesToHomePage());
            Assert.assertTrue(homePage.verifyPageLogo());
            Assert.assertTrue(giveGift.verifyPathsToGiveAGiftPage());
            Assert.assertTrue(giveGift.goToGiveAGiftPage());
            Assert.assertTrue(giveGift.selectDeliveryMethodEmail());
            Assert.assertTrue(giveGift.checkForOccationsAndClick());
            Assert.assertTrue(giveGift.pickADesign());
            Assert.assertTrue(giveGift.giftDetails());
            Assert.assertTrue(giveGift.giftPreview());
            Assert.assertTrue(giveGift.reviewYourOrder());
            Assert.assertTrue(giveGift.continueAsAGuest());
            Assert.assertTrue(giveGift.checkOutDetails());

        } catch (Exception e) {
            e.printStackTrace();
            fail("couldnt finish the gifting procedure");
        }
    }


    @Test
    public void redeemAGift() {
        try {


            Assert.assertTrue(homePage.navigatesToHomePage());

            Assert.assertTrue(homePage.verifyPageLogo());

            Assert.assertTrue(homePage.clickOnRedeemAGift());

            Assert.assertTrue(redeemGift.validGiftCodeAndEnter("15XW-1KE8-CE58"));

            //Assert.assertTrue(redeemGift.verifyRedeemAnotherCodeWithInvalidCode("2AVJ-QG3V-M2F1"));

            //Assert.assertTrue(redeemGift.invalidGiftCodeEnter("2AVJ-QG3V-M2F1"));

            Assert.assertTrue(redeemGift.insertEmail("udara@calcey.com"));

            Assert.assertTrue(redeemGift.insertAccountNo());

            //2AVJ-QG3V-M2F1/AEBL-W4YM-BKM6/15XW-1KE8-CE58


        } catch (Exception e) {

            e.printStackTrace();

        }
    }


        @After
        public void tearDown () {
            // driver.quit();
        }
    }


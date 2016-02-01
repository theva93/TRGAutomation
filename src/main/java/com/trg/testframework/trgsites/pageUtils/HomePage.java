package com.trg.testframework.trgsites.pageUtils;

import com.trg.testframework.trgsites.pageUtils.CommonOperations;
import com.trg.testframework.steps.Logger;
import com.trg.testframework.trgsites.propertyObjects.LandingObjValues;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HomePage {

    WebDriver driver = null;
    String screenShotDir = "";
    CommonOperations commonOperations = null;


    public void recieveDriver(WebDriver driver, CommonOperations commonOperations) {
        this.driver = driver;
        this.commonOperations = commonOperations;
    }

    public Boolean verifyPageLogo() throws Exception {
        Boolean pageLogoVerified = false;
        try {
            WebElement headerElement = driver.findElement(By.cssSelector(LandingObjValues.getElement("mainPageHeader")));
            pageLogoVerified = headerElement.findElement(By.cssSelector(LandingObjValues.getElement("logoImage"))).isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.log("failed to verify Page Logo");
        }
        return pageLogoVerified;
    }


    public Boolean navigatesToHomePage() {
        Boolean homePageLoaded = false;
        try {
            WebElement Herotexts = driver.findElement(By.cssSelector(LandingObjValues.getElement("mainPageHeroText")));
            if (Herotexts.isDisplayed()) {
                WebElement heading1 = Herotexts.findElement(By.cssSelector(LandingObjValues.getElement("heroText_1")));
                WebElement heading2 = Herotexts.findElement(By.cssSelector(LandingObjValues.getElement("heroText_2")));
                if (heading1.isDisplayed() && heading2.isDisplayed()) {
                    homePageLoaded = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.log("failed to navigate to Home page");
        }
        return homePageLoaded;
    }

    public Boolean clickOnRedeemAGift(){
        Boolean clickedOnRedeemAGiftButton=false;

        try{

            WebElement RedeemAGift= driver.findElement(By.cssSelector(LandingObjValues.getElement("redeemAGift")));
            RedeemAGift.click();
            clickedOnRedeemAGiftButton=true;
        }catch (Exception e){
            throw  e;

        }

        return clickedOnRedeemAGiftButton;
    }
}

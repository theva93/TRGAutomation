package com.trg.testframework.trgsites.pageUtils;


import com.trg.testframework.steps.Logger;
import com.trg.testframework.trgsites.propertyObjects.GiveGiftObjValues;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Calcey on 1/25/2016.
 */
public class CommonOperations {
    int tries=3;


    WebDriver driver=null;

    public CommonOperations(WebDriver driver){
        this.driver=driver;
    }

    public boolean waitForAnElementDisplayed(WebDriver d,int sleeptime, By condition) {
        int tries=4;
        for(int count = 0; count < tries; ++count) {
            try {
                WebElement e = d.findElement(condition);
                if(e.isDisplayed()) {
                    return true;
                }

                try {
                    Thread.sleep((long)sleeptime);
                } catch (InterruptedException var9) {
                    Thread.currentThread().interrupt();
                }
            } catch (Exception var10) {
                try {
                    Thread.sleep((long)sleeptime);
                } catch (InterruptedException var8) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        return false;
    }

    public boolean waitForAnElementDisplayed(String Webelement,WebElement webElement, int tries, int sleepTime) {
        for (int count = 0; count < tries; ++count) {
            try {

                if (webElement.isDisplayed()) {
                    Logger.log(""+Webelement+" displayed ");
                    return true;
                }
                else {
                    try {
                        Thread.sleep((long) sleepTime);
                    } catch (InterruptedException var9) {
                        Thread.currentThread().interrupt();
                    }
                }
            } catch (Exception var10) {
                try {
                    Thread.sleep((long) sleepTime);
                } catch (InterruptedException var8) {
                    Thread.currentThread().interrupt();
                }
                Logger.log("Couldn't display the "+Webelement+"");
            }
        }

        return false;
    }

    public WebElement waitForAnElementPresent(WebDriver driver, int waitTime, By condition) throws Exception {
        WebElement waitForElement=null;
        for(int i=0;i<tries;i++) {
            waitForElement = (new WebDriverWait(driver, waitTime))
                    .until(ExpectedConditions.presenceOfElementLocated(condition));
            if(driver.findElement(condition).isDisplayed()){
                break;
            }
        }
        return waitForElement;
    }

    public WebElement waitForAnElementClickable(WebDriver driver, int waitTime, By condition) throws Exception {
        WebElement waitForClickable=null;
        for(int i=0;i<tries;i++) {
            waitForClickable = (new WebDriverWait(driver, waitTime))
                    .until(ExpectedConditions.elementToBeClickable(condition));
            if (driver.findElement(condition).isDisplayed()) {
                break;
            }
        }
        return waitForClickable;
    }
    public WebElement waitForAnElementClickableByObject(WebDriver driver, int waitTime, WebElement webElement) throws Exception {
        WebElement waitForClickable=null;
        for(int i=0;i<tries;i++) {
            waitForClickable = (new WebDriverWait(driver, waitTime))
                    .until(ExpectedConditions.elementToBeClickable(webElement));
            if (webElement.isDisplayed()) {
                break;
            }
        }
        return waitForClickable;
    }

    public WebElement getSectionHeader(){
        WebElement sectionHeader=driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("sectionHeader")));
        return  sectionHeader;
    }

}

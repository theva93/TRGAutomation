package com.trg.testframework.trgsites.pageUtils;


import com.trg.testframework.steps.Logger;
import com.trg.testframework.trgsites.pageUtils.CommonOperations;
import com.trg.testframework.trgsites.propertyObjects.RedeemGiftObjValues;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * Created by calcey on 1/21/16.
 */
public class RedeemGift {

    WebDriver driver = null;
    CommonOperations commonOperations= null;

    String screenShotDir = "";


    public void recieveDriver(WebDriver driver, CommonOperations commonOperations) {

        this.driver = driver;
        this.commonOperations = commonOperations;

    }

    public boolean validGiftCodeAndEnter(String validCode) {

        boolean userEnterdAValidCode = false;
        try {

            WebElement codeTextField = driver.findElement(By.cssSelector(RedeemGiftObjValues.getElement("codeTextField"))).findElement(By.name("coupon"));
            WebElement redeemGiftButton = driver.findElement(By.cssSelector(RedeemGiftObjValues.getElement("test")));

            if (commonOperations.waitForAnElementDisplayed("codeTextField", codeTextField, 3, 500)) {
                codeTextField.sendKeys(validCode);
                Logger.log("valid " + validCode + " entered");

                if (commonOperations.waitForAnElementDisplayed("redeemGiftButton", redeemGiftButton, 3, 500)) {
                    redeemGiftButton.click();
                    Logger.log("redeemGiftButton clicked");
                    Thread.sleep(2000);

                    if (driver.findElement(By.cssSelector("[class=section-heading]")).getText().trim().equals("congratulations!\ntime to divide your gift any way you want.")) {
                        Logger.log("User entered a valid Gift code and move to next page");
                        userEnterdAValidCode = true;
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.log("Enter the valid Gift code coupon and cannot move further");

        }
        return userEnterdAValidCode;
    }


    public boolean invalidGiftCodeEnter(String invalidCode) throws InterruptedException {

        boolean invalidCodeEntered = false;
        try {


            WebElement codeTextField = driver.findElement(By.cssSelector(RedeemGiftObjValues.getElement("codeTextField"))).findElement(By.name("coupon"));
            WebElement redeemGiftButton = driver.findElement(By.cssSelector(RedeemGiftObjValues.getElement("test")));

            if (commonOperations.waitForAnElementDisplayed("codeTextField", codeTextField, 3, 500)) {


                codeTextField.sendKeys(invalidCode);
                Logger.log("" + invalidCode + " entered");


                if (commonOperations.waitForAnElementDisplayed("redeemGiftButton", redeemGiftButton, 3, 1500)) {


                    redeemGiftButton.click();
                    Logger.log("clicked redeemGiftButton");
                    Thread.sleep(2000);

                    if (driver.findElement(By.xpath(RedeemGiftObjValues.getElement("errorMessageRedeemCode"))).getText().trim().equals("Sorry. This coupon is invalid.")) {

                        invalidCodeEntered = true;
                        Logger.log("User entered invalid coupon and couldn't go further ");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.log("User entered invalid coupon but can go further ");
        }
        return invalidCodeEntered;
    }


    public boolean insertEmail(String email) {
        boolean emailInserted = false;
        try {


            WebElement emailText = driver.findElement(By.id("Recipient Email"));


            if (commonOperations.waitForAnElementDisplayed("emailText", emailText, 3, 500)) {
                emailText.sendKeys(email);
                Logger.log("" + email + " entered");
                WebElement continueButton = driver.findElement(By.cssSelector(RedeemGiftObjValues.getElement("continueButton")));
                Thread.sleep(2000);

                if (commonOperations.waitForAnElementDisplayed("continueButton", continueButton, 3, 1500)) {

                    continueButton.click();
                    Logger.log("continueButton clicked");
                    emailInserted = true;
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
            Logger.log("Couldn't enter the email");

        }

        return emailInserted;

    }


    public boolean insertAccountNo() throws InterruptedException {

        boolean accountNumberInserted = false;

        try {

            WebElement accountNumberText = driver.findElement(By.name("accountDetails"));
            WebElement checkOutButton = driver.findElement(By.cssSelector(RedeemGiftObjValues.getElement("checkOutButton")));

            accountNumberText.sendKeys("165411651654");
            Logger.log("Account number inserted");


            if (commonOperations.waitForAnElementDisplayed("checkOutButton", checkOutButton, 3, 500)) {
                checkOutButton.click();
                Logger.log("checkOutButton clicked");
                accountNumberInserted = true;
            }


        } catch (Exception e) {

            e.printStackTrace();
            Logger.log("Couldn't enter Account number");


        }


        return accountNumberInserted;
    }

    public boolean verifyRedeemAnotherCodeWithInvalidCode(String invalidCode) {

        boolean verifiedRedeemAnotherCodeWithInvalidCode = false;
        try {

            WebElement redeemeAnotherGiftButton = driver.findElement(By.cssSelector(RedeemGiftObjValues.getElement("butttonRedeemAnotherCode")));
            redeemeAnotherGiftButton.click();
            WebElement redeemeAnotherCodeTextField = driver.findElement(By.cssSelector(RedeemGiftObjValues.getElement("redeemeAnotherCodeTextField")));
            redeemeAnotherCodeTextField.sendKeys(invalidCode);

            Thread.sleep(2000);
            WebElement redeemAnotherCodeRedeemButton = driver.findElement(By.cssSelector(RedeemGiftObjValues.getElement("redeemAnotherCodeRedeemButton")));
            redeemAnotherCodeRedeemButton.click();
            Logger.log("test");
            Thread.sleep(2000);

            if (driver.findElement(By.xpath(RedeemGiftObjValues.getElement("errorMessageRedeemAnotherCode"))).getText().trim().equals("Coupon is not valid.")) {

                verifiedRedeemAnotherCodeWithInvalidCode= true;
                Logger.log("User entered invalid coupon in Redeem Another code path and couldn't go further ");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return verifiedRedeemAnotherCodeWithInvalidCode;

    }
}




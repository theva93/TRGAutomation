package com.trg.testframework.trgsites.pageUtils;

import com.trg.testframework.trgsites.pageUtils.CommonOperations;
import com.trg.testframework.steps.Logger;
import com.trg.testframework.trgsites.propertyObjects.GiveGiftObjValues;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Calcey on 1/19/2016.
 */
public class GiveGift {

    WebDriver driver = null;
    WebElement continueToNextStep;
    WebElement homePageHeader;
    CommonOperations commonOperations = null;

    public void receiveDriver(WebDriver driver, CommonOperations commonOperations) {
        this.driver = driver;
        this.commonOperations = commonOperations;
    }

    public Boolean goToGiveAGiftPage() throws Exception {
        Boolean landedToGiveAGiftPage = false;
        try {
            commonOperations.waitForAnElementClickable(driver, 10, By.cssSelector(GiveGiftObjValues.getElement("giveTheResposibleGift")));
            WebElement giveTheResposibleGift = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("giveTheResposibleGift")));
            giveTheResposibleGift.click();

            WebElement giveGiftPage = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("giveAGiftHeading")));
            if (giveGiftPage.isDisplayed() && giveGiftPage.getText().toLowerCase().contains("give a gift")) {
                landedToGiveAGiftPage = true;
                Logger.log("Landed to give a gift page");
            }
        } catch (Exception e) {
            Logger.log("could not land to the give a gift page");
            e.printStackTrace();
        }
        return landedToGiveAGiftPage;
    }

    public Boolean verifyPathsToGiveAGiftPage() throws Exception {

        Boolean giveGiftPageLoaded = false;
        try {
            commonOperations.waitForAnElementClickable(driver, 10, By.cssSelector(GiveGiftObjValues.getElement("giveTheResposibleGift")));
            WebElement giveTheResposibleGift = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("giveTheResposibleGift")));
            giveTheResposibleGift.click();

            commonOperations.waitForAnElementClickable(driver, 10, By.cssSelector(GiveGiftObjValues.getElement("homePageHeader")));
            homePageHeader = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("homePageHeader")));
            homePageHeader.click();

            commonOperations.waitForAnElementClickable(driver, 10, By.cssSelector(GiveGiftObjValues.getElement("GiveAGift")));
            WebElement GiveAGift = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("GiveAGift")));
            GiveAGift.click();

            commonOperations.waitForAnElementClickable(driver, 10, By.cssSelector(GiveGiftObjValues.getElement("homePageHeader")));
            homePageHeader = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("homePageHeader")));
            homePageHeader.click();

            commonOperations.waitForAnElementClickable(driver, 10, By.cssSelector(GiveGiftObjValues.getElement("GetStartedNow")));
            WebElement GetStartedNow = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("GetStartedNow")));
            GetStartedNow.click();

            WebElement giveGiftPage = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("giveAGiftHeading")));
            if (giveGiftPage.isDisplayed() && giveGiftPage.getText().toLowerCase().contains("give a gift")) {
                giveGiftPageLoaded = true;
                Logger.log("Paths to Give a Gift page is Verified ");
            }

            commonOperations.waitForAnElementClickable(driver, 10, By.cssSelector(GiveGiftObjValues.getElement("homePageHeader")));
            homePageHeader = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("homePageHeader")));
            homePageHeader.click();

        } catch (Exception e) {
            Logger.log("cannot verify the paths to the Give a gift page");
            e.printStackTrace();
        }

        return giveGiftPageLoaded;
    }

    public Boolean selectDeliveryMethodEmail() throws Exception {

        Boolean selectedDeliveryByEmail = false;
        try {
            commonOperations.waitForAnElementClickable(driver, 10, By.xpath(GiveGiftObjValues.getElement("deliveryOptionEmail")));
            WebElement deliveryOptions = driver.findElement(By.xpath(GiveGiftObjValues.getElement("deliveryOptionEmail")));
            deliveryOptions.click();

            continueToNextStep();
            WebElement OccasionsHeading = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("OccasionsHeading")));
            if (OccasionsHeading.getText().toLowerCase().contains("choose the occasion")) {
                selectedDeliveryByEmail = true;
                Logger.log("selected Delivery method by Email");
            }

        } catch (Exception e) {
            Logger.log("cannot select the delivery method by Email");
            e.printStackTrace();
        }
        return selectedDeliveryByEmail;
    }

    public Boolean selectDeliveryMethodprintAtHome() {

        Boolean selectedPrintAtHome = false;
        try {
            WebElement deliveryOptions = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("deliveryOptionPrintAtHome")));
            if (deliveryOptions.isDisplayed()) {
                selectedPrintAtHome = true;
            }
        } catch (Exception e) {
            Logger.log("cannot select the delivery method Print at Home");
            e.printStackTrace();
        }

        return selectedPrintAtHome;
    }

    public Boolean checkForOccationsAndClick() throws Exception {
        Boolean selectedOneOccation = false;
        int index = 1;
        try {
            commonOperations.waitForAnElementPresent(driver, 10, By.cssSelector(GiveGiftObjValues.getElement("selectOccations")));
            List<WebElement> selectOccations = driver.findElements(By.cssSelector(GiveGiftObjValues.getElement("selectOccations")));
            if (selectOccations.size() == 6) {
                Logger.log("All Occations are available to select");
            }
            WebElement selectOneOccation = selectOccations.get(index).findElement(By.tagName("a"));
            selectOneOccation.click();
            selectedOneOccation = true;
            Logger.log("selected one occation");
            continueToNextStep();
        } catch (Exception e) {
            Logger.log("cannot check for the occations and click one");
            e.printStackTrace();
        }

        return selectedOneOccation;
    }

    public Boolean pickADesign() throws Exception {

        Boolean pickedADesign = false;
        int randomDesignindex = 2;

        try {
            WebElement pickADesignPageHeader = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("pickADesignPageHeader")));
            if (pickADesignPageHeader.getText().toLowerCase().contains("pick a design")) {
                System.out.println("pick a design page Loaded");
            }
            WebElement useOurDesignsTab = driver.findElement(By.xpath(GiveGiftObjValues.getElement("useOurDesignsTab")));
            useOurDesignsTab.click();

            commonOperations.waitForAnElementPresent(driver, 10, By.xpath(GiveGiftObjValues.getElement("imagesToPickDesign")));
            List<WebElement> designsToPick = driver.findElements(By.xpath(GiveGiftObjValues.getElement("imagesToPickDesign")));

            if (designsToPick.size() > 0) {
                Logger.log("Thumbnails to select designs are available");
            }
            commonOperations.waitForAnElementClickableByObject(driver, 10, designsToPick.get(randomDesignindex));
            designsToPick.get(randomDesignindex).click();

            List<WebElement> continueTonextStepDesigns = driver.findElements(By.cssSelector(GiveGiftObjValues.getElement("continueTonextStepDesigns")));
            for (WebElement we : continueTonextStepDesigns) {
                if (we.getAttribute("ng-disabled").contains("!design.selectedBasicDesign")) {
                    we.click();
                    break;
                }
            }
            System.out.println("Design is picked");
            pickedADesign = true;
        } catch (Exception e) {
            Logger.log("cannot pick A design for the card from the given");
            e.printStackTrace();
        }
        return pickedADesign;
    }

    public Boolean giftDetails() throws Exception {
        Boolean enteredGiftDetails = false;
        String GiftDetailrecipientEmail="udara@calcey.com";
        String GiftDetailSenderEmail="thevan@calcey.com";
        String GiftDetailFrom="Theva";
        String GiftDetailMessage="TRG Test Automation";

        try {
            WebElement giftDetailsHeader = commonOperations.getSectionHeader();
            if (giftDetailsHeader.getText().toLowerCase().contains("gift details: select an amount")) {
                System.out.println("The Gift details page is available");
            }

            commonOperations.waitForAnElementPresent(driver, 10, By.cssSelector(GiveGiftObjValues.getElement("radioAmounts")));

            List<WebElement> radioAmounts = driver.findElements(By.cssSelector(GiveGiftObjValues.getElement("radioAmounts")));
            radioAmounts.get(2).click();
            WebElement recipientEmail = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("recipientEmail")));
            recipientEmail.sendKeys(GiftDetailrecipientEmail);
            WebElement fromGiftDetail = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("from")));
            fromGiftDetail.sendKeys(GiftDetailFrom);
            WebElement senderEmail = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("senderEmail")));
            senderEmail.sendKeys(GiftDetailSenderEmail);

            WebElement messageForGiftDetail = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("message")));
            messageForGiftDetail.sendKeys(GiftDetailMessage);
            enteredGiftDetails = true;
            continueToNextStep();

        } catch (Exception e) {
            Logger.log("Unable to fill Gift details");
            e.printStackTrace();
        }

//        try {
//            int randomMonthIndex = 2;
//            int randomDateIndex = 10;
//            WebElement calendarButtonGiftDetail = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("calendarButtonGiftDetail")));
//            calendarButtonGiftDetail.click();
//            WebElement calendarMonthPanel = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("calendarMonthPanel")));
//            if (!calendarMonthPanel.isDisplayed()) {
//                Logger.log("calendar month menu is not visible");
//            }
//            List<WebElement> calendarMonthsAvailabletoClick = driver.findElements(By.cssSelector(GiveGiftObjValues.getElement("calendarButtonGiftDetail")));
//            if (calendarMonthsAvailabletoClick.size() != 12) {
//                Logger.log("calendar months are not available to click");
//            }
//            calendarMonthsAvailabletoClick.get(randomMonthIndex).click();
//            WebElement calendarDatePanel = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("calendarDatePanel")));
//            if (!calendarDatePanel.isDisplayed()) {
//                Logger.log("Calendar date menu is not visible");
//            }
//            List<WebElement> calendarDatesAvailableToClick = driver.findElements(By.xpath("//div[@class='moment-picker-specific-views']/table/tbody/tr/td)"));
//            if (calendarDatesAvailableToClick.size() != 42) {
//                Logger.log("Calendar dates are not availale to click");
//            }
//            calendarDatesAvailableToClick.get(randomDateIndex).click();
//
//        } catch (Exception e) {
//            Logger.log("unable to select a Delivery Date");
//            e.printStackTrace();
//        }

        return enteredGiftDetails;
    }

    public Boolean giftPreview() throws Exception {
        Boolean giftPreviewCompleted = false;
        try {
            commonOperations.waitForAnElementClickable(driver, 10, By.cssSelector(GiveGiftObjValues.getElement("continueToNextStepGiftPreview")));
            WebElement continueToNextStepGiftPreview = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("continueToNextStepGiftPreview")));
            continueToNextStepGiftPreview.click();
            giftPreviewCompleted = commonOperations.getSectionHeader().getText().toLowerCase().contains("review your order");
            giftPreviewCompleted = true;
        } catch (Exception e) {
            Logger.log("unable to preview the Gift");
            e.printStackTrace();
        }
        return giftPreviewCompleted;
    }

    public Boolean reviewYourOrder() throws Exception {
        Boolean reviewedTheOrder = false;
        try {
            reviewedTheOrder = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("reviewYourOrder"))).getText().toLowerCase().contains("review your order");
            continueToNextStep();
            reviewedTheOrder = true;
        } catch (Exception e) {
            Logger.log("unable to review the Order");
            e.printStackTrace();
        }
        return reviewedTheOrder;
    }

    public Boolean continueAsAGuest() throws Exception {
        Boolean continuedAsAGuest = false;
        try {
            commonOperations.waitForAnElementClickable(driver, 10, By.cssSelector(GiveGiftObjValues.getElement("continueAsAGuestButton")));
            WebElement continueAsAGuestButton = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("continueAsAGuestButton")));
            continueAsAGuestButton.click();
            continuedAsAGuest = commonOperations.getSectionHeader().getText().toLowerCase().contains("checkout");
            continuedAsAGuest = true;
        } catch (Exception e) {
            Logger.log("cannot continue as a guest");
            e.printStackTrace();
        }
        return continuedAsAGuest;
    }

    public Boolean checkOutDetails() throws Exception {
        String creditCardNo = "4111 1111 1111 1111";
        String expirationDate = "03/2016";
        String cvvCheckOutNum = "123";
        Boolean providedCheckOutDetails = false;
        try {
            commonOperations.waitForAnElementPresent(driver, 10, By.cssSelector(GiveGiftObjValues.getElement("checkOutDetailContainer")));
            driver.switchTo().frame(driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("checkOutDetailContainer"))));
            commonOperations.waitForAnElementPresent(driver,10, By.cssSelector(GiveGiftObjValues.getElement("creditCardNumberLabel")));
            WebElement creditCardNumberLabel = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("creditCardNumberLabel")));
            creditCardNumberLabel.click();
            WebElement creditCardNumber = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("creditCardNumber")));
            creditCardNumber.sendKeys(creditCardNo);
            WebElement expirationDtaeLabel = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("expirationDtaeLabel")));
            expirationDtaeLabel.click();
            WebElement expirationDateCheckOut = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("expirationDateCheckOut")));
            expirationDateCheckOut.sendKeys(expirationDate);
            WebElement cvvLabel = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("cvvLabel")));
            cvvLabel.click();
            WebElement cvvCheckOut = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("cvvCheckOutDetail")));
            cvvCheckOut.sendKeys(cvvCheckOutNum);
            providedCheckOutDetails = true;
            driver.switchTo().defaultContent();
            WebElement continueCheckOut=driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("continueCheckOut")));
            continueCheckOut.click();

        } catch (Exception e) {
            Logger.log("unable to checkOut the Details");
            e.printStackTrace();
        }

        return providedCheckOutDetails;
    }

    public void continueToNextStep() throws Exception {
        try {
            commonOperations.waitForAnElementClickable(driver, 10, By.cssSelector(GiveGiftObjValues.getElement("continueToNextStep")));
            continueToNextStep = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("continueToNextStep")));
            continueToNextStep.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}



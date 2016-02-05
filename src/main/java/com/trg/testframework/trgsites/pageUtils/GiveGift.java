package com.trg.testframework.trgsites.pageUtils;

import com.trg.testframework.trgsites.pageUtils.CommonOperations;
import com.trg.testframework.steps.Logger;
import com.trg.testframework.trgsites.propertyObjects.GiveGiftObjValues;
import org.jboss.netty.channel.ExceptionEvent;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Array;
import java.util.List;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
/**
 * Created by Calcey on 1/19/2016.
 */
public class GiveGift {

    WebDriver driver = null;
    WebElement continueToNextStep;
    WebElement homePageHeader;
    CommonOperations commonOperations = null;
    HomePage homePage=null;
    private String[] wrongEmails = new String[5];

    public void receiveDriver(WebDriver driver, CommonOperations commonOperations, HomePage homePage) {
        this.driver = driver;
        this.commonOperations = commonOperations;
        this.homePage = homePage;
    }
    public boolean isGiveAGiftPageLanded (){
        boolean landedToGiveAGiftPage=false;
        try {
            commonOperations.waitForAnElementPresent(driver,10,By.cssSelector(GiveGiftObjValues.getElement("giveAGiftHeading")));
            WebElement giveGiftPage = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("giveAGiftHeading")));
            if (giveGiftPage.isDisplayed() && giveGiftPage.getText().toLowerCase().contains("give a gift")) {
                landedToGiveAGiftPage = true;
            }

        }catch (Exception e){
            Logger.log("failed to land to Give A gift Page");
            e.printStackTrace();
        }


        return landedToGiveAGiftPage;
    }

    public Boolean goToGiveAGiftPage() throws Exception {
        Boolean landedToGiveAGiftPage = false;
        try {
            commonOperations.waitForAnElementClickable(driver, 10, By.cssSelector(GiveGiftObjValues.getElement("giveTheResposibleGift")));
            WebElement giveTheResposibleGift = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("giveTheResposibleGift")));
            giveTheResposibleGift.click();
            landedToGiveAGiftPage=isGiveAGiftPageLanded();

        } catch (Exception e) {
            Logger.log("could not land to the give a gift page");
            e.printStackTrace();
        }
        return landedToGiveAGiftPage;
    }

    public boolean PathToGiveAGiftPageGiveAGift(){
        boolean verifiedPathGiveAGift=false;
        try{
            commonOperations.waitForAnElementClickable(driver, 10, By.cssSelector(GiveGiftObjValues.getElement("GiveAGift")));
            WebElement GiveAGift = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("GiveAGift")));
            GiveAGift.click();

            verifiedPathGiveAGift=isGiveAGiftPageLanded();
            Assert.assertTrue(goToHomePage());

        }catch (Exception e){
            Logger.log("error in path to Give A Gift through 'Give A Gift' ");
        }
        return verifiedPathGiveAGift;
    }
    public boolean PathToGiveAGiftPageGetStartedNow(){
        boolean verifiedPathGetStartedNow=false;
        try{
            commonOperations.waitForAnElementClickable(driver, 10, By.cssSelector(GiveGiftObjValues.getElement("GetStartedNow")));
            WebElement GetStartedNow = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("GetStartedNow")));
            GetStartedNow.click();

            verifiedPathGetStartedNow=isGiveAGiftPageLanded();
            Assert.assertTrue(goToHomePage());

        }catch (Exception e){
            Logger.log("error in path to Give A Gift through 'Give A Gift' ");
        }
        return verifiedPathGetStartedNow;
    }

    public boolean pathToGiveAGiftCreateAGiftFooter() {
        boolean verifiedPathCreateAGiftFooter = false;
        try {
            commonOperations.waitForAnElementClickable(driver, 10, By.xpath(GiveGiftObjValues.getElement("createAGiftFooter")));
            WebElement createAGiftFooter = driver.findElement(By.xpath(GiveGiftObjValues.getElement("createAGiftFooter")));
            createAGiftFooter.click();

            verifiedPathCreateAGiftFooter = isGiveAGiftPageLanded();
            Assert.assertTrue(goToHomePage());

        } catch (Exception e) {
            Logger.log("error in path to Give A Gift through 'Create A Gift' in footer");
        }
        return verifiedPathCreateAGiftFooter;
    }

    public boolean pathToGiveAGiftURL(){
        boolean verifiedPathCreateAGiftURL=false;
        String URLGiveAGift="http://trg.calcey.net/#/gift/create";
        try{
            driver.navigate().to(URLGiveAGift);
            verifiedPathCreateAGiftURL=isGiveAGiftPageLanded();
            Assert.assertTrue(goToHomePage());
        }catch (Exception e){
            Logger.log("failed to land to Give A gift Page trough Direct URL");
        }

        return verifiedPathCreateAGiftURL;
    }


    public Boolean verifyPathsToGiveAGiftPage() throws Exception {
        Boolean giveGiftPageLoaded = false;
        try {
            Assert.assertTrue(PathToGiveAGiftPageGiveAGift());
            Assert.assertTrue(PathToGiveAGiftPageGetStartedNow());
            Assert.assertTrue(pathToGiveAGiftCreateAGiftFooter());
            Assert.assertTrue(pathToGiveAGiftURL());
            giveGiftPageLoaded=true;
        } catch (Exception e) {
            Logger.log("cannot verify the paths to the Give a gift page");
            e.printStackTrace();
        }
        return giveGiftPageLoaded;
    }

    public Boolean verifyDeliveryMethodsUI(){
        Boolean verifiedDeliveryMethodsUI = false;
        try{
            commonOperations.waitForAnElementPresent(driver, 10, By.xpath(GiveGiftObjValues.getElement("deliveryOptions")));
            List<WebElement> deliveryOptions=driver.findElements(By.xpath(GiveGiftObjValues.getElement("deliveryOptions")));
            for(WebElement we:deliveryOptions){
                if(!we.isDisplayed()){
                    Logger.log("Delivery options are not visible or clickable");
                }
            }
            WebElement compareDeliveryMethods=driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("compareDeliveryMethods")));
            compareDeliveryMethods.click();
            commonOperations.waitForAnElementPresent(driver, 5, By.xpath(GiveGiftObjValues.getElement("aboutDeliveryMethodsElements")));
            List<WebElement> aboutDeliveryMethodsElements=driver.findElements(By.xpath(GiveGiftObjValues.getElement("aboutDeliveryMethodsElements")));
            for (WebElement we:aboutDeliveryMethodsElements ){
                if(!we.isDisplayed()){
                    Logger.log("Missing Compare details for delivery methods");
                }
            }
            commonOperations.waitForAnElementPresent(driver, 5, By.cssSelector(GiveGiftObjValues.getElement("getStartedNowDeliveryMethods")));
            WebElement getStartedNowDeliveryMethods=driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("getStartedNowDeliveryMethods")));
            getStartedNowDeliveryMethods.click();
            verifiedDeliveryMethodsUI=true;

        }catch (Exception e){
            Logger.log("errors in user interface for selecting the delivery methods");
            e.printStackTrace();
        }
        return verifiedDeliveryMethodsUI;
    }

    public Boolean  selectDeliveryMethodEmail() throws Exception {

        Boolean selectedDeliveryByEmail = false;
        try {
            commonOperations.waitForAnElementClickable(driver, 10, By.xpath(GiveGiftObjValues.getElement("deliveryOptionEmail")));
            WebElement deliveryOptions = driver.findElement(By.xpath(GiveGiftObjValues.getElement("deliveryOptionEmail")));
            deliveryOptions.click();

            continueToNextStep();
            WebElement OccasionsHeading = commonOperations.getSectionHeader();
            if (OccasionsHeading.getText().toLowerCase().contains("choose the occasion")) {
                selectedDeliveryByEmail = true;
            }
        } catch (Exception e) {
            Logger.log("cannot select the delivery method by Email");
            e.printStackTrace();
        }
        return selectedDeliveryByEmail;
    }

    public Boolean selectDeliveryMethodPrintAtHome() {

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
        int randomChoiceOccasion;
        try {
            commonOperations.waitForAnElementPresent(driver, 10, By.cssSelector(GiveGiftObjValues.getElement("selectOccations")));
            List<WebElement> selectOccations = driver.findElements(By.cssSelector(GiveGiftObjValues.getElement("selectOccations")));
            if (selectOccations.size()<=0) {
                Logger.log("Occasions are not available to click");
            }
            randomChoiceOccasion=commonOperations.randomInt(0,(selectOccations.size()-1));
            WebElement selectOneOccation = selectOccations.get(randomChoiceOccasion).findElement(By.tagName("a"));
            selectOneOccation.click();
            selectedOneOccation = true;

            continueToNextStep();
        } catch (Exception e) {
            Logger.log("cannot check for the occations and click one");
            e.printStackTrace();
        }
        return selectedOneOccation;
    }

    public Boolean pickADesign() throws Exception {

        Boolean pickedADesign = false;
        int randomDesignindex;

        try {
            WebElement pickADesignPageHeader = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("pickADesignPageHeader")));
            if (!pickADesignPageHeader.getText().toLowerCase().contains("pick a design")) {
                Logger.log("failed to load pick a design page ");
            }
            WebElement useOurDesignsTab = driver.findElement(By.xpath(GiveGiftObjValues.getElement("useOurDesignsTab")));
            useOurDesignsTab.click();

            commonOperations.waitForAnElementPresent(driver, 10, By.xpath(GiveGiftObjValues.getElement("imagesToPickDesign")));
            List<WebElement> designsToPick = driver.findElements(By.xpath(GiveGiftObjValues.getElement("imagesToPickDesign")));

            if (designsToPick.size() == 0) {
                Logger.log("Thumbnails are not available to select designs ");
            }
            randomDesignindex=commonOperations.randomInt(0,(designsToPick.size()-1));
            commonOperations.waitForAnElementClickableByObject(driver, 10, designsToPick.get(randomDesignindex));
            designsToPick.get(randomDesignindex).click();

            List<WebElement> continueTonextStepDesigns = driver.findElements(By.cssSelector(GiveGiftObjValues.getElement("continueTonextStepDesigns")));
            for (WebElement we : continueTonextStepDesigns) {
                if (we.getAttribute("ng-disabled").contains("!design.selectedBasicDesign")) {
                    we.click();
                    break;
                }
            }
            pickedADesign = true;
        } catch (Exception e) {
            Logger.log("cannot pick A design for the card from the given");
            e.printStackTrace();
        }
        return pickedADesign;
    }

    public boolean validateSenderEmail(){
        boolean validatedSenderEmail = false;
        try {
            for(int i=0; i< Array.getLength(wrongEmails);i++) {
                validatedSenderEmail=false;
                commonOperations.waitForAnElementPresent(driver, 10, By.cssSelector(GiveGiftObjValues.getElement("senderEmail")));
                WebElement senderEmail = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("senderEmail")));
                senderEmail.sendKeys(wrongEmails[i]);
                WebElement fromGiftDetail = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("from")));
                fromGiftDetail.click();

                WebElement senderEmailErrorMessage= driver.findElement(By.xpath(GiveGiftObjValues.getElement("senderEmailErrorMessage")));
                if ((senderEmailErrorMessage.getText().contains("Please enter a valid sender email."))) {
                    validatedSenderEmail = true;
                    driver.navigate().refresh();
                }
            }
        }catch (Exception e){
            Logger.log("error in validating Recipient email");
        }

        return validatedSenderEmail;
    }

    public boolean validateRecipientEmail() {
        boolean validatedRecipientEmail = false;
        try {
            for (int i = 0; i < Array.getLength(wrongEmails); i++) {
                validatedRecipientEmail = false;
                commonOperations.waitForAnElementPresent(driver, 10, By.cssSelector(GiveGiftObjValues.getElement("recipientEmail")));
                WebElement recipientEmail = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("recipientEmail")));
                recipientEmail.sendKeys(wrongEmails[i]);

                WebElement fromGiftDetail = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("from")));
                fromGiftDetail.click();
                WebElement recipientEmailErrorMessage = driver.findElement(By.xpath(GiveGiftObjValues.getElement("recipientEmailErrorMessage")));
                if (recipientEmailErrorMessage.getText().contains("Please enter a valid recipient email.")) {
                    validatedRecipientEmail = true;
                    driver.navigate().refresh();
                }
            }
        } catch (Exception e) {
            Logger.log("error in validating Recipient email");
        }
        return validatedRecipientEmail;
    }

    public boolean validateGiftAmountSelections(){
        boolean validatedSelectionsForAmount=false;
        List<WebElement> radioButtonSelections=driver.findElements(By.cssSelector(GiveGiftObjValues.getElement("radioAmounts")));
        for(int i=0; i<(radioButtonSelections.size());i++){
            Logger.log(radioButtonSelections.get(i).findElement(By.tagName("span")).getText());
        }
        radioButtonSelections.get(2).click();
        WebElement otherAmountTextField=driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("otherAmountTextField")));
        otherAmountTextField.getText();
        String checking1 =radioButtonSelections.get(2).findElement(By.tagName("input")).getAttribute("checked");
        String checking =radioButtonSelections.get(1).findElement(By.tagName("input")).getAttribute("checked");


        validatedSelectionsForAmount=true;



        return validatedSelectionsForAmount;
    }


    public  boolean validateGiftDetails(){
        boolean validatedGiftDetailsInput=false;
        wrongEmails[0] = "thevan";
        wrongEmails[1] = "thevan@";
        wrongEmails[2] = "thevan@calcey";
        wrongEmails[3] = "thevan@calcey.";
        wrongEmails[4] = "thevan@calcey.comlq";

        try {
            Assert.assertTrue(validateGiftAmountSelections());
            Assert.assertTrue(validateRecipientEmail());
            Assert.assertTrue(validateSenderEmail());
            validatedGiftDetailsInput=true;
        }catch (Exception e){
            Logger.log("Give a gift details Validation error");
            e.printStackTrace();
        }

        return validatedGiftDetailsInput;
    }

    public Boolean giftDetails() throws Exception {
        Boolean enteredGiftDetails = false;
        String GiftDetailrecipientEmail = "udara@calcey.com";
        String GiftDetailSenderEmail = "thevan@calcey.com";
        String GiftDetailFrom = "Theva";
        String GiftDetailMessage = "TRG Test Automation";

        try {
            WebElement giftDetailsHeader = commonOperations.getSectionHeader();
            if (!giftDetailsHeader.getText().toLowerCase().contains("gift details: select an amount")) {
               Logger.log("The Gift details page is Not available");
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
//            continueToNextStep();

        } catch (Exception e) {
            Logger.log("Unable to fill Gift details");
            e.printStackTrace();
        }

        try {
            int randomMonthIndex;
            int randomDateIndex;
            int randomHourDetailInedx;
            int randomMinuteIndex;

            WebElement calendarButtonGiftDetail = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("calendarButtonGiftDetail")));
            calendarButtonGiftDetail.click();
            WebElement calendarMonthPanel = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("calendarMonthPanel")));
            if (!calendarMonthPanel.isDisplayed()) {
                Logger.log("calendar month menu is not visible");
            }
            commonOperations.waitForAnElementPresent(driver, 10, By.cssSelector(GiveGiftObjValues.getElement("CalendarMonthsAvailabletoClick")));
            List<WebElement> calendarMonthsAvailabletoClick = calendarMonthPanel.findElements(By.cssSelector(GiveGiftObjValues.getElement("CalendarMonthsAvailabletoClick")));
            if (calendarMonthsAvailabletoClick.size() < 0) {
                Logger.log("calendar months are not available to click");
            }
            randomMonthIndex = commonOperations.randomInt(0, (calendarMonthsAvailabletoClick.size() - 1));
            calendarMonthsAvailabletoClick.get(randomMonthIndex).click();

            commonOperations.waitForAnElementPresent(driver, 10, By.xpath(GiveGiftObjValues.getElement("calendarDatePanel")));
            List<WebElement> calendarDatesAvailableToClick = driver.findElements(By.xpath(GiveGiftObjValues.getElement("calendarDatesAvailableToClick")));
            if (calendarDatesAvailableToClick.size() < 0) {
                Logger.log("Calendar dates are not available to click");
            }
            randomDateIndex = commonOperations.randomInt(0, (calendarDatesAvailableToClick.size() - 1));
            calendarDatesAvailableToClick.get(randomDateIndex).click();

            commonOperations.waitForAnElementPresent(driver, 10, By.cssSelector(GiveGiftObjValues.getElement("setHourDateDetail")));
            List<WebElement> setHourDateDetail = driver.findElements(By.cssSelector(GiveGiftObjValues.getElement("setHourDateDetail")));
            randomHourDetailInedx = commonOperations.randomInt(0, (setHourDateDetail.size() - 1));
            setHourDateDetail.get(randomHourDetailInedx).click();

            commonOperations.waitForAnElementPresent(driver, 10, By.cssSelector(GiveGiftObjValues.getElement("setMinuteDateDetail")));
            List<WebElement> setMinuteDateDetail = driver.findElements(By.cssSelector(GiveGiftObjValues.getElement("setMinuteDateDetail")));
            randomMinuteIndex = commonOperations.randomInt(0, (setMinuteDateDetail.size() - 1));
            setMinuteDateDetail.get(randomMinuteIndex).click();

            continueToNextStep();

        } catch (Exception e) {
            Logger.log("unable to select a Delivery Date");
            e.printStackTrace();
        }

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
            commonOperations.waitForAnElementPresent(driver, 10, By.cssSelector(GiveGiftObjValues.getElement("creditCardNumberLabel")));
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
            WebElement continueCheckOut = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("continueCheckOut")));
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
            Logger.log("unable to continue to next step");
            e.printStackTrace();
        }
    }

    public String getRedeemCode() {
        String giftRedeemCode = "";
        try {
            commonOperations.waitForAnElementPresent(driver, 10, By.cssSelector(GiveGiftObjValues.getElement("redeemCode")));
            WebElement redeemCode = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("redeemCode")));
            giftRedeemCode = redeemCode.getText();
        } catch (Exception e) {
            Logger.log("cannot get the RedeemCode");
            e.printStackTrace();
        }
        return giftRedeemCode;
    }
    public Boolean goToHomePage(){
        Boolean landedHomePage=false;
        try {
            commonOperations.waitForAnElementClickable(driver, 10, By.cssSelector(GiveGiftObjValues.getElement("homePageHeader")));
            homePageHeader = driver.findElement(By.cssSelector(GiveGiftObjValues.getElement("homePageHeader")));
            homePageHeader.click();

            if(homePage.verifyHomePageLoaded()){
                landedHomePage=true;
            }
        }catch (Exception e){
            Logger.log("failed to land home page");
            e.printStackTrace();
        }
        return  landedHomePage;
    }

}



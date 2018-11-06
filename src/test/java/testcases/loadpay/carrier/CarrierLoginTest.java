package testcases.loadpay.carrier;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierLoginPage;
import util.TestUtil;

public class CarrierLoginTest extends TestBase {
	CarrierLoginPage loginPage;
	String carrierUsername, carrierPassword, carrierDOT, carrierEIN = "";

	public CarrierLoginTest() {
		super();

	}

	@BeforeClass
	public void setUp() {
		initialization();
		TestUtil.className = this.getClass().getName();
		loginPage = new CarrierLoginPage();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "getCarrierLoginData")
	public void loginTest(String user, String pass) throws InterruptedException {

		if (super.getProperties().getProperty("useDynamicCarrierData").contains("true")) {
			carrierUsername = CarrierRegisterTest.carrierUsername;
			carrierPassword = CarrierRegisterTest.carrierPassword;
		} else {
			carrierUsername = user;
			carrierPassword = pass;
		}

		carrierDOT = "1234567";
		carrierEIN = "99-9999999";

		loginPage.Carrierlogin(carrierUsername, carrierPassword);

		wait.until(ExpectedConditions.elementToBeClickable(loginPage.getEinField()));

		// enter EIN and click Next if enabled
		if (loginPage.getEinField().isEnabled()) {
			loginPage.setEinField(carrierEIN);
			loginPage.clickEinNextButton();
		}

		// accept terms and conditions
		if (loginPage.getTermsAndConditionsCheckBox().isEnabled()) {
			loginPage.clickTermsAndConditionsCheckBox();
			loginPage.clickFinishButton();
			Assert.assertTrue(
					loginPage.getConfirmationPopup().getText()
							.contains("Your LoadPay™ registration has been completed successfully."),
					"Registration success message not found");
			loginPage.clickConfirmationPopupCloseButton();
		}

		if (loginPage.getDonotshowagaincheckbox().isDisplayed()) {
			loginPage.getDonotshowagaincheckbox().click();
			loginPage.getPayMeNowPopupSaveButton().click();
			// loginPage.closePaymeNowPopUp();
		}
	}

}

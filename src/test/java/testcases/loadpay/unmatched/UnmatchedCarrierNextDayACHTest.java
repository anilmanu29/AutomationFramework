package testcases.loadpay.unmatched;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.unmatched.UnmatchedCarrierNextDayACH;
import util.TestUtil;

public class UnmatchedCarrierNextDayACHTest extends TestBase {

	UnmatchedCarrierNextDayACH umCarrierObj;
	CarrierLoginPage loginPage;

	/*-------Initializing driver---------*/
	public UnmatchedCarrierNextDayACHTest() {
		super();

	}

	@BeforeClass
	public void setUp() {

		initialization();
		TestUtil.className = this.getClass().getName();
		loginPage = new CarrierLoginPage();
		umCarrierObj = new UnmatchedCarrierNextDayACH();
		wait = new WebDriverWait(driver, 30);
	}
	/*-------Initializing driver---------*/

	/*-------Login to Load Pay as Broker---------*/

	/*
	 * @Test(dataProvider = "getCarrierLoginData", priority = 32) public void
	 * loginTest(String user, String pass) throws InterruptedException {
	 * loginPage.Carrierlogin(user, pass);
	 * 
	 * }
	 */

	@Test()
	public void carrierPaymenowNextDAYACH() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(umCarrierObj.getLoginBtn()));
		umCarrierObj.carrierLogin();

		wait.until(ExpectedConditions.elementToBeClickable(loginPage.getEinField()));

		// enter EIN and click Next if enabled
		if (loginPage.getEinField().isEnabled()) {
			loginPage.setEinField("99-9999999");
			loginPage.clickEinNextButton();
		}

		// accept terms and conditions
		if (loginPage.getTermsAndConditionsCheckBox().isEnabled()) {
			loginPage.clickTermsAndConditionsCheckBox();
			loginPage.clickFinishButton();
			Assert.assertTrue(loginPage.getConfirmationPopup().getText().contains(
					"registration has been completed successfully."), "Registration success message not found");
			loginPage.clickConfirmationPopupCloseButton();
		}

		if (loginPage.getDonotshowagaincheckbox().isDisplayed()) {
			loginPage.closePaymeNowPopUp();
		}

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierObj.getPaidamt()));
		umCarrierObj.getAmount();
		wait.until(ExpectedConditions.elementToBeClickable(umCarrierObj.getBtn_paymenow()));
		umCarrierObj.clickPaymenow();
		wait.until(ExpectedConditions.elementToBeClickable(umCarrierObj.getNextdayamt()));
		umCarrierObj.getnextdayAmount();
		wait.until(ExpectedConditions.elementToBeClickable(umCarrierObj.getBtn_selectnextdayach()));
		umCarrierObj.clickSelectButton();
		wait.until(ExpectedConditions.elementToBeClickable(umCarrierObj.getBtn_confirm()));
		umCarrierObj.clickConfirmButton();
		wait.until(ExpectedConditions.elementToBeClickable(umCarrierObj.getTab_paid()));
		umCarrierObj.clickPaidTab();
		umCarrierObj.gettotalpaiyAmount();
		umCarrierObj.verifyNextDayach();
	}

}

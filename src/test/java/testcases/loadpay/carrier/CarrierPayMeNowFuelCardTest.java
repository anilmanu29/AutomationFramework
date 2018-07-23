package testcases.loadpay.carrier;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierPaymeNowFuelCard;

public class CarrierPayMeNowFuelCardTest extends TestBase {

	CarrierPaymeNowFuelCard cfot;
	CarrierLoginPage loginPage;

	/*-------Initializing driver---------*/
	public CarrierPayMeNowFuelCardTest() {
		super();
	}

	@BeforeClass
	public void setUp() {

		initialization();
		loginPage = new CarrierLoginPage();
		cfot = new CarrierPaymeNowFuelCard();
	}
	/*-------Initializing driver---------*/

	/*-------Login to Load Pay as Broker---------*/

	@Test(dataProvider = "getCarrierLoginData")
	public void loginTest(String user, String pass) throws InterruptedException {
		loginPage.Carrierlogin(user, pass);

	}

	@Test(dataProvider = "getCarrierFuelcardaccountNumbersData", dependsOnMethods = { "loginTest" })
	public void carrierPaymenowFuelCard(String fleet_accountnbr, String fts_accountnbr) throws InterruptedException {

		cfot.clickPaymenow();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		/*
		 * cfot.clickPaymenowicon();
		 * wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		 */
		cfot.clickSelectButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cfot.clickaddnewcard();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cfot.clickfleetone();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cfot.input_accountnbr(fleet_accountnbr);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cfot.clicksubmit();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cfot.clickfuelcardsubmit();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cfot.clickConfirmButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cfot.clickPaidTab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cfot.clickpaymenowtab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cfot.clickPaymenow();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cfot.clickSelectButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cfot.clickaddnewcard();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cfot.clickFTS();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cfot.input_accountnbr(fts_accountnbr);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cfot.clicksubmit();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cfot.clickfuelcardsubmit();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cfot.clickConfirmButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cfot.clickPaidTab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		/*
		 * cw.gettotalpaiyAmount();
		 * wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		 * cw.verifywiretransfer();
		 */
	}

}

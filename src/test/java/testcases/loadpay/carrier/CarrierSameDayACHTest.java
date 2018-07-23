package testcases.loadpay.carrier;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierSameDAYACH;

public class CarrierSameDayACHTest extends TestBase {

	CarrierSameDAYACH cs;
	CarrierLoginPage loginPage;

	/*-------Initializing driver---------*/
	public CarrierSameDayACHTest() {
		super();
	}

	@BeforeClass
	public void setUp() {

		initialization();
		loginPage = new CarrierLoginPage();
		cs = new CarrierSameDAYACH();
	}
	/*-------Initializing driver---------*/

	/*-------Login to Load Pay as Broker---------*/

	@Test(dataProvider = "getCarrierLoginData")
	public void loginTest(String user, String pass) throws InterruptedException {
		loginPage.Carrierlogin(user, pass);

	}

	@Test(dependsOnMethods = "loginTest")
	public void carrierPaymenowSameDayACH() throws InterruptedException {
		cs.getAmount();
		cs.clickPaymenow();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cs.getsamedayAmount();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cs.clickSelectButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cs.clickConfirmButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cs.clickPaidTab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cs.gettotalpaiyAmount();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cs.verifySamedayach();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

	}

}

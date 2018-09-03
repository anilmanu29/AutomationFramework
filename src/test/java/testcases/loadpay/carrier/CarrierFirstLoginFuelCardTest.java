package testcases.loadpay.carrier;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierFirstLoginFuelCard;

public class CarrierFirstLoginFuelCardTest extends TestBase {
	CarrierFirstLoginFuelCard loginPage;

	public CarrierFirstLoginFuelCardTest() {
		super();

	}

	@BeforeClass
	public void setUp() {
		initialization();
		loginPage = new CarrierFirstLoginFuelCard();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "getPaymentDataforUnmatchcarrier")
	public void loginTest(String carrierEmail, String invoiceno, String loadid, String amt, String payto, String ein)
			throws InterruptedException {
		loginPage.carrierfirstLogin();
		loginPage.clickNext(ein);
		loginPage.clickAcceptCheckbox();
		loginPage.clickEmailcheckbox();
		loginPage.clickFinish();
		loginPage.clickClose();
	}

}

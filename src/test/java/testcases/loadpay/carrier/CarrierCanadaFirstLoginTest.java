package testcases.loadpay.carrier;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierCanadaFirstLogin;

public class CarrierCanadaFirstLoginTest extends TestBase {
	CarrierCanadaFirstLogin loginPage;

	public CarrierCanadaFirstLoginTest() {
		super();
		wait = new WebDriverWait(driver, 30);
	}

	@BeforeClass
	public void setUp() {
		initialization();
		loginPage = new CarrierCanadaFirstLogin();
	}

	@Test()
	public void loginTest() throws InterruptedException {
		loginPage.carrierfirstLogin();
	}

}

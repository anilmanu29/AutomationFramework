package testcases.loadpay.carrier;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierCanadaFirstLogin;
import util.TestUtil;

public class CarrierCanadaFirstLoginTest extends TestBase {
	CarrierCanadaFirstLogin loginPage;

	public CarrierCanadaFirstLoginTest() {
		super();

	}

	@BeforeClass
	public void setUp() {
		initialization();
		TestUtil.className = this.getClass().getName();
		loginPage = new CarrierCanadaFirstLogin();
		wait = new WebDriverWait(driver, 30);
	}

	@Test()
	public void loginTest() throws InterruptedException {
		loginPage.carrierfirstLogin();
	}

}

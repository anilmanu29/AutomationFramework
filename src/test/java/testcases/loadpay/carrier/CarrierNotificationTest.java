package testcases.loadpay.carrier;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierNotification;

public class CarrierNotificationTest extends TestBase {

	CarrierNotification cn;
	CarrierLoginPage cl;
	String payment_status = "Verified";
	String invoice;

	/*-------Initializing driver---------*/

	public CarrierNotificationTest() {
		super();

	}

	@BeforeClass
	public void setUp() {

		initialization();
		cn = new CarrierNotification();
		cl = new CarrierLoginPage();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "getCarrierLoginData")
	public void loginCarrier(String un, String pwd) throws InterruptedException {
		cl = new CarrierLoginPage();
		cl.Carrierlogin(un, pwd);

	}

	@Test(dependsOnMethods = "loginCarrier")
	public void carrierNotification() throws InterruptedException {
		cn = new CarrierNotification();
		cn.clickNotifications();
		cn.carrierInvoice();
		cl.CarrierLogout();

	}

}

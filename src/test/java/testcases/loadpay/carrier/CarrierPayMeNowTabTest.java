package testcases.loadpay.carrier;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierPayMeNowTab;

public class CarrierPayMeNowTabTest extends TestBase {

	CarrierPayMeNowTab cp;
	CarrierLoginPage lp;

	/*-------Initializing driver---------*/

	public CarrierPayMeNowTabTest() {
		super();
	}

	@BeforeClass
	public void setUp() {

		initialization();
		lp = new CarrierLoginPage();
		cp = new CarrierPayMeNowTab();

	}

	@Test(dataProvider = "getCarrierLoginData")
	public void loginCarrier(String un, String pwd) throws InterruptedException {
		lp = new CarrierLoginPage();
		lp.Carrierlogin(un, pwd);

	}

	@Test(dependsOnMethods = { "loginCarrier" })
	public void carrierPaymeNow() throws InterruptedException {
		cp.clickPaymenow();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cp.days();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cp.Amount();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cp.Payer();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cp.Invoice();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cp.LoadID();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cp.ScheduledDate();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	}

}

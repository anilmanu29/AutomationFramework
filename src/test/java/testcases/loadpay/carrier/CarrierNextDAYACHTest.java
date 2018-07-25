package testcases.loadpay.carrier;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierNextDAYACH;

public class CarrierNextDAYACHTest extends TestBase {

	CarrierNextDAYACH carrierNextDayObj;
	CarrierLoginPage loginPage;

	/*-------Initializing driver---------*/
	public CarrierNextDAYACHTest() {
		super();
		wait = new WebDriverWait(driver, 30);
	}

	@BeforeClass
	public void setUp() {

		initialization();
		loginPage = new CarrierLoginPage();
		carrierNextDayObj = new CarrierNextDAYACH();
	}
	/*-------Initializing driver---------*/

	/*-------Login to Load Pay as Broker---------*/

	@Test(dataProvider = "getCarrierLoginData")
	public void loginTest(String user, String pass) throws InterruptedException {
		loginPage.Carrierlogin(user, pass);

	}

	@Test(dependsOnMethods = "loginTest")
	public void carrierPaymenowNextDAYACH() throws InterruptedException {
		carrierNextDayObj.getAmount();
		carrierNextDayObj.clickPaymenow();
		carrierNextDayObj.getnextdayAmount();
		carrierNextDayObj.clickSelectButton();
		carrierNextDayObj.clickConfirmButton();
		carrierNextDayObj.clickPaidTab();
		carrierNextDayObj.gettotalpaiyAmount();
		carrierNextDayObj.verifyNextDayach();

	}

}

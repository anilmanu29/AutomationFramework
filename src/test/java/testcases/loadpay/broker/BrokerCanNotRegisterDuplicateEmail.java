package testcases.loadpay.broker;

import java.io.IOException;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerRegister;

public class BrokerCanNotRegisterDuplicateEmail extends TestBase {

	BrokerRegister brokerRegister;
	BrokerLoginPage brokerLoginPage;
	String brokerUsername;
	public static String emailid;

	@BeforeClass
	public void setUp() throws IOException {
		initialization();
		wait = new WebDriverWait(driver, 30);
		brokerRegister = new BrokerRegister();
		brokerLoginPage = new BrokerLoginPage();
	}

	@Test(dataProvider = "getBrokerCanNotRegisterDuplicateEmailData")
	public void BrokerRegister(String Email, String ConfirmEmail) throws IOException, InterruptedException {

		brokerUsername = Email;
		brokerRegister.signup();
		// clicking on carrier Register
		brokerRegister.shipperRegister();
		brokerRegister.BrokerEmail(Email);
		brokerRegister.confirmEmail(ConfirmEmail);
		/* Thread.sleep(2000); */
		brokerRegister.verifyErrorMessage();
		log.info("--------Unable to Login with duplicate email is verified-------");
	}

}

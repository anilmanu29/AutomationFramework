package testcases.loadpay.unmatched;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.admin.AdminPayByCheck;
import pages.loadpay.unmatched.UnmatchedCarrierAdminPayByCheck;

public class UnmatchedCarrierAdminPayByCheckTest extends TestBase {

	AdminHomePage adminHomePageObj;
	AdminLogin adminLoginObj;
	AdminPayByCheck adminPayByCheckObj;
	UnmatchedCarrierAdminPayByCheck UnCarrierAdminPBC;
	public static String acountname;
	String brokerUsername = "";
	String brokerPassword = "";

	/*-------Initializing driver---------*/

	public UnmatchedCarrierAdminPayByCheckTest() {
		super();
		wait = new WebDriverWait(driver, 30);
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();

		adminLoginObj = new AdminLogin();
		adminHomePageObj = new AdminHomePage();
		adminPayByCheckObj = new AdminPayByCheck();
		UnCarrierAdminPBC = new UnmatchedCarrierAdminPayByCheck();
	}

	@Test(dataProvider = "getBrokerLoginData")
	public void getBrokerCredentials(String username, String pwd) throws InterruptedException {
		// login as broker
		brokerUsername = username;
		brokerPassword = pwd;
	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "getBrokerCredentials")
	public void verifyAdminPayByCheck(String Username, String pass) throws InterruptedException, AWTException {
		adminHomePageObj.AdminURL();
		wait.until(ExpectedConditions.elementToBeClickable(adminLoginObj.getUserName()));
		adminLoginObj.adminUserPass(Username, pass);
		wait.until(ExpectedConditions.elementToBeClickable(adminLoginObj.getLoginBtn()));
		adminLoginObj.adminLogin();
		wait.until(ExpectedConditions.elementToBeClickable(adminLoginObj.getCustomerTab()));
		adminLoginObj.ClickOnCustomersTab();
		wait.until(ExpectedConditions.elementToBeClickable(adminLoginObj.getSearch()));
		log.info(brokerUsername);
		adminLoginObj.ClickOnSearchBox(brokerUsername);
		wait.until(ExpectedConditions.elementToBeClickable(adminLoginObj.getClickonSearchButton()));
		adminLoginObj.ClickOnSearchButton();
		wait.until(ExpectedConditions.elementToBeClickable(adminLoginObj.getDoubleClickID()));
		adminLoginObj.DoubleClickID();
		adminPayByCheckObj.clickPayments();
		log.info(UnCarrierAdminPBC.getPaymentId1().getText());
		adminPayByCheckObj.ClickOnsearchKeyword(UnCarrierAdminPBC.getPaymentId1().getText());
		adminPayByCheckObj.getPaymentID();
		adminPayByCheckObj.clickSearch();
		adminPayByCheckObj.searchKeyword();
		adminPayByCheckObj.clickSearch1();
		adminPayByCheckObj.clickgridcollapse();
		adminPayByCheckObj.clickPayByCheck();
		adminPayByCheckObj.selectTerms();
	}

	@Test(dataProvider = "getCcarrierMatchedPayByCheckPayMNWData", dependsOnMethods = "verifyAdminPayByCheck")
	public void carrierPaymenowPayByCheck(String EnterDOTNnumber, String ContactName) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(adminPayByCheckObj.getTxt_DOT()));
		adminPayByCheckObj.EnterDOTNnumber(EnterDOTNnumber);
		wait.until(ExpectedConditions.elementToBeClickable(adminPayByCheckObj.getTxt_ContactName()));
		adminPayByCheckObj.ContactName(ContactName);
		adminPayByCheckObj.clickPayByChecksubmit();
		adminPayByCheckObj.clickAddCheckNumber();
		adminPayByCheckObj.ClickOnEnterCheckNumber();
	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "carrierPaymenowPayByCheck")
	public void verifyAdminPayByCheckTermPayment(String Username, String pass)
			throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.elementToBeClickable(adminLoginObj.getCustomerTab()));
		adminLoginObj.ClickOnCustomersTab();
		wait.until(ExpectedConditions.elementToBeClickable(adminLoginObj.getSearch()));
		log.info(brokerUsername);
		adminLoginObj.ClickOnSearchBox(brokerUsername);
		wait.until(ExpectedConditions.elementToBeClickable(adminLoginObj.getClickonSearchButton()));
		adminLoginObj.ClickOnSearchButton();
		wait.until(ExpectedConditions.elementToBeClickable(adminLoginObj.getDoubleClickID()));
		adminLoginObj.DoubleClickID();
		adminPayByCheckObj.clickPayments();
		log.info(UnCarrierAdminPBC.getPaymentId2().getText());
		adminPayByCheckObj.ClickOnsearchKeyword(UnCarrierAdminPBC.getPaymentId2().getText());
		adminPayByCheckObj.getPaymentID();
		adminPayByCheckObj.clickSearch();
		adminPayByCheckObj.searchKeyword();
		adminPayByCheckObj.clickSearch1();
		adminPayByCheckObj.clickgridcollapse();
		adminPayByCheckObj.clickPayByCheck();
		adminPayByCheckObj.selectTerms();
		adminPayByCheckObj.selectTermsTermPayment();

	}

	@Test(dataProvider = "getCcarrierMatchedPayByCheckPayMNWData", dependsOnMethods = "verifyAdminPayByCheckTermPayment")
	public void carrierTermPaymentPayByCheck(String EnterDOTNnumber, String ContactName) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(adminPayByCheckObj.getTxt_DOT()));
		adminPayByCheckObj.EnterDOTNnumber(EnterDOTNnumber);
		wait.until(ExpectedConditions.elementToBeClickable(adminPayByCheckObj.getTxt_ContactName()));
		adminPayByCheckObj.ContactName(ContactName);
		adminPayByCheckObj.clickPayByChecksubmit();
		adminPayByCheckObj.clickAddCheckNumber();
		adminPayByCheckObj.ClickOnEnterCheckNumber();
	}

}

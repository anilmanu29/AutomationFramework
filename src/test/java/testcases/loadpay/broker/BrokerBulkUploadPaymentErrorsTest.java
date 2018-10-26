package testcases.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerBulkUploadPaymentsmatchedCarrier;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerScheduledPaymentsTab;

public class BrokerBulkUploadPaymentErrorsTest extends TestBase {

	BrokerBulkUploadPaymentsmatchedCarrier BrokerBulkUploadPaymentErrors;
	BrokerLoginPage loginPage;
	BrokerScheduledPaymentsTab brokerschedulepaymentstab;
	List<String> firstRowData = null;
	List<String> lastRowData = null;
	String searchForText = "";
	String brokerUsername, brokerPassword = "";

	/*-------Initializing driver---------*/
	public BrokerBulkUploadPaymentErrorsTest() {
		super();

	}

	@BeforeClass
	public void setUp() {

		initialization();
		loginPage = new BrokerLoginPage();
		BrokerBulkUploadPaymentErrors = new BrokerBulkUploadPaymentsmatchedCarrier();
		wait = new WebDriverWait(driver, 30);
	}
	/*-------Initializing driver---------*/

	/*-------Login to Load Pay as Broker---------*/

	@Test(dataProvider = "getBrokerLoginData")
	public void loginBroker(String un, String pwd) throws InterruptedException, IOException, AWTException {

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUsername = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUsername = un;
			brokerPassword = pwd;
		}

		loginPage.Brokerlogin(brokerUsername, brokerPassword);
		/* verifyBulkUploadPaymentsmatchedCarrierElementsDisplayed(); */

	}

	@Test(dependsOnMethods = "loginBroker")
	public void verifyBrokerBulkUploadPaymentErrors() throws InterruptedException {
		verifyBulkUploadPaymentsmatchedCarrierElementsDisplayed();

	}

	@Test(dependsOnMethods = "verifyBrokerBulkUploadPaymentErrors")
	public void verifynewPayment() throws InterruptedException {
		BrokerBulkUploadPaymentErrors.newPayment();
	}

	@Test(dependsOnMethods = "verifynewPayment")
	public void verifyUploadErrorFile() throws InterruptedException, IOException, AWTException {
		BrokerBulkUploadPaymentErrors.UploadFileforError();
	}

	@Test(dependsOnMethods = "verifyUploadErrorFile")
	public void verifyClickimportgrayedout() throws InterruptedException, IOException {
		BrokerBulkUploadPaymentErrors.Clickimport();

		Actions ToolTip1 = new Actions(driver);
		WebElement alttext = driver.findElement(By.xpath(
				".//*[@id='angularScope']/div[2]/div/div[3]/div/div[2]/div[2]/div[1]/div/div[2]/table/tbody/tr[1]/td[4]"));
		ToolTip1.clickAndHold(alttext).perform();
		String ToolTipText = alttext.getAttribute("title");
		log.info("ToolTipText: " + ToolTipText);

	}

	public void verifyBulkUploadPaymentsmatchedCarrierElementsDisplayed() {

		// Verify that the web elements for the Processed tab exist
		Assert.assertTrue(BrokerBulkUploadPaymentErrors.lnk_newpayment.isDisplayed(), "newpayment link  not found");
		Assert.assertTrue(BrokerBulkUploadPaymentErrors.link_Upload.isDisplayed(), "upload link not found");
		Assert.assertTrue(BrokerBulkUploadPaymentErrors.link_schpaymnt.isDisplayed(),
				"schedule payment tile not found");

		Assert.assertNotNull(BrokerBulkUploadPaymentErrors.searchInputField, "Search Input Field not found");
		Assert.assertNotNull(BrokerBulkUploadPaymentErrors.searchButton, "Search Button not found");

	}

}

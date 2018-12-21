package testcases.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import outlook.outlooklogin;
import pages.loadpay.broker.BrokerAvailableCreditTab;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerOutlook;
import util.TestUtil;

public class BrokerAvailableCreditTabTest extends TestBase {

	BrokerAvailableCreditTab brokeravailablecredittab;
	BrokerLoginPage brokerloginpage;
	BrokerOutlook brokeroutlook;
	outlooklogin outlooklog;
	String brokerUsername, brokerPassword = "";

	/*-------Initializing driver---------*/

	public BrokerAvailableCreditTabTest() {
		super();

	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		TestUtil.className = this.getClass().getName();
		brokeravailablecredittab = new BrokerAvailableCreditTab();
		brokerloginpage = new BrokerLoginPage();
		brokeroutlook = new BrokerOutlook();
		outlooklog = new outlooklogin();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(description = "LP-5398 Broker Request Additional Credit Login", dataProvider = "getBrokerLoginData")
	public void loginBrokerTest(String user, String pass) throws InterruptedException {

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUsername = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUsername = user;
			brokerPassword = pass;
		}
		brokerloginpage.Brokerlogin(brokerUsername, brokerPassword);

	}

	@Test(description = "LP-5398 Broker Request Additional Credit Credit Tab", dependsOnMethods = "loginBrokerTest")
	public void ClickBrokerAvailableCreditTabTest() throws InterruptedException {
		brokeravailablecredittab.clickAvailableCreditTab();
	}

	@Test(description = "LP-5398 Broker Request Additional Credit Click Credit Button", dependsOnMethods = "ClickBrokerAvailableCreditTabTest")
	public void RequestAdditionalCreditButtonTest() throws InterruptedException {
		brokeravailablecredittab.clickRequestAdditionalCreditButton();
	}

	@Test(description = "LP-5398 Broker Request Additional Credit Pop Up", dependsOnMethods = "RequestAdditionalCreditButtonTest")
	public void RequestAdditionalCreditPopUpTest() throws InterruptedException {
		brokeravailablecredittab.clickCloseButton();
		wait.until(ExpectedConditions.elementToBeClickable(brokerloginpage.getBtn_logout()));
		brokerloginpage.BrokerLogout();
	}

	@Test(description = "LP-5398 Broker Request Additional Credit Outlook Login", dataProvider = "getoutlookLoginData", dependsOnMethods = "RequestAdditionalCreditPopUpTest")
	public void BrokerOutlookTest(String un, String pwd) throws InterruptedException {

		try {
			outlooklog.outlookLogin(un, pwd);
			brokeroutlook.clickPopUp();
			brokeroutlook.clickOpenMailBox();
			brokeroutlook.enterEmail(super.getProperties().getProperty("loadpaytestEmail"));
			Assert.assertTrue(SearchInbox("Credit increase request"));
			brokeroutlook.quit();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Boolean SearchInbox(String SearchText) throws InterruptedException {
		Thread.sleep(2000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(6000);

		List<WebElement> list = driver
				.findElements(By.xpath("//*[@class='ms-font-l lvHighlightSubjectClass lvHighlightAllClass']"));
		for (WebElement e : list) {
			wait.until(ExpectedConditions.elementToBeClickable(e));
			e.click();
			// log.info(e.getText());
			if (e.getText().contains(SearchText)) {
				return true;
			}

		}

		return false;
	}
}

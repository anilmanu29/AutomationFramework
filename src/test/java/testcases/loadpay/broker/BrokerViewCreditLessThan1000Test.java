package testcases.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerOutlook;
import pages.loadpay.broker.BrokerViewCreditLessThan1000;
import pages.loadpay.outlook.outlooklogin;

public class BrokerViewCreditLessThan1000Test extends TestBase {
	// BrokerLoginPage loginPage;
	BrokerViewCreditLessThan1000 CreditLessThan1000;
	BrokerOutlook brokeroutlook;
	outlooklogin outlooklog;

	public BrokerViewCreditLessThan1000Test() {
		super();

	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		CreditLessThan1000 = new BrokerViewCreditLessThan1000();
		brokeroutlook = new BrokerOutlook();
		outlooklog = new outlooklogin();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "getBrokerLoginData")
	public void loginTest(String user, String pass) throws InterruptedException {

		CreditLessThan1000.Brokerlogin(user, pass);
		CreditLessThan1000.AvailableCreditTab();
		CreditLessThan1000.BrokerLogout();
	}

	@SuppressWarnings("static-access")
	@Test(description = "Broker Sees 10% Notification Email in Outlook", dataProvider = "getoutlookLoginData", dependsOnMethods = "loginTest")
	public void BrokerOutlookTest(String un, String pwd) throws InterruptedException {

		try {
			outlooklog.outlookLogin(un, pwd);
			brokeroutlook.clickPopUp();
			brokeroutlook.clickOpenMailBox();
			brokeroutlook.enterEmail(super.prop.getProperty("email"));
			SearchInbox("Broker/Shipper Credit Limit 10% Notification");
			brokeroutlook.quit();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void SearchInbox(String SearchText) throws InterruptedException {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(6000);

		List<WebElement> list = driver
				.findElements(By.xpath("//*[@class='ms-font-l lvHighlightSubjectClass lvHighlightAllClass']"));
		for (WebElement e : list) {
			Thread.sleep(2000);
			e.click();
			Thread.sleep(1000);
			// System.out.println(e.getText());
			if (e.getText().contains(SearchText)) {
				Thread.sleep(1000);
				Assert.assertTrue(e.getText().contains(SearchText));
				break;
			}

		}
	}
}

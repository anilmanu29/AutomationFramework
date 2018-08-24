package testcases.loadpay.carrier;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierOutlook;
import pages.loadpay.outlook.outlooklogin;

public class CarrierOutlookTest extends TestBase {
	CarrierOutlook carrierOutlookObj;
	outlooklogin outlook;

	Date currentTime;
	String formattedDate = "";
	Long longTime;
	DateFormat formatter;
	String currentHour = "";
	String currentMinutes = "";
	String timeArray[] = new String[2];

	public CarrierOutlookTest() {
		super();

	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		outlook = new outlooklogin();
		carrierOutlookObj = new CarrierOutlook();
		wait = new WebDriverWait(driver, 30);
		currentTime = new Date();
	}

	@Test(dataProvider = "getoutlookLoginData")
	public void login(String un, String pwd) throws InterruptedException, AWTException {
		outlook.outlookLogin(un, pwd);
	}

	@Test(dependsOnMethods = "login")
	public void outlookloginTest() throws InterruptedException, AWTException {
		carrierOutlookObj.clickPopUp();
		carrierOutlookObj.clickOpenMailBox();
		carrierOutlookObj.enterEmail(super.getProperties().getProperty("email"));
		// outlookk.clickOpen();
		getTimestamp();
		carrierOutlookObj.outlookSearchInbox(CarrierRegisterTest.carrierUsername, currentHour, currentMinutes);
		carrierOutlookObj.handleNewInbox();
		carrierOutlookObj.verifyConfirmationMessage();

	}

	public void getTimestamp() {
		formatter = new SimpleDateFormat("HH:mm");
		formatter.setTimeZone(TimeZone.getTimeZone("MST"));
		longTime = currentTime.getTime();
		formattedDate = formatter.format(longTime);
		timeArray = formattedDate.split(":");
		currentHour = timeArray[0];
		currentMinutes = timeArray[1];

		log.info("\n\n\n===============================");
		log.info("Current date: " + longTime);
		log.info("Formatted date: " + formattedDate);
		log.info("Current Hour: " + currentHour);
		log.info("Current Minutes: " + currentMinutes);
		log.info("===============================");
	}
}

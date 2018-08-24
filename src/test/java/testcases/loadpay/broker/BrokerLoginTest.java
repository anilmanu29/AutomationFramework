package testcases.loadpay.broker;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerLoginPage;

public class BrokerLoginTest extends TestBase {
	BrokerLoginPage loginPage;
	String brokerUsername, brokerPassword = "";

	public BrokerLoginTest() {
		super();

	}

	@BeforeClass
	public void setUp() {

		initialization();
		wait = new WebDriverWait(driver, 30);
		loginPage = new BrokerLoginPage();
	}

	@Test(dataProvider = "getBrokerLoginData")
	public void loginTest(String user, String pass) throws InterruptedException {

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUsername = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUsername = user;
			brokerPassword = pass;
		}

		loginPage.Brokerlogin(brokerUsername, brokerPassword);

		completeRegistration();

		wait.until(ExpectedConditions.elementToBeClickable(loginPage.getBtn_logout()));
		Thread.sleep(2000);
		loginPage.BrokerLogout();
	}

	public void completeRegistration() {
		// enter EIN
		WebElement einInputField = driver.findElement(By.xpath("//*[@id='EIN']"));
		wait.until(ExpectedConditions.elementToBeClickable(einInputField));
		einInputField.clear();
		einInputField.sendKeys("99-9999999");

		// enter deposit amount
		WebElement depositAmtField = driver.findElement(By.xpath("//*[@id='ControlAmount']"));
		wait.until(ExpectedConditions.elementToBeClickable(depositAmtField));
		depositAmtField.clear();
		depositAmtField.sendKeys(BrokerRegisterTest.depositAmount);

		// click Next
		WebElement nextButton = driver.findElement(By.xpath("//*[@id='formCompany']/input"));
		wait.until(ExpectedConditions.elementToBeClickable(nextButton));
		nextButton.click();

		// accept terms and conditions
		WebElement acceptTermsCheckBox = driver.findElement(By.xpath("//*[@id='AcceptedTerms']"));
		wait.until(ExpectedConditions.elementToBeClickable(acceptTermsCheckBox));
		acceptTermsCheckBox.click();

		WebElement finishButton = driver.findElement(By.xpath("//*[@id='termsForm']/input"));
		wait.until(ExpectedConditions.elementToBeClickable(finishButton));
		finishButton.click();

		WebElement confirmationPopup = driver
				.findElement(By.xpath("//*[@id='angularScope']/div[3]/div/div/div[1]/div/p"));
		wait.until(ExpectedConditions.elementToBeClickable(confirmationPopup));
		log.info("Confirmation message: " + confirmationPopup.getText());
		Assert.assertTrue(
				confirmationPopup.getText().contains("Your LoadPay™ registration has been completed successfully."),
				"Registration success message not found");

		WebElement confirmationPopupClose = driver
				.findElement(By.xpath("//*[@id='angularScope']/div[3]/div/div/div[2]/button"));
		confirmationPopupClose.click();
	}

}

package testcases.v5prototype;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.v5_prototype.v5_prototype_LoginPage;
import util.TestUtil;

public class v5LoginTest extends TestBase {
	v5_prototype_LoginPage v5LoginPage;

	String userName = "jason1016@mailinator.truckstop.com";
	String passWord = "Password@3";

	public v5LoginTest() {
		super();

	}

	@BeforeClass
	public void setUp() {
		initialization();
		wait = new WebDriverWait(driver, 30);
		TestUtil.className = this.getClass().getName();
		v5LoginPage = new v5_prototype_LoginPage();
	}

	@Test(description = "v5_prototype_LoginPageTest_Login")
	public void loginTest() throws InterruptedException {

		driver.get(super.getProperties().getProperty("v5_prototypeURL"));

		Assert.assertTrue(v5LoginPage.verifyPageLoaded(), "Login Page did not load successfully");

		v5LoginPage.setEmailAddressTextField(userName);

		v5LoginPage.setPasswordPasswordField(passWord);

		v5LoginPage.setRememberMeCheckboxField();

		v5LoginPage.unsetRememberMeCheckboxField();

		v5LoginPage.clickLoginButton();

		Assert.assertTrue(v5LoginPage.acknowledgeButton.isDisplayed());

		v5LoginPage.clickAcknowledgeButton();
	}

}

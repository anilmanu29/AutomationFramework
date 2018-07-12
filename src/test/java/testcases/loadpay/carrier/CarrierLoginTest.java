package testcases.loadpay.carrier;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierLoginPage;

public class CarrierLoginTest extends TestBase{
	CarrierLoginPage loginPage;
	String carrierUserName, carrierPassword, carrierDOT, carrierEIN = "";
	
	public CarrierLoginTest()
	{
		super();
	}
	
	@BeforeClass
	public void setUp()
	{
		
		initialization();
		loginPage = new CarrierLoginPage();	
	}

	@Test(dataProvider="getCarrierLoginData")
	public void loginTest(String user,String pass) throws InterruptedException
	{
		carrierUserName = user;
		carrierPassword = pass;
		carrierDOT = "1234567";
		carrierEIN = "99-9999999";
		
		loginPage.Carrierlogin(carrierUserName, carrierPassword);
	
		Thread.sleep(5000);
		
		//enter EIN and click Next if enabled
		if(loginPage.getEinField().isEnabled())
		{
			loginPage.setEinField(carrierEIN);
			Thread.sleep(1000);
			loginPage.clickEinNextButton();
			Thread.sleep(1000);
		}
		
		//accept terms and conditions
		if(loginPage.getTermsAndConditionsCheckBox().isEnabled())
		{
			loginPage.clickTermsAndConditionsCheckBox();
			Thread.sleep(1000);
			loginPage.clickFinishButton();
			Thread.sleep(1000);
			Assert.assertTrue(loginPage.getConfirmationPopup().getText().contains("Your LoadPay™ registration has been completed successfully."), "Registration success message not found");
			Thread.sleep(1000);
			loginPage.clickConfirmationPopupCloseButton();
		}
	}
	
	
	
}

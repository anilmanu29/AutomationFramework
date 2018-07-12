package pages.loadpay.carrier;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.TestBase;

public class CarrierAccountLockedafter10FailedLogins extends TestBase{
	
	//Page Factory - OR:
	@FindBy(xpath="//input[@id='UserName']")
	public WebElement UserName;
	
	@FindBy(xpath="//input[@id='Password']")
	public WebElement Password;
	
	@FindBy(xpath="//input[contains(@type,'submit')]")
	public WebElement loginBtn;
	
	@FindBy(xpath = "//a[text()='Logoff']")
	public WebElement btn_logout;
	
	@FindBy(xpath = "//*[@id='page-main']/div/div/div[2]/div/div/div[1]/form/div[1]/ul/li")
	public WebElement failuremessage;
	
	@FindBy(xpath = "//*[@id='page-main']/div/div/div[2]/div/div/div[1]/form/div[1]/ul/li")
	public WebElement accountlockedmessage;
	
	
	
	
	
	//Initializing the Page Objects:
	public CarrierAccountLockedafter10FailedLogins()
	{
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	public String validateLoginPageTitle()
	{
		return driver.getTitle();
	}

	
	public void Carrierloginlock(String un, String pwd, String wrgpwd) throws InterruptedException
	{
		/*for (int i = 0; i<=10;i++)
		{
			
			UserName.clear();
				UserName.sendKeys(un);
				Thread.sleep(1000);
				Password.clear();
				Password.sendKeys(wrgpwd);
				Thread.sleep(1000);
		//loginBtn.click();
		    	JavascriptExecutor js = (JavascriptExecutor)driver;
		    	js.executeScript("arguments[0].click();", loginBtn);
		}*/
		
		
		   for (int i = 0; i<=10;i++)
				{
					
					UserName.clear();
						UserName.sendKeys(un);
						Thread.sleep(1000);
						Password.clear();
						Password.sendKeys(wrgpwd);
						Thread.sleep(1000);
				//loginBtn.click();
				    	JavascriptExecutor js = (JavascriptExecutor)driver;
				    	js.executeScript("arguments[0].click();", loginBtn);
				    	
				    	Assert.assertTrue(failuremessage.isDisplayed(), "Failure message is NOT displayed");
				}
		   
		Assert.assertTrue(accountlockedmessage.isDisplayed(), "Account locked message is NOT Displayed");
	}
	public void CarrierLogout()
	{
		btn_logout.click();
	}
}

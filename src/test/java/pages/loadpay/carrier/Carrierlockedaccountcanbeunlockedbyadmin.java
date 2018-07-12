package pages.loadpay.carrier;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;

public class Carrierlockedaccountcanbeunlockedbyadmin extends TestBase{
	
	//Page Factory - OR:
	@FindBy(xpath="//input[@id='UserName']")
	WebElement UserName;
	
	@FindBy(xpath="//input[@id='Password']")
	WebElement Password;
	
	@FindBy(xpath="//input[contains(@type,'submit')]")
	WebElement loginBtn;
	@FindBy(xpath = "//a[text()='Logoff']")
	private WebElement btn_logout;
	
	
	
	//Initializing the Page Objects:
	public Carrierlockedaccountcanbeunlockedbyadmin()
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
		}
	}
	public void CarrierLogout()
	{
		btn_logout.click();
	}
}

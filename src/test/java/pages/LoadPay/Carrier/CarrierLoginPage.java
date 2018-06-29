package pages.LoadPay.Carrier;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;

public class CarrierLoginPage extends TestBase{
	
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
	public CarrierLoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	public String validateLoginPageTitle()
	{
		return driver.getTitle();
	}

	
	public void Carrierlogin(String un, String pwd)
	{
		
				UserName.sendKeys(un);
				Password.sendKeys(pwd);
		//loginBtn.click();
		    	JavascriptExecutor js = (JavascriptExecutor)driver;
		    	js.executeScript("arguments[0].click();", loginBtn);
	}
	public void CarrierLogout()
	{
		btn_logout.click();
	}
}

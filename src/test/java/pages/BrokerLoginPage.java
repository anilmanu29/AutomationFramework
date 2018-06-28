package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrokerLoginPage extends TestBase{
	WebDriverWait wait;
	public static String bemail;
	
	//Page Factory - OR:
	@FindBy(xpath="//input[@id='UserName']")
	WebElement UserName;
	
	@FindBy(xpath="//input[@id='Password']")
	WebElement Password;
	
	@FindBy(xpath="//input[contains(@type,'submit')]")
	WebElement loginBtn;
	
	@FindBy(xpath = "//a[text()='Logoff']")
	private WebElement btn_logout;

	@FindBy(xpath= "//a[@href='/Account/ResetPassword']")
	WebElement forgotPassword;

	
	//Initializing the Page Objects:
	public BrokerLoginPage()
	{
		
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 10);
	}
	
	//Actions:
	public String validateLoginPageTitle()
	{
		return driver.getTitle();
	}

	
	public void Brokerlogin(String un, String pwd)
	{
		bemail=un;
		UserName.sendKeys(un);
		Password.sendKeys(pwd);
		//loginBtn.click();
		    	JavascriptExecutor js = (JavascriptExecutor)driver;
		    	js.executeScript("arguments[0].click();", loginBtn);
	}

	public void brokerVerificationLogin(String UserName, String NewPassword) {
		bemail = UserName;
		this.UserName.sendKeys(UserName);
		Password.sendKeys(NewPassword);
		//loginBtn.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", loginBtn);
	}


	public void BrokerLogout()
	{
		btn_logout.click();
	}

	public void verificationBrokerLogout() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btn_logout));
		Thread.sleep(3000);
		btn_logout.click();
	}



	public void forgotPasswordButton() {
		forgotPassword.click();
	}
	
	
	
	
	
	
	
	
	
	

}

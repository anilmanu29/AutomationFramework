package pages.LoadPay.Carrier;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;
import testcases.LoadPay.Broker.BrokerPaymentforUnmatchedCarrierTest;
import testcases.LoadPay.Unmatched.UnmatchedCarrierOutlookTest;

public class CarrierFirstLogin extends TestBase{
	
	WebDriverWait wait = null;

	//Page Factory - OR:
	@FindBy(id="EIN")
	WebElement field_ein;
	
	@FindBy(xpath="//input[@value='Next']")
	WebElement button_next;
	
	@FindBy(id="AcceptedTerms")
	WebElement checkboxaccept;
	
	@FindBy(id = "EmailTerms")
	private WebElement checkboxemail;
	
	@FindBy(xpath = "//input[@value='Finish']")
	private WebElement btn_finish;
	
	@FindBy(xpath = "//button[text()='Close']")
	private WebElement btn_close;
	
	@FindBy(xpath = "//input[@id='UserName']")
	WebElement UserName;

	@FindBy(xpath = "//input[@id='Password']")
	WebElement Password;

	@FindBy(xpath = "//input[contains(@type,'submit')]")
	WebElement loginBtn;
	
	//Initializing the Page Objects:
	public CarrierFirstLogin()
	{
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}
	
	//Actions:
	public String validateLoginPageTitle()
	{
		return driver.getTitle();
	}

	public void carrierfirstLogin() {
		UserName.sendKeys(BrokerPaymentforUnmatchedCarrierTest.al.get(1));
		Password.sendKeys(UnmatchedCarrierOutlookTest.pwd);
		loginBtn.click();
	}

	public void clickNext(String ein) throws InterruptedException
	{
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(field_ein));
		field_ein.sendKeys(ein);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", button_next);
	}
	public void clickAcceptCheckbox()
	{
		wait.until(ExpectedConditions.elementToBeClickable(checkboxaccept));
		checkboxaccept.click();
	} 
	
	public void clickEmailcheckbox()
	{
		wait.until(ExpectedConditions.elementToBeClickable(checkboxemail));
		checkboxemail.click();
	} 
	
	public void clickFinish()
	{
		wait.until(ExpectedConditions.elementToBeClickable(btn_finish));
		btn_finish.click();
	}
	
	public void clickClose()
	{
		wait.until(ExpectedConditions.elementToBeClickable(btn_close));
		btn_close.click();
	}
	
}

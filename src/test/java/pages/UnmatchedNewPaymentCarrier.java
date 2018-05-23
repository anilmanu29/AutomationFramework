package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.TestBase;
import testcases.BrokerPaymentforUnmatchedCarrierTest;
import testcases.UnmatchedCarrierOutlookNewPaymentTest;
import testcases.UnmatchedCarrierOutlookTest;


public class UnmatchedNewPaymentCarrier extends TestBase{
	
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
	
	@FindBy(xpath = "//a[@href='#/Payments/PayMeNow']")
	private WebElement paymenow;
	
	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]")
	private WebElement carrierPayment;

	@FindBy(xpath = "//a[contains(@href,'#/Payments/ScheduledPayments')]")
	private WebElement ScheduledPayments;
	
	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[2]/div/div[1]/div/div[4]/div")
	private WebElement collapseDetails;
	
	//Initializing the Page Objects:
	public UnmatchedNewPaymentCarrier()
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
		Password.sendKeys(UnmatchedCarrierOutlookNewPaymentTest.pwd);
		loginBtn.click();
	}

	public void clickNext(String ein)
	{
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
	
	public void clickPaymenow() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(paymenow));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", paymenow);
	
		
	}

	/*public void clickcarrierPayment() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(carrierPayment));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", carrierPayment);
		
	}
	
	public void clickScheduledPayments() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(ScheduledPayments));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", ScheduledPayments);
		
	}*/
	
	public void clickcollapseDetails() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(collapseDetails));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", collapseDetails);
		
	}
}


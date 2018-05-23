package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;
import testcases.BrokerPaymentforUnmatchedCarrierTest;
import testcases.BrokerRegisterCanadaTest;
import testcases.UnmatchedCarrierOutlookTest;

public class BrokerCanadaFirstLogin extends TestBase{
	
	WebDriverWait wait = null;

	//Page Factory - OR:
	@FindBy(id="EIN")
	WebElement field_ein;
	
	@FindBy(id="ControlAmount")
	WebElement field_loadpaydepositeamt;
	
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
	public BrokerCanadaFirstLogin()
	{
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}
	
	//Actions:
	public String validateLoginPageTitle()
	{
		return driver.getTitle();
	}

	public void brokerfirstLogin() {
		wait.until(ExpectedConditions.elementToBeClickable(UserName));
		UserName.sendKeys(BrokerRegisterCanadaTest.emailid);
		Password.sendKeys(BrokerRegisterCanadaTest.pwd);
		loginBtn.click();
		wait.until(ExpectedConditions.elementToBeClickable(field_ein));
	}



}

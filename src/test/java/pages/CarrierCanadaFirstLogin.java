package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.TestBase;
import testcases.CarrierRegisterCanadaTest;

public class CarrierCanadaFirstLogin extends TestBase{
	
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
	public CarrierCanadaFirstLogin()
	{
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}
	
	//Actions:
	public String validateLoginPageTitle()
	{
		return driver.getTitle();
	}

	public void carrierfirstLogin() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(UserName));
		UserName.sendKeys(CarrierRegisterCanadaTest.cemail);
		Password.sendKeys(CarrierRegisterCanadaTest.password);
		Thread.sleep(1000);
		loginBtn.click();
		
	}



}

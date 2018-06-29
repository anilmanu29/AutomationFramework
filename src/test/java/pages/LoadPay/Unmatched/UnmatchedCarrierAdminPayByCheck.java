package pages.LoadPay.Unmatched;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;

import base.TestBase;
import testcases.LoadPay.Broker.BrokerPaymentforUnmatchedCarrierTest;
import testcases.LoadPay.Broker.BrokerRegisterCanadaTest;

public class UnmatchedCarrierAdminPayByCheck  extends TestBase{
	
	WebDriverWait wait = null;
	String paymentidd;
	//Page Factory - OR:
	@FindBy(id="EIN")
	WebElement field_ein;
	
	@FindBy(id="ControlAmount")
	WebElement field_loadpaydepositeamt;
	
	@FindBy(xpath=".//a[contains(text(),'Payments')]")
	WebElement link_Payments;
	
	@FindBy(xpath="//a[@href='#/Search']")
	WebElement link_Search;
	
	@FindBy(id="AcceptedTerms")
	WebElement checkboxaccept;
	
	@FindBy(id = "EmailTerms")
	private WebElement checkboxemail;
	
	@FindBy(xpath = "//input[@id='searchKeyword']")
	private WebElement FieldSearch;
	
	@FindBy(xpath = "//input[contains(@id,'searchKeyword')]")
	private WebElement searchKeyword;
	
	@FindBy(xpath = "//button[text()='Close']")
	private WebElement btn_close;
	
	@FindBy(xpath = "//input[contains(@value,'Search')]")
	private WebElement btn_Search;
	
	@FindBy(xpath = ".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div[1]/div/div[2]/div/div[2]/div/div/div[2]/div/a")
	private WebElement grid_collapse;
	
	@FindBy(xpath = "//button[contains(@ng-click,'PayByCheck();')]")
	private WebElement btn_PayByCheck;

	@FindBy(xpath = ".//*[@id='Terms']")
	private WebElement select_Terms;
	
	
	
	@FindBy(xpath = ".//*[@id='DOT']")
	private WebElement txt_DOT;
	
	@FindBy(xpath = "//input[contains(@name,'ContactName')]")
	private WebElement txt_ContactName;
	
	@FindBy(xpath = "//input[contains(@value,'Submit')]")
	private WebElement btn_paybychksubmit;
	
	
	@FindBy(xpath = "//input[@id='UserName']")
	WebElement UserName;

	@FindBy(xpath = "//input[@id='Password']")
	WebElement Password;

	@FindBy(xpath = "//input[contains(@type,'submit')]")
	WebElement loginBtn;
	
	@FindBy(xpath = "//div[@class='carrierPayment']//child::div[9]//child::span")
	WebElement paymentid;
	
	
	
	//Initializing the Page Objects:
	public UnmatchedCarrierAdminPayByCheck()
	{
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}
	
	//Actions:
	public String validateLoginPageTitle()
	{
		return driver.getTitle();
	}

	public void clickPayments() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", link_Payments);
		Thread.sleep(5000);

}
	
	
	
	public void ClickOnsearchKeyword(String invoice) throws InterruptedException
	{		
		FieldSearch.click();
		Thread.sleep(1000);
			FieldSearch.sendKeys(invoice);
		Thread.sleep(1000);
		FieldSearch.sendKeys(Keys.RETURN);
		Thread.sleep(1000);
			
	}
	
	public void ClickOnsearchKeywordterm(String invoice) throws InterruptedException
	{		
		FieldSearch.click();
		Thread.sleep(1000);
			FieldSearch.sendKeys(invoice);
		Thread.sleep(1000);
		FieldSearch.sendKeys(Keys.RETURN);
		Thread.sleep(1000);
			
	}
	
	
	public void getPaymentID() throws InterruptedException {
		Thread.sleep(1000);
		paymentidd = paymentid.getText();
		System.out.println(paymentidd);
}
	
	public void clickSearch() throws InterruptedException {
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", link_Search);

}
	
	public void searchKeyword() throws InterruptedException
	{		
		Thread.sleep(1000);
		searchKeyword.click();
		searchKeyword.sendKeys(paymentidd);
		Thread.sleep(1000);
		searchKeyword.sendKeys(Keys.RETURN);
		Thread.sleep(1000);
			
	}
	
	
	public void clickSearch1() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btn_Search);
		Thread.sleep(5000);

}
	
	public void clickgridcollapse() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", grid_collapse);
		Thread.sleep(2000);

}
	
	public void clickPayByCheck() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btn_PayByCheck);
		Thread.sleep(2000);
	
}
	/*
	 public void selectTerms() throws InterruptedException
	  {
		 select_Terms.click();
		  Select pay = new Select(select_Terms);
			pay.selectByIndex( 1 );
			Thread.sleep(3000);
		    
	  }
	 */
	 public void selectTermsTermPayment() throws InterruptedException
	  {
		 select_Terms.click();
		  Select pay = new Select(select_Terms);
			pay.selectByIndex( 2 );
			Thread.sleep(3000);
		    
	  }
	 
	 public void EnterDOTNnumber(String EnterDOTNnumber)
	  {
		 txt_DOT.sendKeys(EnterDOTNnumber);
	  }
	  
	 public void ContactName(String ContactName)
	  {
		 txt_ContactName.sendKeys(ContactName);
	  }
	 
	 
	 public void clickPayByChecksubmit() throws InterruptedException {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", btn_paybychksubmit);
			Thread.sleep(2000);
		
	}
}

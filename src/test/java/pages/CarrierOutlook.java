package pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.TestBase;
import testcases.BrokerPaymentforUnmatchedCarrierTest;
import testcases.BrokerRegisterTest;
import testcases.CarrierRegisterTest;

public class CarrierOutlook extends TestBase {
	WebDriverWait wait =null;
	// Page Factory - OR:
	@FindBy(id = "username")
	WebElement UserName;

	@FindBy(xpath = ".//*[@id='lgnDiv']/div[9]/div/span")
	WebElement loginButton;

	@FindBy(id = "password")
	WebElement Password;

	@FindBy(xpath = "//button[contains(@class,'_hl_2 _hl_e ms-font-weight-regular')]")
	WebElement haspopup;

	@FindBy(xpath = "//span[contains(text(),'Open another mailbox')]")
	WebElement lnkopenanothermail;

	@FindBy(xpath = "//span[contains(text(),'Search mail and people')]")
	WebElement fieldSearchMail;

	@FindBy(xpath = "//input[@role='textbox']")
	WebElement fieldTextbox;

	@FindBy(xpath = "//*[@title='Loadpay Test All']")
	WebElement searchSuggestion;

	@FindBy(xpath = "//span[text()='Open']")
	WebElement buttonOpen;

	@FindBy(xpath = "//table/tbody/tr/td//child::a[text()='Click to Verify ']")
	public WebElement linkVerify;
	
	@FindBy(xpath = "//div[@id='page-main']//child::*[text()='Email Verified']")
	WebElement emailverifymessage;
	
	@FindBy(xpath = "//table/tbody/tr/td//child::a[text()='Verify Email ']")
	WebElement verifyEmailLink;
	
//	@FindBy(xpath = "html/body/div[2]/div/div[3]/div[3]/div/div[1]/div[2]/div[4]/div/div/div[1]/div/div/button")
//	WebElement fieldsearch;
	
	@FindBy(xpath = "//button[@type='button'][@aria-label='Activate Search Textbox']")
	WebElement fieldsearch;
//	
//	@FindBy(xpath = "html/body/div[2]/div/div[3]/div[3]/div/div[1]/div[2]/div[4]/div/div/div[1]/div/div/div/div[1]/form/div/input")
//	WebElement fieldsearchinput;
	
	@FindBy(xpath = "//input[@aria-label='Search. Press Enter to Start Searching.']")
	WebElement fieldsearchinput;
	
	@FindBy(xpath = ".//*[contains(@class,'lvHighlightAllClass')]//following::*[text()='Verify your email address']")
	WebElement verifyImailMail;

//	@FindBy(xpath = "//div[@role='option']//child::span[2]")
//	WebElement keyword;
	
	@FindBy(xpath = "//button[@aria-label='Start search']")
	WebElement btnsearch;
	
	@FindBy(xpath = "//*[contains(@class,'lvHighlightAllClass')][text()='Verify your email address']")
	WebElement verifyEmail;
	
	@FindBy(xpath = "//span[text()='Search contacts and directory']")
	WebElement buttonsearchcontacts;
	
	@FindBy(xpath = "//*[@role='menuitemradio'][text()='Unread']")
	WebElement lnkunread;
	
	@FindBy(xpath = "//*[@id='ItemHeader.ToContainer']/div/div/div/span/span/div/span[2]")
	WebElement emailid;

	// Initializing the Page Objects:
	public CarrierOutlook() throws IOException {

		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);

	}

	public void clickPopUp() throws InterruptedException {
		Thread.sleep(4000);
		fieldSearchMail.click();
		Thread.sleep(1000);
		haspopup.click();
		Thread.sleep(1000);
	}

	public void clickOpenMailBox() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", lnkopenanothermail);

	}
	
	public void enterEmail(String email) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(fieldTextbox));
		fieldTextbox.sendKeys(email);
		Thread.sleep(1000);
		try {
			buttonsearchcontacts.click();
			Thread.sleep(1000);
			buttonOpen.click();
		}
		catch(Exception e) {
			searchSuggestion.click();
			Thread.sleep(1000);
			buttonOpen.click();
		}
	} 

	/*public void enterEmail(String email) throws InterruptedException {
		fieldTextbox.sendKeys(email);
		Thread.sleep(1000);
		searchSuggestion.click();
	}*/

	/*public void clickOpen() {
		buttonOpen.click();
	}
*/
	public void handleNewInbox() throws InterruptedException {
		Thread.sleep(2000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(6000);
		
	//List<WebElement> list = driver.findElements(By.xpath(".//*[contains(@class, 'lvHighlightSubjectClass')]"));
		List<WebElement> list = driver.findElements(By.xpath("//*[@class='ms-font-l lvHighlightSubjectClass lvHighlightAllClass']"));

		for(WebElement e : list)
		{
			Thread.sleep(1000);
			e.click();
			Thread.sleep(1000);
			System.out.println(emailid.getText());
			if(emailid.getText().equalsIgnoreCase(CarrierRegisterTest.email+";"))
			{
				Thread.sleep(1000);
				linkVerify.click();
				break;
			}
			
		}
		
		
	/*	JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", fieldsearch);
		Thread.sleep(1000);
		fieldsearchinput.sendKeys(CarrierRegisterTest.email);
		Thread.sleep(1000);
		JavascriptExecutor jc = (JavascriptExecutor) driver;
		jc.executeScript("arguments[0].click();", btnsearch);
		Thread.sleep(3000);
		//fieldsearchinput.sendKeys(Keys.ENTER);
		JavascriptExecutor jk = (JavascriptExecutor) driver;
		jk.executeScript("arguments[0].click();", verifyEmail);
		Thread.sleep(2000);*/
//		verifyEmail.click();
//		Thread.sleep(2000);
//		JavascriptExecutor jl = (JavascriptExecutor) driver;
//		jl.executeScript("arguments[0].click();", linkVerify);
		//linkVerify.click();


	}
	
	public void handleUpdatedEmailInbox(String updatedEmailAddress) throws InterruptedException {
		Thread.sleep(2000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(6000);
		
		List<WebElement> list = driver.findElements(By.xpath("//*[@class='ms-font-l lvHighlightSubjectClass lvHighlightAllClass']"));

		for(WebElement e : list)
		{
			Thread.sleep(1000);
			e.click();
			Thread.sleep(1000);
			System.out.println(emailid.getText());
			if(emailid.getText().equalsIgnoreCase(updatedEmailAddress +";"))
			{
				Thread.sleep(1000);
				verifyEmailLink.click();
				break;
			}
			
		}
	}

	public void verifyConfirmationMessage() throws InterruptedException {
		Thread.sleep(1000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(2));
		Thread.sleep(1000);
		Assert.assertTrue(emailverifymessage.isDisplayed());
		driver.close();
		driver.switchTo().window(tabs.get(1));

	}
	
	public void outlookSearchInbox(String updatedBrokerEmailAddress) throws InterruptedException
	{
		WebElement searchInput;
		WebElement searchButton;
		WebElement infoMessage;
		WebElement emailid;
		Integer retryCount = 0;
		Integer maxRetryCount = 300;
		
		Thread.sleep(2000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(6000);
		
		WebElement searchField = driver.findElement(By.xpath("//span[text()='Search mail and people']"));
		searchField.click();
		
		searchInput = driver.findElement(By.xpath("//input[@aria-label='Search. Press Enter to Start Searching.']"));
		searchButton = driver.findElement(By.xpath("//button[@aria-label='Start search']"));
		
		Thread.sleep(1000);
		searchInput.sendKeys(updatedBrokerEmailAddress);
		searchButton.click();
		
		infoMessage = driver.findElement(By.id("conv.mail_list_view_info_message"));
		System.out.println("Info message text: " + infoMessage.getText());
		
		while(infoMessage.isDisplayed() && (retryCount < maxRetryCount))
		{
			searchButton.click();
			Thread.sleep(1000);
			infoMessage = driver.findElement(By.id("conv.mail_list_view_info_message"));
		}
		
		emailid = driver.findElement(By.xpath("//*[@id='ItemHeader.ToContainer']/div/div/div/span/span/div/span[2]"));
		
		System.out.println("Email ID text: " + emailid.getText());
		Assert.assertTrue(emailid.getText().equalsIgnoreCase(updatedBrokerEmailAddress+";"), "Email ID not found!");
			
		Thread.sleep(1000);
	}


}

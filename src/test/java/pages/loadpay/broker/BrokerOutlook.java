package pages.loadpay.broker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.TestBase;
import testcases.loadpay.broker.BrokerRegisterTest;

public class BrokerOutlook extends TestBase {
	
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
	WebElement linkVerify;

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
	
	@FindBy(xpath = "//*[@id='ItemHeader.ToContainer']/div/div/div/span/span/div/span[2]")
	WebElement emailid;

	@FindBy(xpath = "//table/tbody/tr/td//child::a[text()='Reset Your Password ']")
	WebElement resetPasswordButton;

	// Initializing the Page Objects:
	public BrokerOutlook() throws IOException {

		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);

	}

	public void clickPopUp() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		fieldSearchMail.click();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		haspopup.click();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	}

	public void clickOpenMailBox() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", lnkopenanothermail);

	}
	
	public void enterEmail(String email) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(fieldTextbox));
		fieldTextbox.sendKeys(email);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		try {
			buttonsearchcontacts.click();
			wait.until(ExpectedConditions.elementToBeClickable(tempElement));
			buttonOpen.click();
		}
		catch(Exception e) {
			searchSuggestion.click();
			wait.until(ExpectedConditions.elementToBeClickable(tempElement));
			buttonOpen.click();
		}
	} 

	/*public void enterEmail(String email) throws InterruptedException {
		fieldTextbox.sendKeys(email);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		searchSuggestion.click();
	}*/

	/*public void clickOpen() {
		buttonOpen.click();
	}
*/
	public void handleNewInbox() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		
		
		
		//List<WebElement> list = driver.findElements(By.xpath("//*[contains(@class, 'lvHighlightSubjectClass')]"));
		List<WebElement> list = driver.findElements(By.xpath("//*[@class='ms-font-l lvHighlightSubjectClass lvHighlightAllClass']"));
		for(WebElement e : list)
		{
			wait.until(ExpectedConditions.elementToBeClickable(tempElement));
			e.click();
			wait.until(ExpectedConditions.elementToBeClickable(tempElement));
			log.info(emailid.getText());
			if(emailid.getText().equalsIgnoreCase(BrokerRegisterTest.emailid+";"))
			{
				wait.until(ExpectedConditions.elementToBeClickable(tempElement));
				linkVerify.click();
				break;
			}
			
		}
		
		
		
		/*JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", fieldsearch);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		fieldsearchinput.sendKeys(BrokerRegisterTest.emailid);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		JavascriptExecutor jc = (JavascriptExecutor) driver;
		jc.executeScript("arguments[0].click();", btnsearch);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		//fieldsearchinput.sendKeys(Keys.ENTER);
		JavascriptExecutor jk = (JavascriptExecutor) driver;
		jk.executeScript("arguments[0].click();", verifyEmail);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));*/
//		verifyEmail.click();
//		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
//		JavascriptExecutor jl = (JavascriptExecutor) driver;
//		jl.executeScript("arguments[0].click();", linkVerify);
		//linkVerify.click();

	}


	public void handleResetPasswordEmailInbox(String EmailAddress) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
   /* ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
    driver.switchTo().window(tabs.get(1));
    wait.until(ExpectedConditions.elementToBeClickable(tempElement));*/
		driver.findElements(By.xpath("//*[@class='ms-font-l lvHighlightSubjectClass lvHighlightAllClass']"));
		log.info(emailid.getText());
		if (emailid.getText().equalsIgnoreCase(EmailAddress + ";")) {
			wait.until(ExpectedConditions.elementToBeClickable(tempElement));
			resetPasswordButton.click();
			wait.until(ExpectedConditions.elementToBeClickable(tempElement));
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(2));
			wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		}
	}

	public void handleUpdatedEmailInbox(String updatedEmailAddress) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		
		List<WebElement> list = driver.findElements(By.xpath("//*[@class='ms-font-l lvHighlightSubjectClass lvHighlightAllClass']"));

		for(WebElement e : list)
		{
			wait.until(ExpectedConditions.elementToBeClickable(tempElement));
			e.click();
			wait.until(ExpectedConditions.elementToBeClickable(tempElement));
			log.info(emailid.getText());
			if(emailid.getText().equalsIgnoreCase(updatedEmailAddress +";"))
			{
				wait.until(ExpectedConditions.elementToBeClickable(tempElement));
				verifyEmailLink.click();
				break;
			}
			
		}
	}

	public void verifyConfirmationMessage() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(2));
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(emailverifymessage.isDisplayed());
		driver.close();
		driver.switchTo().window(tabs.get(1));

	}
	
	public void outlookSearchInbox(String updatedBrokerEmailAddress, String hour, String minutes) throws InterruptedException
	{
		WebElement searchInput;
		WebElement searchButton;
		WebElement infoMessage;
		WebElement emailid;
		Integer retryCount = 0;
		Integer maxRetryCount = 300;
		
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		
		WebElement searchField = driver.findElement(By.xpath("//span[text()='Search mail and people']"));
		searchField.click();
		
		searchInput = driver.findElement(By.xpath("//input[@aria-label='Search. Press Enter to Start Searching.']"));
		searchButton = driver.findElement(By.xpath("//button[@aria-label='Start search']"));
		
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		searchInput.sendKeys(updatedBrokerEmailAddress);
		searchButton.click();
		
		infoMessage = driver.findElement(By.id("conv.mail_list_view_info_message"));
		log.info("Info message text: " + infoMessage.getText());
		
		while ((infoMessage.isDisplayed() || checkEmailTimeStamp(hour, minutes)) && (retryCount < maxRetryCount)) {
			searchButton.click();
			wait.until(ExpectedConditions.elementToBeClickable(tempElement));
			infoMessage = driver.findElement(By.id("conv.mail_list_view_info_message"));
			retryCount++;
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		emailid = driver.findElement(By.xpath("//*[@id='ItemHeader.ToContainer']/div/div/div/span/span/div/span[2]"));
		
		log.info("Email ID text: " + emailid.getText());
		//Assert.assertTrue(emailid.getText().equalsIgnoreCase(updatedBrokerEmailAddress+";"), "Email ID not found!");
			
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	}
	
	private Boolean checkEmailTimeStamp(String hour, String minutes)
	{
		WebElement emailTimeStamp;
		String emailTime = "";
		Integer approximateHour = 0;
		Integer approximateMinutes = 0;
		Integer actualHour = 0;
		Integer actualMinutes = 0;
		String[] timeParser;
		
		emailTimeStamp = driver.findElement(By.id("ItemHeader.DateReceivedLabel"));
		emailTime = emailTimeStamp.getText();
		emailTime = emailTime.substring(emailTime.length()-8, emailTime.length());
		log.info("\n\n\nEmail time: " + emailTime);
		timeParser = emailTime.split(":");
		timeParser[0] = timeParser[0].trim();
		timeParser[1] = timeParser[1].trim();
		timeParser[1] = timeParser[1].substring(0, 2);
		
		approximateHour = Integer.parseInt(hour);
		approximateMinutes = Integer.parseInt(minutes);
		
		if(approximateHour > 12)
			approximateHour -= 12;
		
		log.info("Approx Hours: " + approximateHour);
		log.info("Approx Minutes: " + approximateMinutes);
		
		actualHour = Integer.parseInt(timeParser[0]);
		actualMinutes = Integer.parseInt(timeParser[1]);
		
		if(actualHour > 12)
			actualHour -= 12;
		
		log.info("Actual Hours: " + actualHour);
		log.info("Actual Minutes: " + actualMinutes);
		
		if((approximateHour != actualHour) || (actualMinutes < approximateMinutes))
		{
			return true;	
		}
		else
		{
			return false;
		}
	}

}

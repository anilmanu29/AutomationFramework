package pages.loadpay.carrier;

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
import testcases.loadpay.carrier.CarrierRegisterCanadaTest;

public class CarrierOutlookCanada extends TestBase {

	WebDriverWait wait=null;
	
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

	// Initializing the Page Objects:
	public CarrierOutlookCanada() throws IOException {
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
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		wait.until(ExpectedConditions.visibilityOf(fieldTextbox));
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
		List<WebElement> list = driver.findElements(By.xpath("//*[@class='ms-font-l lvHighlightSubjectClass lvHighlightAllClass']"));
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		for(WebElement e : list)
		{
			wait.until(ExpectedConditions.elementToBeClickable(tempElement));
			e.click();
			wait.until(ExpectedConditions.elementToBeClickable(tempElement));
			if(emailid.getText().equalsIgnoreCase(CarrierRegisterCanadaTest.cemail+";"))
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
/*		verifyEmail.click();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		JavascriptExecutor jl = (JavascriptExecutor) driver;
		jl.executeScript("arguments[0].click();", linkVerify);*/
		//linkVerify.click();

	}

	public void verifyConfirmationMessage() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(2));
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(emailverifymessage.isDisplayed());
	}

}

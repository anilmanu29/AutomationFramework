package pages.loadpay.unmatched;

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
import testcases.loadpay.broker.BrokerPaymentforUnmatchedCarrierTest;

public class UnmatchedCariierEmailVerifyNextDay extends TestBase {

	WebDriverWait wait = null;

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

	// @FindBy(xpath =
	// "html/body/div[2]/div/div[3]/div[3]/div/div[1]/div[2]/div[4]/div/div/div[1]/div/div/button")
	// WebElement fieldsearch;

	@FindBy(xpath = "//button[@type='button'][@aria-label='Activate Search Textbox']")
	WebElement fieldsearch;
	//
	// @FindBy(xpath =
	// "html/body/div[2]/div/div[3]/div[3]/div/div[1]/div[2]/div[4]/div/div/div[1]/div/div/div/div[1]/form/div/input")
	// WebElement fieldsearchinput;

	@FindBy(xpath = "//input[@aria-label='Search. Press Enter to Start Searching.']")
	WebElement fieldsearchinput;

	@FindBy(xpath = ".//*[contains(@class,'lvHighlightAllClass')]//following::*[text()='Verify your email address']")
	WebElement verifyImailMail;

	// @FindBy(xpath = "//div[@role='option']//child::span[2]")
	// WebElement keyword;

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
	public UnmatchedCariierEmailVerifyNextDay() throws IOException {

		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);

	}

	public void clickPopUp() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(fieldSearchMail));
		fieldSearchMail.click();
		wait.until(ExpectedConditions.elementToBeClickable(haspopup));
		haspopup.click();
	}

	public void clickOpenMailBox() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", lnkopenanothermail);

	}

	public void enterEmail(String email) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(fieldTextbox));
		fieldTextbox.sendKeys(email);
		wait.until(ExpectedConditions.elementToBeClickable(buttonsearchcontacts));
		try {
			buttonsearchcontacts.click();
			wait.until(ExpectedConditions.elementToBeClickable(buttonOpen));
			buttonOpen.click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(searchSuggestion));
			searchSuggestion.click();
			wait.until(ExpectedConditions.elementToBeClickable(buttonOpen));
			buttonOpen.click();
		}
	}

	public void handleNewInbox() throws InterruptedException {
		Thread.sleep(1000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(5000);

		List<WebElement> list = driver
				.findElements(By.xpath("//*[@class='ms-font-l lvHighlightSubjectClass lvHighlightAllClass']"));
		log.info(list.size());

		for (WebElement e : list) {
			wait.until(ExpectedConditions.elementToBeClickable(e));
			e.click();
			wait.until(ExpectedConditions.elementToBeClickable(emailid));

			if (emailid.getText().equalsIgnoreCase(BrokerPaymentforUnmatchedCarrierTest.al.get(0) + ";")) {
				wait.until(ExpectedConditions.elementToBeClickable(linkVerify));
				linkVerify.click();
				break;
			}
		}
	}

	public void verifyConfirmationMessage() throws InterruptedException {
		Thread.sleep(1000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(2));
		wait.until(ExpectedConditions.elementToBeClickable(emailverifymessage));
		Assert.assertTrue(emailverifymessage.isDisplayed());
		driver.close();
		driver.switchTo().window(tabs.get(1));

	}

}

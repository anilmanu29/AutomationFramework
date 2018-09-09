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

import base.TestBase;
import testcases.loadpay.broker.SchpaymentwithoutBankAccountPayByInvoiceEnabledTest;

public class SchpaymentwithoutBankAccountPayByInvoiceEnabled extends TestBase {

	// Page Factory - OR:
	WebDriverWait wait = null;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	@FindBy(xpath = "//input[contains(@type,'submit')]")
	public WebElement loginBtn;

	@FindBy(xpath = "//input[@id='UserName']")
	public WebElement UserName;

	@FindBy(xpath = "//input[@id='Password']")
	public WebElement Password;

	@FindBy(xpath = "//a[text()='Logoff']")
	private WebElement btn_logout;

	@FindBy(xpath = "//button[contains(@class,'_hl_2 _hl_e ms-font-weight-regular')]")
	WebElement haspopup;

	@FindBy(xpath = "//span[contains(text(),'Open another mailbox')]")
	WebElement lnkopenanothermail;

	@FindBy(xpath = ".//*[@id='termsForm']/input")
	WebElement click_submit;

	@FindBy(xpath = "//span[contains(text(),'Search mail and people')]")
	WebElement fieldSearchMail;

	@FindBy(xpath = "//input[@role='textbox']")
	public WebElement fieldTextbox;

	@FindBy(xpath = ".//*[@id='formCompany']/input")
	public WebElement nextButton;

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

	@FindBy(xpath = "//button[@type='button'][@aria-label='Activate Search Textbox']")
	WebElement fieldsearch;

	@FindBy(id = "EIN")
	public WebElement field_ein;

	@FindBy(xpath = "//input[@id='AcceptedTerms']")
	public WebElement AcceptedTerms;

	@FindBy(xpath = "//*[@id='formCompany']/input")
	WebElement einNextButton;

	@FindBy(xpath = "//*[@id='AcceptedTerms']")
	WebElement termsAndConditionsCheckBox;

	@FindBy(xpath = "//*[@id='termsForm']/input")
	WebElement finishButton;

	@FindBy(xpath = "//a[text()='New Payment']")
	private WebElement lnk_newpayment;

	@FindBy(xpath = "//input[@aria-label='Search. Press Enter to Start Searching.']")
	WebElement fieldsearchinput;

	@FindBy(xpath = ".//*[contains(@class,'lvHighlightAllClass')]//following::*[text()='Verify your email address']")
	WebElement verifyImailMail;

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

	@FindBy(xpath = "//input[@value='Add Later']")
	public WebElement click_addlater;

	@FindBy(xpath = "//*[@role='menuitemradio'][text()='Unread']")
	WebElement lnkunread;

	// Initializing the Page Objects:
	public SchpaymentwithoutBankAccountPayByInvoiceEnabled() throws IOException {

		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);

	}

	public void ClickPopUp() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(fieldSearchMail));
		fieldSearchMail.click();

		wait.until(ExpectedConditions.elementToBeClickable(haspopup));
		haspopup.click();
	}

	public void ClickOpenMailBox() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", lnkopenanothermail);

	}

	public void clickAcceptedTerms() {
		wait.until(ExpectedConditions.elementToBeClickable(AcceptedTerms));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", AcceptedTerms);

	}

	public void clicksubmit() {
		wait.until(ExpectedConditions.elementToBeClickable(click_submit));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", click_submit);

	}

	public void enterEmail(String email) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(fieldTextbox));
		fieldTextbox.sendKeys(email);

		try {
			wait.until(ExpectedConditions.elementToBeClickable(buttonsearchcontacts));
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

	public void clickaddlater() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_addlater));

		js.executeScript("arguments[0].click();", click_addlater);
	}

	public void newPayment() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(lnk_newpayment));
		js.executeScript("arguments[0].click();", lnk_newpayment);
		// lnk_newpayment.click();
	}

	public void BrokerLogout() {
		wait.until(ExpectedConditions.elementToBeClickable(btn_logout));
		js.executeScript("arguments[0].click();", btn_logout);
		// btn_logout.click();
	}

	public void brokerfirstLogin() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(UserName));
		UserName.click();
		UserName.clear();
		UserName.sendKeys(SchpaymentwithoutBankAccountPayByInvoiceEnabledTest.emailid);

		wait.until(ExpectedConditions.elementToBeClickable(Password));
		Password.click();
		Password.clear();
		Password.sendKeys(SchpaymentwithoutBankAccountPayByInvoiceEnabledTest.brokerPassword);

		wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
		loginBtn.click();
		wait.until(ExpectedConditions.elementToBeClickable(field_ein));
		field_ein.sendKeys(SchpaymentwithoutBankAccountPayByInvoiceEnabledTest.EIN);

		wait.until(ExpectedConditions.elementToBeClickable(nextButton));
		nextButton.click();
	}

	public void brokerSecondLogin() {
		wait.until(ExpectedConditions.elementToBeClickable(UserName));
		UserName.click();
		UserName.clear();
		UserName.sendKeys(SchpaymentwithoutBankAccountPayByInvoiceEnabledTest.emailid);

		wait.until(ExpectedConditions.elementToBeClickable(Password));
		Password.click();
		Password.clear();
		Password.sendKeys(SchpaymentwithoutBankAccountPayByInvoiceEnabledTest.brokerPassword);

		wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
		loginBtn.click();
	}

	public void handleNewInbox(String brokerUsername) throws InterruptedException {
		Thread.sleep(2000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(6000);

		// List<WebElement> list = driver.findElements(By.xpath("//*[contains(@class,
		// 'lvHighlightSubjectClass')]"));
		List<WebElement> list = driver
				.findElements(By.xpath("//*[@class='ms-font-l lvHighlightSubjectClass lvHighlightAllClass']"));
		for (WebElement e : list) {
			Thread.sleep(2000);
			e.click();
			Thread.sleep(1000);
			System.out.println(emailid.getText());
			if (emailid.getText().equalsIgnoreCase(brokerUsername + ";")) {
				Thread.sleep(2000);
				linkVerify.click();
				break;
			}
		}
	}

	public void handleResetPasswordEmailInbox(String EmailAddress) throws InterruptedException {
		Thread.sleep(1000);

		driver.findElements(By.xpath("//*[@class='ms-font-l lvHighlightSubjectClass lvHighlightAllClass']"));
		System.out.println(emailid.getText());
		if (emailid.getText().equalsIgnoreCase(EmailAddress + ";")) {
			Thread.sleep(3000);
			resetPasswordButton.click();
			Thread.sleep(2000);
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(2));
			Thread.sleep(2000);

		}
	}

	public void handleUpdatedEmailInbox(String updatedEmailAddress) throws InterruptedException {
		Thread.sleep(2000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(6000);

		List<WebElement> list = driver
				.findElements(By.xpath("//*[@class='ms-font-l lvHighlightSubjectClass lvHighlightAllClass']"));

		for (WebElement e : list) {
			Thread.sleep(1000);
			e.click();
			Thread.sleep(1000);
			System.out.println(emailid.getText());
			if (emailid.getText().equalsIgnoreCase(updatedEmailAddress + ";")) {
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
		// Assert.assertTrue(emailverifymessage.isDisplayed());
		driver.close();
		driver.switchTo().window(tabs.get(1));

	}

	public void outlookSearchInbox(String updatedBrokerEmailAddress, String hour, String minutes)
			throws InterruptedException {
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

		while (infoMessage.isDisplayed() && (retryCount < maxRetryCount)) {
			searchButton.click();
			Thread.sleep(5000);
			infoMessage = driver.findElement(By.id("conv.mail_list_view_info_message"));

			if (!infoMessage.isDisplayed()) {
				if (checkEmailTimeStamp(hour, minutes))
					break;
			}

			retryCount++;
		}

		emailid = driver.findElement(By.xpath("//*[@id='ItemHeader.ToContainer']/div/div/div/span/span/div/span[2]"));

		System.out.println("Email ID text: " + emailid.getText());
	}

	private Boolean checkEmailTimeStamp(String hour, String minutes) {
		WebElement emailTimeStamp;
		String emailTime = "";
		Integer approximateHour = 0;
		Integer approximateMinutes = 0;
		Integer actualHour = 0;
		Integer actualMinutes = 0;
		String[] timeParser;

		emailTimeStamp = driver.findElement(By.id("ItemHeader.DateReceivedLabel"));
		emailTime = emailTimeStamp.getText();
		emailTime = emailTime.substring(emailTime.length() - 8, emailTime.length());
		System.out.println("\n\n\nEmail time: " + emailTime);
		timeParser = emailTime.split(":");
		timeParser[0] = timeParser[0].trim();
		timeParser[1] = timeParser[1].trim();
		timeParser[1] = timeParser[1].substring(0, 2);

		approximateHour = Integer.parseInt(hour);
		approximateMinutes = Integer.parseInt(minutes);

		if (approximateHour > 12)
			approximateHour -= 12;

		System.out.println("Approx Hours: " + approximateHour);
		System.out.println("Approx Minutes: " + approximateMinutes);

		actualHour = Integer.parseInt(timeParser[0]);
		actualMinutes = Integer.parseInt(timeParser[1]);

		if (actualHour > 12)
			actualHour -= 12;

		System.out.println("Actual Hours: " + actualHour);
		System.out.println("Actual Minutes: " + actualMinutes);

		if ((approximateHour == actualHour) && (approximateMinutes <= actualMinutes)) {
			return true;
		} else {
			return false;
		}
	}

}
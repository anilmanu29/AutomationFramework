package pages.loadpay.carrier;

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

public class CarrierlockedAccountResetPassword extends TestBase {

	Carrierlockedaccountcanbeunlockedbyadmin carrierlockaccountobj;
	WebDriverWait wait = null;
	JavascriptExecutor js;

	// Page Factory - OR:
	@FindBy(xpath = "//a[text()='Forgot Password?']")
	public WebElement resetpwdlink;

	@FindBy(id = "UserName")
	public WebElement usernamefield;

	@FindBy(xpath = "//input[@value='Reset Password']")
	public WebElement resetpasswordbutton;

	@FindBy(xpath = "//a[text()='Logoff']")
	public WebElement btn_logout;

	@FindBy(xpath = "//*[@id='page-main']/h2")
	public WebElement succesfulmessage;

	@FindBy(xpath = "//*[@id='ItemHeader.ToContainer']/div/div/div/span/span/div/span[2]")
	public WebElement emailid;

	@FindBy(xpath = "//table/tbody/tr/td//child::a[text()='Reset Your Password ']")
	public WebElement buttonresetpassword;

	// Initializing the Page Objects:
	public CarrierlockedAccountResetPassword() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		js = (JavascriptExecutor) driver;
		carrierlockaccountobj = new Carrierlockedaccountcanbeunlockedbyadmin();
	}

	public void lockCarrierAccount(String un, String pwd, String wrgpwd) throws InterruptedException {
		carrierlockaccountobj.Carrierloginlock(un, pwd, wrgpwd);
	}

	public void clickResetPasswordlink() {
		wait.until(ExpectedConditions.elementToBeClickable(resetpwdlink));
		js.executeScript("arguments[0].click();", resetpwdlink);
	}

	public void enterUserName(String aemail) {
		wait.until(ExpectedConditions.elementToBeClickable(usernamefield));
		usernamefield.sendKeys(aemail);
	}

	public void clickResetPasswordButton() {
		wait.until(ExpectedConditions.elementToBeClickable(resetpasswordbutton));
		js.executeScript("arguments[0].click();", resetpasswordbutton);
	}

	public void outlookSearchInbox(String emailaddress, String hour, String minutes) throws InterruptedException {
		WebElement searchInput;
		WebElement searchButton;
		WebElement infoMessage;
		Integer retryCount = 0;
		Integer maxRetryCount = 300;
		
		Thread.sleep(2000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(2));
		Thread.sleep(6000);

		WebElement searchField = driver.findElement(By.xpath("//span[text()='Search mail and people']"));
		searchField.click();

		searchInput = driver.findElement(By.xpath("//input[@aria-label='Search. Press Enter to Start Searching.']"));
		searchButton = driver.findElement(By.xpath("//button[@aria-label='Start search']"));

		Thread.sleep(1000);
		searchInput.sendKeys(emailaddress);
		searchButton.click();

		infoMessage = driver.findElement(By.id("conv.mail_list_view_info_message"));
		System.out.println("Info message text: " + infoMessage.getText());
		
		while ((infoMessage.isDisplayed() || checkEmailTimeStamp(hour, minutes)) && (retryCount < maxRetryCount)) {
			searchButton.click();
			Thread.sleep(1000);
			infoMessage = driver.findElement(By.id("conv.mail_list_view_info_message"));
			retryCount++;
		}
		

		emailid = driver.findElement(By.xpath("//*[@id='ItemHeader.ToContainer']/div/div/div/span/span/div/span[2]"));

		System.out.println("Email ID text: " + emailid.getText());
		Assert.assertTrue(emailid.getText().equalsIgnoreCase(emailaddress + ";"), "Email ID not found!");

		Thread.sleep(1000);
	}

	public void handleUpdatedEmailInbox(String emailaddress) throws InterruptedException {
		Thread.sleep(2000);

		List<WebElement> list = driver
				.findElements(By.xpath("//*[@class='ms-font-l lvHighlightSubjectClass lvHighlightAllClass']"));

		for (WebElement e : list) {
			Thread.sleep(1000);
			e.click();
			Thread.sleep(1000);
			System.out.println(emailid.getText());
			if (emailid.getText().equalsIgnoreCase(emailaddress + ";")) {
				Thread.sleep(1000);
				wait.until(ExpectedConditions.elementToBeClickable(buttonresetpassword));
				js.executeScript("arguments[0].click();", buttonresetpassword);
				Thread.sleep(2000);
				break;
			}

		}

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(3));
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
		System.out.println("\n\n\nEmail time: " + emailTime);
		timeParser = emailTime.split(":");
		timeParser[0] = timeParser[0].trim();
		timeParser[1] = timeParser[1].trim();
		timeParser[1] = timeParser[1].substring(0, 2);
		
		approximateHour = Integer.parseInt(hour);
		approximateMinutes = Integer.parseInt(minutes);
		System.out.println("Approx Hours: " + approximateHour);
		System.out.println("Approx Minutes: " + approximateMinutes);
		
		actualHour = Integer.parseInt(timeParser[0]);
		actualMinutes = Integer.parseInt(timeParser[1]);
		System.out.println("Actual Hours: " + actualHour);
		System.out.println("Actual Minutes: " + actualMinutes);
		
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

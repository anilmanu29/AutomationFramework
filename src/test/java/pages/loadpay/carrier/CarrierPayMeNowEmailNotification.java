package pages.loadpay.carrier;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.TestBase;

public class CarrierPayMeNowEmailNotification extends TestBase {

	WebDriverWait wait = null;
	Actions act = null;
	ArrayList<String> tabs;
	String verifytext = "";
	JavascriptExecutor js = (JavascriptExecutor) driver;

	@FindBy(xpath = "//*[@class='PMN'][contains(text(),'PayMeNow')]")
	public WebElement paymenowtab;

	@FindBy(xpath = "//*[text()='SCHEDULED PAYMENTS']")
	public WebElement scheduledpaymetstab;

	@FindBy(xpath = ".//*[text()='PAID']")
	public WebElement paidtab;

	@FindBy(xpath = "//table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/span/strong[3]")
	private WebElement paymenttype;

	@FindBy(xpath = "//*[@id='angularScope']/div[2]/div/div[3]/ul/li[3]/a/div/div[1]/div[2]/span[1]")
	private WebElement paidamt;

	@FindBy(xpath = "//*[@id='paymentOptionsDiv']/div[2]/div[1]/div[3]/p/span")
	private WebElement nextdaydayamt;

	@FindBy(xpath = "//*[@id='paymentOptionsDiv']/div[3]/div[1]/div[5]/span[3]//following::text()[1]")
	private WebElement paymenowfee;

	public CarrierPayMeNowEmailNotification() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		act = new Actions(driver);
	}

	public void openandSwitchToNewWindow(int winid) throws InterruptedException {

		((JavascriptExecutor) driver).executeScript("window.open();");
		Thread.sleep(1000);
		tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(winid));

	}

	public void switchToNewWindow(int winid) throws InterruptedException {

		Thread.sleep(1000);
		tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(winid));

	}

	public void outlookSearchInbox(String searchtext, String emailaddress, String paymenttypetext)
			throws InterruptedException {
		WebElement searchInput;
		WebElement searchButton;
		WebElement emailid;
		Thread.sleep(5000);
		WebElement searchField = driver.findElement(By.xpath("//span[text()='Search mail and people']"));
		wait.until(ExpectedConditions.elementToBeClickable(searchField));
		searchField.click();

		searchInput = driver.findElement(By.xpath("//input[@aria-label='Search. Press Enter to Start Searching.']"));
		searchButton = driver.findElement(By.xpath("//button[@aria-label='Start search']"));

		wait.until(ExpectedConditions.elementToBeClickable(searchButton));
		searchInput.sendKeys(searchtext);
		searchButton.click();

		String paymenttypemessagee = "";

		while ((!paymenttypemessagee.contains(paymenttypetext))) {
			wait.until(ExpectedConditions.elementToBeClickable(searchButton));
			searchButton.click();
			paymenttypemessagee = driver.findElement(By.xpath(
					"//table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/span/strong[3]"))
					.getText();

		}

		List<WebElement> list = driver.findElements(By.xpath(".//*[text()='PayMeNow Payment Notification']"));
		emailid = driver.findElement(By.xpath("//*[@id='ItemHeader.ToContainer']/div/div/div/span/span/div/span[2]"));
		for (WebElement e : list) {
			wait.until(ExpectedConditions.elementToBeClickable(e));

			js.executeScript("arguments[0].click();", e);
			// e.click();
			wait.until(ExpectedConditions.elementToBeClickable(emailid));
			// log.info(emailid.getText());
			if (emailid.getText().equalsIgnoreCase(emailaddress + ";")) {
				wait.until(ExpectedConditions.elementToBeClickable(paymenttype));
				log.info(paymenttype.getText());
				Assert.assertTrue(paymenttype.getText().equalsIgnoreCase(paymenttypetext),
						"Payment type text not found!");
				break;
			}

		}
	}

	/**
	 * @return the paymenowtab
	 */
	public WebElement getPaymenowtab() {
		return paymenowtab;
	}

	/**
	 * @return the scheduledpaymetstab
	 */
	public WebElement getScheduledpaymetstab() {
		return scheduledpaymetstab;
	}

	/**
	 * @return the paidtab
	 */
	public WebElement getPaidtab() {
		return paidtab;
	}

	/**
	 * @return the paidamt
	 */
	public WebElement getPaidamt() {
		return paidamt;
	}

	/**
	 * @return the nextdaydayamt
	 */
	public WebElement getNextdaydayamt() {
		return nextdaydayamt;
	}

	/**
	 * @return the paymenowfee
	 */
	public WebElement getPaymenowfee() {
		return paymenowfee;
	}
}

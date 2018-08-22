package pages.loadpay.carrier;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class CarrierPayMeNowTab extends TestBase {
	WebDriverWait wait = null;
	Actions act = null;

	@FindBy(xpath = "//a[@href='#/Payments/PayMeNow']")
	private WebElement paymenow;

	@FindBy(xpath = "//*[@class='row ng-scope']//child::a[contains(text(),'Days')]")
	private WebElement days;

	@FindBy(xpath = "//*[@class='row ng-scope']//child::a[contains(text(),'Amount')]")
	private WebElement Amount;

	@FindBy(xpath = "//*[@class='row ng-scope']//child::a[contains(text(),'Payer')]")
	private WebElement Payer;

	@FindBy(xpath = "//*[@class='row ng-scope']//child::a[contains(text(),'Invoice')]")
	private WebElement Invoice;

	@FindBy(xpath = "//*[@class='row ng-scope']//child::a[contains(text(),'Load ID')]")
	private WebElement LoadID;

	@FindBy(xpath = "//*[@class='row ng-scope']//child::a[contains(text(),'Scheduled Date')]")
	private WebElement ScheduledDate;

	public CarrierPayMeNowTab() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		act = new Actions(driver);
	}

	public void clickPaymenow() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(paymenow));
		paymenow.click();
	}

	public void days() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(days));
		act.moveToElement(days).click().perform();
	}

	public void Amount() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(Amount));
		Amount.click();
	}

	public void Payer() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(Payer));
		Payer.click();
	}

	public void LoadID() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(LoadID));
		LoadID.click();
	}

	public void Invoice() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(Invoice));
		Invoice.click();
	}

	public void ScheduledDate() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(ScheduledDate));
		ScheduledDate.click();
	}
}

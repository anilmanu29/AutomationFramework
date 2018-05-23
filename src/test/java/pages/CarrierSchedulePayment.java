package pages;

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

public class CarrierSchedulePayment extends TestBase {

	WebDriverWait wait = null;
	Actions act = null;

	@FindBy(xpath = "//a[@href='#/Payments/PayMeNow']")
	private WebElement paymenow;

	@FindBy(xpath = "//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]")
	private WebElement carrierPayment;

	@FindBy(xpath = "//a[contains(@href,'#/Payments/ScheduledPayments')]")
	private WebElement ScheduledPayments;

	@FindBy(xpath = "//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]")
	private WebElement collapseDetails;

	@FindBy(xpath = "//div[@aria-expanded='false']//child::div[5]/div/span")
	List<WebElement> inovicecount;

	public CarrierSchedulePayment() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		act = new Actions(driver);
	}

	public void clickPaymenow() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(paymenow));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", paymenow);
	}

	public void clickcarrierPayment(String invoicenumber) throws InterruptedException {

		for (int i = 0; i < inovicecount.size(); i++) {
			String invoicenum = inovicecount.get(i).getText();
			if (invoicenum.equalsIgnoreCase(invoicenumber)) {
				System.out.println(invoicenum);
				Thread.sleep(1000);
				act.moveToElement(inovicecount.get(i)).click().perform();
				break;
			}

		}
	}

	public void clickScheduledPayments(String invoicenumber) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(ScheduledPayments));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", ScheduledPayments);

		for (int i = 0; i < inovicecount.size(); i++) {
			String invoicenum = inovicecount.get(i).getText();
			if (invoicenum.equalsIgnoreCase(invoicenumber)) {
				System.out.println(invoicenum);
				Thread.sleep(1000);
				act.moveToElement(inovicecount.get(i)).click().perform();
				break;
			}

		}

	}

}

package pages;

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

public class CarrierSchedulePayment extends TestBase {

	WebDriverWait wait = null;
	Actions act = null;

	@FindBy(xpath = "//a[@href='#/Payments/PayMeNow']")
	private WebElement paymenow;

	@FindBy(xpath = "//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]")
	private WebElement carrierPayment;

	@FindBy(xpath = "//a[contains(@href,'#/Payments/ScheduledPayments')]")
	private WebElement ScheduledPayments;

	/*@FindBy(xpath = "//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]")
	private WebElement collapseDetails;*/
	
	//
	@FindBy(xpath = "//*[@class='row ng-scope']//child::a[contains(text(),'Days')]")
	public WebElement DaysColumn;
	
	@FindBy(xpath = "//*[@class='row ng-scope']//child::a[contains(text(),'Amount')]")
	public WebElement amountColumn;
	
	@FindBy(xpath = "//*[@class='row ng-scope']//child::a[contains(text(),'Payer')]")
	public WebElement payerColumn;
	
	@FindBy(xpath = "//*[@class='row ng-scope']//child::a[contains(text(),'Invoice #')]")
	public WebElement InvoiceColumn;
	
	@FindBy(xpath = "//*[@class='row ng-scope']//child::a[contains(text(),'Load ID')]")
	public WebElement loadIDColumn;
	
	@FindBy(xpath = "//*[@class='row ng-scope']//child::a[contains(text(),'Scheduled Date')]")
	public WebElement schdateColumn; 	
	
	@FindBy(id = "searchKeyword")
	public WebElement searchInputField;
	
	@FindBy(xpath = "//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[1]/div/input[2]")
	public WebElement searchButton;
	
	@FindBy(xpath = "//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]")
	public WebElement expandCollapseFirstRow;

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

	

	public void clickScheduledPaymentsTab() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(ScheduledPayments));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", ScheduledPayments);


	}
	
	public void clickDaysColumn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(DaysColumn));
		DaysColumn.click();
	}
	
	public void clickAmountColumn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(amountColumn));
		amountColumn.click();
	}

	public void clickPayerColumn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(payerColumn));
		payerColumn.click();
	}
	
	public void clickLoadIDColumn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(loadIDColumn));
		loadIDColumn.click();
	}

	public void clickInvoiceColumn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(InvoiceColumn));
		InvoiceColumn.click();
	}
	
	public void clickloadIDColumn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(loadIDColumn));
		loadIDColumn.click();
	}
	
	public void clickschdateColumn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(schdateColumn));
		schdateColumn.click();
	}
	
	public void enterSearchText(String searchText) throws InterruptedException {
		searchInputField.clear();
		searchInputField.sendKeys(searchText);
	}
	
	public void clickSearchButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(searchButton));
		searchButton.click();
	}
	
	public void clickFirstRow() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(expandCollapseFirstRow));
		expandCollapseFirstRow.click();
	}
	
	public List<String> getFirstRowData() throws InterruptedException{
		Thread.sleep(2000);
		List<String> dataElements = new ArrayList<String>();
		List <WebElement> webElements = driver.findElements(By.xpath("//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div"));
		
		for(WebElement element : webElements) {

			dataElements.add(element.getText());
		}

		return dataElements;
	}

}

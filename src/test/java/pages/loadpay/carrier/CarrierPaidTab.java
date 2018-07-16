package pages.loadpay.carrier;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class CarrierPaidTab extends TestBase {

	WebDriverWait wait = null;
	Actions act = null;

	@FindBy(xpath = "//a[@href='#/Payments/Paid']")
	public WebElement paidTab;
	
	@FindBy(xpath = "//a[@href='#/Payments/ScheduledPayments']")
	public WebElement schedulePaymentsTab;
	
	@FindBy(xpath = "//a[@href='#/Payments/PayMeNow']")
	public WebElement payMeNowTab;
	
	@FindBy(xpath = "//*[@class='row ng-scope']//child::a[contains(text(),'Status')]")
	public WebElement statusColumn;
	
	@FindBy(xpath = "//*[@class='row ng-scope']//child::a[contains(text(),'Amount')]")
	public WebElement amountColumn;
	
	@FindBy(xpath = "//*[@class='row ng-scope']//child::a[contains(text(),'Payer')]")
	public WebElement payerColumn;
	
	@FindBy(xpath = "//*[@class='row ng-scope']//child::a[contains(text(),'Load ID')]")
	public WebElement loadIDColumn;
	
	@FindBy(id = "searchKeyword")
	public WebElement searchInputField;
	
	@FindBy(xpath = "//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[1]/div/input[2]")
	public WebElement searchButton;
	
	@FindBy(xpath = "//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]")
	public WebElement expandCollapseFirstRow;

	public CarrierPaidTab() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		act = new Actions(driver);
	}

	public void clickPaidTab() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(paidTab));
		Thread.sleep(2000);
		paidTab.click();
	}
	
	public void clickStatusColumn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(statusColumn));
		statusColumn.click();
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
	
	public Integer getRowCount() throws InterruptedException{
		Thread.sleep(2000);
		WebElement recordCount = driver.findElement(By.xpath("//*[@id='angularScope']/div[2]/div/div[3]/ul/li[3]/a/div/div[1]/div[1]/span[1]"));
		
		log.info("String Record count: " + recordCount.getText());
		Integer intRowCount = Integer.parseInt(recordCount.getText());
		log.info("Integer Record count: " + intRowCount);
		
		return intRowCount;
	}
}

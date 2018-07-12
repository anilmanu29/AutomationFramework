package pages.loadpay.broker;

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

import base.TestBase;

public class BrokerProcessedTab extends TestBase {

	WebDriverWait wait = null;
	Actions act = null;
	JavascriptExecutor js;

	@FindBy(xpath = "//a[@href='#/Payments/Processed']")
	private WebElement processedtab;
	
	@FindBy(xpath = "//*[@class='row ng-scope']//child::a[contains(text(),'Pay Selection')]")
	public WebElement paySelectionColumn;
	
	@FindBy(xpath = "//*[@class='row ng-scope']//child::a[contains(text(),'Discount ')]")
	public WebElement discountcolumn;
	
	@FindBy(xpath = "//*[@class='row ng-scope']//child::a[contains(text(),'Pulled From Bank Date')]")
	public WebElement pulledfrombankdataColumn;
	
	@FindBy(xpath = "//*[@class='row ng-scope']//child::a[contains(text(),'Paid to Carrier')]")
	public WebElement paidtocarrierDColumn;
	
	@FindBy(xpath = "//*[@class='row ng-scope']//child::a[contains(text(),'Amount Pulled')]")
	public WebElement amountpulledColumn;
	
	@FindBy(xpath = "//*[@class='row ng-scope']//child::a[contains(text(),'Amount Paid to Carrier')]")
	public WebElement amountpaidtocarrierColumn;
	
	@FindBy(xpath = "//*[@class='row ng-scope']//child::a[text()='Carrier ']")
	public WebElement carriercolumn;
	
	@FindBy(xpath = "//*[@class='row ng-scope']//child::a[text()='Load ID ']")
	public WebElement loadidColumn;

	@FindBy(id = "searchKeyword")
	public WebElement searchInputField;
	
	@FindBy(xpath = "//input[@value='Search']")
	public WebElement searchButton;
	
	@FindBy(xpath = "//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]")
    private WebElement expandCollapseFirstRow;
	
	@FindBy(xpath = "//*[@aria-expanded='true']//child::div[6]/span")
	public WebElement invoice;
	
	@FindBy(xpath = "//*[@aria-expanded='true']//child::div[4]//child::div[1]/span")
	public WebElement companyname;
	
	@FindBy(xpath = "//*[@aria-expanded='true']//child::div[5]//child::div[1]/span")
	public WebElement loadid;
	
	@FindBy(xpath = "//div[@aria-expanded='true']/div[3]/div[2]/div")
	public WebElement amount;
	
	@FindBy(xpath = "//div[@aria-expanded='true']/div[3]/div[1]/div")
	public WebElement date;
	


	public BrokerProcessedTab() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		act = new Actions(driver);
		js = (JavascriptExecutor) driver;
	}

	public void clickProcessedTab() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(processedtab));
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", processedtab);
		Thread.sleep(2000);
	}

	public void clickPaySelectionColumn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(paySelectionColumn));
		js.executeScript("arguments[0].click();", paySelectionColumn);
	}
	
	public void clickDiscountColumn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(discountcolumn));
		js.executeScript("arguments[0].click();", discountcolumn);
	}

	public void clickPulledfromBankdataColumnColumn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(pulledfrombankdataColumn));
		js.executeScript("arguments[0].click();", pulledfrombankdataColumn);

	}

	public void clickPaidToCarrierColumn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(paidtocarrierDColumn));
		js.executeScript("arguments[0].click();", paidtocarrierDColumn);
	
	}
	public void clickAmountPulledColumn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(amountpulledColumn));
		js.executeScript("arguments[0].click();", amountpulledColumn);
	
	}
	
	public void clickAmountpaidToCarrierColumn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(amountpaidtocarrierColumn));
		js.executeScript("arguments[0].click();", amountpaidtocarrierColumn);
	
	}
	
	public void clickCarrierColumn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(carriercolumn));
		js.executeScript("arguments[0].click();", carriercolumn);
	
	}
	
	public void clickLoadIDColumn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(loadidColumn));
		js.executeScript("arguments[0].click();", loadidColumn);
	}
	
	public void enterSearchText(String searchText) throws InterruptedException {
		searchInputField.clear();
		Thread.sleep(1000);
		searchInputField.sendKeys(searchText);
	}
	
	public void clickSearchButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(searchButton));
		js.executeScript("arguments[0].click();", searchButton);
	}
	
	public void clickFirstRow() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(expandCollapseFirstRow));
		js.executeScript("arguments[0].click();", expandCollapseFirstRow);
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

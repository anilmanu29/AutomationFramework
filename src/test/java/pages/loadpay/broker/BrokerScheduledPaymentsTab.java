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


public class BrokerScheduledPaymentsTab extends TestBase {
	WebDriverWait wait = null;
	Actions act = null;
	JavascriptExecutor js;
	
	
	@FindBy(xpath = "//a[contains(text(),'Payment Type')]")
	public WebElement click_paymenType;
	
	@FindBy(xpath = "//a[contains(text(),'Anticipated Pull Date')]")
	public WebElement click_pulldate;
	
	@FindBy(xpath = "//a[contains(text(),'Pay To Date')]")
	public WebElement click_PayToDate;
	
	@FindBy(xpath = "//a[contains(text(),'Amount')]")
	public WebElement click_Amount;
	
	@FindBy(xpath = "//a[@ng-click='SortBy(field)']")
	public WebElement click_Carrier;
	
	@FindBy(xpath = "//a[contains(text(),'Invoice #')]")
	public WebElement click_InvoiceID;
	
	@FindBy(xpath = "//a[contains(text(),'Load ID')]")
	public WebElement Click_LoadID;
	
	@FindBy(id = "searchKeyword")
	public WebElement searchInputField;
	
	@FindBy(xpath = "//input[@value='Search']")
	public WebElement searchButton;
	
	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]/div/div[3]")
	private WebElement expandCollapseFirstRow;
	
	@FindBy(xpath = "//*[@aria-expanded='true']//child::div[6]/span")
	public WebElement invoice;

	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[9]/div/div[1]/div/div[4]/div/span")
	public WebElement CompanyName;
	
	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]/div/div[3]")
	public WebElement Amount;
	
	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[9]/div/div[1]/div/div[2]/div/div[2]")
	public WebElement PayToDate;
	
	@FindBy(xpath = "//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]/div/div[2]/div/div[1]/i")
	public WebElement AnticipatedPullDate;
	
	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]/div/div[5]")
	public WebElement invoiceNum;


	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]/div/div[6]")
	public WebElement LoadIDNum;
	
	
public BrokerScheduledPaymentsTab() {
	PageFactory.initElements(driver, this);
	wait = new WebDriverWait(driver, 30);
	act = new Actions(driver);
	js = (JavascriptExecutor) driver; 
}

public void clickpaymenTypeLink() throws InterruptedException {
	wait.until(ExpectedConditions.elementToBeClickable(click_paymenType));
	Thread.sleep(2000);
	click_paymenType.click();
	
}


public void clickAnticipatedPullDate() throws InterruptedException {
	wait.until(ExpectedConditions.elementToBeClickable(click_pulldate));
	click_pulldate.click();
}

public void clickPayToDate() throws InterruptedException {
	wait.until(ExpectedConditions.elementToBeClickable(click_PayToDate));
	click_PayToDate.click();
}

public void clickAmount() throws InterruptedException {
	wait.until(ExpectedConditions.elementToBeClickable(click_Amount));
	click_Amount.click();
}

public void clickCarrier() throws InterruptedException {
	wait.until(ExpectedConditions.elementToBeClickable(click_Carrier));
	click_Carrier.click();
}

public void clickinvoice() throws InterruptedException {
	wait.until(ExpectedConditions.elementToBeClickable(click_InvoiceID));
	click_InvoiceID.click();
}

public void clickLoadID() throws InterruptedException {
	wait.until(ExpectedConditions.elementToBeClickable(Click_LoadID));
	Click_LoadID.click();
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


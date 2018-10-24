package pages.loadpay.broker;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.TestBase;

public class BrokerProcessedTab extends TestBase {

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
	}

	public void clickProcessedTab() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(processedtab));
		processedtab.click();
		wait.until(ExpectedConditions.elementToBeClickable(paySelectionColumn));
	}

	public void clickPaySelectionColumn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(paySelectionColumn));
		paySelectionColumn.click();
	}

	public void clickDiscountColumn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(discountcolumn));
		discountcolumn.click();
	}

	public void clickPulledfromBankdataColumnColumn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(pulledfrombankdataColumn));
		pulledfrombankdataColumn.click();

	}

	public void clickPaidToCarrierColumn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(paidtocarrierDColumn));
		paidtocarrierDColumn.click();

	}

	public void clickAmountPulledColumn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(amountpulledColumn));
		amountpulledColumn.click();

	}

	public void clickAmountpaidToCarrierColumn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(amountpaidtocarrierColumn));
		amountpaidtocarrierColumn.click();

	}

	public void clickCarrierColumn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(carriercolumn));
		carriercolumn.click();

	}

	public void clickLoadIDColumn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(loadidColumn));
		loadidColumn.click();
	}

	public void enterSearchText(String searchText) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(searchInputField));
		searchInputField.click();
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

	public List<String> getFirstRowData() throws InterruptedException {

		List<String> dataElements = new ArrayList<String>();
		List<WebElement> webElements = driver.findElements(
				By.xpath("//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div"));

		for (WebElement element : webElements) {

			dataElements.add(element.getText());
		}

		return dataElements;
	}

}

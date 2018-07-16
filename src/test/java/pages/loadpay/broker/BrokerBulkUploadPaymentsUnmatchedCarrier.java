package pages.loadpay.broker;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
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

public class BrokerBulkUploadPaymentsUnmatchedCarrier extends TestBase
{
	String  payment_status = "Verified";
	String payment_statuss = "Unmatched:";
	String load;
	String    scheduledamount;
	public static float totalamounnt;
	String schedule;
	String scheduleamt;
	WebDriverWait wait = null;
	Actions act = null;
	JavascriptExecutor js;
	
	
	@FindBy(xpath = "//a[text()='New Payment']")
	public WebElement lnk_newpayment;
	
	@FindBy(xpath = "//div[@class='total ng-scope notlast col-sm-6']//child::span[1]")
	public WebElement tab_shedulepayment;

	@FindBy(xpath = "//div[@class='total ng-scope']//child::span[1]")
	public WebElement creditvalue;

	@FindBy(xpath = ".//*[@id='dropzoneForm']")
	public WebElement link_Upload;

	
	@FindBy(xpath="//*[@class='carrierPayment ng-scope']/div/div[5]/div")
	List< WebElement> List_payment;
	
	/*@FindBy(name ="File Upload")
	private WebElement path_Upload;*/
	
	
	@FindBy(xpath="//input[contains(@value,'Import')]")
	public WebElement btn_import;
	
	@FindBy(xpath=".//*[@id='angularScope']/div[2]/div/div[3]/ul/li[2]/a")
	public WebElement link_schpaymnt;
	
	@FindBy(xpath=".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]")
	public WebElement link_griddown;
	
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
	
	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]")
	private WebElement expandCollapseFirstRow;
	
	@FindBy(xpath = "//*[@aria-expanded='true']//child::div[6]/span")
	public WebElement invoice;

	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[9]/div/div[1]/div/div[4]/div/span")
	public WebElement CompanyName;
	
	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]/div/div[3]")
	public WebElement Amount;
	
	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]/div/div[2]/div/div[2]")
	public WebElement PayToDate;
	
	@FindBy(xpath = "//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]/div/div[2]/div/div[1]/i")
	public WebElement AnticipatedPullDate;
	
	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]/div/div[5]")
	public WebElement invoiceNum;


	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]/div/div[6]")
	public WebElement LoadIDNum;
	
	
	
	/*-------PageFactory---------*/
	
	public BrokerBulkUploadPaymentsUnmatchedCarrier()
	{
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		act = new Actions(driver);
		js = (JavascriptExecutor) driver; 
	}
	
	
	
	/*-------New Payment---------*/
	public void newPayment() throws InterruptedException
	{
		lnk_newpayment.click();
		//Thread.sleep(2000);
	}
	

	
	
	public void ClickUpload() throws InterruptedException, IOException
	{
		link_Upload.click();
		Thread.sleep(2000);
		
	}
	
	
	
	/*	
	public void SelectUploadFile() throws InterruptedException, IOException
	{
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"/Drivers/autoit-v3-setup.exe");
		path_Upload.sendKeys("C:\\Users\\anilkumarb\\Music\\LoadPayBulkTemplatenew.csv");
		Runtime.getRuntime().exec("C:\\Users\\anilkumarb\\Desktop\\AutoIT\\FileUpload.exe");
		
		
		Thread.sleep(2000);
		
	}*/
	
	public void UploadFile() throws IOException, InterruptedException, AWTException
	{
		
		// Specify the file location with extension
		 StringSelection sel = new StringSelection("C:\\AUTOMATION\\SELENIUM\\_Project\\testing\\SeleniumAutomation\\LoadPay-Truckstop\\SeleniumAutomation\\UnmatchedCarrier\\LoadPayBulkTemplate.csv");
		 		
		
		   // Copy to clipboard
		 Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel,null);
		 log.info("selection" +sel);
		 
		 link_Upload.click();

		// Create object of Robot class
		 Robot robot = new Robot();
		 Thread.sleep(1000);
		      
		  // Press Enter
		 robot.keyPress(KeyEvent.VK_ENTER);

		// Release Enter
		 robot.keyRelease(KeyEvent.VK_ENTER);

		  // Press CTRL+V
		 robot.keyPress(KeyEvent.VK_CONTROL);
		 robot.keyPress(KeyEvent.VK_V);

		// Release CTRL+V
		 robot.keyRelease(KeyEvent.VK_CONTROL);
		 robot.keyRelease(KeyEvent.VK_V);
		 
		  // Press Enter
		 robot.keyPress(KeyEvent.VK_ENTER);

		// Release Enter
		 robot.keyRelease(KeyEvent.VK_ENTER);
		 Thread.sleep(1000);

		
		
		
		/*
		driver.findElement(By.xpath(".//*[@id='dropzoneForm']/div/div")).click();
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\AutoItScripts\\FileUpload.exe");
		//logger.debug("Bulk uploadfiles");
*/		
		
	}
	
	public void Clickimport() throws InterruptedException, IOException
	{
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btn_import);
		
	}

	public void Clickschpayment() throws InterruptedException, IOException
	{
		link_schpaymnt.click();
		Thread.sleep(2000);
		
	}
	
	
	public void ClickGridDown() throws InterruptedException, IOException
	{
		link_griddown.click();
		Thread.sleep(2000);
		
	}
	
	public void clickAnticipatedPullDate() throws InterruptedException {
		
		click_pulldate.click();
	}

	public void clickPayToDate() throws InterruptedException {
		
		click_PayToDate.click();
	}

	public void clickAmount() throws InterruptedException {
		
		click_Amount.click();
	}

	public void clickCarrier() throws InterruptedException {
		
		click_Carrier.click();
	}

	public void clickinvoice() throws InterruptedException {
		
		click_InvoiceID.click();
	}

	public void clickLoadID() throws InterruptedException {
		
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

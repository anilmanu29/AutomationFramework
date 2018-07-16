package pages.loadpay.broker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;
import testcases.loadpay.broker.BrokerBankAccountUpdateTest;


public class BrokerBankAccountUpdate extends TestBase {
	WebDriverWait wait = null;
	JavascriptExecutor js;
	ArrayList<String> tabs;
	String amount;
	String pennyamount;
	String pennyamountt;
	int j=0;
	String pennyamt;
	String pennyamounts;
	String pennyamoun;

	@FindBy(xpath = "//a[text()='Account']")
	private WebElement lnk_account;

	@FindBy(xpath = "//a[contains(text(),'Banking')]")
	private WebElement lnk_banking;

	@FindBy(xpath = "//div[@class='well myaccount ng-scope in collapse']//child::div[1]//child::a[1]")
	private WebElement lnk_addnewbankaccount;

	@FindBy(id = "NameOnAccount")
	private WebElement field_accountname;

	@FindBy(id = "BankingRouting")
	private WebElement field_routnumber;

	@FindBy(id = "BankingAccount")
	private WebElement field_accnum;

	@FindBy(id = "ConfirmBankingAccount")
	private WebElement field_confirmaccnum;

	@FindBy(xpath = "//form[@id='formBanking']//child::input[@value='Save']")
	private WebElement button_save;

	@FindBy(xpath = "//button[text()='Set default']")
	private WebElement button_satdefault;

	@FindBy(xpath = "//button[text()='Set default']//preceding::p[7]/b")
	private WebElement accname;

	@FindBy(xpath = "//button[text()='Set default']//preceding::p[6]/b")
	private WebElement routingnum;

	@FindBy(xpath = "//button[text()='Remove']")
	private  List<WebElement> button_remove;

	@FindBy(xpath = "//a[@class='btn btn-link menuOption'][contains(text(),'Banking')]")
	private WebElement lnk_adminbanking;

	@FindBy(xpath = "//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[2]/div[2]/div/div/div[1]/div[1]/div/div/p[9]/span/b")
	private WebElement pennyverificationamt;

	@FindBy(xpath = "//a[text()='Verify']")
	private WebElement lnk_verify;

	@FindBy(xpath = "//a[text()='Verify']//following::input[@type='text'][1]")
	private WebElement field_amount;

	@FindBy(xpath = "//a[text()='Verify']//following::input[@type='text'][1]//following::input[@value='Verify'][1]")
	private WebElement btn_verify;

	@FindBy(xpath = "//*[text()='Banking Information']//following::div[@class='well']/p[1]/b")
	private List<WebElement> banking;

	@FindBy(xpath = "//button[text()='Remove']//preceding::b[3]")
	private List<WebElement> lists;
	
	@FindBy(xpath = "//input[@value='checking']")
	private WebElement radiobtn_personalchecking;

	@FindBy(xpath = "//input[@value='savings']")
	private WebElement radiobtn_personalsaving;


	public BrokerBankAccountUpdate() throws IOException {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		js = (JavascriptExecutor) driver;
	}

	public void clickAccountlink() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(lnk_account));
		js.executeScript("arguments[0].click();", lnk_account);
	}

	public void clickBankingLink() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(lnk_banking));
		js.executeScript("arguments[0].click();", lnk_banking);
	}

	public void clickAddNewBankAccountLink() throws InterruptedException {
		// Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(lnk_addnewbankaccount));
		js.executeScript("arguments[0].click();", lnk_addnewbankaccount);
	}

	public String enterAccountName(String accname) throws InterruptedException {
		// Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(field_accountname));
		field_accountname.sendKeys(accname);
		return accname;
	}

	public String enterRoutingNumber(String routingnum) throws InterruptedException {
		// Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(field_routnumber));
		field_routnumber.sendKeys(routingnum);
		return routingnum;
	}

	public void enterAccountNumber(String accnum) throws InterruptedException {
		// Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(field_accnum));
		field_accnum.sendKeys(accnum);
	}

	public void enterConfirmAccountNumber(String confirmaccnum) throws InterruptedException {
		// Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(field_confirmaccnum));
		field_confirmaccnum.sendKeys(confirmaccnum);
	}
	
	public void clickPersonalCheckingRadioButton() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(radiobtn_personalchecking));
		js.executeScript("arguments[0].click();", radiobtn_personalchecking);
	}

	public void clickPersonalSavinggRadioButton() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(radiobtn_personalsaving));
		js.executeScript("arguments[0].click();", radiobtn_personalsaving);
	}

	public void clickSaveButton() throws InterruptedException {
		// Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(button_save));
		js.executeScript("arguments[0].click();", button_save);
		Thread.sleep(1000);
	}

	public String verifyAccountName() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(accname));
		return accname.getText();
	}

	public String verifyRoutingNumber() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(routingnum));
		return routingnum.getText();
	}


	public void clickRemoveButton() throws InterruptedException {
		Thread.sleep(1000);
		for (int i = 0; i < lists.size(); i++) {
			if (lists.get(i).getText().equalsIgnoreCase(BrokerBankAccountUpdateTest.routingnumber)) {
				Thread.sleep(1000);
				log.info(BrokerBankAccountUpdateTest.routingnumber);
				Thread.sleep(1000);
				 j=i;
				
				break;
			}
		}
			Thread.sleep(1000);
			button_remove.get(j).click();
			wait.until(ExpectedConditions.alertIsPresent());
			Thread.sleep(1000);
			driver.switchTo().alert().accept();			
		
	}
}

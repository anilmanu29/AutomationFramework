package pages.loadpay.broker;

import java.awt.AWTException;
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
import pages.loadpay.admin.AdminHomePage;
import testcases.loadpay.broker.BrokerBankingTest;

public class BrokerBanking extends TestBase {
	WebDriverWait wait = null;
	JavascriptExecutor js;
	AdminHomePage ahp;
	ArrayList<String> tabs;
	String amount;
	String pennyamount;
	String pennyamountt;
	int j;
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
	private WebElement button_remove;

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

	@FindBy(xpath = ".//*[text()='Banking Information']//following::div[@class='well']/p[1]/b")
	private List<WebElement> banking;

	@FindBy(xpath = "//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[2]/div[2]/div/div/div[1]/div/div/div/p[9]/span")
	private List<WebElement> lists;

	@FindBy(xpath = "//input[@value='checking']")
	private WebElement radiobtn_personalchecking;

	@FindBy(xpath = "//input[@value='savings']")
	private WebElement radiobtn_personalsaving;

	public BrokerBanking() throws IOException {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		js = (JavascriptExecutor) driver;
		ahp = new AdminHomePage();
	}

	public void clickAccountlink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(lnk_account));
		lnk_account.click();
	}

	public void clickBankingLink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(lnk_banking));
		lnk_banking.click();
	}

	public void clickAddNewBankAccountLink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(lnk_addnewbankaccount));
		lnk_addnewbankaccount.click();
	}

	public String enterAccountName(String accname) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(field_accountname));
		field_accountname.click();
		field_accountname.clear();
		field_accountname.sendKeys(accname);
		return accname;
	}

	public void enterRoutingNumber(String routingnum) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(field_routnumber));
		field_routnumber.click();
		field_routnumber.clear();
		field_routnumber.sendKeys(routingnum);
	}

	public void enterAccountNumber(String accnum) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(field_accnum));
		field_accnum.click();
		field_accnum.clear();
		field_accnum.sendKeys(accnum);
	}

	public void enterConfirmAccountNumber(String confirmaccnum) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(field_confirmaccnum));
		field_confirmaccnum.click();
		field_confirmaccnum.clear();
		field_confirmaccnum.sendKeys(confirmaccnum);
	}

	public void clickPersonalCheckingRadioButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(radiobtn_personalchecking));
		radiobtn_personalchecking.click();
	}

	public void clickPersonalSavinggRadioButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(radiobtn_personalsaving));
		radiobtn_personalsaving.click();
	}

	public void clickSaveButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(button_save));
		button_save.click();
	}

	public String verifyAccountName() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(accname));
		return accname.getText();
	}

	public String verifyRoutingNumber() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(routingnum));
		return routingnum.getText();
	}

	public void verifyBankAccount() throws InterruptedException, AWTException {
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("window.open();");
		Thread.sleep(5000);
		tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(1000);
		ahp.AdminURL();
	}

	public void clickAdminBankingLink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(lnk_adminbanking));
		lnk_adminbanking.click();
	}

	public String getPennyVerificationAmount() throws InterruptedException {
		Thread.sleep(1000);

		int count = banking.size();
		for (int i = 0; i < count; i++) {
			if (banking.get(i).getText().equalsIgnoreCase(BrokerBankingTest.acountname)) {
				log.info(BrokerBankingTest.acountname);

				j = i;
				break;
			}
		}

		log.info(lists.size());
		log.info(lists.get(j).getText());
		pennyamt = lists.get(j).getText();

		pennyamt.replaceAll("Penny Verification Amount:", "");
		log.info((pennyamt.length()));

		if (pennyamt.length() == 33) {
			pennyamoun = pennyamt.replaceAll("\\$", "");
			pennyamounts = pennyamoun.replace("0.", "");
		} else {
			pennyamounts = pennyamt.replaceAll("\\$", "");
		}

		return pennyamounts;

	}

	public void closeTab() throws InterruptedException {
		driver.close();
		Thread.sleep(1000);
		driver.switchTo().window(tabs.get(0));
	}

	public void clickVerifyLink() throws InterruptedException {
		js.executeScript("window.scrollBy(0,250)");
		wait.until(ExpectedConditions.elementToBeClickable(lnk_verify));
		lnk_verify.click();

	}

	public void enterAmount() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(field_amount));
		field_amount.click();
		field_amount.clear();
		field_amount.sendKeys(pennyamounts);
		log.info(pennyamounts);
	}

	public void clickVerifyButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btn_verify));
		btn_verify.click();
	}

	public void clickSetDefault() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(button_satdefault));
		button_satdefault.click();
		Thread.sleep(1000);

		wait.until(ExpectedConditions.alertIsPresent());
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
		driver.navigate().refresh();

		wait.until(ExpectedConditions.elementToBeClickable(lnk_banking));
		lnk_banking.click();

		wait.until(ExpectedConditions.elementToBeClickable(button_satdefault));
		button_satdefault.click();

		wait.until(ExpectedConditions.alertIsPresent());
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
		driver.navigate().refresh();
		Thread.sleep(1000);

		wait.until(ExpectedConditions.elementToBeClickable(lnk_banking));
		lnk_banking.click();
		Thread.sleep(1000);
	}

	public void clickRemoveButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(button_remove));
		button_remove.click();

		wait.until(ExpectedConditions.alertIsPresent());
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
	}

}

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

	// *[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[2]/div[2]/div/div/div[1]/div/div/div/p[9]/span

	public BrokerBanking() throws IOException {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		js = (JavascriptExecutor) driver;
		ahp = new AdminHomePage();
	}

	public void clickAccountlink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		wait.until(ExpectedConditions.elementToBeClickable(lnk_account));
		js.executeScript("arguments[0].click();", lnk_account);
	}

	public void clickBankingLink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		wait.until(ExpectedConditions.elementToBeClickable(lnk_banking));
		js.executeScript("arguments[0].click();", lnk_banking);
	}

	public void clickAddNewBankAccountLink() throws InterruptedException {
		// wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		wait.until(ExpectedConditions.elementToBeClickable(lnk_addnewbankaccount));
		js.executeScript("arguments[0].click();", lnk_addnewbankaccount);
	}

	public String enterAccountName(String accname) throws InterruptedException {
		// wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		wait.until(ExpectedConditions.elementToBeClickable(field_accountname));
		field_accountname.sendKeys(accname);
		return accname;
	}

	public void enterRoutingNumber(String routingnum) throws InterruptedException {
		// wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		wait.until(ExpectedConditions.elementToBeClickable(field_routnumber));
		field_routnumber.sendKeys(routingnum);
	}

	public void enterAccountNumber(String accnum) throws InterruptedException {
		// wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		wait.until(ExpectedConditions.elementToBeClickable(field_accnum));
		field_accnum.sendKeys(accnum);
	}

	public void enterConfirmAccountNumber(String confirmaccnum) throws InterruptedException {
		// wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		wait.until(ExpectedConditions.elementToBeClickable(field_confirmaccnum));
		field_confirmaccnum.sendKeys(confirmaccnum);
	}
	
	public void clickPersonalCheckingRadioButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		wait.until(ExpectedConditions.elementToBeClickable(radiobtn_personalchecking));
		js.executeScript("arguments[0].click();", radiobtn_personalchecking);
	}

	public void clickPersonalSavinggRadioButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		wait.until(ExpectedConditions.elementToBeClickable(radiobtn_personalsaving));
		js.executeScript("arguments[0].click();", radiobtn_personalsaving);
	}

	public void clickSaveButton() throws InterruptedException {
		// wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		wait.until(ExpectedConditions.elementToBeClickable(button_save));
		js.executeScript("arguments[0].click();", button_save);
	}

	public String verifyAccountName() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		wait.until(ExpectedConditions.elementToBeClickable(accname));
		return accname.getText();
	}

	public String verifyRoutingNumber() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		wait.until(ExpectedConditions.elementToBeClickable(routingnum));
		return routingnum.getText();
	}

	public void verifyBankAccount() throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		((JavascriptExecutor) driver).executeScript("window.open();");
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		ahp.AdminURL();
	}

	public void clickAdminBankingLink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		wait.until(ExpectedConditions.elementToBeClickable(lnk_adminbanking));
		js.executeScript("arguments[0].click();", lnk_adminbanking);
	}

	public String getPennyVerificationAmount() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		int count = banking.size();
		for (int i = 0; i < count; i++) {
			if (banking.get(i).getText().equalsIgnoreCase(BrokerBankingTest.acountname)) {
				log.info(BrokerBankingTest.acountname);
				
				j = i;
				break;
			}
		}

		// List<WebElement> lists =
		// driver.findElements(By.xpath("//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[2]/div[2]/div/div/div[1]/div/div/div/p[9]/span"));
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
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		driver.switchTo().window(tabs.get(0));
	}

	public void clickVerifyLink() throws InterruptedException {
		js.executeScript("window.scrollBy(0,250)");
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		wait.until(ExpectedConditions.elementToBeClickable(lnk_verify));
		js.executeScript("arguments[0].click();", lnk_verify);

	}

	public void enterAmount() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		wait.until(ExpectedConditions.elementToBeClickable(field_amount));
		js.executeScript("arguments[0].click();", field_amount);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		log.info(pennyamounts);
		field_amount.sendKeys(pennyamounts);
	}

	public void clickVerifyButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		wait.until(ExpectedConditions.elementToBeClickable(btn_verify));
		js.executeScript("arguments[0].click();", btn_verify);
	}

	public void clickSetDefault() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		wait.until(ExpectedConditions.elementToBeClickable(button_satdefault));
		js.executeScript("arguments[0].click();", button_satdefault);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		wait.until(ExpectedConditions.alertIsPresent());
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		driver.switchTo().alert().accept();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		wait.until(ExpectedConditions.elementToBeClickable(lnk_banking));
		js.executeScript("arguments[0].click();", lnk_banking);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		wait.until(ExpectedConditions.elementToBeClickable(button_satdefault));
		js.executeScript("arguments[0].click();", button_satdefault);
		wait.until(ExpectedConditions.alertIsPresent());
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		driver.switchTo().alert().accept();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		wait.until(ExpectedConditions.elementToBeClickable(lnk_banking));
		js.executeScript("arguments[0].click();", lnk_banking);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	}

	public void clickRemoveButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		// wait.until(ExpectedConditions.elementToBeClickable(button_remove));
		js.executeScript("arguments[0].click();", button_remove);
		wait.until(ExpectedConditions.alertIsPresent());
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		driver.switchTo().alert().accept();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	}

}

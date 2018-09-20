package pages.loadpay.carrier;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class CarrierBanking extends TestBase {
	WebDriverWait wait = null;
	JavascriptExecutor js;

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

	@FindBy(xpath = "//input[@value='checking']")
	private WebElement radiobtn_personalchecking;

	@FindBy(xpath = "//input[@value='savings']")
	private WebElement radiobtn_personalsaving;

	public CarrierBanking() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		js = (JavascriptExecutor) driver;
	}

	public void clickAccountlink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(lnk_account));
		js.executeScript("arguments[0].click();", lnk_account);
	}

	public void clickBankingLink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(lnk_banking));
		js.executeScript("arguments[0].click();", lnk_banking);
	}

	public void clickAddNewBankAccountLink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(lnk_addnewbankaccount));
		js.executeScript("arguments[0].click();", lnk_addnewbankaccount);
	}

	public String enterAccountName(String accname) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(field_accountname));
		field_accountname.sendKeys(accname);
		return accname;
	}

	public void enterRoutingNumber(String routingnum) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(field_routnumber));
		field_routnumber.sendKeys(routingnum);
		Thread.sleep(1000);
		field_routnumber.sendKeys(Keys.ENTER);
	}

	public void enterAccountNumber(String accnum) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(field_accnum));
		field_accnum.sendKeys(accnum);
	}

	public void enterConfirmAccountNumber(String confirmaccnum) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(field_confirmaccnum));
		field_confirmaccnum.sendKeys(confirmaccnum);
	}

	public void clickPersonalCheckingRadioButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(radiobtn_personalchecking));
		js.executeScript("arguments[0].click();", radiobtn_personalchecking);
	}

	public void clickPersonalSavinggRadioButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(radiobtn_personalsaving));
		js.executeScript("arguments[0].click();", radiobtn_personalsaving);
	}

	public void clickSaveButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(button_save));
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", button_save);
	}

	public String verifyAccountName() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(accname));
		return accname.getText();
	}

	public String verifyRoutingNumber() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(routingnum));
		return routingnum.getText();
	}

	public void clickSetDefault() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(button_satdefault));
		js.executeScript("arguments[0].click();", button_satdefault);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(lnk_banking));
		js.executeScript("arguments[0].click();", lnk_banking);
		wait.until(ExpectedConditions.elementToBeClickable(button_satdefault));
		js.executeScript("arguments[0].click();", button_satdefault);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
		driver.navigate().refresh();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(lnk_banking));
		js.executeScript("arguments[0].click();", lnk_banking);
	}

	public void clickRemoveButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(button_remove));
		js.executeScript("arguments[0].click();", button_remove);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
	}

}

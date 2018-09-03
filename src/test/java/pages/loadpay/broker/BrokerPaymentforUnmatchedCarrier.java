package pages.loadpay.broker;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class BrokerPaymentforUnmatchedCarrier extends TestBase {
	String payment_status = "Verified";
	String payment_statuss = "Unmatched:";
	String load;
	String scheduledamount;

	@FindBy(xpath = "//a[text()='New Payment']")
	private WebElement lnk_newpayment;

	@FindBy(xpath = "//div[@class='total ng-scope notlast col-sm-6']//child::span[1]")
	private WebElement tab_shedulepayment;

	@FindBy(xpath = "//div[@class='total ng-scope']//child::span[1]")
	private WebElement creditvalue;

	@FindBy(id = "CarrierEmail")
	private WebElement field_carrieremail;

	@FindBy(id = "PaymentAmount")
	private WebElement field_amount;

	@FindBy(id = "InvoiceNumber")
	public WebElement field_invoicenum;

	@FindBy(id = "LoadNumber")
	private static WebElement field_loadid;

	@FindBy(xpath = "//input[@type='checkbox']")
	private WebElement checkbox_advance;

	@FindBy(xpath = "//input[@value='Schedule']")
	private WebElement button_schedule;

	@FindBy(id = "UserName")
	private WebElement field_username;

	@FindBy(id = "Password")
	private WebElement field_password;

	@FindBy(xpath = "//input[@value='Login']")
	private WebElement button_login;

	@FindBy(id = "searchKeyword")
	private WebElement field_searchbox;

	@FindBy(xpath = "//input[@value='Search']")
	public WebElement btn_search;

	@FindBy(xpath = "//button[@type='button'][text()='OK']")
	private WebElement popup_ok;

	// @FindBy(xpath = "//div[@class='col-md-10 content']//child::span[1]")
	// private WebElement paymentstatus;

	@FindBy(xpath = "//div[contains(@class,'carrierPayment ')]//child::span[@class='ng-scope']")
	private WebElement paymentstatus;

	@FindBy(xpath = "//*[@id=\"angularScope\"]/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]/div/div[5]/div/i")
	private WebElement row;

	@FindBy(xpath = ".//*[@id='searchKeyword']//following::div[@class='col-md-2 content ng-binding'][1]")
	private WebElement amount;

	@FindBy(xpath = "//div[@class='carrierPayment ng-scope']//child::div[5]/div/span")
	private static WebElement invoicenumber;

	@FindBy(xpath = "//div[@class='carrierPayment ng-scope']//child::div[6]/div/span")
	private static WebElement loadidd;

	@FindBy(xpath = "//*[text()='remove_circle']")
	private WebElement btn_cancelpayment;

	@FindBy(xpath = "//a[text()='Logoff']")
	private WebElement btn_logout;

	@FindBy(xpath = "//*[@class='carrierPayment ng-scope']/div/div[5]/div")
	List<WebElement> List_payment;

	@FindBy(id = "CompanyName")
	private WebElement companyname;

	/*-------PageFactory---------*/

	public BrokerPaymentforUnmatchedCarrier() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}

	/*-------New Payment---------*/
	public void newPayment() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(lnk_newpayment));
		lnk_newpayment.click();
	}

	/*-------Carrier email---------*/
	public void carrierEmail(String cemail) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(field_carrieremail));
		field_carrieremail.clear();
		field_carrieremail.click();
		field_carrieremail.sendKeys(cemail);
	}

	/*-------Amount---------*/
	public String amount(String amt) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(field_amount));
		field_amount.sendKeys(amt);
		return amt;
	}

	/*-------Invoicenumber---------*/
	public String invoiceNumber(String invoiceno) {
		wait.until(ExpectedConditions.elementToBeClickable(field_invoicenum));
		field_invoicenum.sendKeys(invoiceno);
		return invoiceno;
	}

	/*-------load id---------*/
	public String loadId(String loadid) {
		wait.until(ExpectedConditions.elementToBeClickable(field_loadid));
		field_loadid.sendKeys(loadid);
		return loadid;
	}

	public void companyName(String payto) {
		wait.until(ExpectedConditions.elementToBeClickable(companyname));
		companyname.sendKeys(payto);

	}

	/*-------advance checkbox---------*/
	public void advanceCheckbox() {
		wait.until(ExpectedConditions.elementToBeClickable(checkbox_advance));
		checkbox_advance.click();
	}

	/*-------schedule paymet---------*/
	public void clickShedulePayment() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(button_schedule));
		button_schedule.click();
	}

	/*-------schedule payment tab---------*/
	public void clickShedulePaymenttab() {
		wait.until(ExpectedConditions.elementToBeClickable(tab_shedulepayment));
		tab_shedulepayment.click();
	}

	/*-------Search Carrier---------*/
	public String searchCarrier(String cemail) {
		wait.until(ExpectedConditions.elementToBeClickable(field_searchbox));
		field_searchbox.sendKeys(cemail);
		return cemail;
	}

	/*-------Search button---------*/
	public void clickSearchButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btn_search));
		btn_search.click();
	}

	/*-------verify payment---------*/
	public String verifyPaymentStatus() {
		String actualpaystatus = paymentstatus.getText();
		return actualpaystatus;

	}

	/*-------verify amount---------*/
	public String verifyscheduledPayment() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(paymentstatus));
		paymentstatus.click();
		String scheduledamount = amount.getText();
		log.info(scheduledamount);
		return scheduledamount;
	}
	/*-------verify invoicenumber---------*/

	public void verifyInvoiceNumber(String invoicenum, String amt) throws InterruptedException {

		Thread.sleep(2000);
		List<WebElement> invoicenumcount = List_payment;
		log.info("Total Sceduled Payments:" + invoicenumcount.size());

		for (int i = 0; i < invoicenumcount.size(); i++) {
			String invoice = invoicenumcount.get(i).getText();

			if (invoice.contains(invoicenum)) {

				log.info(invoice);
				invoicenumcount.get(i).click();

				wait.until(ExpectedConditions.elementToBeClickable(amount));
				scheduledamount = amount.getText();

				if (scheduledamount.contains(amt)) {
					log.info("Amount is :" + "" + scheduledamount);
				}

				break;
			}
		}
	}

	/*-------verify loadid---------*/

	public String verifyLoadId() {
		String load = loadidd.getText();
		log.info(load);
		return load;

	}

	public String getEin(String ein) {
		return ein;

	}

}

package pages.loadpay.broker;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.TestBase;

public class ShipperAdvancePayment extends TestBase {
	String payment_status = "Verified";
	String payment_statuss = "Unmatched:";
	String load;
	String scheduledate;
	public static float totalamounnt;
	String schedule;
	String scheduleamt;

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
	private WebElement field_invoicenum;

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
	private WebElement btn_search;

	@FindBy(xpath = "//button[@type='button'][text()='OK']")
	private WebElement popup_ok;

	@FindBy(xpath = "//div[contains(@class,'carrierPayment ')]//child::span[@class='ng-scope']")
	private WebElement paymentstatus;

	@FindBy(xpath = "//*[@id=\"angularScope\"]/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]/div/div[5]/div/i")
	private WebElement row;

	@FindBy(xpath = "//*[@id='searchKeyword']//following::div[@class='col-md-2 content ng-binding'][1]")
	private WebElement amount;

	@FindBy(xpath = "//div[@class='carrierPayment ng-scope']//child::div[5]/div/span")
	private static WebElement invoicenumber;

	@FindBy(xpath = "//div[@class='carrierPayment ng-scope']//child::div[6]/div/span")
	private static WebElement loadidd;

	@FindBy(xpath = "//*[text()='remove_circle']")
	private WebElement btn_cancelpayment;

	@FindBy(xpath = "//a[text()='Logoff']")
	private WebElement btn_logout;

	@FindBy(xpath = "//div[@class='carrierPayment ng-scope']//following::div/div[6]/div/span")
	List<WebElement> loadids;

	@FindBy(id = "PaymentDate")
	private WebElement fieldpaymentdate;

	@FindBy(xpath = "//div[@aria-expanded='true']//child::div[7]/div/span")
	private WebElement expectedscheduldate;

	/*-------PageFactory---------*/

	public ShipperAdvancePayment() {
		PageFactory.initElements(driver, this);
	}

	/*-------New Payment---------*/
	public void newPayment() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(lnk_newpayment));
		lnk_newpayment.click();
	}

	/*-------Carrier email---------*/
	public void carrierEmail(String cemail) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(field_carrieremail));
		field_carrieremail.click();
		field_carrieremail.sendKeys(cemail);
	}

	/*-------Amount---------*/
	public String amount(String amt) {
		wait.until(ExpectedConditions.elementToBeClickable(field_amount));
		field_amount.clear();
		field_amount.sendKeys(amt);
		return amt;
	}

	/*-------Invoicenumber---------*/
	public String invoiceNumber(String invoiceno) {
		wait.until(ExpectedConditions.elementToBeClickable(field_invoicenum));
		field_invoicenum.clear();
		field_invoicenum.sendKeys(invoiceno);
		return invoiceno;
	}

	/*-------load id---------*/
	public String loadId(String loadid) {
		wait.until(ExpectedConditions.elementToBeClickable(field_loadid));
		field_loadid.clear();
		field_loadid.sendKeys(loadid);
		return loadid;
	}

	/*-------advance checkbox---------*/
	public void advanceCheckbox() {
		wait.until(ExpectedConditions.elementToBeClickable(checkbox_advance));
		checkbox_advance.click();
	}

	public String getPaymentDate() {
		String date = fieldpaymentdate.getAttribute("value");
		return date;
	}

	/*-------schedule paymet---------*/
	public void clickShedulePayment() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(button_schedule));
		button_schedule.click();
	}

	/*-------schedule payment tab---------*/
	public void clickShedulePaymenttab() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tab_shedulepayment));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", tab_shedulepayment);
	}

	/*-------Search Carrier---------*/
	public void searchCarrier(String cemail) {
		wait.until(ExpectedConditions.elementToBeClickable(field_searchbox));
		field_searchbox.clear();
		field_searchbox.sendKeys(cemail);
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
	public float verifyscheduledPayment() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(paymentstatus));
		paymentstatus.click();
		String scheduledamount = amount.getText();
		schedule = scheduledamount.replaceAll("\\$", "");
		scheduleamt = schedule.replaceAll(",", "");
		totalamounnt = Float.parseFloat(scheduleamt);
		log.info(totalamounnt);

		log.info(scheduledamount);
		return totalamounnt;
	}
	/*-------verify invoicenumber---------*/

	public void verifyScheduledDate(String paymentdate, String loadidd) throws InterruptedException {

		List<WebElement> loadiidscount = loadids;

		log.info("Total loadids :" + loadiidscount.size());

		for (int i = 0; i < loadiidscount.size(); i++)

		{
			String expectedloadid = loadids.get(i).getText();

			if (expectedloadid.equalsIgnoreCase(loadidd))

			{
				log.info(expectedloadid);
				loadids.get(i).click();
				wait.until(ExpectedConditions.elementToBeClickable(expectedscheduldate));
				scheduledate = expectedscheduldate.getText();
				// Assert.assertEquals(scheduledate, paymentdate);

				if (scheduledate.equalsIgnoreCase(paymentdate))

				{
					log.info("Date is :" + "" + scheduledate);

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

	public void logout() {
		wait.until(ExpectedConditions.elementToBeClickable(btn_logout));
		btn_logout.click();
	}

}

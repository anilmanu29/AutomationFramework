package pages.loadpay.carrier;

import java.awt.AWTException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class CarrierNextDAYACH extends TestBase {

	WebDriverWait wait = null;
	Actions act = null;
	String totalamt;
	String amtnextdayach;
	String amtbeforepaidsameday;
	float amtbeforesamedayach;
	String amtnextdayachh;
	String amtnxtdayach;
	float amttnextdayach;
	float total;
	String totalamount;
	String totalamountt;
	String amtbeforepaidsmday;
	String amtbefore;
	String paymenowfe;
	String paymenow;
	String paymenowfeee;
	float paymenowf;

	@FindBy(xpath = "//*[@class='getpaid']")
	private WebElement btn_paymenow;

	@FindBy(xpath = "//*[@id='paymentOptionsDiv']/div[3]/div[1]/div[7]/span/text()[4]//following::*[@value='Select'][1]")
	private WebElement btn_selectnextdayach;

	@FindBy(xpath = "//div[@id='payMeNowQuoteDiv']//child::button[text()='CONFIRM']")
	private WebElement btn_confirm;

	@FindBy(xpath = "//*[@class='title ng-binding'][text()='PAID']")
	private WebElement tab_paid;

	@FindBy(xpath = "//*[@id='angularScope']/div[2]/div/div[3]/ul/li[3]/a/div/div[1]/div[2]/span[1]")
	private WebElement paidamt;

	@FindBy(xpath = "//*[@id='paymentOptionsDiv']/div[1]/div[1]/div[3]/p/span")
	private WebElement sameDayAmt;

	@FindBy(xpath = "//*[@id='paymentOptionsDiv']/div[2]/div[1]/div[3]/p/span")
	private WebElement wireTransferAmt;

	@FindBy(xpath = "//*[@id='paymentOptionsDiv']/div[3]/div[1]/div[3]/p/span")
	private WebElement nextDayAmt;

	@FindBy(xpath = "//*[@id='paymentOptionsDiv']/div[4]/div[1]/div[3]/p/span")
	private WebElement fuelCardAmt;

	@FindBy(xpath = ".//*[@id='paymentOptionsDiv']/div[3]/div[1]/div[5]/span[3]//following::text()[1]")
	private WebElement paymenowfee;

	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/ul/li[1]/a/div/div/div[2]/span[2]")
	private WebElement paymenowtab;

	public CarrierNextDAYACH() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		act = new Actions(driver);
	}

	public void getAmount() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(paidamt));
		amtbeforepaidsameday = paidamt.getText();
		amtbeforepaidsmday = amtbeforepaidsameday.replaceAll("\\$", "");
		amtbefore = amtbeforepaidsmday.replaceAll(",", "");
		amtbeforesamedayach = Float.parseFloat(amtbefore);
		System.out.println(amtbeforesamedayach);
	}

	public void clickPaymenow() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btn_paymenow));
		btn_paymenow.click();
		Thread.sleep(1000);
	}

	public void getnextdayAmount() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(nextDayAmt));
		amtnextdayach = nextDayAmt.getText();
		amtnextdayachh = amtnextdayach.replaceAll("\\$", "");
		amtnxtdayach = amtnextdayachh.replaceAll(",", "");
		amttnextdayach = Float.parseFloat(amtnxtdayach);
		System.out.println(amttnextdayach);

		// paymenowfe =paymenowfee.getText();
		// paymenow=paymenowfe.replaceAll("\\$", "");
		// paymenowfeee= paymenow.replaceAll(",", "");
		// paymenowf = Float.parseFloat(paymenowfeee);
		// System.out.println(paymenowf);
		//
		// achtransfees= achtransfee.getText();
		// achtrafee= achtransfees.replaceAll("\\$", "");
		// achfee = achtrafee.replaceAll(",", "");
		// achtrans = Float.parseFloat(achfee);
		// System.out.println(achtrans);

	}

	public void clickSelectButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btn_selectnextdayach));
		act.moveToElement(btn_selectnextdayach).click().perform();
		// btn_selectnextdayach.click();
		Thread.sleep(1000);
	}

	public void clickConfirmButton() throws InterruptedException, AWTException {
		// TestUtil.zoomIN();
		wait.until(ExpectedConditions.elementToBeClickable(btn_confirm));
		Thread.sleep(1000);
		btn_confirm.click();
		Thread.sleep(1000);
	}

	public void clickPaidTab() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tab_paid));
		tab_paid.click();
		Thread.sleep(1000);
	}

	public void gettotalpaiyAmount() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(paidamt));
		totalamt = paidamt.getText();
		totalamount = totalamt.replaceAll("\\$", "");
		totalamountt = totalamount.replaceAll(",", "");
		total = Float.parseFloat(totalamountt);
		System.out.println(total);

	}

	public void verifyNextDayach() throws InterruptedException {
		amttnextdayach = (total - amtbeforesamedayach);
		System.out.println(amttnextdayach);

	}
}
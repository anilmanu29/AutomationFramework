package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.TestBase;


public class CarrierNextDAYACH extends TestBase {

	WebDriverWait wait = null;
	Actions act = null;
	String totalamt;
	String amtsamedayach;
	String amtbeforepaidsameday;
	float amtbeforesamedayach;
	 String amtsamedayachh;
	 String amtsmdayach;
	 float amttsamedayach;
	 float total;
	 String totalamount;
	 String totalamountt;
	 String amtbeforepaidsmday;
	 String amtbefore ;
	 String paymenowfe;
	 String paymenow;
	 String paymenowfeee;
	 float paymenowf;
	 

	@FindBy(xpath = ".//*[@class='getpaid']")
	private WebElement btn_paymenow;

	@FindBy(xpath = ".//*[@id='paymentOptionsDiv']/div[2]/div[1]/div[9]/input[1]")
	private WebElement btn_selectnextdayach;

	@FindBy(xpath = "//div[@id='payMeNowQuoteDiv']//child::button[text()='CONFIRM']")
	private WebElement btn_confirm;

	@FindBy(xpath = "//*[@class='title ng-binding'][text()='PAID']")
	private WebElement tab_paid;
	
	@FindBy(xpath = "//*[@id='angularScope']/div[2]/div/div[3]/ul/li[3]/a/div/div[1]/div[2]/span[1]")
	private WebElement paidamt;
	
	@FindBy(xpath = ".//*[@id='paymentOptionsDiv']/div[3]/div[1]/div[3]/p/span")
	private WebElement samedayamt;
	
	@FindBy(xpath = ".//*[@id='paymentOptionsDiv']/div[3]/div[1]/div[5]/span[3]//following::text()[1]")
	private WebElement paymenowfee;
	
	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/ul/li[1]/a/div/div/div[2]/span[2]")
	private WebElement paymenowtab;
	
	
	
	

	public CarrierNextDAYACH() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		act = new Actions(driver);
	}
	

	public void  getAmount() throws InterruptedException {
		Thread.sleep(2000);
		amtbeforepaidsameday = paidamt.getText();
		 amtbeforepaidsmday = amtbeforepaidsameday.replaceAll("\\$", "");
		 amtbefore = amtbeforepaidsmday.replaceAll(",", "");	
		 amtbeforesamedayach = Float.parseFloat(amtbefore);
		 System.out.println(amtbeforesamedayach);
	}
	
	
	public void clickPaymenow() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btn_paymenow));
		btn_paymenow.click();
	}


	public void getsamedayAmount() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(samedayamt));
		 amtsamedayach = samedayamt.getText();
		 amtsamedayachh = amtsamedayach.replaceAll("\\$", "");
		 amtsmdayach = amtsamedayachh.replaceAll(",", "");	
		 amttsamedayach = Float.parseFloat(amtsmdayach);
		 System.out.println(amttsamedayach);	
		 
//		paymenowfe =paymenowfee.getText();
//		paymenow=paymenowfe.replaceAll("\\$", "");
//		paymenowfeee= paymenow.replaceAll(",", "");
//		paymenowf = Float.parseFloat(paymenowfeee);
//		 System.out.println(paymenowf);	
//		 
//		 achtransfees= 	achtransfee.getText();
//		 achtrafee= achtransfees.replaceAll("\\$", "");
//		achfee =  achtrafee.replaceAll(",", "");
//		achtrans = Float.parseFloat(achfee);
//		System.out.println(achtrans);
		
		 
		 
	}
	
	public void clickSelectButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btn_selectnextdayach));
		btn_selectnextdayach.click();
	}

	public void clickConfirmButton() throws InterruptedException {
		act.pause(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btn_confirm);
	}

	public void clickPaidTab() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tab_paid));
		act.moveToElement(tab_paid).click().perform();
	}
	
	public void gettotalpaiyAmount() throws InterruptedException {
		//wait.until(ExpectedConditions.elementToBeClickable(paidamt));
		Thread.sleep(1000);
		 totalamt = paidamt.getText();
		 totalamount =  totalamt.replaceAll("\\$", "");
		 totalamountt =	totalamount.replaceAll(",", "");	
		 total = Float.parseFloat(totalamountt);
		 System.out.println(total);	
		 
		
	}
	public void verifyNextDayach() throws InterruptedException {
		Thread.sleep(1000);
		amttsamedayach = (total - amtbeforesamedayach);
		System.out.println(amttsamedayach);
	
	}
}


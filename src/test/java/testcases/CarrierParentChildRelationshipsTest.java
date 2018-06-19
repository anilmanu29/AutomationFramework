package testcases;


import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.AdminHomePage;
import pages.AdminLogin;
import pages.AdminPayByCheck;
import pages.BrokerLoginPage;
import pages.BrokerNewPayment;
import pages.BrokerOutlook;
import pages.CarrierLoginPage;
import pages.CarrierNextDAYACH;
import pages.CarrierParentChildRelationships;
import pages.CarrierPaymeNowFuelCard;
import pages.CarrierSameDAYACH;
import pages.CarrierWireTransfer;


public class CarrierParentChildRelationshipsTest extends TestBase {
	CarrierLoginPage loginPage;
	BrokerLoginPage brokerlogin;
	BrokerNewPayment bp;
	CarrierParentChildRelationships carrierchildrelation;
	CarrierSameDAYACH carriersamedayach;
	CarrierNextDAYACH carriernextdayach;
	CarrierWireTransfer carrierwiretransferach;
	CarrierPaymeNowFuelCard carrierpaymenowfuelcard;
	List<String> firstRowData = null;
	List<String> lastRowData = null;
	JavascriptExecutor jse;
	String searchForInvoice = "";
	public static String nemail;
	BrokerOutlook brokeroutlook;
	String pwd;
	AdminHomePage ahp;
	AdminLogin all;
	AdminPayByCheck apbc;
	public String invoicenum;;
	// static String invoice;
	 public static ArrayList<String> arraylist;

	public CarrierParentChildRelationshipsTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {
		initialization();
		loginPage = new CarrierLoginPage();
		carrierchildrelation = new CarrierParentChildRelationships();
		jse = (JavascriptExecutor) driver;
		brokeroutlook = new BrokerOutlook();
		carriersamedayach = new CarrierSameDAYACH();
		carriernextdayach = new CarrierNextDAYACH();
		carrierwiretransferach = new CarrierWireTransfer();
		carrierpaymenowfuelcard = new CarrierPaymeNowFuelCard();
		brokerlogin = new BrokerLoginPage();
		bp = new BrokerNewPayment();
		all = new AdminLogin();
		ahp = new AdminHomePage(); 
		apbc=new AdminPayByCheck();
		arraylist = new ArrayList<String>();
	}

	@Test(dataProvider = "getCarrierLoginData", priority = 1)
	public void loginAsCarrier(String un, String pwd) throws InterruptedException {
		// login as carrier
		loginPage.Carrierlogin(un, pwd);
	}

	@Test(dataProvider = "getCarrierParentChildData", dependsOnMethods = { "loginAsCarrier" }, priority = 2)
	public void verifyAlertMessageTest(String fn, String ln, String email, String nemailid)
			throws InterruptedException {
		carrierchildrelation.clickAccountLink();
		carrierchildrelation.clickEmailLink();
		carrierchildrelation.clickAddUserButton();
		carrierchildrelation.enterFirstName(fn);
		carrierchildrelation.enterLastName(ln);
		carrierchildrelation.enterNewEmailID(email);
		carrierchildrelation.clickSaveButton();
		Assert.assertTrue(
				carrierchildrelation.getAlertMessage().contains("Email address already linked to a LoadPay account"),
				"Alert message is NOT Displayed");
	}

	@Test(dataProvider = "getCarrierParentChildData", dependsOnMethods = { "loginAsCarrier" }, priority = 3)
	public void verifyAddChildAccount(String fn, String ln, String email, String nemailid) throws InterruptedException {
		carrierchildrelation.clickCancelButton();
		carrierchildrelation.clickAddUserButton();
		carrierchildrelation.enterFirstName(fn);
		carrierchildrelation.enterLastName(ln);
		nemail = carrierchildrelation.enterNewEmailID(nemailid);
		carrierchildrelation.enablePaymentAccess();
		carrierchildrelation.clickSaveButton();
		verifyEmailAddress(nemailid);
	}

	//
	@Test(dataProvider = "getoutlookLoginData", priority = 4)
	public void verifyEmailAddress(String un, String pwd) throws InterruptedException {
		carrierchildrelation.outlookLogin(un, pwd);
		carrierchildrelation.getVerificationCodeData();
		carrierchildrelation.enterVerificationCode();
		carrierchildrelation.clickVerifyButton();
		((JavascriptExecutor) driver).executeScript("window.open()");
		Thread.sleep(1000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get(super.prop.getProperty("outlookurl"));
		brokeroutlook.clickPopUp();
		brokeroutlook.clickOpenMailBox();
		brokeroutlook.enterEmail(super.prop.getProperty("email"));

	}

	@Test(dataProvider = "getCarrierParentChildPasswordData", priority = 5)
	public void verifyResetPassword(String nwpwd, String confmpwd, String forcepwd, String confirmforcepwd)
			throws InterruptedException {
		carrierchildrelation.resetPassword(nwpwd, confmpwd);
		pwd = nwpwd;

	}

	@Test(dataProvider = "getCarrierFuelcardaccountNumbersData", priority = 6)
	public void verifyChildAccountLogin(String fleet_accountnbr, String fts_accountnbr) throws InterruptedException {
		loginPage.Carrierlogin(nemail, pwd);
		carriersamedayach.getAmount();
		carriersamedayach.clickPaymenow();
		carriersamedayach.getsamedayAmount();
		Thread.sleep(2000);
		carriersamedayach.clickSelectButton();
		Thread.sleep(2000);
		carriersamedayach.clickConfirmButton();
		Thread.sleep(2000);
		carriersamedayach.gettotalpaiyAmount();
		Thread.sleep(2000);
		carriersamedayach.verifySamedayach();
		
		 carriernextdayach.getAmount();
		 carriernextdayach.clickPaymenow();
		 Thread.sleep(3000);
		 carriernextdayach.getnextdayAmount();
		 Thread.sleep(3000);
		 carriernextdayach.clickSelectButton();
		 Thread.sleep(3000);
		 carriernextdayach.clickConfirmButton();
		 Thread.sleep(3000);
		 carriernextdayach.gettotalpaiyAmount();
		 Thread.sleep(3000);
		 carriernextdayach.verifyNextDayach();
		
		 carrierwiretransferach.getAmount();
		 Thread.sleep(2000);
		 carrierwiretransferach.clickPaymenow();
		 Thread.sleep(2000);
		 carrierwiretransferach.getwiretransferAmount();
		 Thread.sleep(2000);
		 carrierwiretransferach.clickSelectButton();
		 Thread.sleep(2000);
		 carrierwiretransferach.clickConfirmButton();
		 Thread.sleep(2000);
		 carrierwiretransferach.gettotalpaiyAmount();
		 Thread.sleep(2000);
		 carrierwiretransferach.verifywiretransfer();
		
		 carrierpaymenowfuelcard.clickPaymenow();
		 Thread.sleep(2000);
		 carrierpaymenowfuelcard.clickSelectButton();
		 Thread.sleep(2000);
		 carrierpaymenowfuelcard.clickaddnewcard();
		 Thread.sleep(2000);
		 carrierpaymenowfuelcard.clickfleetone();
		 Thread.sleep(2000);
		 carrierpaymenowfuelcard.input_accountnbr(fleet_accountnbr);
		 Thread.sleep(2000);
		 carrierpaymenowfuelcard.clicksubmit();
		 Thread.sleep(2000);
		 carrierpaymenowfuelcard.clickfuelcardsubmit();
		 Thread.sleep(2000);
		 carrierpaymenowfuelcard.clickConfirmButton();
		 Thread.sleep(2000);
		 carrierpaymenowfuelcard.clickPaidTab();
		 Thread.sleep(2000);
		 carrierpaymenowfuelcard.clickpaymenowtab();
		 Thread.sleep(2000);
		 carrierpaymenowfuelcard.clickPaymenow();
		 Thread.sleep(2000);
		 carrierpaymenowfuelcard.clickSelectButton();
		 Thread.sleep(2000);
		 carrierpaymenowfuelcard.clickaddnewcard();
		 Thread.sleep(2000);
		 carrierpaymenowfuelcard.clickFTS();
		 Thread.sleep(2000);
		 carrierpaymenowfuelcard.input_accountnbr(fts_accountnbr);
		 Thread.sleep(2000);
		 carrierpaymenowfuelcard.clicksubmit();
		 Thread.sleep(2000);
		 carrierpaymenowfuelcard.clickfuelcardsubmit();
		 Thread.sleep(2000);
		 carrierpaymenowfuelcard.clickConfirmButton();
		 Thread.sleep(2000);
		 carrierpaymenowfuelcard.clickPaidTab();
		 //Thread.sleep(2000);
		
		driver.close();
		Thread.sleep(1000);
		ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(2));
		Thread.sleep(2000);
		driver.close();
		Thread.sleep(1000);
		ArrayList<String> tabb = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabb.get(1));
		driver.close();
		Thread.sleep(1000);
		ArrayList<String> closetab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(closetab.get(0));

	}

	@Test(dataProvider = "getCarrierLoginData", priority = 7)
	public void loginCarrier(String un, String pwd) throws InterruptedException {
		loginPage.Carrierlogin(un, pwd);
	}

	@Test(dataProvider = "getCarrierParentChildPasswordData", priority = 8)
	public void verifyForcedPasswordReset(String pwd, String confpwd, String forcepwd, String confirmforcepwd)
			throws InterruptedException {
		carrierchildrelation.clickAccountLink();
		carrierchildrelation.clickEmailLink();
		carrierchildrelation.forcePasswordReset();
		((JavascriptExecutor) driver).executeScript("window.open()");
		Thread.sleep(1000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get(super.prop.getProperty("outlookurl"));
		brokeroutlook.clickPopUp();
		brokeroutlook.clickOpenMailBox();
		brokeroutlook.enterEmail(super.prop.getProperty("email"));
		carrierchildrelation.resetPassword(forcepwd, confirmforcepwd);

	}

	@Test(dataProvider = "getCarrierParentChildData", priority = 9)
	public void verifyEditAccountTest(String fn, String ln, String email, String nemailid) throws InterruptedException {
		carrierchildrelation.clickAccountLink();
		carrierchildrelation.clickEmailLink();
		carrierchildrelation.editAccount();
		carrierchildrelation.enterFirstName(ln);
		carrierchildrelation.enterLastName(fn);
		carrierchildrelation.clickSaveButton();
		carrierchildrelation.carrierLogOut();
	}

	@Test(dataProvider="getBrokerLoginData", priority=10)
	public void loginTest(String user,String pass) throws InterruptedException
	{
		
		brokerlogin.Brokerlogin(user, pass);
	
	}
	@Test(dataProvider="getPaymentData", priority=11)
	public void brokernewPayment(String cemail, String invoiceno, String loadid, String amt) throws InterruptedException {
		
		bp = new BrokerNewPayment();
		bp.newPayment();
		Thread.sleep(1000);
		BrokerNewPaymentTest.email =  bp.carrierEmail(cemail);
		Thread.sleep(1000);
		bp.amount(amt);
		Thread.sleep(1000);
		invoicenum = bp.invoiceNumber(invoiceno);
		arraylist.add(invoicenum);
		Thread.sleep(1000);
		bp.loadId(loadid);
		Thread.sleep(1000);
		//bp.advanceCheckbox();
		//Thread.sleep(1000);
		bp.clickShedulePayment();
		Thread.sleep(1000);
		bp.clickShedulePaymenttab();
		Thread.sleep(1000);
		bp.searchCarrier(cemail);
		Thread.sleep(1000);
		bp.clickSearchButton();
		Thread.sleep(1000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(1000);
		 bp.verifyInvoiceNumber(invoiceno,amt);
		 Thread.sleep(1000);
		//Assert.assertEquals(bp.verifyPaymentStatus(), payment_status);
		System.out.println(bp.verifyPaymentStatus());
		//bp.logout();
	}
	



	
	
	@Test(dataProvider = "getAdminLoginData", priority =12)
	public void verifyAdminPayByCheck(String Username, String pass) throws InterruptedException, AWTException {
		bp.logout();
		Thread.sleep(1000);
		ahp.AdminURL(); 
		Thread.sleep(2000);
		all.adminUserPass(Username, pass);
		all.adminLogin();
		Thread.sleep(1000);
		all.ClickOnCustomersTab();
		Thread.sleep(1000);
		System.out.println(BrokerLoginPage.bemail);
		all.ClickOnSearchBox(BrokerLoginPage.bemail);
		Thread.sleep(1000);
		all.ClickOnSearchButton();
		Thread.sleep(1000);
		all.DoubleClickID();
		Thread.sleep(1000);
		apbc.clickPayments();
		Thread.sleep(3000);
		//System.out.println(BrokerNewPaymentTest.al.get(0));
		apbc.ClickOnsearchKeyword(CarrierParentChildRelationshipsTest.arraylist.get(0));
		Thread.sleep(2000);
		apbc.getPaymentID();
		Thread.sleep(2000);
		apbc.clickSearch();
		Thread.sleep(2000);
		apbc.searchKeyword();
		Thread.sleep(2000);
		apbc.clickSearch1();
		Thread.sleep(2000);
		apbc.clickgridcollapse();
		Thread.sleep(2000);
		apbc.clickPayByCheck();
		Thread.sleep(2000);
		apbc.selectTerms();
		Thread.sleep(3000);
		
	}
		@Test(dataProvider = "getCcarrierMatchedPayByCheckPayMNWData",priority=13)
		public void carrierPaymenowPayByCheck(String EnterDOTNnumber,String ContactName) throws InterruptedException {
		apbc.EnterDOTNnumber(EnterDOTNnumber);
		Thread.sleep(3000);
		apbc.ContactName(ContactName);
		Thread.sleep(3000);
		apbc.clickPayByChecksubmit();
		Thread.sleep(3000);
		all.AdminLogOut();
	

	}
		
		@Test(dataProvider = "getAdminLoginData", priority =14)
		public void verifyAdminPayByCheckTermPayment(String Username, String pass) throws InterruptedException, AWTException {
			Thread.sleep(1000);
			ahp.AdminURL(); 
			Thread.sleep(2000);
			all.adminUserPass(Username, pass);
			all.adminLogin();
			Thread.sleep(1000);
			all.ClickOnCustomersTab();
			Thread.sleep(1000);
			System.out.println(BrokerLoginPage.bemail);
			all.ClickOnSearchBox(BrokerLoginPage.bemail);
			Thread.sleep(1000);
			all.ClickOnSearchButton();
			Thread.sleep(1000);
			all.DoubleClickID();
			Thread.sleep(1000);
			apbc.clickPayments();
			Thread.sleep(3000);
			apbc.ClickOnsearchKeywordterm(CarrierParentChildRelationshipsTest.arraylist.get(1));
			Thread.sleep(2000);
			apbc.getPaymentID();
			Thread.sleep(2000);
			apbc.clickSearch();
			Thread.sleep(2000);
			apbc.searchKeyword();
			Thread.sleep(2000);
			apbc.clickSearch1();
			Thread.sleep(2000);
			apbc.clickgridcollapse();
			Thread.sleep(2000);
			apbc.clickPayByCheck();
			Thread.sleep(2000);
			apbc.selectTerms();
			Thread.sleep(2000);
			apbc.selectTermsTermPayment();
			Thread.sleep(3000);
			
		}
			@Test(dataProvider = "getCcarrierMatchedPayByCheckPayMNWData",priority=15)
			public void carrierTermPaymentPayByCheck(String EnterDOTNnumber,String ContactName) throws InterruptedException {
			apbc.EnterDOTNnumber(EnterDOTNnumber);
			Thread.sleep(3000);
			apbc.ContactName(ContactName);
			Thread.sleep(3000);
			apbc.clickPayByChecksubmit();
			Thread.sleep(3000);
			all.AdminLogOut();
			
		}

			@Test(dataProvider = "getCarrierLoginData", priority = 16)
			public void verifyDeleteChildAccountTest(String un, String password) throws InterruptedException {
			driver.get(prop.getProperty("url"));
			loginPage.Carrierlogin(un, password);
			carrierchildrelation.clickAccountLink();
			carrierchildrelation.clickEmailLink();
			carrierchildrelation.deleteChildAccount();
			
			}
	
		
		
	
	public void verifyEmailAddress(String nemailid) throws InterruptedException {
		Thread.sleep(1000);
		List<WebElement> list = driver.findElements(By.xpath("//div[@class='ellipsis ng-binding']"));
		for (WebElement e : list) {
			Thread.sleep(2000);
			// System.out.println(e.getText());
			if (e.getText().contains(nemailid)) {
				Thread.sleep(1000);
				Assert.assertTrue(e.getText().contains(nemailid));
				break;
			}

		}

	}

}

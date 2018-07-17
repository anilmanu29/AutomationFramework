package testcases.loadpay.admin;

import base.TestBase;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.admin.AdminWireTransfer;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerNewPayment;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierWireTransfer;
import testcases.loadpay.broker.BrokerNewPaymentTest;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class AdminWireTransferTest extends TestBase {
    AdminHomePage adminHomePage;
    AdminLogin adminLogin;
    AdminWireTransfer adminWireTransfer;
    BrokerNewPayment brokerNewPayment;
    BrokerNewPaymentTest brokerNewPaymentTest;
    BrokerLoginPage brokerloginPage;
    CarrierWireTransfer carrierWireTransfer;
    CarrierLoginPage carrierLoginPage;
    
    String acountName;
    String paymentStatus = "Verified";
    String invoice;
    String email;
    ArrayList<String> invoiceList;

    /*-------Initializing driver---------*/
    public AdminWireTransferTest() { super(); }

    @BeforeClass
    public void setUp() throws IOException {
        initialization();
        adminLogin = new AdminLogin();
        adminHomePage = new AdminHomePage();
        brokerloginPage = new BrokerLoginPage();
        brokerNewPayment = new BrokerNewPayment();
        brokerNewPaymentTest = new BrokerNewPaymentTest();
        adminWireTransfer = new AdminWireTransfer();
        carrierLoginPage = new CarrierLoginPage();
        carrierWireTransfer = new CarrierWireTransfer();
        invoiceList = new ArrayList<String>();
        log = Logger.getLogger(AdminWireTransferTest.class.getName());
        log.info("Test Set Up");
    }

    /*-------Login to Load Pay as Broker---------*/
    @Test(description = "LP-6230 Admin Wire Transfer", dataProvider = "getBrokerLoginData")
    public void loginBroker(String un, String pwd) {
        brokerloginPage.Brokerlogin(un, pwd);
        log.info("Broker Login");

    }

    /*-------Scheduling New Payment as a Broker---------*/

    @Test(description = "LP-6230 Admin Wire Transfer", dataProvider = "getPaymentData", dependsOnMethods = "loginBroker")
    public void brokerNewPayment(String cemail, String invoiceno, String loadid, String amt) throws InterruptedException {
        log.info("Create new Payment ");
        brokerNewPayment.newPayment();
        Thread.sleep(1000);
        brokerNewPayment.carrierEmail(cemail);
        Thread.sleep(1000);
        brokerNewPayment.amount(amt);
        Thread.sleep(1000);
        invoice = brokerNewPayment.invoiceNumber(invoiceno);
        invoiceList.add(invoice);
        Thread.sleep(1000);
        brokerNewPayment.loadId(loadid);
        Thread.sleep(1000);
        //bp.advanceCheckbox();
        //Thread.sleep(1000);
        brokerNewPayment.clickShedulePayment();
        Thread.sleep(1000);
        brokerNewPayment.clickShedulePaymenttab();
        Thread.sleep(1000);
        brokerNewPayment.searchCarrier(cemail);
        Thread.sleep(1000);
        brokerNewPayment.clickSearchButton();
        Thread.sleep(1000);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,250)", "");
        Thread.sleep(1000);
        brokerNewPayment.verifyInvoiceNumber(invoiceno, amt);
        Thread.sleep(1000);
        //Assert.assertEquals(bp.verifyPaymentStatus(), payment_status);
        System.out.println(brokerNewPayment.verifyPaymentStatus());

    }

    /*-------Login as Carrier------*/
    @Test(dataProvider = "getCarrierLoginData", dependsOnMethods = "brokerNewPayment")
    public void carrierLogin(String un, String pwd) throws InterruptedException {
        brokerNewPayment.logout();
        log.info("Broker LogOut");
        Thread.sleep(2000);
        carrierLoginPage.Carrierlogin(un, pwd);
        log.info("Carrier Login");
        Thread.sleep(2000);

    }

    /*------- Perform PayMeNow Wire Transfer------*/
    @Test(dependsOnMethods = "carrierLogin")
    public void performPaymeNow() throws InterruptedException {
        carrierWireTransfer.getAmount();
        Thread.sleep(2000);
        carrierWireTransfer.clickPaymenow();
        Thread.sleep(2000);
        carrierWireTransfer.getwiretransferAmount();
        Thread.sleep(2000);
        carrierWireTransfer.clickSelectButton();
        Thread.sleep(2000);
        carrierWireTransfer.clickConfirmButton();
        log.info("Perform Carrier Wire Transfer");
        Thread.sleep(3000);
        carrierWireTransfer.getAmount();
        Thread.sleep(2000);
        carrierWireTransfer.clickPaymenow();
        Thread.sleep(2000);
        carrierWireTransfer.getwiretransferAmount();
        Thread.sleep(2000);
        carrierWireTransfer.clickSelectButton();
        Thread.sleep(2000);
        carrierWireTransfer.clickConfirmButton();
        log.info("Perform Carrier Wire Transfer");
        Thread.sleep(3000);
        carrierLoginPage.CarrierLogout();
        log.info("Carrier LogOut");
    }

    /*------Login as Admin And Verify Wire Transfer------------*/
    @Test(dataProvider = "getAdminLoginData", dependsOnMethods = "performPaymeNow")
    public void goToAdminPage(String Username, String pass) throws InterruptedException, AWTException {
        Thread.sleep(2000);
        adminHomePage.AdminURL();
        Thread.sleep(2000);
        adminLogin.adminUserPass(Username, pass);
        adminLogin.adminLogin();
        log.info("Admin Login");
        Thread.sleep(1000);
        adminLogin.ClickOnCustomersTab();
        Thread.sleep(1000);
        System.out.println(BrokerLoginPage.bemail);
        adminLogin.ClickOnSearchBox(BrokerLoginPage.bemail);
        Thread.sleep(1000);
        adminLogin.ClickOnSearchButton();
        Thread.sleep(1000);
        adminLogin.DoubleClickID();
        Thread.sleep(1000);
        adminWireTransfer.clickPayments();
        Thread.sleep(3000);
        adminWireTransfer.ClickOnsearchKeywordterm(invoiceList.get(0));
        Thread.sleep(2000);
        adminWireTransfer.getPaymentID();
        Thread.sleep(2000);
        adminWireTransfer.clickSearch();
        Thread.sleep(2000);
        adminWireTransfer.searchKeyword();
        Thread.sleep(2000);
        adminWireTransfer.clickSearch1();
        Thread.sleep(2000);
        adminWireTransfer.clickgridcollapse();
        Thread.sleep(2000);
        adminWireTransfer.clickWireTransferButton();
        Thread.sleep(2000);
        adminWireTransfer.markOFacCheckbox();
        Thread.sleep(2000);
        adminWireTransfer.enterWireTransferConfirmationNumber();
        Thread.sleep(1000);
        adminWireTransfer.confirmWireTransfer();
        log.info("Confirm Wire Transfer");
        Thread.sleep(2000);
        adminWireTransfer.verifyPaymentIssued();
        Thread.sleep(2000);

        /*------- Go To Admin and Fail Payment------*/
        adminLogin.ClickOnCustomersTab();
        Thread.sleep(1000);
        System.out.println(BrokerLoginPage.bemail);
        adminLogin.ClickOnSearchBox(BrokerLoginPage.bemail);
        Thread.sleep(1000);
        adminLogin.ClickOnSearchButton();
        Thread.sleep(1000);
        adminLogin.DoubleClickID();
        Thread.sleep(1000);
        adminWireTransfer.clickPayments();
        Thread.sleep(3000);
        adminWireTransfer.ClickOnsearchKeywordterm(invoiceList.get(1));
        Thread.sleep(2000);
        adminWireTransfer.getPaymentID();
        log.info("Get Payment id for second payment");
        Thread.sleep(2000);
        adminWireTransfer.clickSearch();
        Thread.sleep(2000);
        adminWireTransfer.searchKeyword();
        Thread.sleep(2000);
        adminWireTransfer.clickSearch1();
        Thread.sleep(2000);
        adminWireTransfer.clickgridcollapse();
        Thread.sleep(2000);
        adminWireTransfer.clickWireTransferButton();
        Thread.sleep(2000);
        adminWireTransfer.enterWireTransferConfirmationNumber();
        Thread.sleep(2000);
        adminWireTransfer.clickFailedWireTransferButton();
        log.info("Fail payment");
        Thread.sleep(2000);
        adminWireTransfer.verifyPaymentFailed();
        log.info("Confirm Wire Transfer Failed");
        Thread.sleep(2000);
        adminLogin.AdminLogOut();
    }

}

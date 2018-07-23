package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.admin.AdminWireTransfer;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerNewPayment;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierWireTransfer;
import testcases.loadpay.broker.BrokerNewPaymentTest;

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
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        brokerNewPayment.carrierEmail(cemail);
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        brokerNewPayment.amount(amt);
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        invoice = brokerNewPayment.invoiceNumber(invoiceno);
        invoiceList.add(invoice);
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        brokerNewPayment.loadId(loadid);
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        //bp.advanceCheckbox();
        //wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        brokerNewPayment.clickShedulePayment();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        brokerNewPayment.clickShedulePaymenttab();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        brokerNewPayment.searchCarrier(cemail);
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        brokerNewPayment.clickSearchButton();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,250)", "");
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        brokerNewPayment.verifyInvoiceNumber(invoiceno, amt);
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        //Assert.assertEquals(bp.verifyPaymentStatus(), payment_status);
        System.out.println(brokerNewPayment.verifyPaymentStatus());

    }

    /*-------Login as Carrier------*/
    @Test(dataProvider = "getCarrierLoginData", dependsOnMethods = "brokerNewPayment")
    public void carrierLogin(String un, String pwd) throws InterruptedException {
        brokerNewPayment.logout();
        log.info("Broker LogOut");
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        carrierLoginPage.Carrierlogin(un, pwd);
        log.info("Carrier Login");
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));

    }

    /*------- Perform PayMeNow Wire Transfer------*/
    @Test(dependsOnMethods = "carrierLogin")
    public void performPaymeNow() throws InterruptedException {
        carrierWireTransfer.getAmount();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        carrierWireTransfer.clickPaymenow();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        carrierWireTransfer.getwiretransferAmount();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        carrierWireTransfer.clickSelectButton();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        carrierWireTransfer.clickConfirmButton();
        log.info("Perform Carrier Wire Transfer");
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        carrierWireTransfer.getAmount();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        carrierWireTransfer.clickPaymenow();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        carrierWireTransfer.getwiretransferAmount();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        carrierWireTransfer.clickSelectButton();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        carrierWireTransfer.clickConfirmButton();
        log.info("Perform Carrier Wire Transfer");
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        carrierLoginPage.CarrierLogout();
        log.info("Carrier LogOut");
    }

    /*------Login as Admin And Verify Wire Transfer------------*/
    @Test(dataProvider = "getAdminLoginData", dependsOnMethods = "performPaymeNow")
    public void goToAdminPage(String Username, String pass) throws InterruptedException, AWTException {
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminHomePage.AdminURL();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminLogin.adminUserPass(Username, pass);
        adminLogin.adminLogin();
        log.info("Admin Login");
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminLogin.ClickOnCustomersTab();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        System.out.println(BrokerLoginPage.bemail);
        adminLogin.ClickOnSearchBox(BrokerLoginPage.bemail);
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminLogin.ClickOnSearchButton();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminLogin.DoubleClickID();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminWireTransfer.clickPayments();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminWireTransfer.ClickOnsearchKeywordterm(invoiceList.get(0));
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminWireTransfer.getPaymentID();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminWireTransfer.clickSearch();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminWireTransfer.searchKeyword();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminWireTransfer.clickSearch1();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminWireTransfer.clickgridcollapse();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminWireTransfer.clickWireTransferButton();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminWireTransfer.markOFacCheckbox();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminWireTransfer.enterWireTransferConfirmationNumber();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminWireTransfer.confirmWireTransfer();
        log.info("Confirm Wire Transfer");
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminWireTransfer.verifyPaymentIssued();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));

        /*------- Go To Admin and Fail Payment------*/
        adminLogin.ClickOnCustomersTab();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        System.out.println(BrokerLoginPage.bemail);
        adminLogin.ClickOnSearchBox(BrokerLoginPage.bemail);
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminLogin.ClickOnSearchButton();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminLogin.DoubleClickID();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminWireTransfer.clickPayments();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminWireTransfer.ClickOnsearchKeywordterm(invoiceList.get(1));
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminWireTransfer.getPaymentID();
        log.info("Get Payment id for second payment");
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminWireTransfer.clickSearch();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminWireTransfer.searchKeyword();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminWireTransfer.clickSearch1();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminWireTransfer.clickgridcollapse();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminWireTransfer.clickWireTransferButton();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminWireTransfer.enterWireTransferConfirmationNumber();
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminWireTransfer.clickFailedWireTransferButton();
        log.info("Fail payment");
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminWireTransfer.verifyPaymentFailed();
        log.info("Confirm Wire Transfer Failed");
        wait.until(ExpectedConditions.elementToBeClickable(tempElement));
        adminLogin.AdminLogOut();
    }

}

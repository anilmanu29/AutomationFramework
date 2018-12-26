package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;

import util.TestUtil;
import util.WebEventListener;
import utility.ReadExcel;

public class TestBase {

	public static WebDriver driver;
	protected static Properties prop = new Properties();
	protected static EventFiringWebDriver eDriver;
	protected static WebEventListener eventListener;
	protected static String userDirectory = "user.dir";
	protected static String userHome = "user.home";
	public static Logger log;
	public static String className = "";
	public static WebDriverWait wait = null;
	public static String loadPayTestDataFilePath = System.getProperty(userDirectory)
			+ "/src/main/java/testdata/LoadPay/LoadPayTestData.xlsx";

	public TestBase() {
		try {
			FileInputStream ip = new FileInputStream(
					System.getProperty(userDirectory) + "/src/main/java/config/config.properties");
			prop.load(ip);
		} catch (IOException e) {
			log.info(e);
		}
	}

	public static void initialization() {
		log = Logger.getLogger(Logger.class.getName());

		if (prop.getProperty("useVideoCapture").contains("true")) {
			TestUtil.beginVideoCapture();
		}

		String browserName = prop.getProperty("browser");

		log.info("Selecting browser type from configuration properties");

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty(userDirectory) + "/Drivers/chromedriver.exe");
			driver = new ChromeDriver();

			// Map<String, Object> prefs = new HashMap<String, Object>();
			// // To Turns off multiple download warning
			// prefs.put("profile.default_content_settings.popups", 0);
			// prefs.put("profile.content_settings.pattern_pairs.*.multiple-automatic-downloads",
			// 1);
			// // Turns off download prompt
			// prefs.put("download.prompt_for_download", false);
			// ChromeOptions options = new ChromeOptions();
			// options.setExperimentalOption("prefs", prefs);
			// driver = new ChromeDriver(options);

		} else if (browserName.equals("FF")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty(userDirectory) + "/Drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equals("IE")) {
			System.setProperty("webdriver.ie.driver",
					System.getProperty(userDirectory) + "/Drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		// DETERMINE APPLICATION UNDER TEST FROM CONFIG.PROPERTIES FILE
		String applicationUnderTest = prop.getProperty("AUT");

		if (applicationUnderTest.contains("Loadpay"))
			driver.get(prop.getProperty("loadPayURL"));
		else if (applicationUnderTest.contains("FreightMatching"))
			driver.get(prop.getProperty(""));
		else if (applicationUnderTest.contains("ITSDispatch"))
			driver.get(prop.getProperty("ITSDispatchURL"));
		else if (applicationUnderTest.contains("Mobile"))
			driver.get(prop.getProperty("mobileURL"));
		else if (applicationUnderTest.contains("V5"))
			driver.get(prop.getProperty("v5_prototypeURL"));

		// USE FULL LOADPAY REGRESSION INPUT DATA - HAS MORE NEW PAYMENT LINES
		if (prop.getProperty("useFullRegressionData").contains("true")) {
			loadPayTestDataFilePath = System.getProperty(userDirectory)
					+ "/src/main/java/testdata/LoadPay/LoadPayTestData_FullRegression.xlsx";
		}

		// Create object of EventListerHandler to register it with EventFiringWebDriver
		eDriver = new EventFiringWebDriver(driver);
		eventListener = new util.WebEventListener();
		eDriver.register(eventListener);
		driver = eDriver;
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 30);
	}

	public static Properties getProperties() {
		return prop;
	}

	@DataProvider
	public static Object[][] getBrokerLoginData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BrokerLoginData");
	}

	@DataProvider
	public static Object[][] getStagingBrokerLoginData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "HandshakeBrokerLogindata");
	}

	@DataProvider
	public static Object[][] getBrokerRegisterData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BrokerRegister");
	}

	@DataProvider
	public static Object[][] getCarrierLoginData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CarrierLoginData");
	}

	@DataProvider
	public static Object[][] getStagingCarrierLoginData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "HandshakeCarrierLogindata");
	}

	@DataProvider
	public static Object[][] getPaymentData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BrokerNewPaymentData");
	}

	@DataProvider
	public static Object[][] getCarrierRegisterData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CarrierRegisterData");
	}

	@DataProvider
	public static Object[][] getoutlookLoginData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "outlookLoginData");
	}

	@DataProvider
	public static Object[][] getAdminLoginData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "AdminLogin");
	}

	@DataProvider
	public static Object[][] getPaymentDataforUnmatchcarrier() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BrokerPaymentDataforUnmatchedCr");
	}

	@DataProvider
	public static Object[][] getBrokerData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BrokerRegisterCanada");
	}

	@DataProvider
	public static Object[][] getCarrierData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CarrierRegisterCanada");
	}

	@DataProvider
	public static Object[][] getCarrierFuelcardaccountNumbersData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CarrierFuelcardaccountNumbers");
	}

	@DataProvider
	public static Object[][] getCarrierBankingData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CarrierBankingData");
	}

	@DataProvider
	public static Object[][] getBrokerBankingData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BrokerBankingData");
	}

	@DataProvider
	public static Object[][] getCcarrierMatchedPayByCheckPayMNWData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CcarrierMatchedPayByCheckPayMNW");
	}

	@DataProvider
	public static Object[][] getCarrierPaidTabData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CarrierPaidTabData");
	}

	@DataProvider
	public static Object[][] getCarrierlockedaccountAdminUnlockData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CarrierlockedaccountAdminUnlock");
	}

	@DataProvider
	public static Object[][] getCarrierParentChildData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "carrierparentchilddata");
	}

	@DataProvider
	public static Object[][] getCarrierParentChildPasswordData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "carrierresetpassworddata");
	}

	@DataProvider
	public static Object[][] getBulkUploadPaymentsmatchedData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BulkUploadPaymentsmatched");
	}

	@DataProvider
	public static Object[][] getBulkUploadPaymentsUnmatchedData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BulkUploadPaymentsUnmatched");
	}

	@DataProvider
	public static Object[][] getCarrierSchedulePaymentTabData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CarrierSchedulePaymentTabData");
	}

	@DataProvider
	public static Object[][] getAdminSearchData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "AdminSearchData");
	}

	@DataProvider
	public static Object[][] getBrokerChangePasswordData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BrokerChangePasswordData");
	}

	@DataProvider
	public static Object[][] getBrokerForgotPassword() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BrokerForgotPassword");
	}

	@DataProvider
	public static Object[][] getCarrierPaymentHistoryData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CarrierPaymentHistorydata");
	}

	@DataProvider
	public static Object[][] getBrokerPaymentHistoryData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BrokerPaymentHistorydata");
	}

	@DataProvider
	public static Object[][] getShipperCompleteAccModuleData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "ShipperCompleteAccModuleData");
	}

	@DataProvider
	public static Object[][] getBrokerProcessedTabSearchData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BrokerProcessedTabSearchData");
	}

	@DataProvider
	public static Object[][] getBrokerDiscountsTabSearchData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BrokerDiscountsTabSearchData");
	}

	@DataProvider
	public static Object[][] getBrokerSchedPaymentTabSearchData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BrokerSchedPaymentTabSearchData");
	}

	@DataProvider
	public static Object[][] getUpdatedPaymentData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BrokerUpdatePaymentData");
	}

	@DataProvider
	public static Object[][] getAdminLoginshipperaccountmoduleData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "AdminLoginshipperaccountmodule");
	}

	@DataProvider
	public static Object[][] getCarrierChangePasswordData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CarrierChangePasswordData");
	}

	@DataProvider
	public static Object[][] getCarrierForgotPasswordData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CarrierForgotPasswordData");
	}

	@DataProvider
	public static Object[][] getCarrierLoginAccountModuleData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CarrierLoginAccountModuleData");
	}

	@DataProvider
	public static Object[][] getAdminforcepasswordData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "AdminforcepasswordData");
	}

	@DataProvider
	public static Object[][] getBrokerBankAccountData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BankAccountDetailsForCopyPaste");
	}

	@DataProvider
	public static Object[][] getCarrierBankAccountData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CarrierBankAccountDetails");
	}

	@DataProvider
	public static Object[][] getRTFLogindata() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "RTFLogin");
	}

	@DataProvider
	public static Object[][] getExtendedCreditAmount() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CreditAmount");
	}

	@DataProvider
	public static Object[][] getCreditAmount() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "ExtendedCreditAmount");
	}

	@DataProvider
	public static Object[][] getLoginHandshakewithRTF_CarrierDa() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "LoginHandshakewithRTF_CarrierDa");
	}

	@DataProvider
	public static Object[][] getBrokerCanNotRegisterDuplicateEmailData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BrokerDuplicateEmail");
	}

	@DataProvider
	public static Object[][] getCarrierCanNotRegisterDuplicateEmailData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CarrierDuplicateEmail");
	}

	@DataProvider
	public static Object[][] getITSDispatchLogindata() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "ITSDispatchLoginData");
	}

	@DataProvider
	public static Object[][] getUpdatedBrokerPaymentData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BrokerUpdatedPaymentData");
	}

	@DataProvider
	public static Object[][] getUpdatedCarrierPaymentData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CarrierUpdatePaymentData");
	}

	@DataProvider
	public static Object[][] getAdminPaymentsGreaterthan45Days() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "AdminPaymentsGreaterthan45Days");
	}

	@DataProvider
	public static Object[][] getpayementmorethan45daysData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "payementmorethan45daysData");
	}

	@DataProvider
	public static Object[][] getAdminCustomersSideMenSearchData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "AdminCustomersSideMenSearchData");
	}

	@AfterClass
	public void quit() throws InterruptedException {
		log.info("END TEST\n\n");

		if (TestUtil.videoStarted && prop.getProperty("useVideoCapture").contains("true")) {
			try {
				TestUtil.endVideoCapture();
			} catch (IOException e) {
				log.info(e);
			}

			TestUtil.updateVideoFileName();
		}

		driver.quit();
	}
}

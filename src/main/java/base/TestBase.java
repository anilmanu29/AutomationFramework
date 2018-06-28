package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import util.TestUtil;
import util.WebEventListener;
import utility.ReadExcel;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public  String Fileppath=System.getProperty("user.dir")+"/src/main/java/testdata/TestData.xlsx";
	
	
	
	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void initialization(){
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", 
					System.getProperty("user.dir")+ "/Drivers/chromedriver.exe");	
			driver = new ChromeDriver(); 
			driver.get(prop.getProperty("url"));
		}
		else if(browserName.equals("FF"))
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+ "/Drivers/geckodriver.exe");	
			driver = new FirefoxDriver(); 
		
			driver.get(prop.getProperty("url"));
			}
		else if(browserName.equals("IE")){
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+ "/Drivers/IEDriverServer.exe");	
			driver = new InternetExplorerDriver(); 
			driver.get(prop.getProperty("url"));
		}
		
			
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new util.WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		
	}
	
	@DataProvider
	public Object[][] getBrokerLoginData() throws InvalidFormatException, IOException
	{
	ReadExcel read = new ReadExcel();
	return read.getCellData(Fileppath, "BrokerLoginData");
	}
	@DataProvider
	public Object[][] getBrokerRegisterData() throws InvalidFormatException, IOException
	{
	ReadExcel read = new ReadExcel();
	return read.getCellData(Fileppath, "BrokerRegister");
	}
	@DataProvider
	public Object[][] getCarrierLoginData() throws InvalidFormatException, IOException
	{
	ReadExcel read = new ReadExcel();
	return read.getCellData(Fileppath, "CarrierLoginData");
	}
	
	@DataProvider
	public Object[][] getPaymentData() throws InvalidFormatException, IOException
	{
	ReadExcel read = new ReadExcel();
	return read.getCellData(Fileppath, "BrokerNewPaymentData");
	}
	@DataProvider
	public Object[][] getCarrierRegisterData() throws InvalidFormatException, IOException
	{
	ReadExcel read = new ReadExcel();
	return read.getCellData(Fileppath, "CarrierRegisterData");
	}
	@DataProvider
	public Object[][] getoutlookLoginData() throws InvalidFormatException, IOException
	{
	ReadExcel read = new ReadExcel();
	return read.getCellData(Fileppath, "outlookLoginData");
	}
	@DataProvider
	public Object[][] getAdminLoginData() throws InvalidFormatException, IOException
	{
	ReadExcel read = new ReadExcel();
	return read.getCellData(Fileppath, "AdminLogin");
	}
	
	@DataProvider
	public Object[][] getPaymentDataforUnmatchcarrier() throws InvalidFormatException, IOException
	{
	ReadExcel read = new ReadExcel();
	return read.getCellData(Fileppath, "BrokerPaymentDataforUnmatchedCr");
	}
	
	@DataProvider
	public Object[][] getBrokerData() throws InvalidFormatException, IOException
	{
	ReadExcel read = new ReadExcel();
	return read.getCellData(Fileppath, "BrokerRegisterCanada");
	}
	@DataProvider
	public Object[][] getCarrierData() throws InvalidFormatException, IOException
	{
	ReadExcel read = new ReadExcel();
	return read.getCellData(Fileppath, "CarrierRegisterCanada");
	}
	
	@DataProvider
	public Object[][] getCarrierFuelcardaccountNumbersData() throws InvalidFormatException, IOException
	{
	ReadExcel read = new ReadExcel();
	return read.getCellData(Fileppath, "CarrierFuelcardaccountNumbers");
	}
	
	@DataProvider
	public Object[][] getCarrierBankingData() throws InvalidFormatException, IOException
	{
	ReadExcel read = new ReadExcel();
	return read.getCellData(Fileppath, "CarrierBankingData");
	} 
	@DataProvider
	public Object[][] getBrokerBankingData() throws InvalidFormatException, IOException
	{
	ReadExcel read = new ReadExcel();
	return read.getCellData(Fileppath, "BrokerBankingData");
	}
	
	@DataProvider
	public Object[][] getCcarrierMatchedPayByCheckPayMNWData() throws InvalidFormatException, IOException
	{
	ReadExcel read = new ReadExcel();
	return read.getCellData(Fileppath, "CcarrierMatchedPayByCheckPayMNW");
	}
	
	@DataProvider
	public Object[][] getCarrierPaidTabData() throws InvalidFormatException, IOException
	{
	ReadExcel read = new ReadExcel();
	return read.getCellData(Fileppath, "CarrierPaidTabData");
	}
	
	@DataProvider
	public Object[][] getCarrierlockedaccountAdminUnlockData() throws InvalidFormatException, IOException
	{
	ReadExcel read = new ReadExcel();
	return read.getCellData(Fileppath, "CarrierlockedaccountAdminUnlock");
	}
	
    @DataProvider
    public Object[][] getCarrierParentChildData() throws InvalidFormatException, IOException
    {
    ReadExcel read = new ReadExcel();
    return read.getCellData(Fileppath, "carrierparentchilddata");
    }

    @DataProvider
    public Object[][] getCarrierParentChildPasswordData() throws InvalidFormatException, IOException
    {
    ReadExcel read = new ReadExcel();
    return read.getCellData(Fileppath, "carrierresetpassworddata");
    }
    
    @DataProvider
    public Object[][] getBulkUploadPaymentsmatchedData() throws InvalidFormatException, IOException
    {
    ReadExcel read = new ReadExcel();
    return read.getCellData(Fileppath, "BulkUploadPaymentsmatched");
    }
    
    @DataProvider
    public Object[][] getBulkUploadPaymentsUnmatchedData() throws InvalidFormatException, IOException
    {
    ReadExcel read = new ReadExcel();
    return read.getCellData(Fileppath, "BulkUploadPaymentsUnmatched");
    }

    @DataProvider
    public Object[][] getCarrierSchedulePaymentTabData() throws InvalidFormatException, IOException
    {
    ReadExcel read = new ReadExcel();
    return read.getCellData(Fileppath, "CarrierSchedulePaymentTabData");
    }
    
    @DataProvider
    public Object[][] getAdminSearchData() throws InvalidFormatException, IOException
    {
    ReadExcel read = new ReadExcel();
    return read.getCellData(Fileppath, "AdminSearchData");
    }

	@AfterClass
	public void quit()
	{
		driver.quit();
	}
}
	 
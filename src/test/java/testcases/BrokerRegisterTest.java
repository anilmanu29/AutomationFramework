package testcases;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.BrokerRegister;


public class BrokerRegisterTest extends TestBase
{
  BrokerRegister r;
  Select type;
  Select stateof;
  Select payment;
  Select state;
  Select country;
  Select Payments;
  WebElement PaymentTerms;
  public static String emailid;
  
	public BrokerRegisterTest()
	{
		super();
	}
  
  @BeforeClass
	public void setUp()
	{
		
		initialization();
		r = new BrokerRegister();	
	}

  @Test( dataProvider = "getBrokerRegisterData", priority=1)
  public void BrokerRegister( String Dotnumber, String CompanyName, String DoingBussinessAS, String Email, String ConfirmEmail, String ZipCode1, String Address, String City,
      String FirstNames, String LastName, String PhoneNumber, String Password, String ConfirmPassword, String NameonAccount, String RoutingNumber, String BankAccountNumber,
      String ConfirmbankAccountNumber ) throws IOException, InterruptedException
  {
   
    r.signup();
    
    // clicking on carrier Register
 
    r.shipperRegister();
    
    
    r.companyname( CompanyName );

	Thread.sleep(1000);
	
    r.doingbussiness( DoingBussinessAS );
    
    
    r.selectType();

	Thread.sleep(1000);
	
    Select type = new Select( driver.findElement( By.xpath( ".//*[@id='EntityType']" ) ) );
    
    type.selectByVisibleText( "C Corporation" );
  
    r.countryofincorporation();

	Thread.sleep(1000);
	
    Select countryof = new Select( driver.findElement( By.xpath( ".//*[@id='IncorporationCountry']" ) ) );
    
    countryof.selectByIndex( 0 );
    
    r.stateofincorporation();

	Thread.sleep(1000);
	
    Select stateof = new Select( driver.findElement( By.xpath( ".//*[@id='IncorporationState']" ) ) );
    
    stateof.selectByVisibleText( "California" );
   
    emailid=  r.BrokerEmail( Email );
 
    r.confirmEmail( ConfirmEmail );

	Thread.sleep(1000);
	r.iCertifyClick();
	Thread.sleep(1000);
    r.paymentTerm();

	Thread.sleep(1000);
    r.next();

Thread.sleep(1000);
	
r.ZipCode( ZipCode1 );

	Thread.sleep(1000);
	
    r.country();

	Thread.sleep(1000);
	
    Select country = new Select( driver.findElement( By.xpath( ".//*[@id='OriginCountry']" ) ) );
    country.selectByVisibleText( "USA" );
    
    r.address( Address );

	Thread.sleep(1000);
	
    r.city( City );
    
    r.State();

	Thread.sleep(1000);
	
    Select state = new Select( driver.findElement( By.xpath( ".//*[@id='State']" ) ) );
    
    state.selectByVisibleText( "CA" );

	Thread.sleep(1000);
    r.submit();
	Thread.sleep(1000);
    r.ContactFirstName(FirstNames);
	Thread.sleep(1000);
	r.LastName(LastName);
	r.Phone(PhoneNumber);
    Thread.sleep(1000);
    r.Password( Password );
    Thread.sleep(1000);
    driver.findElement(By.xpath(".//*[@id='Registration_User_Password']"));
	r.ConfirmPassword(ConfirmPassword);
  	Thread.sleep(1000);
  	r.Next();
  	Thread.sleep(1000);
  	r.AccountName(NameonAccount);
  	r.BankingAccount(BankAccountNumber);
	Thread.sleep(1000);
  	r.BankingRouting(RoutingNumber);
	Thread.sleep(1000);
	Thread.sleep(1000);
r.ConfirmBankingAccount(ConfirmbankAccountNumber);
	Thread.sleep(1000);
	Thread.sleep(1000);
    r.submit();
    System.out.println(" Broker Register Completed...");
	Thread.sleep(1000);
	
    System.out.print( "BrokerRegisterSuccessfully");
  
  }
 
}

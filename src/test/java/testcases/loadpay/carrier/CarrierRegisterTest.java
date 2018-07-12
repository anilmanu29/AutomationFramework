	package testcases.loadpay.carrier;
	import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierRegisterPage;

	

	public class CarrierRegisterTest extends TestBase
	{
	  CarrierRegisterPage cr;
	  Select type;
	  Select stateof;
	  Select payment;
	  Select state;
	  Select country;
	  Select Payments;
	  WebElement PaymentTerms;
	  public static String email;
	  
		public CarrierRegisterTest()
		{
			super();
		}
	  
	  @BeforeClass
		public void setUp()
		{
			
			initialization();
			cr = new CarrierRegisterPage(); 
		}

	  @Test(dataProvider ="getCarrierRegisterData")
	  public void CarrierRegister( String Dotnumber, String CompanyName, String DoingBussinessAS, String Email, String ConfirmEmail, String ZipCode1, String Address, String City,
	      String FirstNames, String LastName, String PhoneNumber, String Password, String ConfirmPassword, String NameonAccount, String RoutingNumber, String BankAccountNumber,
	      String ConfirmbankAccountNumber ) throws IOException, InterruptedException
	  {
	   
	    cr.signup();
	    
	    // clicking on carrier Register
	 
	    cr.CarrierRegister();
	    
	    
	    cr.companyname( CompanyName );

		Thread.sleep(1000);
		
	    cr.doingbussiness( DoingBussinessAS );
	    
	    
	    cr.selectType();

		Thread.sleep(1000);
		
	    Select type = new Select( driver.findElement( By.xpath( ".//*[@id='EntityType']" ) ) );
	    
	    type.selectByVisibleText( "C Corporation" );
	  
	    cr.countryofincorporation();

		Thread.sleep(1000);
		
	    Select countryof = new Select( driver.findElement( By.xpath( ".//*[@id='IncorporationCountry']" ) ) );
	    
	    countryof.selectByIndex( 0 );
	    
	    cr.stateofincorporation();

		Thread.sleep(1000);
		
	    Select stateof = new Select( driver.findElement( By.xpath( ".//*[@id='IncorporationState']" ) ) );
	    
	    stateof.selectByVisibleText( "California" );
	   
	    email = cr.CarrierEmail(Email);
	    
	 
	    cr.confirmEmail( ConfirmEmail );

		Thread.sleep(1000);
		cr.iCertifyClick();
		Thread.sleep(1000);

		Thread.sleep(1000);
	    cr.next();

	Thread.sleep(1000);
		
	cr.ZipCode( ZipCode1 );

		Thread.sleep(1000);
		
	    cr.country();

		Thread.sleep(1000);
		
	    Select country = new Select( driver.findElement( By.xpath( ".//*[@id='OriginCountry']" ) ) );
	    country.selectByVisibleText( "USA" );
	    
	    cr.address( Address );

		Thread.sleep(1000);
		
	    cr.city( City );
	    
	    cr.State();

		Thread.sleep(1000);
		
	    Select state = new Select( driver.findElement( By.xpath( ".//*[@id='State']" ) ) );
	    
	    state.selectByVisibleText( "CA" );

		Thread.sleep(1000);
	    cr.submit();
		Thread.sleep(1000);
	    cr.ContactFirstName(FirstNames);
		Thread.sleep(1000);
		cr.LastName(LastName);
		cr.Phone(PhoneNumber);
	    Thread.sleep(1000);
	    cr.Password( Password );
	    Thread.sleep(1000);
	    driver.findElement(By.xpath(".//*[@id='Registration_User_Password']"));
		cr.ConfirmPassword(ConfirmPassword);
	  	Thread.sleep(1000);
	  	cr.Next();
	  	Thread.sleep(1000);
	  	cr.AccountName(NameonAccount);
	  	cr.BankingAccount(BankAccountNumber);
		Thread.sleep(1000);
	  	cr.BankingRouting(RoutingNumber);
		Thread.sleep(1000);
		Thread.sleep(1000);
		cr.ConfirmBankingAccount(ConfirmbankAccountNumber);
		Thread.sleep(1000);
		Thread.sleep(1000);
	    cr.submit();
	    System.out.println("Carrier Registration Completed...");
	  
	  }
	
}

	
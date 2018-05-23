package testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.CarrierRegisterCanada;


public class CarrierRegisterCanadaTest  extends TestBase
{
	CarrierRegisterCanada cr;
	  Select type;
	  Select stateof;
	  Select payment;
	  Select state;
	  Select country;
	  Select Payments;
	  WebElement PaymentTerms;
	  public static String cemail; 
	  public static String password;
	  
		public CarrierRegisterCanadaTest()
		{
			super();
		}
	  
	  @BeforeClass
		public void setUp()
		{
			
			initialization();
			cr = new CarrierRegisterCanada(); 
		}

	  @Test(dataProvider ="getCarrierData", priority=6)
	  public void CarrierRegister( String Dotnumber, String CompanyName, String DoingBussinessAS, String Email, String ConfirmEmail, String country, String state, String ZipCode1, String Address, String City, String ocountry, String States,
	      String FirstNames, String LastName, String PhoneNumber, String Password, String ConfirmPassword, String NameonAccount, String RoutingNumber, String BankAccountNumber,
	      String ConfirmbankAccountNumber ) throws IOException, InterruptedException
	  {
	   
	    cr.signup();
	    cr.CarrierRegister();
	    Thread.sleep(1000);
	   if(Dotnumber==null)
	   {
		   cr.company(CompanyName);
		   Thread.sleep(1000);
	   }
	   else
	   {
		   Thread.sleep(1000);
		   cr.dotnumber(Dotnumber);
	    	
	   }
	   Thread.sleep(1000);
	   	cr.doingbussiness( DoingBussinessAS );
	    cr.selectType();
	    Thread.sleep(1000);
		Select type = new Select( driver.findElement( By.xpath( ".//*[@id='EntityType']" ) ) );
	    type.selectByVisibleText( "C Corporation" );
		Thread.sleep(1000);
		cr.countrydropdown(country, state);
		Thread.sleep(1000);
		cemail =  cr.CarrierEmail(Email);
	    cr.confirmEmail( ConfirmEmail );
		Thread.sleep(1000);
		cr.iCertifyClick();
		Thread.sleep(1000);
	    cr.next();
	    Thread.sleep(1000);
	    if(Dotnumber==null)
	    {
			
			Thread.sleep(1000);
			 cr.originCountry(ocountry, States);
			Thread.sleep(1000);
			cr.ZipCode( ZipCode1 );
			cr.address( Address );
			Thread.sleep(1000);
			cr.city( City );
			Thread.sleep(1000);

		  }
		   else
		   {
			  cr.originCountry(ocountry, States);
			  cr.ZipCode( ZipCode1 );
			  Thread.sleep(1000);
			}
		Thread.sleep(1000);
	    cr.submit();
		Thread.sleep(1000);
	    cr.ContactFirstName(FirstNames);
		Thread.sleep(1000);
		cr.LastName(LastName);
		cr.Phone(PhoneNumber);
	    Thread.sleep(1000);
	    password =  cr.Password( Password );
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
		cr.ConfirmBankingAccount(ConfirmbankAccountNumber);
		Thread.sleep(1000);
		Thread.sleep(1000);
	    cr.submit();
		Thread.sleep(1000);
  
	  }

}

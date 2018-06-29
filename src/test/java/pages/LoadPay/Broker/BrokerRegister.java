package pages.LoadPay.Broker;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import base.TestBase;

public class BrokerRegister extends TestBase
{
	
  @FindBy(xpath=".//*[@id='page-main']/div/div/div[2]/div/div/div[2]/div[2]/div/a")
	WebElement signupButton;
  @FindBy(xpath=".//*[@id='angularScope']/div[2]/div[3]/div/button[2]")
	WebElement BrokersignupButton;
  @FindBy(xpath=".//*[@id='DOT'] ")
	WebElement Dotnumber;
  @FindBy(xpath=".//*[@id='Name'] ")
	WebElement CompanyName;
  @FindBy(xpath=".//*[@id='DoingBusinessAs'] ")
	WebElement doingbussiness;
  @FindBy(xpath=".//*[@id='EntityType']")
	WebElement TypeofEntity;
  @FindBy(xpath=".//*[@id='IncorporationCountry'] ")
	WebElement countryIncorporation;
  @FindBy(xpath=".//*[@id='IncorporationState']")
	WebElement stateIncorporation;
  @FindBy(xpath=".//*[@id='Registration_User_UserName']")
	WebElement Email;
  @FindBy(xpath=" .//*[@id='Registration_User_UserNameConfirm']")
	WebElement confirmEmail ;
  @FindBy(xpath=" .//*[@id='ICertify']")
	WebElement Icertify;
  
  @FindBy(xpath=".//*[@id='PMNTerm'] ")
	WebElement PaymentTerms;
  
  @FindBy(xpath=".//*[@id='formCompany']/div/div[12]/div/div/input ")
	WebElement Next;
  @FindBy(xpath="//input[@id='ZipCode']")
	WebElement ZipCode;
@FindBy(xpath="//select[@id='OriginCountry']")
	WebElement Country;
@FindBy(xpath="//input[@id='StreetAddress']")
	WebElement address;
@FindBy(xpath="//input[@id='City']")
	WebElement city;
@FindBy(xpath="//select[@id='State']")
	WebElement State; 
@FindBy(xpath="//input[@type='submit']")
	WebElement submit; 

@FindBy(xpath="//input[@id='ContactFirstName']")
	WebElement ContactFirstName;
@FindBy(xpath="//input[contains(@id,'LastName')]")
	WebElement LastName;
@FindBy(xpath="//input[contains(@id,'Phone')]")
	WebElement Phone;
@FindBy(xpath="//input[contains(@id,'Ext')]")
	WebElement Ext;
@FindBy(xpath="//input[contains(@id,'Mobile')]")
	WebElement Mobile;
@FindBy(xpath="//input[contains(@id,'Password')]")
	WebElement Password;
@FindBy(xpath="//input[contains(@id,'ConfirmPassword')]")
	WebElement ConfirmPassword;
@FindBy(xpath="//input[@type='submit']")
	WebElement submit1;

@FindBy(xpath="//input[contains(@id,'AccountName')]")
	WebElement AccountName;

@FindBy(xpath="//input[@id='BankingRouting']")
	WebElement BankingRouting;

@FindBy(xpath="//input[@id='BankingAccount']")
	WebElement BankingAccount;
@FindBy(xpath="//input[@id='ConfirmBankingAccount']")
	WebElement ConfirmBankingAccount;
@FindBy(xpath="//input[@type='submit']")
	WebElement submit2;


  
  
  public BrokerRegister()
	{
		PageFactory.initElements(driver, this);
	} 
  
  public void signup()
  {
	  signupButton.click();
  }

  public void shipperRegister()
  {
	  BrokersignupButton.click();
  }

  public void dotnumber( String Dot )
  {
	  Dotnumber.sendKeys(Dot);
  }

  public void companyname( String Company )
  {
	  CompanyName.sendKeys(Company);
  }

  public void doingbussiness( String doingBussiness )
  {
	  doingbussiness.sendKeys(doingBussiness);
  }

  public void selectType()
  {
    TypeofEntity.click();
   
  }

  public void countryofincorporation()
  {
	  countryIncorporation.click();
  }

  public void stateofincorporation()
  {
	  stateIncorporation.click();
  }

  public String BrokerEmail( String email )
  {
	  Email.sendKeys(email);
	  return email;
  }

  public void confirmEmail( String ConfirmEmail )
  {
  confirmEmail.sendKeys(ConfirmEmail);
	  
  }

  public void iCertifyClick()
  {
  Icertify.click();
  }

  public void paymentTerm()
  {
	  PaymentTerms.click();
	  Select pay = new Select(PaymentTerms);
	    
		pay.selectByIndex( 2 );
	    
  }
  
  public void next()
  {
	  Next.click();
  }
   public void ZipCode(String ZipCode1)
  {
	  ZipCode.sendKeys(ZipCode1);
  }
  
  
  public void country()
  {
	  Country.click();
  }
  
  
  
  public void address(String Address)
  {
	  address.sendKeys(Address);
  }
  
  
  public void city(String City)
  {
	  city.sendKeys(City);
  }
  
  public void State()
  {
	  State.sendKeys();
  }
  
  public void submit()
  {


JavascriptExecutor js = (JavascriptExecutor)driver;
js.executeScript("arguments[0].click();", submit);
	  
	  //submit.click();
  }
  
  
  public void ContactFirstName(String FirstName)
  {
	  ContactFirstName.sendKeys(FirstName);
  }
  
  public void LastName(String lastName)
  {
	  LastName.sendKeys(lastName);
  }
  
  
  public void Phone(String PhoneNumber)
  {
	  Phone.sendKeys(PhoneNumber);
  }
  
  public void Password(String pass)
  {
	  Password.sendKeys(pass);
  }
  
  
  public void ConfirmPassword(String confirmpass)
  {
	  ConfirmPassword.sendKeys(confirmpass);
  }
  
  public void Next()
  {

JavascriptExecutor js = (JavascriptExecutor)driver;
js.executeScript("arguments[0].click();", submit1);
  }
  
  
  public void AccountName(String NameonAccount)
  {
	  AccountName.sendKeys(NameonAccount);
  }
  
  
  
  public void BankingRouting(String routingNumber)
  {
	  BankingRouting.sendKeys(routingNumber);
  }
  
  
  public void BankingAccount(String BankAccountNumber)
  {
	  BankingAccount.sendKeys(BankAccountNumber);
  }
  
  public void ConfirmBankingAccount(String ConfirmBankAccountNumber)
  {
	  ConfirmBankingAccount.sendKeys(ConfirmBankAccountNumber);
  }
  
  public void Finish()
  {
	  submit2.click();
  }
} 
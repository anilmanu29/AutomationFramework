package pages;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import base.TestBase;

public class AdminLogin extends TestBase
{
	Select s;
	
	@FindBy(xpath=".//*[@id='UserName']")
	WebElement UserName;
	
	@FindBy(xpath=".//*[@id='Password']")
	WebElement Password;
	
	@FindBy(xpath="html/body/div[1]/div/div/div[2]/form/div[4]/input")
	WebElement loginBtn;

	@FindBy(xpath=".//*[@id='angularScope']/div[1]/div/div[1]/div/nav/div[2]/ul/li[8]/a")
	WebElement logOut;
	
	@FindBy(xpath=".//*[@id='angularScope']/div[1]/div/div[1]/div/nav/div[2]/ul/li[3]/a")
	WebElement CustomerTab;
	
	@FindBy(xpath="//*[@id='angularScope']/div[1]/div/div[2]/div/div/div[2]/div/div[2]/table/tbody/tr/td[1]/a")
	WebElement CustomerId;

	@FindBy(xpath=".//*[@id='searchKeyword']")
	WebElement search;
	

	@FindBy(xpath="//input[@value='Search']")
	WebElement ClickonSearchButton;
	

	@FindBy(xpath="//a[contains(@class,'ng-binding')]")
	WebElement DoubleClickID;
	

	@FindBy(xpath=".//*[@id='CustomerStatusId']")
	WebElement CustomersatatusIdDropDown ;
	
	@FindBy(xpath=".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[1]/a[7]")
	WebElement editloginuser ;
	
	@FindBy(xpath=".//*[@id='TabList']/div/table/tbody/tr/td[6]/input")
	WebElement CancelLockout ;


	@FindBy(xpath=".//*[@id='formCompany']/div[2]/input")
	WebElement updateButton;

	public AdminLogin() throws IOException 
		{	
	
		PageFactory.initElements(driver, this);
		}
		 
		public void adminUserPass(String Username, String pass) throws InterruptedException
		{
				UserName.sendKeys(Username);
				Password.sendKeys(pass);
			
		}
		
		public void adminLogin() throws InterruptedException
		{
			
			loginBtn.click();
			
	
		}
		public void  AdminLogOut() throws InterruptedException
		{
			logOut.click();
			
		}
		public void ClickOnCustomersTab() throws InterruptedException
		{
			CustomerTab.click();
		}
	public void ClickOnSearchBox(String keyword) throws InterruptedException
	{		
		search.click();
		search.sendKeys(keyword);
	}
	public void ClickOnSearchButton() throws InterruptedException
	{	
		ClickonSearchButton.click();	
	}
	public void DoubleClickID() throws InterruptedException
	{
		
		DoubleClickID.click();
	}
	
	public void clickCustomerId() throws InterruptedException
	{
		
		CustomerId.click();
	}
	
	public void clickeditloginuser() throws InterruptedException
	{
		
		editloginuser.click();
	}
	
	public void clickCancelLockout() throws InterruptedException
	{
		
		CancelLockout.click();
	}
	
	public void StatusIDDropDown() throws InterruptedException
	{
		Thread.sleep(1000);
		CustomersatatusIdDropDown.click();
		s=new Select((CustomersatatusIdDropDown));
		/*s.deselectByVisibleText("Active");*/
		s.selectByVisibleText("Active");
	}
	public void UpdateButton() throws InterruptedException
	{
		
		updateButton.click();
			
	}
	}
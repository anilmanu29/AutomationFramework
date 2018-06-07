package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class BrokerAvailableCreditTab extends TestBase{
	
	public static String bemail;
	WebDriverWait wait = null;
	Actions act = null;
	
	@FindBy(xpath = "//*[text()='AVAILABLE CREDIT']")
	WebElement AvailableCreditTab;
	
	@FindBy(xpath="//button[text()='REQUEST ADDITIONAL CREDIT']")
	WebElement RequestAdditionalCreditButton;

	@FindBy(xpath="//button[text()='Close']")
	WebElement CloseButton;
	
	public BrokerAvailableCreditTab() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		act = new Actions(driver);
	} 
	
	public void clickAvailableCreditTab() throws InterruptedException
	{	Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(AvailableCreditTab));
		Thread.sleep(1000);
		AvailableCreditTab.click();		
	}
	
	public void clickRequestAdditionalCreditButton() throws InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(RequestAdditionalCreditButton));
		Thread.sleep(2000);
		RequestAdditionalCreditButton.click();
  
	}
	public void clickCloseButton() throws InterruptedException
	{
		
		wait.until(ExpectedConditions.elementToBeClickable(CloseButton));
		Thread.sleep(1000);
		CloseButton.click();		
	}
	
}

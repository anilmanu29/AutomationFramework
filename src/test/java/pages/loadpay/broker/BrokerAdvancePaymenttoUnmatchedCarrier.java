package pages.loadpay.broker;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.TestBase;

public class BrokerAdvancePaymenttoUnmatchedCarrier extends TestBase {
	
	WebDriverWait wait = null;
	Actions act = null;
	JavascriptExecutor js;

	@FindBy(id = "AdvancePayment")
	private WebElement checkbox_AdvancePayment;
	
	@FindBy(xpath = "//input[@value='Schedule']")
	private WebElement schedulebutton;
	
	@FindBy(xpath = "//div[@role='alert']")
	private WebElement errormessage;
	
	
	
	/*-------PageFactory---------*/
	public BrokerAdvancePaymenttoUnmatchedCarrier() {
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, 20);
		act = new Actions(driver);
	}

	public void clickAdvancePaymentCheckbox()
	{
		if(!checkbox_AdvancePayment.isSelected())
		{
			js.executeScript("arguments[0].click();", checkbox_AdvancePayment);
		}
	}
	
	public void clickScheduleButton()
	{
		wait.until(ExpectedConditions.elementToBeClickable(schedulebutton));
		js.executeScript("arguments[0].click();", schedulebutton);	
	}
	
	public String alertMessage()
	{
		wait.until(ExpectedConditions.elementToBeClickable(errormessage));
		return errormessage.getText();
	}
}
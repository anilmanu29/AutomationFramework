package pages.loadpay.carrier;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class CarrierLoginPage extends TestBase{
	WebDriverWait wait;
	public static String cemail;
	
	//Page Factory - OR:
	@FindBy(xpath="//input[@id='UserName']")
	WebElement UserName;
	
	@FindBy(xpath="//input[@id='Password']")
	WebElement Password;
	
	@FindBy(xpath="//input[contains(@type,'submit')]")
	WebElement loginBtn;
	
	@FindBy(xpath = "//a[text()='Logoff']")
	private WebElement btn_logout;
	
	@FindBy(xpath="//*[@id='AcceptedTerms']")
	WebElement termsAndConditionsCheckBox;
	
	@FindBy(xpath="//*[@id='termsForm']/input")
	WebElement finishButton;
	
	@FindBy(xpath="//*[@id='angularScope']/div[3]/div/div/div[1]/div/p")
	WebElement confirmationPopup;
	
	@FindBy(xpath="//*[@id='angularScope']/div[3]/div/div/div[2]/button")
	WebElement confirmationPopupCloseBtn;
	
	@FindBy(xpath="//*[@id='EIN']")
	WebElement einField;
	
	@FindBy(xpath="//*[@id='formCompany']/input")
	WebElement einNextButton;
	
	@FindBy(xpath = ("//a[contains(text(), 'Forgot Password?')]"))
	WebElement forgotPassword;
	
	//Initializing the Page Objects:
	public CarrierLoginPage()
	{
		PageFactory.initElements(driver, this);
	}

	//Actions:
	
	/**
	 * @return the einField
	 */
	public WebElement getEinField() {
		return einField;
	}

	/**
	 * @param einField the einField to set
	 * @throws InterruptedException 
	 */
	public void setEinField(String EIN) throws InterruptedException {
		einField.clear();
		Thread.sleep(1000);
		einField.sendKeys(EIN);
	}
	
	public void clickEinNextButton()
	{
		einNextButton.click();
	}

	/**
	 * @return the termsAndConditionsCheckBox
	 */
	public WebElement getTermsAndConditionsCheckBox() {
		return termsAndConditionsCheckBox;
	}
	
	public void clickTermsAndConditionsCheckBox()
	{
		termsAndConditionsCheckBox.click();
	}

	/**
	 * @return the finishButton
	 */
	public WebElement getFinishButton() {
		return finishButton;
	}
	
	public void clickFinishButton()
	{
		finishButton.click();
	}

	/**
	 * @return the confirmationPopup
	 */
	public WebElement getConfirmationPopup() {
		return confirmationPopup;
	}
	
	public void clickConfirmationPopupCloseButton()
	{
		confirmationPopupCloseBtn.click();
	}
	
	public String validateLoginPageTitle()
	{
		return driver.getTitle();
	}

	
	public void Carrierlogin(String un, String pwd)
	{
		cemail=un;
		
				UserName.sendKeys(un);
				Password.sendKeys(pwd);
		//loginBtn.click();
		    	JavascriptExecutor js = (JavascriptExecutor)driver;
		    	js.executeScript("arguments[0].click();", loginBtn);
	}
	public void carrierVerificationLogin(String UserName, String NewPassword) {
		cemail = UserName;
		this.UserName.sendKeys(UserName);
		Password.sendKeys(NewPassword);
		//loginBtn.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", loginBtn);
	}




	public void CarrierLogout()
	{
		btn_logout.click();
	}

	public void verificationCarrierLogout() throws InterruptedException {
		//wait.until(ExpectedConditions.elementToBeClickable(btn_logout));
		Thread.sleep(3000);
		btn_logout.click();
	}



	public void forgotPasswordButton() {
		forgotPassword.click();
	}
	
}

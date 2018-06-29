package pages.LoadPay.Admin;

import base.TestBase;

import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminEditEmailCarrier extends TestBase {
	WebDriverWait wait;
	WebElement checkbox;
	
	@FindBy(xpath = ("//a[contains(text(),'Email / Login / Users')]"))
	WebElement emailLoginUsersPage;
	
	@FindBy(xpath = ("//a[contains(text(),'Company')]"))
	WebElement companyPage;
	
	@FindBy(xpath = (".//*[@id=\"formCompany\"]/div[2]/input"))
	WebElement updateCompanyButton;
	
	@FindBy(xpath = (".//*[@value='Refresh']"))
	WebElement refreshButton;
	
	@FindBy(xpath = (".//*[@value='Edit Email']"))
	WebElement editEmailButton;
	
	@FindBy(xpath = (".//*[@id=\"NewEmail\"]"))
	WebElement newEmailField;
	
	@FindBy(xpath = (".//*[@id=\"NewEmailConfirm\"]"))
	WebElement newEmailConfirmField;
	
	@FindBy(xpath = (".//*[@id=\"formEditEmailAddress\"]/section[1]/div/div[4]/div[5]/div/button[1]"))
	WebElement cancelEmailEditButton;
	
	@FindBy(xpath = (".//*[@id=\"formEditEmailAddress\"]/section[1]/div/div[4]/div[5]/div/button[2]"))
	WebElement updateEmailEditButton;
	
	@FindBy(xpath = (".//*[@id=\"formEditEmailAddress\"]/section[2]/div[4]/div/button"))
	WebElement closeEmailConfirmationButton;
	
	@FindBy(xpath = (".//*[@id=\"formEditEmailAddress\"]/section[2]/div[2]/div/label"))
	WebElement newLoadPayEmailLabel;
	
	@FindBy(xpath = (".//*[@id=\"TabList\"]/div/table/tbody/tr/td[1]/div/div[1]"))
	WebElement emailPagePrimaryAddress;

	public AdminEditEmailCarrier() {
	  PageFactory.initElements(driver, this);
	  wait = new WebDriverWait(driver, 10);
	}
	
	public void openEmailLoginUsersPage() {
	  emailLoginUsersPage.click();
	}
	
	public void openCompanyPage() {
		companyPage.click();
	}

	public void clickRefreshButton() {
	  refreshButton.click();
	}

	public void clickEditEmailButton() {
	  editEmailButton.click();
	}
  
	public void clickCancelEmailEditButton() {
	  cancelEmailEditButton.click();
	}
  
	public void clickUpdateEmailEditButton() {
	  updateEmailEditButton.click();
	}
	
	public void clickUpdateCompanyButton() {
		updateCompanyButton.click();
	}
  
	public void clickCloseEmailConfirmationButton() {
	  closeEmailConfirmationButton.click();
	}
	
	public void setNewEmailAddress(String emailAddress) {
	  newEmailField.clear();
	  newEmailField.sendKeys(emailAddress);
	}
  
	public void confirmNewEmailAddress(String emailAddress) {
	  newEmailConfirmField.clear();
	  newEmailConfirmField.sendKeys(emailAddress);
	}

	public WebElement getNewEmailField() {
		return newEmailField;
	}
	
	public WebElement getNewEmailConfirmField() {
		return newEmailConfirmField;
	}
	
	public WebElement getCancelEmailEditButton() {
		return cancelEmailEditButton;
	}
	
	public WebElement getUpdateEmailEditButton() {
		return updateEmailEditButton;
	}

	public WebElement getRefreshButton() {
		return refreshButton;
	}

	public WebElement getEditEmailButton() {
		return editEmailButton;
	}
	
	public WebElement getCloseEmailConfirmationButton() {
		return closeEmailConfirmationButton;
	}
	
	public WebElement getNewLoadPayEmailLabel() {
		return newLoadPayEmailLabel;
	}

	public WebElement getEmailPagePrimaryAddress() {
		return emailPagePrimaryAddress;
	}
	
	public int getRandomNumber(int min, int max)
	{
		return ThreadLocalRandom.current().nextInt(min, max);
	}
}
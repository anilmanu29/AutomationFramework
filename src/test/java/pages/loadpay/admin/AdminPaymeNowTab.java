package pages.loadpay.admin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class AdminPaymeNowTab extends TestBase {
	WebDriverWait wait;
	WebElement checkbox;

	@FindBy(xpath = ("//a[contains(text(),'PayMeNow')]"))
	WebElement PayMeNowTab;

	@FindBy(id = "PMNEnrolled")
	WebElement enrollInPaymeNowButton;

	@FindBy(id = "PMNLocked")
	WebElement lockPayMeNowStatusButton;

	@FindBy(xpath = "//*[@id='formPMN']/div/div[3]/input[2]")
	WebElement updateButton;

	public AdminPaymeNowTab() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}

	public void openPayMeNowTab() {
		wait.until(ExpectedConditions.elementToBeClickable(PayMeNowTab));
		PayMeNowTab.click();
	}

	public void clickEnrollInPayMeNow() {

		if (!enrollInPaymeNowButton.isSelected()) {
			wait.until(ExpectedConditions.elementToBeClickable(enrollInPaymeNowButton));
			enrollInPaymeNowButton.click();
		}
	}

	public void clickLockPaymeNowStatusButton() {

		if (!lockPayMeNowStatusButton.isSelected()) {
			wait.until(ExpectedConditions.elementToBeClickable(lockPayMeNowStatusButton));
			lockPayMeNowStatusButton.click();
		}
	}

	public void clickUpdateButton() {
		wait.until(ExpectedConditions.elementToBeClickable(updateButton));
		updateButton.click();
	}

}

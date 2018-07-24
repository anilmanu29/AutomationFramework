package pages.loadpay.admin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class AdminPayMeNowUnlockTab extends TestBase {
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

	public AdminPayMeNowUnlockTab() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 10);
	}

	public void openPayMeNowTab() {
		wait.until(ExpectedConditions.elementToBeClickable(PayMeNowTab));
		PayMeNowTab.click();
	}

	public void clickEnrollInPayMeNow() {

		if (!enrollInPaymeNowButton.isSelected()) {
			wait.until(ExpectedConditions.elementToBeClickable(enrollInPaymeNowButton));
			enrollInPaymeNowButton.click();
			log.info("User Enrolled in PayMeNow ");
		} else {
			log.info("User is not enrolled in PayMeNow");
		}
	}

	public void clickLockPaymeNowStatusButton() {

		if (!lockPayMeNowStatusButton.isSelected()) {
			wait.until(ExpectedConditions.elementToBeClickable(lockPayMeNowStatusButton));
			lockPayMeNowStatusButton.click();
			log.info("User Locked PayMeNow status button");
		}
	}

	public void clickUnLockPaymeNowStatusButton() {

		if (lockPayMeNowStatusButton.isSelected()) {
			wait.until(ExpectedConditions.elementToBeClickable(lockPayMeNowStatusButton));
			lockPayMeNowStatusButton.click();
			wait.until(ExpectedConditions.elementToBeClickable(enrollInPaymeNowButton));
			enrollInPaymeNowButton.click();

			log.info("User Unlocked PayMeNow status button");
			log.info("User Unenrolled from PayMeNow");
		}
	}

	public void clickUpdateButton() {
		wait.until(ExpectedConditions.elementToBeClickable(updateButton));
		updateButton.click();
	}

}

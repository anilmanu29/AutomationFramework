package pages.loadpay.broker;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class BrokerPayMeNowTab extends TestBase {

	WebDriverWait wait = null;
	JavascriptExecutor js;
	Actions act = null;

	@FindBy(xpath = "//a[contains(text(),'PayMeNow')]")
	WebElement payMeNowTab;

	@FindBy(id = "PMNEnrolled")
	WebElement enrollInPayMeNow;

	@FindBy(xpath = "//a[@href='#/MyAccount']")
	WebElement accountTab;

	@FindBy(xpath = ".//*[@id='formPMN']/div/div[3]/input[2]")
	WebElement updateButton;

	public BrokerPayMeNowTab() throws IOException {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 10);
		act = new Actions(driver);
		js = (JavascriptExecutor) driver;
	}

	public void openAccountTab() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(accountTab));
		accountTab.click();
	}

	public void openPayMeNowTab() {
		wait.until(ExpectedConditions.elementToBeClickable(payMeNowTab));
		payMeNowTab.click();

	}

	public void enrollPayMeNow() {
		wait.until(ExpectedConditions.elementToBeClickable(enrollInPayMeNow));
		enrollInPayMeNow.click();

	}

	public void updateButton() {
		wait.until(ExpectedConditions.visibilityOf(updateButton));
		js.executeScript("arguments[0].click();", updateButton);
		updateButton.click();
	}
}
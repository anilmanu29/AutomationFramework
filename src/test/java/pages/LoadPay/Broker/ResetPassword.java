package pages.LoadPay.Broker;

import base.TestBase;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.*;

public class ResetPassword extends TestBase {
    WebDriverWait wait;


    @FindBy(css= "input.btn.btn-default")
    WebElement resetPasswordButton;

    @FindBy(id="UserName")
    WebElement userNameField;

    @FindBy(css = "#page-main>h2")
    WebElement emailHasBeenSent;

    public ResetPassword() {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    public void enterUserName(String UserName){
        wait.until(ExpectedConditions.elementToBeClickable(userNameField));
		userNameField.sendKeys(UserName);
    }
    public void clickResetPassword() {
		wait.until(ExpectedConditions.elementToBeClickable(resetPasswordButton));
        resetPasswordButton.click();
    }

     public String verificationPage() {
         wait.until(ExpectedConditions.visibilityOf(emailHasBeenSent));
		return emailHasBeenSent.getText();

     }
}



package pages.loadpay.carrier;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;


public class CarrierEmailLoginUsersPage extends TestBase {
  WebDriverWait wait;
  JavascriptExecutor js;

  @FindBy(xpath = "//a[@href='#/MyAccount']")
  WebElement accountTab;

  @FindBy(xpath = "//a[contains(text(),'Email / Login / Users')]")
  WebElement emailLoginUsers;

  @FindBy(xpath = "//div[@id='loginId']")
  WebElement passwordAccountSecurityLink;

  @FindBy(xpath = "//a[contains(text(),'Change Password')]")
  WebElement changePasswordButton;

  @FindBy(xpath = "//input[@id='Old_Password']")
  WebElement currentPasswordField;

  @FindBy(id = "User_Password")
  WebElement newPasswordField;

  @FindBy(id = "User_ConfirmPassword")
  WebElement confirmNewPasswordField;

  @FindBy(xpath = "//form[@id='formLogIn']/div[4]/input")
  WebElement updateButton;

  @FindBy(xpath = "//div[contains(text(),'Saved')]")
  WebElement savedAlert;

  @FindBy(xpath = "//div[contains(text(),'Your new Password cannot be any of your last four Passwords.')]")
  WebElement errorMesssage;

  @FindBy(xpath = "//a[text()='Logoff']")
  WebElement logOff;


  public CarrierEmailLoginUsersPage() {
    PageFactory.initElements(driver, this);
    wait = new WebDriverWait(driver, 10);
    js = (JavascriptExecutor) driver;
  }

  public void openAccountTab() {
    wait.until(ExpectedConditions.elementToBeClickable(accountTab));
    accountTab.click();
  }

  public void goToEmailLoginUsers() throws InterruptedException {
    Thread.sleep(2000);
    wait.until(ExpectedConditions.elementToBeClickable(emailLoginUsers));
    js.executeScript("arguments[0].click();", emailLoginUsers);
    emailLoginUsers.click();
  }

  public void openPasswordAccountSecurityLink() throws InterruptedException {
    wait.until(ExpectedConditions.elementToBeClickable(passwordAccountSecurityLink));
    js.executeScript("arguments[0].click();", passwordAccountSecurityLink);
    passwordAccountSecurityLink.click();
  }

  public void clickChangePasswordButton() {
    wait.until(ExpectedConditions.elementToBeClickable(changePasswordButton));
    js.executeScript("arguments[0].click();", changePasswordButton);
    changePasswordButton.click();
    changePasswordButton.click();
  }

  public void clicCurrentPasswordField() {
    currentPasswordField.click();
  }

  public void enterCurrentPassword(String CurrentPassword) {
    currentPasswordField.sendKeys(CurrentPassword);
  }

  public void clickNewPasswordField() {
    wait.until(ExpectedConditions.elementToBeClickable(newPasswordField));
    newPasswordField.click();
  }

  public void enterNewPassword(String NewPassword) {
    newPasswordField.sendKeys(NewPassword);
  }

  public void clickConfirmNewPasswordField() {
    wait.until(ExpectedConditions.elementToBeClickable(confirmNewPasswordField));
    confirmNewPasswordField.click();
  }

  public void enterConfirmNewPasswordField(String ConfirmNewPassword) {
    confirmNewPasswordField.sendKeys(ConfirmNewPassword);
  }

  public void clickUpdateButton() {
    wait.until(ExpectedConditions.elementToBeClickable(updateButton));
    updateButton.click();
    wait.until(ExpectedConditions.visibilityOf(savedAlert));
  }


  public String verificationMessage() {
    return savedAlert.getText();
  }

  public boolean logOffButton() {
    return logOff.isDisplayed();

  }

 /* public boolean lastFourPasswords() {
    return errorMesssage.isDisplayed();
  }*/
}
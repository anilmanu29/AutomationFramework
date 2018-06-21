package pages;

import base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


  public class AdminPayMeNowUnlockTab extends TestBase {
    WebDriverWait wait;
    WebElement checkbox;

    @FindBy(xpath = ("//a[contains(text(),'PayMeNow')]"))
    static WebElement PayMeNowTab;

    @FindBy(id = "PMNEnrolled")
    static
    WebElement enrollInPaymeNowButton;

    @FindBy(id = "PMNLocked")
    static
    WebElement lockPayMeNowStatusButton;

    @FindBy(xpath = "//*[@id='formPMN']/div/div[3]/input[2]")
    static
    WebElement updateButton;


    public AdminPayMeNowUnlockTab() {
      PageFactory.initElements(driver, this);
      wait = new WebDriverWait(driver, 10);
    }

    public static void openPayMeNowTab() {
      PayMeNowTab.click();
    }

    public static void clickEnrollInPayMeNow() {

      if(!enrollInPaymeNowButton.isSelected()) {
        enrollInPaymeNowButton.click();
        System.out.println("User Enrolled in PayMeNow ");
      } else {
        System.out.println("User is not enrolled in PayMeNow");
      }
    }

    public static void clickLockPaymeNowStatusButton() {

      if(!lockPayMeNowStatusButton.isSelected()) {
        lockPayMeNowStatusButton.click();
        System.out.println("User Locked PayMeNow status button");
      }
    }

    public static void clickUnLockPaymeNowStatusButton() {

      if(lockPayMeNowStatusButton.isSelected()) {
        lockPayMeNowStatusButton.click();
        enrollInPaymeNowButton.click();

        System.out.println("User Unlocked PayMeNow status button");
        System.out.println("User Unenrolled from PayMeNow");
      }
    }

    public static void clickUpdateButton() {
      updateButton.click();
    }

  }




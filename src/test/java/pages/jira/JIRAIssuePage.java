package pages.jira;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class JIRAIssuePage extends TestBase {
	private WebDriver driver;
	JavascriptExecutor js;

	@FindBy(id = "unfreezedGridBody")
	private WebElement executionGridBody;

	public JIRAIssuePage() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		js = (JavascriptExecutor) driver;
	}

}

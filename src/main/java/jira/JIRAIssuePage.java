package jira;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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

	public void goToJiraURL(String jiraID) {
		String url = prop.getProperty("JiraBrowseURL") + jiraID;
		driver.navigate().to(url);
		wait.until(ExpectedConditions.urlToBe(url));
	}

	public void updateExecutionStatus(String executionStatus) {
		Select select = new Select(driver.findElement(By.xpath("@id='dropDown' and @title='UNEXECUTED']")));
		select.selectByValue(executionStatus);

		driver.navigate().refresh();

	}

}

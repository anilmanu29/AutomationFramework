package jira;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import base.TestBase;

public class JIRAIssuePage extends TestBase {

	@FindBy(id = "unfreezedGridBody")
	public static WebElement executionGridBody;

	public JIRAIssuePage() {
		PageFactory.initElements(driver, this);
	}

	public static void goToJiraIssuePage(String jiraID) {
		String url = prop.getProperty("JiraBrowseURL") + jiraID;
		driver.navigate().to(url);
		wait.until(ExpectedConditions.urlToBe(url));
	}

	public static void updateExecutionStatus(String executionStatus) {
		Select select = new Select(driver.findElement(By.xpath("@id='dropDown' and @title='UNEXECUTED']")));
		select.selectByValue(executionStatus);

		driver.navigate().refresh();

	}

}

package pages;
import org.openqa.selenium.support.PageFactory;
import base.TestBase;
import java.awt.AWTException;
import java.io.IOException;

public class AdminHomePage extends TestBase
{
		public AdminHomePage() throws IOException 
		{
			PageFactory.initElements(driver, this);
			
		}
		
		public void AdminURL() throws AWTException, InterruptedException
		{  
			
			driver.get(super.prop.getProperty("AdminURL"));
			
		}
}

		
		
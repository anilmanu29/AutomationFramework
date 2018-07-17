/*
 * @autor : ANILKUMAR
 * 
 */
package extentreportlistener;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReporterNG implements IReporter {
	private ExtentReports extent;

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
			String outputDirectory) {
		String currentDir = System.getProperty("user.dir"); 
		
		long currentTimeMillis = System.currentTimeMillis();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd,yyyy HH:mm");    
		Date formattedDate = new Date(currentTimeMillis);
		String currentDate = dateFormat.format(formattedDate);
		
		extent = new ExtentReports(currentDir + "/output/" + "/Reports/" + currentDate + File.separator
				+ "Extent.html", true);
		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();

				buildTestNodes(context.getPassedTests(), LogStatus.PASS);
				buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
				buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
			}
		}

		extent.flush();
		extent.close();
	}

	private void buildTestNodes(IResultMap tests, LogStatus status) {
		ExtentTest test;

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.startTest(result.getMethod().getMethodName());

				test.setStartedTime(getTime(result.getStartMillis()));
				test.setEndedTime(getTime(result.getEndMillis()));

				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);

				if (result.getThrowable() != null) {
					test.log(status, result.getThrowable());
				} else {
					test.log(status, "Test " + status.toString().toLowerCase()
							+ "ed");
				}

				extent.endTest(test);
			}
		}
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
	
	 @SuppressWarnings("unused")
	private String millisToTimeConversion(long seconds) {

	        final int MINUTES_IN_AN_HOUR = 60;
	        final int SECONDS_IN_A_MINUTE = 60;

	        int minutes = (int) (seconds / SECONDS_IN_A_MINUTE);
	        seconds -= minutes * SECONDS_IN_A_MINUTE;

	        int hours = minutes / MINUTES_IN_AN_HOUR;
	        minutes -= hours * MINUTES_IN_AN_HOUR;

	        return prefixZeroToDigit(hours) + ":" + prefixZeroToDigit(minutes) + ":" + prefixZeroToDigit((int)seconds);
	    }

	private String prefixZeroToDigit(int num){
	        int number=num;
	        if(number<=9)
	            return "0"+number;
	        else
	            return ""+number;
	    }
}
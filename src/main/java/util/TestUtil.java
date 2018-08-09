package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import base.TestBase;

public class TestUtil extends TestBase {

	public static final long PAGE_LOAD_TIMEOUT = 20;
	public static final long IMPLICIT_WAIT = 20;
	public static final String TESTDATA_SHEET_PATH = System.getProperty("user.dir") + "/src/main/java/com/ra"
			+ "/qa/testdata/TestData.xlsx";

	Workbook book;
	Sheet sheet;

	public void switchToFrame() {
		driver.switchTo().frame("mainpanel");
	}

	public Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			log.info(e);
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			log.info(e);
		} catch (IOException e) {
			log.info(e);
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;
	}

	public void takeScreenshotAtEndOfTest() throws IOException {

		Calendar now = Calendar.getInstance();

		int currentYear = now.get(Calendar.YEAR);
		int currentMonth = now.get(Calendar.MONTH) + 1;

		String strYear = Integer.toString(currentYear);
		String strMonth = "";

		if (currentMonth < 10)
			strMonth = "0" + Integer.toString(currentMonth);

		// (1) get today's date
		Date today = Calendar.getInstance().getTime();

		// (2) create a date "formatter" (the date format we want)
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss_zzz");

		// (3) create a new String using the date format we want
		String fileName = formatter.format(today);

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");

		FileUtils.copyFile(scrFile, new File(
				currentDir + "/output/screenshots/" + strYear + "/" + strMonth + File.separator + fileName + ".png"));

	}

	// this function takes a single string and removes the .0 that Excel likes to
	// add regardless of formatting
	public static String removeDecimalZeroFormat(String strData) {
		if (strData.contains(".0"))
			strData = strData.replace(".0", "");

		return strData;
	}

	public static void DateFormatTestExample(String[] args) {
		Calendar currentDateTime = Calendar.getInstance();
		System.out.println("---------------------------------------------------");
		System.out.println("-- Get Year/Month/Day/Hour/Minute using Calendar --");
		System.out.println("---------------------------------------------------");
		System.out.println("Year - Calendar [" + currentDateTime.get(Calendar.YEAR) + "]");
		System.out.println("Month - Calendar [" + currentDateTime.get(Calendar.MONTH) + "]");
		System.out.println("Day - Calendar [" + currentDateTime.get(Calendar.DAY_OF_MONTH) + "]");
		System.out.println("Hour - Calendar [" + currentDateTime.get(Calendar.HOUR_OF_DAY) + "]");
		System.out.println("Minute - Calendar [" + currentDateTime.get(Calendar.MINUTE) + "]");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-- Get Year/Month/Day/Hour/Minute and Time Zone using SimpleDateFormat --");
		System.out.println("-------------------------------------------------------------------------");
		SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
		SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
		SimpleDateFormat minuteFormat = new SimpleDateFormat("mm");
		SimpleDateFormat timeZoneFormat = new SimpleDateFormat("zzz");

		Date currentDate = new Date(currentDateTime.getTimeInMillis());
		System.out.println("Year - SimpleDateFormat [" + yearFormat.format(currentDate) + "]");
		System.out.println("Month - SimpleDateFormat [" + monthFormat.format(currentDate) + "]");
		System.out.println("Day - SimpleDateFormat [" + dayFormat.format(currentDate) + "]");
		System.out.println("Hour - SimpleDateFormat [" + hourFormat.format(currentDate) + "]");
		System.out.println("Minute - SimpleDateFormat [" + minuteFormat.format(currentDate) + "]");
		System.out.println("Time Zone - SimpleDateFormat [" + timeZoneFormat.format(currentDate) + "]");
	}

}

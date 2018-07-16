package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import base.TestBase;

public class TestUtil extends TestBase{
	
	public static final long PAGE_LOAD_TIMEOUT = 20;
	public static final long IMPLICIT_WAIT = 20;
	public static final String TESTDATA_SHEET_PATH = System.getProperty("user.dir")+ "/src/main/java/com/ra"
					+ "/qa/testdata/TestData.xlsx";
	
	Workbook book;
	Sheet sheet;

	public void switchToFrame(){
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
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		
		FileUtils.copyFile(scrFile, new File(currentDir + "/output/screenshots/" + System.currentTimeMillis() + ".png"));
		
		}
	
	//this function takes a single string and removes the .0 that Excel likes to add regardless of formatting
	public static String removeDecimalZeroFormat(String strData)
	{
		if(strData.contains(".0"))
			strData = strData.replace(".0","");
		
		return strData;
	}

}

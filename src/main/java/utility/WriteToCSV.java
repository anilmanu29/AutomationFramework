package utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

public class WriteToCSV {

	public static void writeDataForCustomSeperatorCSV(String filePath, Iterable<String[]> dataList) {

		// first create file object for file placed at location
		// specified by filepath
		File file = new File(filePath);

		try {
			// create FileWriter object with file as parameter
			FileWriter outputfile = new FileWriter(file);

			// create CSVWriter with '|' as separator
			CSVWriter writer = new CSVWriter(outputfile, '|', CSVWriter.NO_QUOTE_CHARACTER,
					CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

			// create a List which contains String array
			List<String[]> data = new ArrayList<String[]>();

			data.add(new String[] { "stuff" });

			writer.writeAll(dataList);

			// closing writer connection
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

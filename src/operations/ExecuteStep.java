package operations;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;

import excelExportAndFileIO.ExcelReader;

public class ExecuteStep {
	private WebDriver _webdriver;
	public ExecuteStep(WebDriver webdriver) {
		_webdriver = webdriver;
	}

	public void ExecuteTestStep(String sheetName, String objectName) throws Exception {

		ExcelReader file = new ExcelReader();
		UIOperation operation = new UIOperation(_webdriver);

		Sheet sheet = file.readExcel("..//myShopHybridFramework//FileFolder", "Keywords.xlsx", sheetName);

		// Find number of rows in excel file
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		Row row = null;

		// Create a loop over all the rows of excel file to read it
		for (int i = 1; i <= rowCount; i++) {
			row = sheet.getRow(i);
			if (row != null) {
				if (row.getCell(1).toString().equalsIgnoreCase(objectName)) {
					Cell dataCell = row.getCell(4, Row.CREATE_NULL_AS_BLANK);

					// Call perform function to perform operation on UI
					operation.perform(row.getCell(0).toString(), row.getCell(2).toString(), row.getCell(3).toString(),
							dataCell.toString());
					break;
				}

			}
		}

	}

}
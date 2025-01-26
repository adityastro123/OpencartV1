package utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "Login")
	public String[][] loginData() throws Exception {
		String xlPath = "./testData/LoginCredentials.xlsx";

		ExcelUtility xlutils = new ExcelUtility(xlPath);
		
		String sheetName = "credentials";
		
		int rowCount = xlutils.getRowCount(sheetName);
		int cellCount = xlutils.getCellCount(sheetName, 1);
		
		String[][] loginCredentials = new String[rowCount - 1][cellCount];
		
		for(int i = 1; i < rowCount; i++) {
			for(int j = 0; j < cellCount; j++) {
				loginCredentials[i - 1][j] = xlutils.getCellData(sheetName, i, j);
			}
		}
		
		return loginCredentials;
	}
}

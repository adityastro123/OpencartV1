package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public FileInputStream fis;
	public FileOutputStream fos;
	public XSSFWorkbook wb;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public String filePath;

	public ExcelUtility(String filePath) {
		this.filePath = filePath;
	}

	public int getRowCount(String sheetName) throws Exception {
		fis = new FileInputStream(filePath);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheet(sheetName);
		wb.close();
		fis.close();
		return (sheet.getLastRowNum() - sheet.getFirstRowNum() + 1); // for 4 rows of data getLastRow will give 3 and getFirstRow will give 0
	}

	public int getCellCount(String sheetName, int rowNum) throws Exception {
		fis = new FileInputStream(filePath);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		int cellCount = row.getLastCellNum() - row.getFirstCellNum();
		wb.close();
		fis.close();
		return cellCount;
	}

	public String getCellData(String sheetName, int rowNum, int colNum) throws Exception {

		fis = new FileInputStream(filePath);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);

		String data;
		try {
			data = cell.toString();
		} catch (Exception e) {
			data = "";
		}

		wb.close();
		fis.close();
		return data;

	}

	public void setCellData(String sheetName, int rowNum, int colNum, String cellData) throws Exception {
		File file = new File(filePath);
		if (!file.exists()) {
			wb = new XSSFWorkbook(); // creating the workbook in the memory and then will be added in the file using FilOutputStream
		}else {
			fis = new FileInputStream(filePath);
			wb = new XSSFWorkbook(fis);
			fis.close();
		}

		
		if (wb.getSheet(sheetName) == null) {
			sheet = wb.createSheet(sheetName);
		}

		sheet = wb.getSheet(sheetName);

		if (sheet.getRow(rowNum) == null) {
			sheet.createRow(rowNum);
		}
		row = sheet.getRow(rowNum);

		cell = row.createCell(colNum);
		cell.setCellValue(cellData);
		fos = new FileOutputStream(filePath);
		wb.write(fos);
		wb.close();
		fos.close();

	}
}

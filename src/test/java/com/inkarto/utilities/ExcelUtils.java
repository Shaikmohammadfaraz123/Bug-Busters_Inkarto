package com.inkarto.utilities;

import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

// Utility class for handling Excel operations using Apache POI
public class ExcelUtils {

	String fileName; // Full path to the Excel file
	FileInputStream fis; // Input stream to read from Excel
	FileOutputStream fos; // Output stream to write to Excel
	Workbook wb; // Workbook object representing the Excel file
	Sheet sheet; // Current sheet object
	CellStyle style; // Style object for formatting cells

	// Constructor: Loads workbook based on the provided Excel file name
	public ExcelUtils(String excelName) {
		this.fileName = System.getProperty("user.dir") + "\\src\\test\\resources\\" + excelName;
		try {
			fis = new FileInputStream(new File(fileName));
			wb = new XSSFWorkbook(fis); // Create workbook from file
		} catch (IOException e) {
			e.printStackTrace(); // Print error if file can't be loaded
		}
	}

	// Returns the number of physical rows in a sheet
	public int getRowCount(String sheetName) {
		sheet = wb.getSheet(sheetName);
		return sheet.getPhysicalNumberOfRows();
	}

	// Returns the number of columns in the first row
	public int getColumnCount(String sheetName) {
		sheet = wb.getSheet(sheetName);
		return sheet.getRow(0).getLastCellNum();
	}

	// Retrieves data from a specific cell and formats it as a string
	public String getCellData(String sheetName, int rownum, int columnum) {
		sheet = wb.getSheet(sheetName);
		Cell cell = sheet.getRow(rownum).getCell(columnum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		try {
			return new DataFormatter().formatCellValue(cell);
		} catch (Exception e) {
			e.printStackTrace();
			return "Missing Data";
		}
	}

	// Writes data into a specific cell and applies styling
	public void setCellData(String sheetName, int rowIndex, int columnIndex, String data) throws IOException {
		sheet = wb.getSheet(sheetName);
		Row row = sheet.getRow(rowIndex);
		if (row == null)
			row = sheet.createRow(rowIndex);
		Cell cell = row.getCell(columnIndex);
		if (cell == null)
			cell = row.createCell(columnIndex);
		cell.setCellValue(data);

		// Apply border and alignment style
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cell.setCellStyle(cellStyle);

		// Auto-size column for better formatting
		sheet.autoSizeColumn(columnIndex);

		// Save changes to file
		fis.close();
		fos = new FileOutputStream(fileName);
		wb.write(fos);
		fos.close();
	}

	// Highlights a cell in green (usually for pass status)
	public void fillGreenColor(String sheetName, int rownum, int columnIndex) throws IOException {
		fillColor(sheetName, rownum, columnIndex, IndexedColors.GREEN);
	}

	// Highlights a cell in red (usually for fail status)
	public void fillRedColor(String sheetName, int rownum, int columnIndex) throws IOException {
		fillColor(sheetName, rownum, columnIndex, IndexedColors.RED);
	}

	// Applies the specified fill color and styles to a cell
	private void fillColor(String sheetName, int rownum, int columnIndex, IndexedColors color) throws IOException {
		sheet = wb.getSheet(sheetName);
		Row row = sheet.getRow(rownum);
		if (row == null)
			row = sheet.createRow(rownum);
		Cell cell = row.getCell(columnIndex);
		if (cell == null)
			cell = row.createCell(columnIndex);

		// Set fill and border styles
		style = wb.createCellStyle();
		style.setFillForegroundColor(color.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		cell.setCellStyle(style);

		// Write changes to file
		fos = new FileOutputStream(fileName);
		wb.write(fos);
		fos.close();
	}

	// Validates actual vs expected data and logs pass/fail result in Excel
	public void validation(String sheetName, int rownum, int columnIndex, String actual, String expected) {
		try {
			Assert.assertEquals(actual, expected);
			setCellData(sheetName, rownum, columnIndex, "PASS");
			fillGreenColor(sheetName, rownum, columnIndex);
		} catch (Throwable e) {
			try {
				setCellData(sheetName, rownum, columnIndex, "FAIL");
				fillRedColor(sheetName, rownum, columnIndex);
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
			throw new AssertionError("Validation failed: " + e.getMessage());
		}
	}

	// Closes workbook and output stream
	public void closeFile() {
		try {
			if (fos != null)
				fos.close();
			if (wb != null)
				wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package com.hybrid.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class utils {

	public WebDriver driver;
	public XSSFWorkbook workBook;
	public void getScreenshot(String result) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src,
				new File(System.getProperty("user.dir") + "\\com\\hybrid\\screenshots" + result + "Screenshot.jpg"));

	}

	public ArrayList<String> getData(String sheetName, String columName, String TestName) throws IOException {

		ArrayList<String> a = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\hybrid\\resources\\Testdata.xlsx");
		workBook = new XSSFWorkbook(fis);
		int count = workBook.getNumberOfSheets();
		for (int i = 0; i < count; i++) {
			if (workBook.getSheetName(i).equalsIgnoreCase(sheetName)) {
				XSSFSheet sheet = workBook.getSheetAt(i);

				Iterator<Row> row = sheet.iterator();
				Row firstrow = row.next();
				Iterator<Cell> cv = firstrow.cellIterator();
				int k = 0;
				int column = 0;
				while (cv.hasNext()) {
					Cell ce = cv.next();
					if (ce.getStringCellValue().equalsIgnoreCase(columName)) {
						column = k;
					}
					k++;
				}
				System.out.println(column);

				while (row.hasNext()) {

					Row r = row.next();
					if (r.getCell(0).getStringCellValue().equalsIgnoreCase(TestName)) {
						Iterator<Cell> ce = r.cellIterator();
						while (ce.hasNext()) {
							Cell c = ce.next();
							if (c.getCellTypeEnum() == CellType.STRING) {
								a.add(c.getStringCellValue());
							} else {
								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							}

						}
					}
				}
			}
		}
		return a;

	}

}

package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils 
{
	public static int getLastRowNumber(String path,int index) throws IOException
	{
		FileInputStream fip = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fip);
		XSSFSheet sheet = workbook.getSheetAt(index);
		int rowCount = sheet.getLastRowNum();
		
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return rowCount;
		
	}
	public static int getLastColumnNumber(String path,int index) throws IOException
	{
		
		FileInputStream fip = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fip);
		XSSFSheet sheet = workbook.getSheetAt(index);
		XSSFRow row = sheet.getRow(0);
		int columnCount = row.getLastCellNum();
		
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return columnCount;
		
	}
	
	public static ArrayList<String[]> getCellData(String path, int index) throws IOException
	{
		FileInputStream fip = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fip);
		XSSFSheet sheet = workbook.getSheetAt(index);
		ArrayList<String[]> arrlst = new ArrayList<String[]>();
		for(int i=1; i<=sheet.getLastRowNum();i++)
		{
			XSSFRow currentRow = sheet.getRow(i);
			String[] arr = new String[currentRow.getLastCellNum()];
			for(int j =0; j< currentRow.getLastCellNum();j++)
			{
				DataFormatter dataFormatter = new DataFormatter();
				arr[j] = dataFormatter.formatCellValue(currentRow.getCell(j));
			}
			arrlst.add(arr);
		}
		return arrlst;
	}
}

package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.api.request.model.UserCredentials;

public class ExcelReaderUtili2 {

	public static Iterator<UserCredentials> loadTestData() {
		// TODO Auto-generated method stub

		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("testData/PhonenixTestData.xlsx");
		XSSFWorkbook myWorkBook = null;
		try {
			myWorkBook = new XSSFWorkbook(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		XSSFSheet mySheet = myWorkBook.getSheet("LoginTestData");
		XSSFRow myRow ;
		XSSFCell myCell;

		XSSFRow headerRows=mySheet.getRow(0);
		
		int usernameIndex =-1;
		int passwordIndex =-1;
		
		for (Cell cell:headerRows) {
			
			if(cell.getStringCellValue().trim().equals("username")) {
				usernameIndex =cell.getColumnIndex();
			}
			
			if(cell.getStringCellValue().trim().equals("password")) {
				passwordIndex=cell.getColumnIndex();
			}
			
			
			
		}
		
		System.out.println(usernameIndex+ " "+ passwordIndex);
		
		int lastRow=mySheet.getLastRowNum();
		XSSFRow rowData;
		UserCredentials userCredentials;
		
		ArrayList<UserCredentials> userList = new ArrayList<UserCredentials>();
		
		for(int rowIndex=1; rowIndex<= lastRow; rowIndex++) {
			
			rowData=mySheet.getRow(rowIndex);
			
			userCredentials = new UserCredentials(rowData.getCell(usernameIndex).toString(), rowData.getCell(passwordIndex).toString());
			
			userList.add(userCredentials);
		}
		
		return userList.iterator();
	}

}

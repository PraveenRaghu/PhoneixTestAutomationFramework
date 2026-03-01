package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.api.request.model.UserCredentials;
import com.dataproviders.api.bean.UserBean;
import com.poiji.bind.Poiji;

public class ExcelReaderUtili {
	private static final Logger LOGGER = LogManager.getLogger(ExcelReaderUtili.class);
	public static <T> Iterator<T> loadTestData(String xlsxFile,String sheetName,Class<T> clazz) {
	
		LOGGER.info(" Loading the .xlsx file from the path{} and sheet name {}",xlsxFile,sheetName);
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(xlsxFile);
		XSSFWorkbook myWorkBook = null;
		try {
			myWorkBook = new XSSFWorkbook(is);
		} catch (IOException e) {
		LOGGER.error("Cannot read the excel {} ",xlsxFile,e );
			e.printStackTrace();
		}

		XSSFSheet mySheet = myWorkBook.getSheet(sheetName);
		
	List<T>	dataList=Poiji.fromExcel(mySheet, clazz);
	return dataList.iterator();
	}

}

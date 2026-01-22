package com.demo.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class ReadCSVFile_MapToPOJO {

	public static void main(String[] args) throws IOException, CsvException {
		// Data reading from Csv file
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testdata/LoginCreds.csv");
		InputStreamReader isr = new InputStreamReader(is);

		CSVReader csvReader = new CSVReader(isr);

		CsvToBean<UserPOJO> csvTOBean = new CsvToBeanBuilder(csvReader).withType(UserPOJO.class)
				.withIgnoreEmptyLine(true).build();

		List<UserPOJO> userList = csvTOBean.parse();
		System.out.println(userList);

	}

}

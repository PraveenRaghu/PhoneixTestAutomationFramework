package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dataproviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtil {
	private static final Logger LOGGER = LogManager.getLogger(CreateJobBeanMapper.class);
	private CSVReaderUtil() {
		
	}
	
	public static <T> Iterator<T> loadCSV(String pathOfCSVFile,Class<T> bean)  {
		// Data reading from Csv file
		LOGGER.info(" Loading the CSV file from the path{}",pathOfCSVFile);
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSVFile);
		InputStreamReader isr = new InputStreamReader(is);

		CSVReader csvReader = new CSVReader(isr);
		LOGGER.info(" Converting the CSV to Bean Class {}",bean);
		CsvToBean<T> csvTOBean = new CsvToBeanBuilder(csvReader)
				.withType(bean)
				.withIgnoreEmptyLine(true).build();

		List<T> list = csvTOBean.parse();
		return list.iterator();

	}

}



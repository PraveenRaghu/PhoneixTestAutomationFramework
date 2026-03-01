package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.UserCredentials;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.ExcelReaderUtili;
import com.api.utils.FakerDataGenerator;
import com.api.utils.JSONReaderUtil;
import com.database.dao.CreateJobPayloadDataDao;
import com.database.dao.MapJobProblemDao;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {
	private static final Logger LOGGER = LogManager.getLogger(MapJobProblemDao.class);
     @DataProvider(name= "LoginAPIDataprovider",parallel=true)
	public static Iterator<UserBean> loginAPIDataProvider() {
    	 LOGGER.info("Loading data from CSV");
    	return CSVReaderUtil.loadCSV("testData/LoginCreds.csv",UserBean.class);
		
	}
     @DataProvider(name= "CreateJobAPIDataprovider",parallel=true)
     public static Iterator<CreateJobPayload> createJOBAPIDatProvider() {
    	 LOGGER.info("Loading data from CSV");
    	 Iterator<CreateJobBean>createJobIterator=CSVReaderUtil.loadCSV("testData/CreateJobData.csv", CreateJobBean.class);
    	 List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
    	 
    	 CreateJobBean tempBean;
    	 CreateJobPayload tempPayload;
    	 while(createJobIterator.hasNext()) {
    		 tempBean=createJobIterator.next();
    		 tempPayload=CreateJobBeanMapper.mapper(tempBean);
    		 payloadList.add(tempPayload);
    	 }
    	 return payloadList.iterator();
     }
     
     @DataProvider(name= "CreateJobAPIFakerAPIDataProvider",parallel=true)
     public static Iterator<CreateJobPayload> createJobFakeDataProvider() {
    	
    	 String fakerCount = System.getProperty("fakerCount", "5");
    	 int fakerCountInt = Integer.parseInt(fakerCount);
    	 LOGGER.info("Generating faker data of count{}",fakerCountInt);
    	Iterator<CreateJobPayload>payloadIterator= FakerDataGenerator.generateFakeCreateJobData(fakerCountInt);
    	return payloadIterator;
     }
     
     @DataProvider(name= "LoginAPIJSONDataprovider",parallel=true)
 	public static Iterator<UserCredentials> loginAPIJSONDataProvider() {
    	 LOGGER.info("Loading data from JSON"); 
     	return JSONReaderUtil.loadJSON("testData/LoginAPITestData.json",UserCredentials[].class);
 		
 	}
     @DataProvider(name= "CreateAPIJSONDataprovider",parallel=true)
  	public static Iterator<CreateJobPayload> createAPIJSONDataProvider() {
    	 LOGGER.info("Loading data from JSON");
      	return JSONReaderUtil.loadJSON("testData/CreateJobAPIData.json",CreateJobPayload[].class);
  		
  	}
     
     @DataProvider(name= "LoginAPIExcelDataprovider",parallel=true)
  	public static Iterator<UserBean> loginAPIExcelDataProvider() {
    	 LOGGER.info("Loading data from Excel");
      	return ExcelReaderUtili.loadTestData("testData/PhonenixTestData.xlsx","LoginTestData", UserBean.class);
  		
  	}
     
     @DataProvider(name= "CreateJobAPIExcelDataprovider",parallel=true)
   	public static Iterator<CreateJobPayload> createJobAPIExcelDataProvider() {
    	 LOGGER.info("Loading data from Excel");
    	 Iterator<CreateJobBean> iterator=ExcelReaderUtili.loadTestData("testData/PhonenixTestData.xlsx","CreateJobTestData", CreateJobBean.class);
List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
    	 
    	 CreateJobBean tempBean;
    	 CreateJobPayload tempPayload;
    	 while(iterator.hasNext()) {
    		 tempBean=iterator.next();
    		 tempPayload=CreateJobBeanMapper.mapper(tempBean);
    		 payloadList.add(tempPayload);
    	 }
    	 
    	 return payloadList.iterator();
   	}
     @DataProvider(name= "CreateJobAPIDBDataprovider",parallel=true)
    	public static Iterator<CreateJobPayload> createJobAPIDBDataProvider(){
    	 LOGGER.info("Loading data from Database");
    	 List<CreateJobBean> beanList=CreateJobPayloadDataDao.getCreateJobPayLoadData();
 		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
 		
 		for (CreateJobBean bean: beanList) {
 			CreateJobPayload payload=CreateJobBeanMapper.mapper(bean);
 			payloadList.add(payload);
 		}
 		return payloadList.iterator();
     }
     
}

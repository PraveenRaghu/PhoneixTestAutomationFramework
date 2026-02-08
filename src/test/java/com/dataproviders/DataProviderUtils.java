package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.UserCredentials;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.ExcelReaderUtili;
import com.api.utils.FakerDataGenerator;
import com.api.utils.JSONReaderUtil;
import com.database.dao.CreateJobPayloadDataDao;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {
     @DataProvider(name= "LoginAPIDataprovider",parallel=true)
	public static Iterator<UserBean> loginAPIDataProvider() {
    	 
    	return CSVReaderUtil.loadCSV("testData/LoginCreds.csv",UserBean.class);
		
	}
     @DataProvider(name= "CreateJobAPIDataprovider",parallel=true)
     public static Iterator<CreateJobPayload> createJOBAPIDatProvider() {
    	 
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
    	 
    	Iterator<CreateJobPayload>payloadIterator= FakerDataGenerator.generateFakeCreateJobData(fakerCountInt);
    	return payloadIterator;
     }
     
     @DataProvider(name= "LoginAPIJSONDataprovider",parallel=true)
 	public static Iterator<UserCredentials> loginAPIJSONDataProvider() {
     	 
     	return JSONReaderUtil.loadJSON("testData/LoginAPITestData.json",UserCredentials[].class);
 		
 	}
     @DataProvider(name= "CreateAPIJSONDataprovider",parallel=true)
  	public static Iterator<CreateJobPayload> createAPIJSONDataProvider() {
      	 
      	return JSONReaderUtil.loadJSON("testData/CreateJobAPIData.json",CreateJobPayload[].class);
  		
  	}
     
     @DataProvider(name= "LoginAPIExcelDataprovider",parallel=true)
  	public static Iterator<UserBean> loginAPIExcelDataProvider() {
      	 
      	return ExcelReaderUtili.loadTestData("testData/PhonenixTestData.xlsx","LoginTestData", UserBean.class);
  		
  	}
     
     @DataProvider(name= "CreateJobAPIExcelDataprovider",parallel=true)
   	public static Iterator<CreateJobPayload> createJobAPIExcelDataProvider() {
       	 
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
    	 List<CreateJobBean> beanList=CreateJobPayloadDataDao.getCreateJobPayLoadData();
 		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
 		
 		for (CreateJobBean bean: beanList) {
 			CreateJobPayload payload=CreateJobBeanMapper.mapper(bean);
 			payloadList.add(payload);
 		}
 		return payloadList.iterator();
     }
     
}

package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
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
}

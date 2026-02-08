package com.database.dao;

import java.util.ArrayList;
import java.util.List;

import com.api.request.model.CreateJobPayload;
import com.api.utils.CreateJobBeanMapper;
import com.dataproviders.api.bean.CreateJobBean;

public class DaoDemoRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<CreateJobBean> beanList=CreateJobPayloadDataDao.getCreateJobPayLoadData();
		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		
		for (CreateJobBean bean: beanList) {
			CreateJobPayload payload=CreateJobBeanMapper.mapper(bean);
			payloadList.add(payload);
		}
		
		System.out.println("---------------------------");
		
		for (CreateJobPayload payload: payloadList) {
			
			System.out.println(payload);
		}

	}

}

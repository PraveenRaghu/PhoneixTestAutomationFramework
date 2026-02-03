package com.api.utils;

import java.util.Iterator;

import com.dataproviders.api.bean.CreateJobBean;


public class ExcelReaderUtili3 {

	public static void main(String[] args) {
		
		Iterator<CreateJobBean> iterator=ExcelReaderUtili2.loadTestData("testData/PhonenixTestData.xlsx","CreateJobTestData", CreateJobBean.class);
		
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}

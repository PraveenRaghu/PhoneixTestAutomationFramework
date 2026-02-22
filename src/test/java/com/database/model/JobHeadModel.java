package com.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JobHeadModel {

	
private int	id;
private String	job_number;
private String	tr_customer_id;
private String	tr_customer_product_id;
private String	mst_service_location_id;
private String	mst_platform_id;
private String	mst_warrenty_status_id;
private String	mst_oem_id;
}

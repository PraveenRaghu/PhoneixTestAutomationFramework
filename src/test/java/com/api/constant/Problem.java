package com.api.constant;

public enum Problem {
	 SMARTPHONE_IS_RUNNING_SLOW(1)
	 ,POOR_BATTERY_LIFE(2);
	int code;
Problem(int code) {
	this.code= code;
	
}
public int getCode() {
	return code;
}


}

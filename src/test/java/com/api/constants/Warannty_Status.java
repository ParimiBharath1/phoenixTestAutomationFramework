package com.api.constants;

public enum Warannty_Status {

	IN_WARRANT(1), OUT_WARRANTY(2);
	
	private int code;
	private Warannty_Status(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	} 

}

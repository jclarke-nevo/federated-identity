package com.nevo.domain;

public class RESTOperationStatus {
	
	private final boolean success;
	private final String message;
	
	public RESTOperationStatus(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}
	
}

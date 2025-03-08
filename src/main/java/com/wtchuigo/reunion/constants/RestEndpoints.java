package com.wtchuigo.reunion.constants;

public final class RestEndpoints {

	// Base urls
	public static final String BASE_URL = "/api/v1/members";
	
	public static final String REGISTER = BASE_URL + "/register"; 

	public static final String LOGIN = BASE_URL + "/login/email/{email}/password/{password}";
	
	public static final String GET_ALL_MEMBERS = BASE_URL + "/get";
	
	public static final String DELETE = BASE_URL + "/delete/id/{id}";
	
	public static final String UPDATE_MEMBER = BASE_URL + "/updateMember"; 
	
	public static final String UPDATE_ADDRESS = BASE_URL + "/updateAddress"; 

}

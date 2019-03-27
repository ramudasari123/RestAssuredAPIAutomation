package com.automation.test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automation.util.CommonMethod;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;

public class PlaceAnOrder {
	
	@BeforeTest
	public void getBaseURL() {
		RestAssured.baseURI = "http://10.12.40.220:30001";
		RestAssured.defaultParser = Parser.JSON;
	}

	@Test
	public void placeOrder() {
		
		String credentials = CommonMethod.registerUser();
		CommonMethod.login(credentials);

	}

}
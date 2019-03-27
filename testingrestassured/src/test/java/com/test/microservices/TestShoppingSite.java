package com.test.microservices;

import java.util.HashMap;

import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.response.Response;

/**
 * 
 * @author harshit.trivedi
 *
 */
public class TestShoppingSite {

	//@Test
	public void PlaceAnOrderWithSingleItem() {
		UserInfoBean userInfoBean = new UserInfoBean();
		userInfoBean.setEmail(RandomStringUtils.randomAlphanumeric(10) + "@malinator.com");
		userInfoBean.setPassword(RandomStringUtils.randomAlphabetic(15));
		userInfoBean.setUsername(RandomStringUtils.randomAlphabetic(15));
		System.out.println("username... : " + userInfoBean.getUsername());
		System.out.println("password... : " + userInfoBean.getPassword());
		CheckoutFlowSteps.CompleteCheckoutFlowForSingleItem(userInfoBean.getUsername(), userInfoBean.getPassword(),
				userInfoBean.getEmail());
	}

	@Test
	public void PlaceAnOrderWithMultipleItem() {
		UserInfoBean userInfoBean = new UserInfoBean();
		userInfoBean.setEmail(RandomStringUtils.randomAlphanumeric(10) + "@malinator.com");
		userInfoBean.setPassword(RandomStringUtils.randomAlphabetic(15));
		userInfoBean.setUsername(RandomStringUtils.randomAlphabetic(15));
		System.out.println("username... : " + userInfoBean.getUsername());
		System.out.println("password... : " + userInfoBean.getPassword());
		CheckoutFlowSteps.CompleteCheckoutFlowForMultipleItem(userInfoBean.getUsername(), userInfoBean.getPassword(),
				userInfoBean.getEmail());
	}

	//@Test
	public void UpdateAndDeleteItemIDFromTheCart() {
		UserInfoBean userInfoBean = new UserInfoBean();
		userInfoBean.setEmail(RandomStringUtils.randomAlphanumeric(10) + "@malinator.com");
		userInfoBean.setPassword(RandomStringUtils.randomAlphabetic(15));
		userInfoBean.setUsername(RandomStringUtils.randomAlphabetic(15));
		System.out.println("username... : " + userInfoBean.getUsername());
		System.out.println("password... : " + userInfoBean.getPassword());
		CheckoutFlowSteps.ItemInAddToCartPageTesting(userInfoBean.getUsername(), userInfoBean.getPassword(),
				userInfoBean.getEmail());
	}

	@BeforeClass
	public static void getBaseURi() {
		RestAssured.baseURI = "http://10.12.40.220:30001";
		RestAssured.defaultParser = Parser.JSON;
		 RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}
}

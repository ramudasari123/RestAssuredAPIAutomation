package com.automation.util;

import static com.jayway.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.testng.Reporter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jayway.restassured.response.Response;

public class CommonMethod {

	private static HashMap<String, String> cookies = new HashMap<String, String>();

	public static String randomGen() {

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
		return formatter.format(date);

	}

	public static String registerUser() {

		RegUserPOJO user = new RegUserPOJO();
		user.setUserName("abc");
		user.setPassword("icpl123");
		user.setEmail("abc");
		Response resp = given().contentType("application/json").body(new Gson().toJson(user)).when().post("/register");
		assertEquals(resp.getStatusCode(), 200, "Verify Status code of Register:");
		Reporter.log("response  : " + resp.getBody().asString());
		return user.getUserName() + ":" + user.getPassword();
	}

	public static void login(String cred) {

		Response res = given().auth().preemptive().basic(cred.split(":")[0].trim(), cred.split(":")[1].trim()).when().get("/login");
		assertEquals(res.getStatusCode(), 200, "Verify Status Code of Login");
		cookies.put("logged_in", res.getCookie("logged_in"));
		cookies.put("md.sid", res.getCookie("md.sid"));
	}
}
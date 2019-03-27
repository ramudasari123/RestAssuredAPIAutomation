package com.test.microservices;

import java.util.HashMap;

import org.testng.Assert;

import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

public class CheckoutFlowSteps {

	
	public static void CompleteCheckoutFlowForSingleItem(String username, String password, String email) {
		HashMap<String, String> userInfo = new HashMap<String, String>();
		userInfo.put("username", username);
		userInfo.put("password", password);
		userInfo.put("email", email);

		// register user
		RestAssured.given().contentType("application/json").accept("application/json").body(new Gson().toJson(userInfo))
				.when().post(MicroServiceUrl.Register).then().assertThat().statusCode(200);

		// login into Application
		Response loginResponse = RestAssured.given().contentType("application/json").auth().preemptive()
				.basic(username, password).when().get(MicroServiceUrl.Login);

		Assert.assertEquals(true, loginResponse.getStatusCode() == 200);
		System.out.println("printing" + loginResponse.body().asString());

		Assert.assertEquals(loginResponse.body().asString().trim(), "Cookie is set");

		String loginId = loginResponse.getCookies().get("logged_in");
		String mdsid = loginResponse.getCookies().get("md.sid");
		System.out.println("Logged In Id" + loginId);
		System.out.println("Md Sid" + mdsid);

		HashMap<String, String> itemInfo = new HashMap<String, String>();
		itemInfo.put("id", "a0a4f044-b040-410d-8ead-4de0446aec7e");

		// add to cart
		RestAssured.given().contentType("application/json").accept("application/json").body(itemInfo)
				.cookie("md.sid", mdsid).cookie("logged_in", loginId).when().post(MicroServiceUrl.CART).then()
				.assertThat().statusCode(201);

		// add shipping address
		HashMap<String, String> shippingINfo = new HashMap<String, String>();
		shippingINfo.put("city", "Kota");
		shippingINfo.put("country", "rajasthan");
		shippingINfo.put("number", "123");
		shippingINfo.put("postcode", "327025");
		shippingINfo.put("street", "char rasta");

		RestAssured.given().contentType("application/json").accept("application/json").body(shippingINfo)
				.cookie("md.sid", mdsid).cookie("logged_in", loginId).when().post(MicroServiceUrl.ADDADDRESS).then()
				.assertThat().statusCode(200);

		// add payment address
		HashMap<String, String> cardInfo = new HashMap<String, String>();
		cardInfo.put("longNum", "4111 1111 1111 1111");
		cardInfo.put("ccv", "111");
		cardInfo.put("expires", "03/18");
		RestAssured.given().contentType("application/json").accept("application/json").body(cardInfo)
				.cookie("md.sid", mdsid).cookie("logged_in", loginId).when().post(MicroServiceUrl.ADDCARD).then()
				.assertThat().statusCode(200);

		// place order
		RestAssured.given().contentType("application/json").accept("application/json").cookie("md.sid", mdsid)
				.cookie("logged_in", loginId).when().post(MicroServiceUrl.ORDER).then().assertThat().statusCode(201);

		// get Order Details
		Response orderDetaisl = RestAssured.given().contentType("application/json").accept("application/json")
				.cookie("md.sid", mdsid).cookie("logged_in", loginId).when().get(MicroServiceUrl.ORDER);

		System.out.println("Order Details...." + orderDetaisl.getBody().asString());

	}


	
	public static void CompleteCheckoutFlowForMultipleItem(String username, String password, String email) {
		HashMap<String, String> userInfo = new HashMap<String, String>();
		userInfo.put("username", username);
		userInfo.put("password", password);
		userInfo.put("email", email);

		// register user
		RestAssured.given().contentType("application/json").accept("application/json").body(new Gson().toJson(userInfo))
				.when().post(MicroServiceUrl.Register).then().assertThat().statusCode(200);

		// login into Application
		Response loginResponse = RestAssured.given().contentType("application/json").auth().preemptive()
				.basic(username, password).when().get(MicroServiceUrl.Login);

		Assert.assertEquals(true, loginResponse.getStatusCode() == 200);
		System.out.println("printing" + loginResponse.body().asString());

		Assert.assertEquals(loginResponse.body().asString().trim(), "Cookie is set");

		String loginId = loginResponse.getCookies().get("logged_in");
		String mdsid = loginResponse.getCookies().get("md.sid");
		System.out.println("Logged In Id" + loginId);
		System.out.println("Md Sid" + mdsid);

		HashMap<String, String> itemInfo = new HashMap<String, String>();
		itemInfo.put("id", "a0a4f044-b040-410d-8ead-4de0446aec7e");

		// add to cart
		RestAssured.given().contentType("application/json").accept("application/json").body(itemInfo)
				.cookie("md.sid", mdsid).cookie("logged_in", loginId).when().post(MicroServiceUrl.CART).then()
				.assertThat().statusCode(201);

		HashMap<String, String> itemInfo2 = new HashMap<String, String>();
		itemInfo2.put("id", "819e1fbf-8b7e-4f6d-811f-693534916a8b");

		// add to cart
		RestAssured.given().contentType("application/json").accept("application/json").body(itemInfo2)
				.cookie("md.sid", mdsid).cookie("logged_in", loginId).when().post(MicroServiceUrl.CART).then()
				.assertThat().statusCode(201);

		// add shipping address
		HashMap<String, String> shippingINfo = new HashMap<String, String>();
		shippingINfo.put("city", "Kota");
		shippingINfo.put("country", "rajasthan");
		shippingINfo.put("number", "123");
		shippingINfo.put("postcode", "327025");
		shippingINfo.put("street", "char rasta");

		RestAssured.given().contentType("application/json").accept("application/json").body(shippingINfo)
				.cookie("md.sid", mdsid).cookie("logged_in", loginId).when().post(MicroServiceUrl.ADDADDRESS).then()
				.assertThat().statusCode(200);

		// add payment address
		HashMap<String, String> cardInfo = new HashMap<String, String>();
		cardInfo.put("longNum", "4111 1111 1111 1111");
		cardInfo.put("ccv", "111");
		cardInfo.put("expires", "03/18");
		RestAssured.given().contentType("application/json").accept("application/json").body(cardInfo)
				.cookie("md.sid", mdsid).cookie("logged_in", loginId).when().post(MicroServiceUrl.ADDCARD).then()
				.assertThat().statusCode(200);

		// place order
		RestAssured.given().contentType("application/json").accept("application/json").cookie("md.sid", mdsid)
				.cookie("logged_in", loginId).when().post(MicroServiceUrl.ORDER).then().assertThat().statusCode(201);

		// get Order Details
		Response orderDetaisl = RestAssured.given().contentType("application/json").accept("application/json")
				.cookie("md.sid", mdsid).cookie("logged_in", loginId).when().get(MicroServiceUrl.ORDER);

		System.out.println("Order Details...." + orderDetaisl.getBody().asString());

	}


	/**
	 * 
	 * @param username
	 * @param password
	 * @param email
	 */
	public static void  ItemInAddToCartPageTesting(String username, String password, String email) {
		HashMap<String, String> userInfo = new HashMap<String, String>();
		userInfo.put("username", username);
		userInfo.put("password", password);
		userInfo.put("email", email);

		// register user
		RestAssured.given().contentType("application/json").accept("application/json").body(new Gson().toJson(userInfo))
				.when().post(MicroServiceUrl.Register).then().assertThat().statusCode(200);

		// login into Application
		Response loginResponse = RestAssured.given().contentType("application/json").auth().preemptive()
				.basic(username, password).when().get(MicroServiceUrl.Login);

		Assert.assertEquals(true, loginResponse.getStatusCode() == 200);
		System.out.println("printing" + loginResponse.body().asString());

		Assert.assertEquals(loginResponse.body().asString().trim(), "Cookie is set");

		String loginId = loginResponse.getCookies().get("logged_in");
		String mdsid = loginResponse.getCookies().get("md.sid");
		System.out.println("Logged In Id" + loginId);
		System.out.println("Md Sid" + mdsid);

		HashMap<String, String> itemInfo = new HashMap<String, String>();
		itemInfo.put("id", "a0a4f044-b040-410d-8ead-4de0446aec7e");

		// add to cart
		RestAssured.given().contentType("application/json").accept("application/json").body(itemInfo)
				.cookie("md.sid", mdsid).cookie("logged_in", loginId).when().post(MicroServiceUrl.CART).then()
				.assertThat().statusCode(201);

		// verify item added into cart
		/**
		 * RestAssured.given().header("Content-type",
		 * "application/json").cookie("md.sid", mdsid) .cookie("logged_in",
		 * loginId).when().get(MicroServiceUrl.CART).then().assertThat().statusCode(200)
		 * .assertThat().body("[0].itemId",
		 * Matchers.equalTo("a0a4f044-b040-410d-8ead-4de0446aec7e")).assertThat()
		 * .body("[0].unitPrice", Matchers.equalTo(7.99));
		 **/
		// updated Item Quantity
		HashMap<String, String> updatedItemInfo = new HashMap<String, String>();
		updatedItemInfo.put("id", "a0a4f044-b040-410d-8ead-4de0446aec7e");
		updatedItemInfo.put("quantity", "3");

		RestAssured.given().contentType("application/json").accept("application/json").body(updatedItemInfo)
				.cookie("md.sid", mdsid).cookie("logged_in", loginId).when().post(MicroServiceUrl.CART).then()
				.assertThat().statusCode(201);

		// verify quantity updated or not in the cart
		/**
		 * RestAssured.given().header("Content-type",
		 * "application/json").cookie("md.sid", mdsid) .cookie("logged_in",
		 * loginId).when().get(MicroServiceUrl.CART).then().assertThat().statusCode(200)
		 * .assertThat().body("[0].itemId",
		 * Matchers.equalTo("a0a4f044-b040-410d-8ead-4de0446aec7e")).assertThat()
		 * .body("[0].quantity", Matchers.equalTo(3));
		 **/

		// delete item id from the cart
		RestAssured.given().contentType("application/json").accept("application/json").cookie("md.sid", mdsid)
				.cookie("logged_in", loginId).when()
				.delete(MicroServiceUrl.CART + "/" + "a0a4f044-b040-410d-8ead-4de0446aec7e").then().assertThat()
				.statusCode(202);
	}

}

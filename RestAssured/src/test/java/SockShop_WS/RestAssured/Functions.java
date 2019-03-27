package SockShop_WS.RestAssured;

import java.util.HashMap;

import org.apache.commons.codec.binary.Base64;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

public class Functions {
	HashMap<String, String> userInfo = new HashMap<String, String>();
	public String userId;
	public String ms_id;
	public void register(String username) {
        userInfo = new HashMap<String, String>();
        userInfo.put("username", username);
        userInfo.put("password", "demo123");
        userInfo.put("email", username + "@mail.com");
        RestAssured.given().contentType("application/json").accept("application/json").body(new Gson().toJson(userInfo)).when().post(ServiceEndPoints.Register).then().assertThat().statusCode(200);
    }

    public void login_user() {
        String authCookie = (userInfo.get("username") + ":" + userInfo.get("password"));
        String authCookieEncoded = new String(Base64.encodeBase64(authCookie.getBytes()));
        RestAssured.given().header("Authorization", "Basic " + authCookieEncoded).contentType("application/json").accept("application/json").when()
                .get(ServiceEndPoints.Login).then().assertThat().statusCode(200).assertThat().body(Matchers.equalTo("Cookie is set"));
        
        Response response = RestAssured.given().header("Authorization", "Basic " + authCookieEncoded)
                .contentType("application/json").when().get("/login");
        userId = response.getCookies().get("logged_in");
        ms_id = response.getCookies().get("md.sid");
    }
    public void addtocart(){
		HashMap<String, String> itemdata = new HashMap<String, String>();
		itemdata.put("id", "3395a43e-2d88-40de-b95f-e00e1502085b");
		RestAssured.given().accept("application/json").contentType("application/json").body(itemdata).cookie("logged_in", userId)
        .cookie("md.sid", ms_id).when().post(ServiceEndPoints.CART).then().assertThat().statusCode(201);
	}
    public void addShipping(){
    	HashMap<String, String> shippinginfo = new HashMap<String, String>();
		shippinginfo.put("street", "rajpath");
		shippinginfo.put("number", "9876543210");
		shippinginfo.put("country", "india");
		shippinginfo.put("city", "ahmedabad");
		shippinginfo.put("postcode", "380054");
		RestAssured.given().accept("application/json").contentType("application/json").body(shippinginfo).cookie("logged_in", userId)
        .cookie("md.sid", ms_id).when().post(ServiceEndPoints.ADDADDRESS).then().assertThat().statusCode(200);
    }
    public void addcard(){
    	HashMap<String, String> cardinfo = new HashMap<String, String>();
		cardinfo.put("longNum", "2368 5678 7456 7245");
		cardinfo.put("expires", "10/2022");
		cardinfo.put("ccv", "123");
		RestAssured.given().accept("application/json").contentType("application/json").body(cardinfo).cookie("logged_in", userId)
        .cookie("md.sid", ms_id).when().post(ServiceEndPoints.ADDCARD).then().assertThat().statusCode(200);
    }
    public void placeorder(){
    	RestAssured.given().contentType("application/json").accept("application/json").cookie("logged_in", userId)
        .cookie("md.sid", ms_id).when().post(ServiceEndPoints.ORDER).then().assertThat().statusCode(201);
    }
    public void orderReview(){
    	Response orderDetaisl = RestAssured.given().contentType("application/json").accept("application/json")
    			.cookie("logged_in", userId)
    	        .cookie("md.sid", ms_id).when().get(ServiceEndPoints.ORDER);
    	System.out.println("Order Details...." + orderDetaisl.getBody().asString());
    }
    public void Registered_user(String username, String password) {
        String authCookie = (username + ":" + password);
        String authCookieEncoded = new String(Base64.encodeBase64(authCookie.getBytes()));
        RestAssured.given().header("Authorization", "Basic " + authCookieEncoded).contentType("application/json").accept("application/json").when()
                .get(ServiceEndPoints.Login).then().assertThat().statusCode(200).assertThat().body(Matchers.equalTo("Cookie is set"));
        
        Response response = RestAssured.given().header("Authorization", "Basic " + authCookieEncoded)
                .contentType("application/json").when().get(ServiceEndPoints.Login);
        userId = response.getCookies().get("logged_in");
        ms_id = response.getCookies().get("md.sid");
    }
    public void updateItem(int quantity){
    	HashMap<String, String> updatedItemInfo = new HashMap<String, String>();
    	String itemquantity = Integer.toString(quantity);
		updatedItemInfo.put("id", "3395a43e-2d88-40de-b95f-e00e1502085b");
		updatedItemInfo.put("quantity", itemquantity);
		
		RestAssured.given().contentType("application/json").accept("application/json").body(updatedItemInfo)
		.cookie("md.sid", ms_id).cookie("logged_in", userId).when().post(ServiceEndPoints.CART).then()
		.assertThat().statusCode(201);
    }
    public void deleteItemFromCart(){
    	RestAssured.given().contentType("application/json").accept("application/json").cookie("md.sid", ms_id)
		.cookie("logged_in", userId).when()
		.delete(ServiceEndPoints.CART + "/" + "a0a4f044-b040-410d-8ead-4de0446aec7e");
    }
}

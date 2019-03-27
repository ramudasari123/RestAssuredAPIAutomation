package SockShop_WS.RestAssured;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;

public class TestFunctionalities {
	@BeforeClass
	public static void getBaseURi() {
		RestAssured.baseURI = ServiceEndPoints.BaseUrl;
		RestAssured.defaultParser = Parser.JSON;
		 RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}
	@Test
	public void placeoreder(){
		Functions functions = new Functions();
		functions.register(RandomStringUtils.randomAlphabetic(15));
		functions.login_user();
		functions.addtocart();
		functions.addShipping();
		functions. addcard();
		functions.placeorder();
		functions.orderReview();
	}
	@Test
	public void updateAndDeleteItemInCart(){
		Functions functions = new Functions();
		functions.Registered_user("xuser", "xuser");
		functions.addtocart();
		functions.updateItem(3);
		functions.deleteItemFromCart();
	}
	@Test
	public void orderMultipleItems(){
		Functions functions = new Functions();
		functions.register(RandomStringUtils.randomAlphabetic(15));
		functions.login_user();
		functions.addtocart();
		functions.updateItem(2);
		functions.deleteItemFromCart();
		functions.placeorder();
		functions.orderReview();
	}
}

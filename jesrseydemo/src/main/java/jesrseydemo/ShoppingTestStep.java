package jesrseydemo;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;

import org.json.JSONObject;
import org.testng.Assert;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.client.filter.LoggingFilter;

/**
 * 
 * @author harshit.trivedi
 *
 */
public class ShoppingTestStep {

	/**
	 * 
	 * @param userName
	 * @param Password
	 * @return
	 */
	public static HashMap<String, String> login(String userName, String Password) {
		HTTPBasicAuthFilter filter = new HTTPBasicAuthFilter(userName, Password);
		Client client = Client.create();
		client.addFilter(filter);

		WebResource webResource = client.resource(MicroServiceUrl.BaseUrl).path(MicroServiceUrl.Login);
		ClientResponse response = webResource.type("application/json").get(ClientResponse.class);

		List<NewCookie> cookie = response.getCookies();
		HashMap<String, String> cookieValue = new HashMap<String, String>();
		for (NewCookie newCookie : cookie) {
			cookieValue.put(newCookie.getName(), newCookie.getValue());
		}

		Assert.assertTrue(cookieValue.containsKey("md.sid"));
		Assert.assertTrue(cookieValue.containsKey("logged_in"));

		return cookieValue;

	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @param email
	 * @return
	 */
	public static String register(String username, String password, String email) {

		Client client = Client.create();

		WebResource webResource = client.resource(MicroServiceUrl.BaseUrl).path(MicroServiceUrl.Register);

		HashMap<String, String> userInfo = new HashMap<String, String>();
		userInfo.put("username", username);
		userInfo.put("password", password);
		userInfo.put("email", email);
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class,
				new Gson().toJson(userInfo).toString());

		Assert.assertEquals(response.getStatus(), 200);
		String output = response.getEntity(String.class);
		JSONObject jsonObject = new JSONObject(output);
		Assert.assertTrue(jsonObject.getString("id") != null);
		return (String) jsonObject.getString("id");
	}

	/**
	 * 
	 * @param username
	 * @param Password
	 * @param itemid
	 * @param mdsid
	 * @param loggedin
	 * @return
	 */
	public static String addItemToCart(String username, String Password, String itemid, String mdsid, String loggedin) {
		HTTPBasicAuthFilter filter = new HTTPBasicAuthFilter(username, Password);
		Client client = Client.create();
		client.addFilter(filter);
		client.addFilter(new LoggingFilter(System.out));
		HashMap<String, String> itemInfo = new HashMap<String, String>();
		itemInfo.put("id", itemid);

		System.out.println("new Gson().toJson(itemInfo).toString()" + new Gson().toJson(itemInfo).toString());

		WebResource webResource = client.resource(MicroServiceUrl.BaseUrl).path(MicroServiceUrl.CART);

		WebResource.Builder builder = webResource.getRequestBuilder();
		builder.accept("application/json").type("application/json");

		NewCookie MDsidCookir = new NewCookie("md.sid", mdsid);
		NewCookie LoggedInCookie = new NewCookie("logged_in", loggedin);
		builder = builder.cookie((Cookie) MDsidCookir).cookie((Cookie) LoggedInCookie);

		ClientResponse response = builder.post(ClientResponse.class, new Gson().toJson(itemInfo).toString());

		System.out.println("status ...." + response.getStatus());
		System.out.println("response....." + response.getEntity(String.class));
		return null;
	}

	/**
	 * 
	 * @param username
	 * @param Password
	 * @param itemid
	 * @param mdsid
	 * @param loggedin
	 * @return
	 */
	public static String updateItemToCart(String username, String Password, String itemid, String quantity,
			String mdsid, String loggedin) {
		HTTPBasicAuthFilter filter = new HTTPBasicAuthFilter(username, Password);
		Client client = Client.create();
		client.addFilter(filter);
		client.addFilter(new LoggingFilter(System.out));
		HashMap<String, String> itemInfo = new HashMap<String, String>();
		itemInfo.put("id", itemid);
		itemInfo.put("quantity", quantity);

		System.out.println("new Gson().toJson(itemInfo).toString()" + new Gson().toJson(itemInfo).toString());

		WebResource webResource = client.resource(MicroServiceUrl.BaseUrl).path(MicroServiceUrl.CART);

		WebResource.Builder builder = webResource.getRequestBuilder();
		builder.accept("application/json").type("application/json");

		NewCookie MDsidCookir = new NewCookie("md.sid", mdsid);
		NewCookie LoggedInCookie = new NewCookie("logged_in", loggedin);
		builder = builder.cookie((Cookie) MDsidCookir).cookie((Cookie) LoggedInCookie);

		ClientResponse response = builder.post(ClientResponse.class, new Gson().toJson(itemInfo).toString());

		System.out.println("status ...." + response.getStatus());
		System.out.println("response....." + response.getEntity(String.class));
		return null;
	}

	/**
	 * 
	 * @param username
	 * @param Password
	 * @param itemid
	 * @param mdsid
	 * @param loggedin
	 * @return
	 */
	public static String deleteItemToCart(String username, String Password, String itemid, String mdsid,
			String loggedin) {
		HTTPBasicAuthFilter filter = new HTTPBasicAuthFilter(username, Password);
		Client client = Client.create();
		client.addFilter(filter);
		client.addFilter(new LoggingFilter(System.out));

		WebResource webResource = client.resource(MicroServiceUrl.BaseUrl).path(MicroServiceUrl.CART + "/" + itemid);

		WebResource.Builder builder = webResource.getRequestBuilder();
		builder.accept("application/json").type("application/json");

		NewCookie MDsidCookir = new NewCookie("md.sid", mdsid);
		NewCookie LoggedInCookie = new NewCookie("logged_in", loggedin);
		builder = builder.cookie((Cookie) MDsidCookir).cookie((Cookie) LoggedInCookie);

		ClientResponse response = builder.delete(ClientResponse.class);

		System.out.println("status ...." + response.getStatus());
		System.out.println("response....." + response.getEntity(String.class));
		return null;
	}

	/**
	 * 
	 * @param username
	 * @param Password
	 * @param mdsid
	 * @param loggedin
	 * @return
	 */
	public static String getItemListFromCart(String username, String Password, String mdsid, String loggedin) {
		HTTPBasicAuthFilter filter = new HTTPBasicAuthFilter(username, Password);
		Client client = Client.create();
		client.addFilter(filter);
		client.addFilter(new LoggingFilter(System.out));

		WebResource webResource = client.resource(MicroServiceUrl.BaseUrl).path(MicroServiceUrl.CART);

		WebResource.Builder builder = webResource.getRequestBuilder();
		builder.accept("application/json").type("application/json");

		NewCookie MDsidCookir = new NewCookie("md.sid", mdsid);
		NewCookie LoggedInCookie = new NewCookie("logged_in", loggedin);
		builder = builder.cookie((Cookie) MDsidCookir).cookie((Cookie) LoggedInCookie);

		ClientResponse response = builder.get(ClientResponse.class);

		System.out.println("status ...." + response.getStatus());
		System.out.println("Item in the cart response....." + response.getEntity(String.class));
		return null;
	}

	public static String addShippingAddress(String username, String Password, String mdsid, String loggedin) {
		HTTPBasicAuthFilter filter = new HTTPBasicAuthFilter(username, Password);
		Client client = Client.create();
		client.addFilter(filter);
		client.addFilter(new LoggingFilter(System.out));
		HashMap<String, String> shippingINfo = new HashMap<String, String>();
		shippingINfo.put("city", "Kota");
		shippingINfo.put("country", "rajasthan");
		shippingINfo.put("number", "123");
		shippingINfo.put("postcode", "327025");
		shippingINfo.put("street", "char rasta");

		WebResource webResource = client.resource(MicroServiceUrl.BaseUrl).path(MicroServiceUrl.ADDADDRESS);

		WebResource.Builder builder = webResource.getRequestBuilder();
		builder.accept("application/json").type("application/json");

		NewCookie MDsidCookir = new NewCookie("md.sid", mdsid);
		NewCookie LoggedInCookie = new NewCookie("logged_in", loggedin);
		builder = builder.cookie((Cookie) MDsidCookir).cookie((Cookie) LoggedInCookie);

		ClientResponse response = builder.post(ClientResponse.class, new Gson().toJson(shippingINfo).toString());

		System.out.println("status ...." + response.getStatus());
		System.out.println("response....." + response.getEntity(String.class));
		return null;
	}

	public static String AddCardDetails(String username, String Password, String mdsid, String loggedin) {
		HTTPBasicAuthFilter filter = new HTTPBasicAuthFilter(username, Password);
		Client client = Client.create();
		client.addFilter(filter);
		client.addFilter(new LoggingFilter(System.out));
		HashMap<String, String> cardInfo = new HashMap<String, String>();
		cardInfo.put("longNum", "4111 1111 1111 1111");
		cardInfo.put("ccv", "111");
		cardInfo.put("expires", "03/18");

		WebResource webResource = client.resource(MicroServiceUrl.BaseUrl).path(MicroServiceUrl.ADDCARD);

		WebResource.Builder builder = webResource.getRequestBuilder();
		builder.accept("application/json").type("application/json");

		NewCookie MDsidCookir = new NewCookie("md.sid", mdsid);
		NewCookie LoggedInCookie = new NewCookie("logged_in", loggedin);
		builder = builder.cookie((Cookie) MDsidCookir).cookie((Cookie) LoggedInCookie);

		ClientResponse response = builder.post(ClientResponse.class, new Gson().toJson(cardInfo).toString());

		System.out.println("status ...." + response.getStatus());
		System.out.println("response....." + response.getEntity(String.class));
		return null;
	}

	/**
	 * 
	 * @param username
	 * @param Password
	 * @param mdsid
	 * @param loggedin
	 */
	public static void placeOrder(String username, String Password, String mdsid, String loggedin) {
		HTTPBasicAuthFilter filter = new HTTPBasicAuthFilter(username, Password);
		Client client = Client.create();
		client.addFilter(filter);
		client.addFilter(new LoggingFilter(System.out));
		WebResource webResource = client.resource(MicroServiceUrl.BaseUrl).path(MicroServiceUrl.ORDER);

		WebResource.Builder builder = webResource.getRequestBuilder();
		builder.accept("application/json").type("application/json");

		NewCookie MDsidCookir = new NewCookie("md.sid", mdsid);
		NewCookie LoggedInCookie = new NewCookie("logged_in", loggedin);
		builder = builder.cookie((Cookie) MDsidCookir).cookie((Cookie) LoggedInCookie);

		ClientResponse response = builder.post(ClientResponse.class);
		System.out.println("status ...." + response.getStatus());
		System.out.println("response....." + response.getEntity(String.class));

	}

	/**
	 * 
	 * @param username
	 * @param Password
	 * @param mdsid
	 * @param loggedin
	 */
	public static void getOrderDetails(String username, String Password, String mdsid, String loggedin) {
		HTTPBasicAuthFilter filter = new HTTPBasicAuthFilter(username, Password);
		Client client = Client.create();
		client.addFilter(filter);
		client.addFilter(new LoggingFilter(System.out));
		WebResource webResource = client.resource(MicroServiceUrl.BaseUrl).path(MicroServiceUrl.ORDER);

		WebResource.Builder builder = webResource.getRequestBuilder();
		builder.accept("application/json").type("application/json");

		NewCookie MDsidCookir = new NewCookie("md.sid", mdsid);
		NewCookie LoggedInCookie = new NewCookie("logged_in", loggedin);
		builder = builder.cookie((Cookie) MDsidCookir).cookie((Cookie) LoggedInCookie);

		ClientResponse response = builder.get(ClientResponse.class);
		System.out.println("status ...." + response.getStatus());
		System.out.println("response....." + response.getEntity(String.class));

	}
}

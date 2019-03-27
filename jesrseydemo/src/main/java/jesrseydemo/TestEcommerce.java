package jesrseydemo;

import java.util.HashMap;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

/**
 * 
 * @author harshit.trivedi
 *
 */
public class TestEcommerce {

	@Test
	public void PlaceAnorder() {
		// generate Daya
		UserInfoBean userInfoBean = new UserInfoBean();
		userInfoBean.setEmail(RandomStringUtils.randomAlphanumeric(10) + "@malinator.com");
		userInfoBean.setPassword(RandomStringUtils.randomAlphabetic(15));
		userInfoBean.setUsername(RandomStringUtils.randomAlphabetic(15));
		System.out.println("username... : " + userInfoBean.getUsername());
		System.out.println("password... : " + userInfoBean.getPassword());
		String userId = ShoppingTestStep.register(userInfoBean.getUsername(), userInfoBean.getPassword(),
				userInfoBean.getEmail());
		HashMap<String, String> cookie = ShoppingTestStep.login(userInfoBean.getUsername(), userInfoBean.getPassword());
		System.out.println("md.sid... : " + cookie.get("md.sid"));
		System.out.println("logged_in... : " + cookie.get("logged_in"));

		ShoppingTestStep.addItemToCart(userInfoBean.getUsername(), userInfoBean.getPassword(),
				"3395a43e-2d88-40de-b95f-e00e1502085b", cookie.get("md.sid"), cookie.get("logged_in"));

		ShoppingTestStep.getItemListFromCart(userInfoBean.getUsername(), userInfoBean.getPassword(),
				cookie.get("md.sid"), cookie.get("logged_in"));
		ShoppingTestStep.addShippingAddress(userInfoBean.getUsername(), userInfoBean.getPassword(),
				cookie.get("md.sid"), cookie.get("logged_in"));
		ShoppingTestStep.AddCardDetails(userInfoBean.getUsername(), userInfoBean.getPassword(), cookie.get("md.sid"),
				cookie.get("logged_in"));

		ShoppingTestStep.placeOrder(userInfoBean.getUsername(), userInfoBean.getPassword(), cookie.get("md.sid"),
				cookie.get("logged_in"));
		ShoppingTestStep.getOrderDetails(userInfoBean.getUsername(), userInfoBean.getPassword(), cookie.get("md.sid"),
				cookie.get("logged_in"));

	}

	// @Test
	public void UpdateandDeleteitemsfromcart() {
		UserInfoBean userInfoBean = new UserInfoBean();
		userInfoBean.setEmail(RandomStringUtils.randomAlphanumeric(10) + "@malinator.com");
		userInfoBean.setPassword(RandomStringUtils.randomAlphabetic(15));
		userInfoBean.setUsername(RandomStringUtils.randomAlphabetic(15));
		System.out.println("username... : " + userInfoBean.getUsername());
		System.out.println("password... : " + userInfoBean.getPassword());
		String userId = ShoppingTestStep.register(userInfoBean.getUsername(), userInfoBean.getPassword(),
				userInfoBean.getEmail());
		HashMap<String, String> cookie = ShoppingTestStep.login(userInfoBean.getUsername(), userInfoBean.getPassword());
		System.out.println("md.sid... : " + cookie.get("md.sid"));
		System.out.println("logged_in... : " + cookie.get("logged_in"));

		ShoppingTestStep.addItemToCart(userInfoBean.getUsername(), userInfoBean.getPassword(),
				"3395a43e-2d88-40de-b95f-e00e1502085b", cookie.get("md.sid"), cookie.get("logged_in"));

		ShoppingTestStep.getItemListFromCart(userInfoBean.getUsername(), userInfoBean.getPassword(),
				cookie.get("md.sid"), cookie.get("logged_in"));

		ShoppingTestStep.updateItemToCart(userInfoBean.getUsername(), userInfoBean.getPassword(),
				"3395a43e-2d88-40de-b95f-e00e1502085b", "3", cookie.get("md.sid"), cookie.get("logged_in"));

		ShoppingTestStep.getItemListFromCart(userInfoBean.getUsername(), userInfoBean.getPassword(),
				cookie.get("md.sid"), cookie.get("logged_in"));

		ShoppingTestStep.deleteItemToCart(userInfoBean.getUsername(), userInfoBean.getPassword(),
				"3395a43e-2d88-40de-b95f-e00e1502085b", cookie.get("md.sid"), cookie.get("logged_in"));

		ShoppingTestStep.getItemListFromCart(userInfoBean.getUsername(), userInfoBean.getPassword(),
				cookie.get("md.sid"), cookie.get("logged_in"));
	}

	// @Test
	public void PlacingMultipleItems() {
		UserInfoBean userInfoBean = new UserInfoBean();
		userInfoBean.setEmail(RandomStringUtils.randomAlphanumeric(10) + "@malinator.com");
		userInfoBean.setPassword(RandomStringUtils.randomAlphabetic(15));
		userInfoBean.setUsername(RandomStringUtils.randomAlphabetic(15));
		System.out.println("username... : " + userInfoBean.getUsername());
		System.out.println("password... : " + userInfoBean.getPassword());
		String userId = ShoppingTestStep.register(userInfoBean.getUsername(), userInfoBean.getPassword(),
				userInfoBean.getEmail());
		HashMap<String, String> cookie = ShoppingTestStep.login(userInfoBean.getUsername(), userInfoBean.getPassword());
		System.out.println("md.sid... : " + cookie.get("md.sid"));
		System.out.println("logged_in... : " + cookie.get("logged_in"));

		ShoppingTestStep.addItemToCart(userInfoBean.getUsername(), userInfoBean.getPassword(),
				"3395a43e-2d88-40de-b95f-e00e1502085b", cookie.get("md.sid"), cookie.get("logged_in"));
		ShoppingTestStep.addItemToCart(userInfoBean.getUsername(), userInfoBean.getPassword(),
				"zzz4f044-b040-410d-8ead-4de0446aec7e", cookie.get("md.sid"), cookie.get("logged_in"));
		ShoppingTestStep.addItemToCart(userInfoBean.getUsername(), userInfoBean.getPassword(),
				"a0a4f044-b040-410d-8ead-4de0446aec7e", cookie.get("md.sid"), cookie.get("logged_in"));

		ShoppingTestStep.getItemListFromCart(userInfoBean.getUsername(), userInfoBean.getPassword(),
				cookie.get("md.sid"), cookie.get("logged_in"));

		ShoppingTestStep.updateItemToCart(userInfoBean.getUsername(), userInfoBean.getPassword(),
				"3395a43e-2d88-40de-b95f-e00e1502085b", "3", cookie.get("md.sid"), cookie.get("logged_in"));

		ShoppingTestStep.getItemListFromCart(userInfoBean.getUsername(), userInfoBean.getPassword(),
				cookie.get("md.sid"), cookie.get("logged_in"));

		ShoppingTestStep.deleteItemToCart(userInfoBean.getUsername(), userInfoBean.getPassword(),
				"a0a4f044-b040-410d-8ead-4de0446aec7e", cookie.get("md.sid"), cookie.get("logged_in"));

		ShoppingTestStep.getItemListFromCart(userInfoBean.getUsername(), userInfoBean.getPassword(),
				cookie.get("md.sid"), cookie.get("logged_in"));

		ShoppingTestStep.addShippingAddress(userInfoBean.getUsername(), userInfoBean.getPassword(),
				cookie.get("md.sid"), cookie.get("logged_in"));
		ShoppingTestStep.AddCardDetails(userInfoBean.getUsername(), userInfoBean.getPassword(), cookie.get("md.sid"),
				cookie.get("logged_in"));

		ShoppingTestStep.placeOrder(userInfoBean.getUsername(), userInfoBean.getPassword(), cookie.get("md.sid"),
				cookie.get("logged_in"));
		ShoppingTestStep.getOrderDetails(userInfoBean.getUsername(), userInfoBean.getPassword(), cookie.get("md.sid"),
				cookie.get("logged_in"));
	}

}

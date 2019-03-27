package SockShop_WS.RestAssured;

import java.util.Random;

public class UtilFunctions {

	public String getemail() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String email = salt.toString()+"@test.com";
        System.out.println("qqqqqq "+email);
        return email;
    }
}

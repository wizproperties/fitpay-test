package main.java;

import org.json.simple.JSONObject;

import main.java.fitpay.User;
import main.java.fitpay.UsersJSON;

/**
 * The UsersUtil class provides basic  utility methods and JSON mappings for the User class
 * @author benwisinski
 */
public final class UsersUtil {

	private UsersUtil() {
		super();
		//setting this as private to prevent accidental util instantiations
	}
	
	
	public static User[] convertJSONtoUsers(String json) {
		JSONObject obj = new JSONObject(null);
		return null; //TODO
	}
	
	public static UsersJSON convertJSONtoUsersJSON(String json) {
		return null; //TODO
	}
	
}

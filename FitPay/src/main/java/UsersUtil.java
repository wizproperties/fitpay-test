package main.java;

import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONObject;

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
	
	/**
	 * Pulls User objects out of a raw JSON string from /users
	 * @param json input
	 * @return User[] from the input json
	 */
	public static User[] convertJSONtoUsers(String json) {
		ArrayList<User> rtn = null;
		
		JSONObject obj = new JSONObject(json);
		JSONArray jsonArray = obj.getJSONArray("results");
		if (jsonArray != null) {
			rtn = new ArrayList<User>();
		
	    for (int i = 0; i < jsonArray.length(); ++i) {
	        JSONObject jsonUser = jsonArray.getJSONObject(i);
	        
	        long createdTSEpoch = jsonUser.getLong("createdTsEpoch");
	        Calendar createdTime = Calendar.getInstance();
	        createdTime.setTimeInMillis(createdTSEpoch);
	        
	        long	 lastModTSEpoch = jsonUser.getLong("createdTsEpoch");
	        Calendar lastModTime = Calendar.getInstance();
	        lastModTime.setTimeInMillis(lastModTSEpoch);
	        
	        String encryptedData = jsonUser.getJSONObject("encryptedData").getString("encryptedDataSkipped");
	        
	        User user = new User();
	        user.setId(jsonUser.getString("id"));
	        user.setCreatedDateTime(createdTime);
	        user.setLastModifiedDateTime(lastModTime);
	        user.setEncryptedData(encryptedData);
	        
	        rtn.add(user);
	        
	    }
		}
	    if (rtn != null && !rtn.isEmpty()) return rtn.toArray(new User[rtn.size()]);
	    else return null;
	}
	
	/**
	 * Populates all members of the UsersJSON class via parsing the passed in json object
	 * @param json input
	 * @return UsersJSON object with all member variables populated
	 */
	public static UsersJSON convertJSONtoUsersJSON(String json) {
		
		JSONObject obj = new JSONObject(json);
		if (obj != null) {
			int limit = obj.getInt("limit");
			int offset = obj.getInt("offset");
			int totalResults = obj.getInt("totalResults");

			UsersJSON rtn = new UsersJSON (limit, offset, totalResults, convertJSONtoUsers(json));
			return rtn;
		}
		else return null; //eclipse says this is dead code, which should be true, but there isnt great documentation on the JSONObject constructor, so I'm leaving it in
	}
	
}

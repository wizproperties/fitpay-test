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
	
	
	public static User[] convertJSONtoUsers(String json) {
		ArrayList<User> rtn = new ArrayList<User>();
		
		JSONObject obj = new JSONObject(json);
		JSONArray jsonArray = obj.getJSONArray("results");
	    for (int i = 0; i < jsonArray.length(); ++i) {
	        final JSONObject jsonUser = jsonArray.getJSONObject(i);
	        System.out.println(jsonUser.getString("id"));
	        
	        int createdTSEpoch = jsonUser.getInt("createdTsEpoch");
	        Calendar createdTime = Calendar.getInstance();
	        createdTime.setTimeInMillis(createdTSEpoch);
	        
	        int lastModTSEpoch = jsonUser.getInt("createdTsEpoch");
	        Calendar lastModTime = Calendar.getInstance();
	        lastModTime.setTimeInMillis(lastModTSEpoch);
	        
	        String encryptedData = jsonUser.getJSONArray("encryptedData").getJSONObject(0).getString("encryptedDataSkipped");
	        
	        User user = new User();
	        user.setId(jsonUser.getString("id"));
	        user.setCreatedDateTime(createdTime);
	        user.setLastModifiedDateTime(lastModTime);
	        user.setEncryptedData(encryptedData);
	        
	        System.out.println(user.toString());
	        System.out.println();
	        
	        rtn.add(user);
	        
	    }
	    
		return (User[]) rtn.toArray();
	}
	
	public static UsersJSON convertJSONtoUsersJSON(String json) {
		return null; //TODO
	}
	
}

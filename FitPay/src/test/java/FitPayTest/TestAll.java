package test.java.FitPayTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import main.java.ConnectionUtil;
import main.java.UsersUtil;
import main.java.fitpay.User;
import main.java.fitpay.UsersJSON;


public class TestAll {
	
//	In case you want to run tests against reusable data or against just the json parser
	private final boolean useLiveConnection = true; //if set to false, tests will use the sample.json file instead
	public static String JSONdata = null;
	private static final int PASSED_LIMIT = 10;
	private static final int PASSED_OFFSET = 0;
	private static final String LIMIT_STRING = "?limit="+PASSED_LIMIT;
	private static final String OFFSET_STRING = "&offset="+PASSED_OFFSET;

	/**
	 * Tests URL connection (regardless of useLiveConnection flag), token, and https call and response structure.
	 * If I wasn't setting this up for someone else to run once, I would make it use the useLiveConnection flag to 
	 * bypass so that you could truly test just the parser if you wanted to.
	 */
	@Test
	public void testConnection() {
		String data=null;  
		try {
			  data = ConnectionUtil.establishConnectionAndGetDataString(null, LIMIT_STRING+OFFSET_STRING);
		  }
		  catch (Exception e) {
			  assertTrue(false, "An Exception Happened in the connection: "+e.getMessage());
		  }
		  assertNotNull(data);
	}

	/**
	 * Basic test to check data parsing is working fine (mostly checks JSON structure)
	 */
	@Test
	public void testDataParsing() {
		UsersJSON usersJson = UsersUtil.convertJSONtoUsersJSON(JSONdata);
		assertNotNull(usersJson);
		User[] users = UsersUtil.convertJSONtoUsers(JSONdata);
		assertNotNull(users);
	}
	
	@Test
	public void testUsersEndpointBasics() {
		UsersJSON usersJson = UsersUtil.convertJSONtoUsersJSON(JSONdata);
//		assertNotNull(usersJson);
//		User[] users = UsersUtil.convertJSONtoUsers(JSONdata);
//		assertNotNull(users);
		
		//test limit, hardcoding to 10 because thats what the default is set to
		assertEquals(usersJson.getLimit(), PASSED_LIMIT);
		assertEquals(usersJson.getUsers().length, PASSED_LIMIT);
		
		//test	offset
		assertEquals(usersJson.getOffset(), PASSED_OFFSET);
		
	}

	/**
	 * Runs before all other methods of this class and checks the useLiveConnection flag to setup JSON data for the rest of the tests
	 */
	@BeforeClass
	public void beforeClass() {
		if (useLiveConnection) {

			try {
				JSONdata = ConnectionUtil.establishConnectionAndGetDataString(null, null);
			}
			catch (Exception e) {
				assertTrue(false, "An Exception Happened in the connection: "+e.getMessage());
			}
		}	  
		else {
			JSONdata = ConnectionUtil.getJSONfromSampleFile();
		}
		assertNotNull(JSONdata);

	}

  @AfterClass
  public void afterClass() {
	  //nothing to do here, URL connection closed as of the return of json data
  }

}

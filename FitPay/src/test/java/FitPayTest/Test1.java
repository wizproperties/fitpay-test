package test.java.FitPayTest;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import main.java.ConnectionUtil;
import main.java.UsersUtil;
import main.java.fitpay.User;


public class Test1 {
	
	public static String JSONdata = null;
  @Test
  public void testConnection() {
	  System.out.println("running test1");
	  assertTrue(true, "some text explaining stuffff");
  }
  
  @Test
  public void testDataParsing() {
	  System.out.println("running test2");
	  User[] users = UsersUtil.convertJSONtoUsers(JSONdata);
	  for(int i=0; i< users.length; i++) {
		  System.out.println("User: "+ users[i].toString());
	  }
	  
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("Checking and establishing connection...");
	  try {
		  JSONdata = ConnectionUtil.establishConnectionAndGetDataString(null, null);
	  }
	  catch (Exception e) {
		  assertTrue(false, "An Exception Happened in the connection: "+e.getMessage());
	  }
	  
	  assertTrue(true, "some text explaining stuffff"); //TODO
	  
  }

  @AfterClass
  public void afterClass() {
	  //nothing to do here, URL connection closed as of the return of json data
  }

}

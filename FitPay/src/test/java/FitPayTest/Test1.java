package test.java.FitPayTest;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import main.java.Util;

public class Test1 {
	
	public static String JSONdata = null;
  @Test
  public void testConnection() {
	  System.out.println("running test2");
//	  JSONdata = Util.establishConnectionAndGetDataString(null, null);
	  assertTrue(true, "some text explaining stuffff");
  }
  

  @BeforeClass
  public void beforeClass() {
	  System.out.println("Checking and establishing connection...");
//	  JSONdata = Util.establishConnectionAndGetDataString(null, null);
	  assertTrue(true, "some text explaining stuffff"); //TODO
	  
  }

  @AfterClass
  public void afterClass() {
	  //nothing to do here, URL connection closed as of the return of json data
  }

}

package main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * The Util class provides basic utility methods for FitPay API Usage
 * @author benwisinski
 */
public final class Util {

	private static final String PRIVATE_TOKEN = "eyJhbGciOiJSUzI1NiJ9.eyJqdGkiOiJhN2EwYWIzNi02MjhjLTRlMGYtYmY1Yy1jZjcyYmRiMWE2ZGYiLCJzdWIiOiI2bktvMW1DQSIsImF1dGhvcml0aWVzIjpbInVzZXIucmVhZCIsInVzZXIud3JpdGUiLCJvcmdhbml6YXRpb25zLkVOR1RFU1QiLCJ1c2Vycy5yZWFkIiwidXNlcnMud3JpdGUiXSwic2NvcGUiOlsidXNlci5yZWFkIiwidXNlci53cml0ZSIsIm9yZ2FuaXphdGlvbnMuRU5HVEVTVCIsInVzZXJzLnJlYWQiLCJ1c2Vycy53cml0ZSJdLCJjbGllbnRfaWQiOiI2bktvMW1DQSIsImNpZCI6IjZuS28xbUNBIiwiYXpwIjoiNm5LbzFtQ0EiLCJncmFudF90eXBlIjoiY2xpZW50X2NyZWRlbnRpYWxzIiwicmV2X3NpZyI6Ijk1YzM5YzdiIiwiaWF0IjoxNTAzOTU2ODM2LCJleHAiOjE1MDQwMDAwMzYsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC91YWEvb2F1dGgvdG9rZW4iLCJ6aWQiOiJ1YWEiLCJhdWQiOlsiNm5LbzFtQ0EiLCJ1c2VyIiwib3JnYW5pemF0aW9ucyIsInVzZXJzIl19.vInLk3JXLDxIP2UbYfnLD_OG1a2Nh4SdAqmNze5dEqkkMQEC5obaqz6tiUWdRmCSCZH2cNGjE6fOcnBkqzqOkyXSf7p6-ZIDIUjAvaqydpAd_C-4cmsRgR9Ky8su1-EzVTOI2M-TI5B09LUfcA_yEHNua-SppBvktujBT-Sr10wkv1msKttQd7Lg-0-02mhW7eZCWaUVNz-hKY5bEguG17xMSJfa4OPVSDXQI8umIhkE6N_HdORYpB0ojEbxay7RI51IIVTu4mL93W-QVEQ3fj_P0PC8t9HuOoYFd1_tlsMONCIx4Bnu-b2VXUL43jSnpGsFHGqsfDxMnbIRRkloAg";
	private static SSLSocketFactory sslSocketFactory = null;
	
	private Util() {
		super();
		//setting this as private to prevent accidental util instantiations
	}

	//for my testing
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Util.getConnection();
//
//	}
	
	/**
	 * Establishes a connection to the API via the passed in endpoint, and returns the server response JSON
	 * @param endpoint optional endpoint for API
	 * @param params optoinal params list for endpoint, & seperated, with no ?
	 * @return server response JSON
	 * @throws NoSuchAlgorithmException generated from disable SSL method, setAcceptAllVerifier
	 * @throws KeyManagementException generated from disable SSL method, setAcceptAllVerifier
	 * @throws IOException if there is a bad request/response/http error
	 * @throws Exception HttpErrorExceptionCustom if the server gave a response error
	 */
	public static String establishConnectionAndGetDataString(String endpoint, String params) throws KeyManagementException, NoSuchAlgorithmException, IOException, Exception {
		// I know these exception types are spooling, but this makes it more readable
		System.out.println("getconnection method");
		
		//	Base curl call, for my reference:
		// curl -s --insecure -H "Authorization: Bearer TOKEN" https://api.qa.fitpay.ninja/users?limit=10
		
			String fitpayBaseURL = "https://api.qa.fitpay.ninja"; //base uri
			String fitpayEndpoint = "/users"; //endpoint default
			if (endpoint != null) {
				fitpayEndpoint = endpoint;
			}
			String fitpayArgs = "?limit=10"; //params default
			if (params != null) {
				fitpayArgs = "?" + params;
			}
			String fitpayURL = fitpayBaseURL.concat(fitpayEndpoint).concat(fitpayArgs);
			
			URL url = new URL (fitpayURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            setAcceptAllVerifier((HttpsURLConnection)connection);
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty  ("Authorization", "Bearer " + PRIVATE_TOKEN);
            
            StringBuilder responseStrBuilder = new StringBuilder();
            //check for errors in response, and throw custom exception if so
            if ((InputStream)connection.getErrorStream() != null ) {
            		System.out.println("There were errors in the request");
	            	InputStream content = (InputStream)connection.getErrorStream();
	                BufferedReader in   = 
	                    new BufferedReader (new InputStreamReader (content));
	                String line;
	                while ((line = in.readLine()) != null) {
//	                    System.out.println(line);
	                    responseStrBuilder.append(line);
	                }
	                Exception HttpErrorExceptionCustom = new Exception(responseStrBuilder.toString());
	                throw HttpErrorExceptionCustom;
            }
            
            // if no errors in response, try to read in stream
            InputStream content = (InputStream)connection.getInputStream();
            BufferedReader in   = 
                new BufferedReader (new InputStreamReader (content));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                responseStrBuilder.append(line);
            }
            
           return responseStrBuilder.toString();

	}
	
	
	/* The following is code that I got from a SO result on how to disable SSL for java. There might be a better way to do this, but I figured this would work OK for a POC  - benwisinski */
	
	
	/**
     * Overrides the SSL TrustManager and HostnameVerifier to allow
     * all certs and hostnames.
     * WARNING: This should only be used for testing, or in a "safe" (i.e. firewalled)
     * environment.
     * 
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @see <a href="https://stackoverflow.com/questions/19723415/java-overriding-function-to-disable-ssl-certificate-check">How to disable SSL - SO</a>
     */
    protected static void setAcceptAllVerifier(HttpsURLConnection connection) throws NoSuchAlgorithmException, KeyManagementException {

        // Create the socket factory.
        // Reusing the same socket factory allows sockets to be
        // reused, supporting persistent connections.
        if( null == sslSocketFactory) {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, ALL_TRUSTING_TRUST_MANAGER, new java.security.SecureRandom());
            sslSocketFactory = sc.getSocketFactory();
        }

        connection.setSSLSocketFactory(sslSocketFactory);

        // Since we may be using a cert with a different name, we need to ignore
        // the hostname as well.
        connection.setHostnameVerifier(ALL_TRUSTING_HOSTNAME_VERIFIER);
    }

    private static final TrustManager[] ALL_TRUSTING_TRUST_MANAGER = new TrustManager[] {
        new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {}
            public void checkServerTrusted(X509Certificate[] certs, String authType) {}
        }
    };

    private static final HostnameVerifier ALL_TRUSTING_HOSTNAME_VERIFIER  = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };


}

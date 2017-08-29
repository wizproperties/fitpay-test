/**
 * 
 */
package java.fitpay;

import java.util.Calendar;

/**
 * @author benwisinski
 * This class provides an object mapping to the /users api of Fitpay. 
 * @see <a href="https://anypoint.mulesoft.com/apiplatform/fitpay/#/portals/organizations/fd8d2eae-7955-4ec9-b009-b03635fe994b/apis/24399/versions/25936/pages/39762">FitPay API</a>
 */
public class User {

	private String id;
	private Calendar createdDateTime;
	private Calendar lastModifiedDateTime;
	private String encryptedData;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the createdDateTime
	 */
	public Calendar getCreatedDateTime() {
		return createdDateTime;
	}
	/**
	 * @param createdDateTime the createdDateTime to set
	 */
	public void setCreatedDateTime(Calendar createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	/**
	 * @return the lastModifiedDateTime
	 */
	public Calendar getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}
	/**
	 * @param lastModifiedDateTime the lastModifiedDateTime to set
	 */
	public void setLastModifiedDateTime(Calendar lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}
	/**
	 * @return the encryptedData
	 */
	public String getEncryptedData() {
		return encryptedData;
	}
	/**
	 * @param encryptedData the encryptedData to set
	 */
	public void setEncryptedData(String encryptedData) {
		this.encryptedData = encryptedData;
	}
	
	/**
	 * Override of toString() method, prints it looking all nice and pretty like
	 */
	@Override
	public String toString() {
		StringBuilder rtn = new StringBuilder();
		rtn.append("ID: " + getId())
			.append("\n")
			.append("Created: "+ getCreatedDateTime().getTime())
			.append("Created: "+ getLastModifiedDateTime().getTime())
			;
		return rtn.toString();
	}
	
}

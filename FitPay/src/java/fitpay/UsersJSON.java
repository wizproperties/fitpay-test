package java.fitpay;

/**
 * @author benwisinski
 * This class provides an object structure to the /users API JSON response of Fitpay. 
 * @see <a href="https://anypoint.mulesoft.com/apiplatform/fitpay/#/portals/organizations/fd8d2eae-7955-4ec9-b009-b03635fe994b/apis/24399/versions/25936/pages/39762">FitPay API</a>
 */
public class UsersJSON {
	
	private int limit;
	private int offset;
	private int totalResults;
	private User[] users;
	
	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}
	/**
	 * @param limit the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}
	/**
	 * @param offset the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}
	/**
	 * @return the totalResults
	 */
	public int getTotalResults() {
		return totalResults;
	}
	/**
	 * @param totalResults the totalResults to set
	 */
	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}
	/**
	 * @return the users
	 */
	public User[] getUsers() {
		return users;
	}
	/**
	 * @param users the users to set
	 */
	public void setUsers(User[] users) {
		this.users = users;
	}
	
	/**
	 * Simple method that returns the length of the users array in this json object
	 * @return int length of the users array, or 0 if null
	 */
	private int getUsersCount() {
		if (this.users != null) {
			return this.users.length;
		}
		return 0;
	}
	

}

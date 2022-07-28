package music.bennington.userservice.model;

public class UserModel {
    private String emailId;
    private String password;
    private String userName;
    private String mobileNo;
    private int userAge;
    private String userGender;
    private boolean active = false;
    private String role = "ROLE_USER";
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public UserModel(String emailId, String password, String userName, String mobileNo, int userAge, String userGender,
			boolean active, String role) {
		super();
		this.emailId = emailId;
		this.password = password;
		this.userName = userName;
		this.mobileNo = mobileNo;
		this.userAge = userAge;
		this.userGender = userGender;
		this.active = active;
		this.role = role;
	}
	public UserModel() {
	
	}

    
}

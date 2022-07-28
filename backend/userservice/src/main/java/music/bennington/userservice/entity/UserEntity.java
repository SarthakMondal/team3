package music.bennington.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "user_info")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)

public class UserEntity
{
    @Id
    @Column(name = "user_email", unique = true, nullable = false)
    private String emailId;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_mobileno", nullable = false)
    private String mobileNo;

    @Column(name = "user_age", nullable = false)
    private int userAge;

    @Column(name = "user_gender")
    private String userGender;

    @Column(name = "user_isactive", nullable = false)
    private boolean active = false;

    @Column(name = "user_role", nullable = false)
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

	public UserEntity(String emailId, String password, String userName, String mobileNo, int userAge, String userGender,
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

	public UserEntity() {
	}
    
    
    
}

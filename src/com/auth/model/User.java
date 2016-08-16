package com.auth.model;

/**用户(主体)
 * */

//@Entity
//@Table(name="t_user")
public class User implements Principal {
	public static final String PRINCIPAL_TYPE="user";	//主体类型
	
//	@Id
//	@GeneratedValue
	private Integer id;
	private String userName;
	private String password;
	private String nickName;
	private Integer status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}

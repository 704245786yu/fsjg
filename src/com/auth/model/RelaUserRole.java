package com.auth.model;

/**用户和角色关联表
 * */
//@Entity
//@Table(name="t_user_role")
public class RelaUserRole {

//	@Id
//	@GeneratedValue
	private Integer id;
	
//	@ManyToOne
//	@JoinColumn(name="uid")
	private User user;
	
//	@ManyToOne
//	@JoinColumn(name="rid")
	private Role role;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
}

package com.auth.model;

/**角色（主体）
 * */
//@Entity
//@Table(name="t_role")
public class Role implements Principal {
	public static final String PRINCIPAL_TYPE="role";	//主体类型
	
//	@Id
//	@GeneratedValue
	private Integer id;
	private String name;
	private String sn;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	
}

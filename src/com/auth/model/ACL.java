package com.auth.model;

/**权限控制的关键类，这个类用来存储主体和资源之间的关系，用来确定主体能够对哪些资源进行哪些操作
 * */
//@Entity
//@Table(name="t_acl")
public class ACL {

	private Integer id;
	private String ptype;//主体类型,如user,role
	private String rtype;//资源类型
	private Integer pid;//主体Id
	private Integer rid;//资源Id
	private Integer aclState;//对方法的操作状态,存储的是一个4个字节的整数，也就是可以存储0~31位的操作。每一位上的取值为0时表示无权限，1表有权限。
	
	/**设置资源类型为menu菜单
	 * */
	public void setMenuType(){
		this.rtype = MenuResource.RES_TYPE;
	}
	
	/**设置资源类型为controller
	 * */
	public void setControllerType(){
		this.rtype = ControllerResource.RES_TYPE;
	}
	
	/**设置主体类型为用户
	 * */
	public void setUserType(){
		this.ptype = User.PRINCIPAL_TYPE;
	}
	
	/**设置主体类型为角色
	 * */
	public void setRoleType(){
		this.ptype = Role.PRINCIPAL_TYPE;
	}
	
	/**设置权限
	 * 在某个位置设置访问或者无法访问
	 * @throws Exception 
	 * */
	public void setPermission(int index, boolean permit){
		try {
			if(index<0 || index>31){
				throw new Exception("权限的位置只能在0~31之间");
			}
			this.aclState = setBit(this.aclState, index, permit);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**具体进行设置
	 * */
	public int setBit(int state, int index, boolean permit){
		int tmp = 1;
		tmp = tmp << index;
		if(permit){
			state = state | tmp;
		}else{
			tmp = ~tmp;
			state = state & tmp;
		}
		return state;
	}
	
	/**检查某个位置是否可以访问,即是否有某个权限
	 * @param index 从0开始
	 * */
	public boolean checkPermission(int index){
		int tmp = 1;
		tmp = tmp << index;
		int num = this.aclState & tmp;
		return num>0;
	}
	
//	@Id
//	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	public String getRtype() {
		return rtype;
	}
	public void setRtype(String rtype) {
		this.rtype = rtype;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public Integer getAclState() {
		return aclState;
	}
	public void setAclState(Integer aclState) {
		this.aclState = aclState;
	}
	
}

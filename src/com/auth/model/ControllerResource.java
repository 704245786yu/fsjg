package com.auth.model;

/**控制器资源
 * */
//@Entity
//@Table(name="t_controller_res")
public class ControllerResource implements SystemResource {
	public static final String RES_TYPE="controller";	//资源类型

//	@Id
//	@GeneratedValue
	private Integer id;
	private String name;//资源的名称（中文名称）
	private String sn;//资源的唯一标识，在系统中可使用类名进行标识
	private String psn;//资源的父类标识
	private String className;//资源所对应的类名：多个类名可通过‘,’进行分割。使用类名全程。
	private Integer orderNum;//资源排序号
//	@ManyToOne
//	@JoinColumn(name="pid")
	private ControllerResource parent;//资源的父类，此字段的主要意义是为了方便在授权的时候进行选择，通过树的方式进行选择。
	
	public void setClassName(String className){
		if(this.className == null || "".equals(this.className)){
			this.className = className;
		}else{
			if(this.className.indexOf(className)>=0){
				//原有的className已经包含了，就直接返回
				return;
			}
			this.className += ","+className;
		}
	}
	
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
	public String getPsn() {
		return psn;
	}
	public void setPsn(String psn) {
		this.psn = psn;
	}
	public String getClassName() {
		return className;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public ControllerResource getParent() {
		return parent;
	}
	public void setParent(ControllerResource parent) {
		this.parent = parent;
	}
	
}

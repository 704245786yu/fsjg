package com.auth.model;

/**Controller资源的操作方法类，用来确定某个资源的操作所对应的方法
 * */
//@Entity
//@Table(name="controller_oper")
public class ControllerOper {

//	@Id
//	@GeneratedValue
	private Integer id;
	private String sn;//标识
	private String methodName;//资源的方法，一个操作可对应多个方法，如add,addInput。在初始化的时候，可以根据方法的名称来确定，如add开头就是ADD，其他没声明的都是READ
	private String name;
	private Integer indexPos;//方法的索引位置。默认：0:Create,1:READ,2:UPDATE,3:DELETE。其他由开发人员手动指定
	private Integer rid;//所对应的资源ID
	private String rsn;//资源的sn
	
	public void setMethodName(String methodName){
		if(this.methodName == null || "".equals(this.methodName)){
			this.methodName = methodName;
		}else{
			if(this.methodName.indexOf(methodName)>=0){
				//原有的className已经包含了，就直接返回
				return;
			}
			this.methodName += ","+methodName;
		}
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getMethodName() {
		return methodName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getIndexPos() {
		return indexPos;
	}
	public void setIndexPos(Integer indexPos) {
		this.indexPos = indexPos;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getRsn() {
		return rsn;
	}

	public void setRsn(String rsn) {
		this.rsn = rsn;
	}
	
}

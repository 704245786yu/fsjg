package com.auth.model;

/**菜单资源
 * */
//@Entity
//@Table(name="meun_res")
public class MenuResource implements SystemResource {
	public static final String RES_TYPE="menu";	//资源类型

//	@Id
//	@GeneratedValue
	private Integer id;
	private String name;//菜单的名称
	private String sn;//菜单的sn不能重复，将来要通过这个sn自动生成页面的超链接，然后为超链接增加一个属性auth_sn,auth_sn的值就是sn
//	@Column(name="menu_pos")
	private MenuPos menuPos;//菜单所在位置
	private String href;//菜单的超链接
	private String icon;//菜单的图标
	private Integer display;//菜单是否显示 0:不显示。1:显示
	private Integer orderNum;//菜单序号
	private String psn;//父菜单的sn，方便初始化的时候做操作
//	@ManyToOne
//	@JoinColumn(name="pid")
	private MenuResource parent;//父菜单资源.方便授权操作
	
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
	public MenuPos getMenuPos() {
		return menuPos;
	}
	public void setMenuPos(MenuPos menuPos) {
		this.menuPos = menuPos;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public Integer getDisplay() {
		return display;
	}
	public void setDisplay(Integer display) {
		this.display = display;
	}
	public String getPsn() {
		return psn;
	}
	public void setPsn(String psn) {
		this.psn = psn;
	}
	public MenuResource getParent() {
		return parent;
	}
	public void setParent(MenuResource parent) {
		this.parent = parent;
	}
	
}

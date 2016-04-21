package com.dto;

import java.util.List;

/**Bootstrap分页查询数据分装类
 * */
public class BootTablePageDto<T> {

	private Long total;//数据记录总数
	private List<T> rows;//对应页面的记录
	
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
}

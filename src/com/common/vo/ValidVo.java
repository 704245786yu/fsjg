package com.common.vo;

/**封装Form表单异步验证数据
 * */
public class ValidVo {

	private boolean valid;

	public ValidVo(){}
	
	public ValidVo(boolean valid) {
		this.valid = valid;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
}

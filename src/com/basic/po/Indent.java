package com.basic.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**加工订单*/
@Entity
@Table(name="process_indent")
public class Indent {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="indent_num")
	private Integer indentNum;//订单编号
	
}

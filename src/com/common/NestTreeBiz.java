package com.common;

import java.io.Serializable;
import java.util.List;

import com.common.po.NestTreePO;

public abstract class NestTreeBiz<DAO extends NestTreeDao<?, ?>, ID extends Serializable, PO extends NestTreePO>
		extends BaseBiz<DAO, ID, PO> {

	public List<PO> getAdjTree(){
		return dao.getAdjTree();
	}
}

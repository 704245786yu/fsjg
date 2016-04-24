package com.common;

import java.io.Serializable;
import java.util.List;

import com.common.po.NestTreePO;

public abstract class NestTreeBiz< DAO extends NestTreeDao<ID, PO>, ID extends Serializable, PO extends NestTreePO >
		extends BaseBiz<DAO, ID, PO> {

	public List<PO> transformAdjTree(ID[] ids){
		return dao.transformAdjTree(ids);
	}
}

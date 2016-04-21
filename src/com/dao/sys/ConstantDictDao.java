package com.dao.sys;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.common.BaseDao;
import com.po.sys.ConstantDict;

@Repository
@Transactional
public class ConstantDictDao extends BaseDao<Integer,ConstantDict>{

}
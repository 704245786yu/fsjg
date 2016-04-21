package com.dao.sys;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.common.BaseDao;
import com.po.sys.ConstantType;

@Repository
@Transactional
public class ConstantTypeDao extends BaseDao<Integer,ConstantType>{

}
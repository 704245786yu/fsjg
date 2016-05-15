package com.sys.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.common.BaseDao;
import com.sys.po.ConstantType;

@Repository
@Transactional
public class ConstantTypeDao extends BaseDao<Integer,ConstantType>{

}
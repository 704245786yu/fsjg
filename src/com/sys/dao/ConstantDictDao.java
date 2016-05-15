package com.sys.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.common.BaseDao;
import com.sys.po.ConstantDict;

@Repository
@Transactional
public class ConstantDictDao extends BaseDao<Integer,ConstantDict>{

}
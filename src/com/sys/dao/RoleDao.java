package com.sys.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.common.BaseDao;
import com.sys.po.Role;

@Repository
@Transactional
public class RoleDao extends BaseDao<Integer,Role>{

}

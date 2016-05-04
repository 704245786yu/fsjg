package com.dao.sys;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.common.BaseDao;
import com.po.sys.Role;

@Repository
@Transactional
public class RoleDao extends BaseDao<Integer,Role>{

}

package com.sys.dao;

import org.springframework.stereotype.Repository;

import com.common.NestTreeDao;
import com.sys.po.Menu;

/**@author zhiyu
 * */
@Repository
public class MenuDao extends NestTreeDao<Integer, Menu> {

}

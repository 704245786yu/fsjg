package com.dao.sys;

import org.springframework.stereotype.Repository;

import com.common.NestTreeDao;
import com.po.sys.Menu;

/**@author zhiyu
 * */
@Repository
public class MenuDao extends NestTreeDao<Integer, Menu> {

}

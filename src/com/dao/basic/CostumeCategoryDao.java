package com.dao.basic;

import org.springframework.stereotype.Repository;

import com.common.NestTreeDao;
import com.po.basic.CostumeCategory;

/**@author zhiyu
 * */
@Repository
public class CostumeCategoryDao extends NestTreeDao<Integer, CostumeCategory> {

}

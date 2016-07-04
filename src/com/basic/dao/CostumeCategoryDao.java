package com.basic.dao;

import org.springframework.stereotype.Repository;

import com.basic.po.CostumeCategory;
import com.common.NestTreeDao;

/**@author zhiyu
 * */
@Repository
public class CostumeCategoryDao extends NestTreeDao<Integer, CostumeCategory> {

}

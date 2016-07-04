package com.basic.biz;

import org.springframework.stereotype.Service;

import com.common.NestTreeBiz;
import com.dao.basic.CostumeCategoryDao;
import com.po.basic.CostumeCategory;

@Service
public class CostumeCategoryBiz extends NestTreeBiz<CostumeCategoryDao, Integer, CostumeCategory>{
	
}
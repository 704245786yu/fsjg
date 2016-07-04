package com.basic.biz;

import org.springframework.stereotype.Service;

import com.basic.dao.CostumeCategoryDao;
import com.basic.po.CostumeCategory;
import com.common.NestTreeBiz;

@Service
public class CostumeCategoryBiz extends NestTreeBiz<CostumeCategoryDao, Integer, CostumeCategory>{
	
}
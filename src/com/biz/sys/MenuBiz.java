package com.biz.sys;

import org.springframework.stereotype.Service;

import com.common.NestTreeBiz;
import com.dao.sys.MenuDao;
import com.po.sys.Menu;

@Service
public class MenuBiz extends NestTreeBiz<MenuDao, Integer, Menu>{

}
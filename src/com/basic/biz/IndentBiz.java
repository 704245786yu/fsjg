package com.basic.biz;

import org.springframework.stereotype.Service;

import com.basic.dao.IndentDao;
import com.basic.po.Indent;
import com.common.BaseBiz;

@Service
public class IndentBiz extends BaseBiz<IndentDao, Integer, Indent> {

}

package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {

	EasyUIDataGridResult findContentAll(long categoryId);
	
	TaotaoResult addContent(TbContent tbContent);
	
	List<TbContent> getTbContent(long categoryId);
}

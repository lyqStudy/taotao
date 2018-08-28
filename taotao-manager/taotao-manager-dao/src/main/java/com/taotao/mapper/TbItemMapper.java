package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;

public interface TbItemMapper {
	public TbItem getItemById(long itemId);
	List<TbItem> getTbItem();
	void addTbItem(TbItem tbItem);
	void addTbItemDec(TbItemDesc tbItemDesc);
}
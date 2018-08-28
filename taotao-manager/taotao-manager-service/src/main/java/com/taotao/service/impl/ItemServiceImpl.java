package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper tbItemMapper;

	@Override
	public TbItem getItemById(long itemId) {
		
		TbItem tbItem=tbItemMapper.getItemById(itemId);
		return tbItem;
	}

	@Override
	public EasyUIDataGridResult getItema(int page, int rows) {
		
		PageHelper.startPage(page,rows);
		List<TbItem> list = tbItemMapper.getTbItem();
		PageInfo<TbItem> pageInfo=new PageInfo<>(list);
		EasyUIDataGridResult result=new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

	@Override
	public TaotaoResult addItem(TbItem item, String desc) {
		// TODO Auto-generated method stub
		// 1、生成商品id
				long itemId = IDUtils.genItemId();
				// 2、补全TbItem对象的属性
				item.setId(itemId);
				//商品状态，1-正常，2-下架，3-删除
				item.setStatus((byte) 1);
				Date date = new Date();
				item.setCreated(date);
				item.setUpdated(date);
				// 3、向商品表插入数据
				tbItemMapper.addTbItem(item);
				// 4、创建一个TbItemDesc对象
				TbItemDesc itemDesc = new TbItemDesc();
				// 5、补全TbItemDesc的属性
				itemDesc.setItemId(itemId);
				itemDesc.setItemDesc(desc);
				itemDesc.setCreated(date);
				itemDesc.setUpdated(date);
				// 6、向商品描述表插入数据
				tbItemMapper.addTbItemDec(itemDesc);
				// 7、TaotaoResult.ok()
				return TaotaoResult.ok();
		
	}



	
}

package com.taotao.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;
	
	@Override
	public List<EasyUITreeNode> getContentCategoryList(long parentId) {
		//根据分类id查询分类
		List<TbContentCategory> tbContentCategorys = tbContentCategoryMapper.getTbContentCategoryById(parentId);
		//创建返回结果集
		List<EasyUITreeNode> result = new ArrayList<EasyUITreeNode>();
		//遍历并且填充返回结果集
		for (TbContentCategory tbContentCategory : tbContentCategorys) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			
			result.add(node);
		}		
		return result;
	}

	@Override
	public TaotaoResult addContentCategory(long parentId, String name) {
		// 1、接收两个参数：parentId、name
				// 2、向tb_content_category表中插入数据。
				// a)创建一个TbContentCategory对象
				TbContentCategory tbContentCategory = new TbContentCategory();
				// b)补全TbContentCategory对象的属性
				tbContentCategory.setIsParent(false);
				tbContentCategory.setName(name);
				tbContentCategory.setParentId(parentId);
				//排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
				tbContentCategory.setSortOrder(1);
				//状态。可选值:1(正常),2(删除)
				tbContentCategory.setStatus(1);
				tbContentCategory.setCreated(new Date());
				tbContentCategory.setUpdated(new Date());
				// c)向tb_content_category表中插入数据
				tbContentCategoryMapper.addTbContentCategory(tbContentCategory);
				// 3、判断父节点的isparent是否为true，不是true需要改为true。
				TbContentCategory parentNode = tbContentCategoryMapper.getTbContentCategoryByParentId(parentId);
				if (!parentNode.getIsParent()) {
					parentNode.setIsParent(true);
					//更新父节点
					tbContentCategoryMapper.updateTbContentCategory(parentNode);
				}
				// 4、需要主键返回。
				// 5、返回TaotaoResult，其中包装TbContentCategory对象
				return TaotaoResult.ok(tbContentCategory);
	}

}

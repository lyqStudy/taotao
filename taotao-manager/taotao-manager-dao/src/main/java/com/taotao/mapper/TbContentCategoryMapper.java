package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.TbContentCategory;

public interface TbContentCategoryMapper {
       List<TbContentCategory> getTbContentCategoryById(long parentId);
       
       void addTbContentCategory(TbContentCategory tbContentCategory);
       
       TbContentCategory getTbContentCategoryByParentId(long parentId);
       
       void updateTbContentCategory(TbContentCategory tbContentCategory);
}
package com.leyou.item.service;

import com.leyou.common.enums.ExceptionEnums;
import com.leyou.common.exception.LyException;
import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author: Pandy
 * @Date: 2019/3/2 22:08
 * @Version 1.0
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据pid查询商品分类
     * @param pid
     * @return
     */
    public List<Category> queryCategoryByPid(Long pid) {
        Category t = new Category();
        //将对象中的非空字段作为查询的条件
        t.setParentId(pid);
        List<Category> list = categoryMapper.select(t);
        //if (list==null||list.isEmpty())
        //判断查询条件
        if (CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnums.CATEGORY_NOT_FOUND);
        }
        return list;
    }

    /**
     * 根据多个商品分类的id  查询商品分类的集合
     * @param ids
     * @return
     */
    public List<Category> queryByIds(List<Long> ids){
        List<Category> categories = categoryMapper.selectByIdList(ids);
        if (CollectionUtils.isEmpty(categories)){
            throw new LyException(ExceptionEnums.CATEGORY_NOT_FOUND);
        }
        return categories;
    }
}

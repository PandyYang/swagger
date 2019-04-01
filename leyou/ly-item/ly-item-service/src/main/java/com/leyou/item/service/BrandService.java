package com.leyou.item.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnums;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: Pandy
 * @Date: 2019/3/6 10:39
 * @Version 1.0
 */
@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 查询品牌
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param key
     * @return
     */
    public PageResult<Brand> queryBrandByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        //分页
        PageHelper.startPage(page,rows);
        //过滤
        /**
         * 过滤
         * WHERE 'name' LIKE "%x%" OR letter == 'x'
         * ORDER BY id DESC
         */
        Example example = new Example(Brand.class);
        if (StringUtils.isNotBlank(key)){
            //过滤条件
            example.createCriteria().orLike("name","%"+key+"%")
                    .orEqualTo("letter",key.toUpperCase());
        }
        //排序
        if (StringUtils.isNotBlank(sortBy)){
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        //查询

        //List<BrandMapper> list = brandMapper.selectByExample(example);
        List<Brand> list = brandMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)){
            throw  new LyException(ExceptionEnums.BRAND_NOT_FOUND);
        }
        //解析分页结果
        PageInfo<Brand> info = new PageInfo<>(list);
        return new PageResult<>(info.getTotal(),list);
    }

    /**
     *新增品牌
     * @param brand  品牌的属性
     * @param cids   所属的分类 三级联动实现
     */
    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {
        //新增品牌
        brand.setId(null);
        int count = brandMapper.insert(brand);
        //返回1代表新增成功 否则就失败
        if (count!=1){
            throw new LyException(ExceptionEnums.BRAND_SAVE_ERROR);
        }
        /**
         * 插入商品种类以及商品id
         * 很显然 种类和id之间是多对一的关系 所以要全部读取 执行
         * 种类次数多的for循环 依次插入即可
         */
        for (Long cid : cids){
            count = brandMapper.insertCategoryBrand(cid, brand.getId());
            if (count!=1){
                throw new LyException(ExceptionEnums.FAILTO_INSERT_MIDDLE);
            }
        }
    }

    /**
     * 根据商品id查询
     * @param id
     * @return
     */
    public Brand queryById(Long id){
        Brand brand = brandMapper.selectByPrimaryKey(id);
        if (brand == null){
            throw new LyException(ExceptionEnums.BRAND_NOT_FOUND);
        }
        return brand;
    }

    /**
     * 根据品牌id查询
     * @param cid
     * @return
     */
    public List<Brand> queryBrandByCid(Long cid) {
        //需要自定义语句进行多表关联查询
        List<Brand> list = brandMapper.queryByCategoryId(cid);
        if (CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnums.CATEGORY_NOT_FOUND_ERROR);
        }
        return list;
    }


}

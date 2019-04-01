package com.leyou.search.service;

import com.leyou.common.enums.ExceptionEnums;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.JsonUtils;
import com.leyou.item.pojo.*;
import com.leyou.search.client.BrandClient;
import com.leyou.search.client.CategoryClient;
import com.leyou.search.client.GoodsClient;
import com.leyou.search.client.SpecificationClient;
import com.leyou.search.pojo.Goods;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.rmi.dgc.Lease;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Pandy
 * @Date: 2019/3/30 7:53
 * @Version 1.0
 */
@Service
public class SearchService {


    @Autowired
    private CategoryClient categoryClient;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private SpecificationClient specificationClient;

    @Autowired
    private BrandClient brandClient;

    public Goods buildGoods(Spu spu){
        Long spuId = spu.getId();
        //查询分类
        List<Category> categories = categoryClient.queryCategoryByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
        if (CollectionUtils.isEmpty(categories)){
            throw new LyException(ExceptionEnums.CATEGORY_NOT_FOUND_ERROR);
        }
            //集合转化为列表
        List<String> names = categories.stream().map(Category::getName).collect(Collectors.toList());
        //查询品牌
        Brand brand = brandClient.queryBrandById(spu.getBrandId());
        if (brand == null) {
            throw new LyException(ExceptionEnums.BRAND_NOT_FOUND);
        }
        //搜索字段整合
        String all = spu.getTitle() + StringUtils.join(names," ") + brand.getName();

        //查询sku
        List<Sku> skus = goodsClient.querySkuBySpuId(spu.getId());
        if (CollectionUtils.isEmpty(skus)){
            throw new LyException(ExceptionEnums.SKU_NOT_FOUND);
        }
        //对sku处理 不需要的字段进行过滤
        //价格集合
        Set<Long> priceList = new HashSet<>();
        List<Map<String,Object>> skuList = new ArrayList<>();
        for (Sku sku : skus) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",sku.getId());
            map.put("price",sku.getTitle());
            map.put("title",sku.getTitle());
            map.put("images",StringUtils.substringBefore(sku.getImages(),","));
            skuList.add(map);
            //处理价格
            priceList.add(sku.getPrice());
        }

        //List<Long> prices = skus.stream().map(Sku::getPrice).collect(Collectors.toList());
        //规格参数
        List<SpecParam> Params = specificationClient.queryParamList(null, spu.getCid3(), true);
        if (CollectionUtils.isEmpty(Params)){
            throw new LyException(ExceptionEnums.SPEC_PARAM_NOT_FOUND);
        }
        //查询商品详情
        SpuDetail spuDetail = goodsClient.queryDetailById(spu.getId());
        //获取通用规格参数 generic
            //json转化为map
                //jsonUtils.parseMap()
        //获取特有规格参数 special
            //StringUtils.nativeRead
                //...
        //整合规格参数 key是规格参数的名字 值是规格参数的值
        Map<String,Object> specs = new HashMap<>();
        for (SpecParam param : Params) {
            //规格名称
            String key = param.getName();
            Object value = "";
            //判断是否是通用规格参数
                //获取value
        }


        //构建goods对象
        Goods goods = new Goods();
        goods.setBrandId(spu.getBrandId());
        goods.setCid1(spu.getCid1());
        goods.setCid2(spu.getCid2());
        goods.setCid3(spu.getCid3());
        goods.setCreateTime(spu.getCreateTime());
        goods.setId(spu.getId());
        goods.setAll(all);// 搜索字段 包含标题 分类品牌 规格等
        goods.setPrice(priceList);// 所有sku的价格集合
        goods.setSkus(JsonUtils.serialize(skus));// 所有sku的集合的json格式
        goods.setSpecs(null);//TODO 所有可搜索的规格字段
        goods.setSubTitle(spu.getSubTitle());
        return goods;
    }
}

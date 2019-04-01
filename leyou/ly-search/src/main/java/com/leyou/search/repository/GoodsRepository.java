package com.leyou.search.repository;

import com.leyou.search.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author: Pandy
 * @Date: 2019/3/29 23:17
 * @Version 1.0
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods,Long> {

}

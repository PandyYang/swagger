package com.leyou.search.client;

import com.leyou.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * @Author: Pandy
 * @Date: 2019/3/29 22:43
 * @Version 1.0
 */
@FeignClient("item-service")
public interface GoodsClient extends GoodsApi {

}

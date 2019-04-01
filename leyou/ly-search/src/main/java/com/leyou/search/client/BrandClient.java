package com.leyou.search.client;

import com.leyou.item.api.BrandApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: Pandy
 * @Date: 2019/3/29 23:06
 * @Version 1.0
 */
@FeignClient("item-service")
public interface BrandClient extends BrandApi {
}

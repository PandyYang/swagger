package com.leyou.item.api;

import com.leyou.item.pojo.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: Pandy
 * @Date: 2019/3/29 22:56
 * @Version 1.0
 */
public interface CategoryApi {
    @GetMapping("category/list/ids")
    List<Category> queryCategoryByIds(@RequestParam("ids") List<Long> ids);

    @GetMapping("category/names")
   List<String> queryNameByIds(@RequestParam("ids") List<Long> ids);
}

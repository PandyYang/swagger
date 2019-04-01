package com.leyou.item.web;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Pandy
 * @Date: 2019/3/6 10:46
 * @Version 1.0
 */
@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 分页查询品牌
     * @param page 当前页
     * @param rows 每页展示数
     * @param sortBy 排序
     * @param desc 是否降序
     * @param key 过滤条件
     * @return
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
          @RequestParam(value = "page",defaultValue = "1") Integer page,
          @RequestParam(value = "rows",defaultValue = "5") Integer rows,
          @RequestParam(value = "sortBy",required = false) String sortBy,
          @RequestParam(value = "desc",defaultValue = "false") Boolean desc,
          @RequestParam(value = "key",required = false) String key

    ){
        PageResult<Brand> result = brandService.queryBrandByPage(page,rows,sortBy,desc,key);
        return ResponseEntity.ok(result);
    }
    /**
     * 新增品牌
     * @param brand brand中的三个对象 品牌名称
     * @param cids  品牌类别
     * 当URL指向的是某一具体业务资源（或者资源列表），例如博客，用户时，使用@PathVariable
     * 当URL需要对资源或者资源列表进行过滤，筛选时，用@RequestParam
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand,@RequestParam("cids")List<Long> cids){
        brandService.saveBrand(brand,cids);
        //有返回体选择body 没有返回体选择build
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 根据cid查询品牌
     * @param cid
     * @return
     */
    @GetMapping("/cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandByCid(@PathVariable("cid") Long cid){
        return ResponseEntity.ok(brandService.queryBrandByCid(cid));
    }

    /**
     * 根据id查询品牌
     * @param id
     * @PathVariable 可以将 URL 中占位符参数绑定到控制器处理方法的入参中
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<Brand> queryBrandById(@PathVariable("id") Long id){
        return ResponseEntity.ok(brandService.queryById(id));
    }
}

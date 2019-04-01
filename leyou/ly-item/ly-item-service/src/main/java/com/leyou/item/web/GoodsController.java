package com.leyou.item.web;

        import com.leyou.common.vo.PageResult;
        import com.leyou.item.pojo.Sku;
        import com.leyou.item.pojo.Spu;
        import com.leyou.item.pojo.SpuDetail;
        import com.leyou.item.service.GoodsService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;
        import tk.mybatis.mapper.annotation.RegisterMapper;

        import javax.xml.ws.Response;
        import java.util.List;

/**
 * @Author: Pandy
 * @Date: 2019/3/22 12:19
 * @Version 1.0
 */
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    /**
     * 分页查询Spu
     * @param page
     * @param rows
     * @param saleable
     * @param key
     * @return
     */
    @GetMapping("/spu/page")
    public ResponseEntity<PageResult<Spu>> querySpuByPage(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "rows",defaultValue = "5") Integer rows,
            @RequestParam(value = "saleable",required = false) Boolean saleable,
            @RequestParam(value = "key",required = false) String key
    ){
        return ResponseEntity.ok(goodsService.querySpuByPage(page,rows,saleable,key));
    }

    /**
     * 商品的新增
     * @param spu
     * @return
     */
    @PostMapping("goods")
    public ResponseEntity<Void> saveGoods(@RequestBody Spu spu){
        goodsService.saveGoods(spu);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     *根据spu的id查询详情detail
     * @param spuId
     * @return
     */
    @GetMapping("/spu/detail/{id}")
    //通过 @PathVariable 可以将 URL 中占位符参数绑定到控制器处理方法的入参中
    public ResponseEntity<SpuDetail> queryDetailById(@PathVariable("id")Long spuId){
        return ResponseEntity.ok(goodsService.queryDetailById(spuId));
    }

    /**
     * 根据spu查询下面的所有sku
     * @param spuId
     * @return
     */
    @GetMapping("sku/list")
    public ResponseEntity<List<Sku>> querySkuBySpuId(@RequestParam("id")Long spuId){
        return ResponseEntity.ok(goodsService.querySkuBySpuId(spuId));
    }
}

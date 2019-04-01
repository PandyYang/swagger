package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author: Pandy
 * @Date: 2019/3/1 10:14
 * @Version 1.0
 * 自定义枚举类型的异常
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnums {

    PRICE_CANNOT_BE_NULL(400,"价格不能为空"),//final static...简写形式
    CATEGORY_NOT_FOUND(404,"返回分类未查到"),
    BRAND_NOT_FOUND(404,"品牌未查到"),
    BRAND_SAVE_ERROR(500,"新增品牌失败"),
    FAILTO_INSERT_MIDDLE(500,"插入中间表失败"),
    UPLOAD_ERROR(500,"文件上传失败"),
    INVALID_FILE_TYPE(500,"非法的文件类型"),
    GROUP_NOT_FOUND_ERROR(500,"未查询到对应的组"),
    PRODUCT_NOT_FOUND(404,"商品不存在"),
    CATEGORY_NOT_FOUND_ERROR(500,"没有相应的类别"),
    ADD_PRODUCT_ERROR(500,"新增商品失败"),
    PRODUCT_DETAIL_NOT_FOUND(500,"商品详情不存在"),
    SKU_NOT_FOUND(500,"所查询的sku不存在"),
    GOODS_STOCK_IS_NULL(500,"没有库存信息"),
    SPEC_PARAM_NOT_FOUND(404,"规格参数未查到")
    ;
    private int code;//返回状态码
    private String msg;//返回信息
}

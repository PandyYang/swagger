package com.leyou.item.vo;

import java.util.Date;

/**
 * @Author: Pandy
 * @Date: 2019/3/22 12:27
 * @Version 1.0
 */
public class SpuVo {
    private Long id;
    private Long brandId;
    private Long cid1;// 1级类目
    private Long cid2;// 2级类目
    private Long cid3;// 3级类目
    private String title;// 标题
    private String subTitle;// 子标题
    private Boolean saleable;// 是否上架
    private Date createTime;// 创建时间
    private String cname;
    private String bname;

}

package com.leyou.item.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: Pandy
 * @Date: 2019/3/6 10:57
 * @Version 1.0
 */
@Data
@Table(name="tb_brand")
public class Brand {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private Character letter;
}

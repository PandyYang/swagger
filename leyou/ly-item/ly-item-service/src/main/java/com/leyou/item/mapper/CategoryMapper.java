package com.leyou.item.mapper;

import com.leyou.item.pojo.Category;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: Pandy
 * @Date: 2019/3/2 22:07
 * @Version 1.0
 */

public interface CategoryMapper extends Mapper<Category>,IdListMapper<Category,Long> {
}

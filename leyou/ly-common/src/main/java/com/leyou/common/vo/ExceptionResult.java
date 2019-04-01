package com.leyou.common.vo;

import com.leyou.common.enums.ExceptionEnums;
import lombok.Data;

/**
 * @Author: Pandy
 * @Date: 2019/3/1 10:25
 * @Version 1.0
 */
@Data
public class ExceptionResult {
    private int status;
    private String message;
    private Long timestamp;

    public ExceptionResult(ExceptionEnums enums) {
        this.status = enums.getCode();
        this.message = enums.getMsg();
        this.timestamp = System.currentTimeMillis();
    }
}

package com.leyou.common.exception;

import com.leyou.common.enums.ExceptionEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author: Pandy
 * @Date: 2019/3/1 10:12
 * @Version 1.0
 * 任意传参的异常处理
 * 因为只在RuntimeException中定义不能进行多种异常返回类型的自定义
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LyException extends RuntimeException {
    private ExceptionEnums exceptionEnums;
}

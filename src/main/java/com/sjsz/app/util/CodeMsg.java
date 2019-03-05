package com.sjsz.app.util;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: qh
 * @Date: 2019/3/1 16:20
 * @Description:
 */
@Getter
@Setter
public class CodeMsg {

    private Integer code;

    private String msg;

    // 通用异常
    public static CodeMsg SUCCESS = new CodeMsg(0,"success");
    public static CodeMsg FAILURE = new CodeMsg(-1,"failure");
    // 业务异常


    public CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}

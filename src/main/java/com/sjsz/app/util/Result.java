package com.sjsz.app.util;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: qh
 * @Date: 2019/3/1 16:17
 * @Description:
 */
@Getter
@Setter
public class Result<T> implements Serializable {

    /**
     * 消息
     */
    private String msg;

    /**
     * 编码
     */
    private Integer code;

    /**
     * 数据
     */
    private T data;

    public Result(T data) {
        this.code = 0;
        this.msg = "成功";
        this.data = data;
    }

    public Result(CodeMsg cm) {
        if (cm == null) {
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }

    /**
     * 成功时调用
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    /**
     * 成功，不需要传入参数
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Result<T> success() {
        return (Result<T>) success("");
    }

    /**
     * 失败时候的调用
     * @param cm
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(CodeMsg cm) {
        return new Result<T>(cm);
    }

    /**
     * 失败时候的调用,扩展消息参数
     * @param cm
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(CodeMsg cm, String msg) {
        cm.setMsg(cm.getMsg()+"--"+msg);
        return new Result<T>(cm);

    }
}

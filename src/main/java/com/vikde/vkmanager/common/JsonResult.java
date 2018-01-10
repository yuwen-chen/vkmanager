package com.vikde.vkmanager.common;

import com.vikde.vkmanager.common.type.JsonResultType;
import com.github.pagehelper.Page;

import java.util.Collection;

/**
 * Created on 2017/3/24.
 *
 * @author vikde
 */
public class JsonResult {
    public final static Object NULL_OBJECT = new Object();
    private int code;
    private String message = "";
    private Object data;
    private int costTime;
    //可选参数
    private Long total;

    /**
     * 创建一个返回实例
     */
    public static JsonResult getSuccessInstance() {
        return getInstance(JsonResultType.SYSTEM_SUCCESS);
    }

    /**
     * 创建一个返回实例
     */
    public static JsonResult getInstance(JsonResultType jsonResultType) {
        return getInstance(jsonResultType, jsonResultType.getDefaultMessage());
    }

    /**
     * 创建一个返回实例,自定义返回信息
     */
    public static JsonResult getInstance(JsonResultType jsonResultType, String message) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(jsonResultType.getCode());
        jsonResult.setMessage(message);
        jsonResult.setData(NULL_OBJECT);
        jsonResult.setCostTime(0);
        return jsonResult;
    }

    /**
     * 设置数据和总量
     */
    @Deprecated
    public void setDataAndTotal(Object object) {
        data = object;
        if (object != null) {
            if (object instanceof Page) {
                Page page = (Page) object;
                total = page.getTotal();
            } else if (object instanceof Collection) {
                total = (long) ((Collection) object).size();
            }
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCostTime() {
        return costTime;
    }

    public void setCostTime(int costTime) {
        this.costTime = costTime;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}

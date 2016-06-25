package com.github.schedulejob.util;

import com.alibaba.fastjson.annotation.JSONType;
import com.github.schedulejob.common.Page;
import com.github.schedulejob.common.Response;
import com.github.schedulejob.common.RetCode;
import com.github.schedulejob.common.RetCodeConst;

import java.util.Objects;

/**
 * 功能简单描述
 *
 * @author: lvhao
 * @since: 2016-6-24 18:52
 */
public final class ResponseBuilder<T> {
    private Class<T> typeClazz;
    private transient Response<T> resp;

    public static ResponseBuilder newResponse(){
        ResponseBuilder instance = new ResponseBuilder();
        instance.resp = new Response();
        instance.resp.setRetCode(RetCodeConst.OK);
        instance.resp.setData(new Object());
        instance.resp.setPage(PageBuilder.DEFAULT_PAGE_INFO);
        return instance;
    }

    public ResponseBuilder<T> withRetCode(RetCode retCode){
        this.resp.setRetCode(retCode);
        return this;
    }

    public ResponseBuilder<T> withData(T t){
        this.resp.setData(t);
        return this;
    }
    public Response<T> build(){
        T data = this.resp.getData();
        if (Objects.isNull(data)) {
            T defaultV = null;
            try {
                defaultV = typeClazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            this.resp.setData(defaultV);
        }
        return this.resp;
    }
}

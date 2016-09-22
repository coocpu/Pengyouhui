package com.enrich.pengyouhui.bean.event;

/**
 * @author Lieber
 * @describe 用于传递Event的实体
 * @date 2016年9月8日
 */
public class CommonPostBean<T> {
    private int postCode;
    private T t;

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public CommonPostBean(int postCode, T t) {
        this.postCode = postCode;
        this.t = t;
    }

    public CommonPostBean() {
    }
}

package com.liyujie.spencapsulation.model;

public class AErrorl {
    public static final String ERR_DOMAIN_NAME_ALINK = "alinkErrorDomain";
    public static final int AKErrorSuccess = 0;
    public static final int AKErrorUnknownError = 4201;
    public static final int AKErrorInvokeNetError = 4101;
    public static final int AKErrorInvokeServerError = 4102;
    public static final int AKErrorServerBusinessError = 4103;
    public static final int AKErrorLoginTokenIllegalError = 4001;
    private String domain;
    private int code;
    private String msg;
    private String subDomain;
    private int subCode;
    private String subMsg;
    private Object originResponseObj;

    public AErrorl() { /* compiled code */ }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSubDomain() {
        return subDomain;
    }

    public void setSubDomain(String subDomain) {
        this.subDomain = subDomain;
    }

    public int getSubCode() {
        return subCode;
    }

    public void setSubCode(int subCode) {
        this.subCode = subCode;
    }

    public String getSubMsg() {
        return subMsg;
    }

    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }

    public Object getOriginResponseObj() {
        return originResponseObj;
    }

    public void setOriginResponseObj(Object originResponseObj) {
        this.originResponseObj = originResponseObj;
    }
}

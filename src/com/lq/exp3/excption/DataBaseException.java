package com.lq.exp3.excption;

/**
 * 数据库异常
 */
public class DataBaseException extends Exception {
    //错误详情
    private String errMsg;

    public DataBaseException(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
        System.err.println("ERROR MSG:" + errMsg);
    }
}

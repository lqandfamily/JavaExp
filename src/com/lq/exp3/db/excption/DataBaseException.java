package com.lq.exp3.db.excption;

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
    public String getMessage() {
        return super.getMessage() + "\nERROR MSG:" + errMsg;
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}

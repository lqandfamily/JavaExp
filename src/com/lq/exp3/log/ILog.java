package com.lq.exp3.log;

import java.io.IOException;

/**
 * 日志记录接口
 */
public interface ILog {
    /**
     * 打印或输入日志
     */
    void log(String msg) throws IOException;

    /**
     * 错误级别日志
     */
    void errorLog(String msg) throws IOException;
}

package com.lq.test.log;

import com.lq.exp3.log.ConsoleLog;
import com.lq.exp3.log.FileLog;
import com.lq.exp3.log.ILog;


/**
 *　测试Log日志功能
 */
public class TestLog {
    public static void main(String[] args) throws Exception {
        ILog consoleLog = new ConsoleLog();
        consoleLog.log("正常信息");
        consoleLog.errorLog("错误信息");
        ILog fileLog = new FileLog("/home/blue/Projctes/IdeaProjects/JavaHomework/test/com/lq/test/log/testLog.txt","utf-8");
        fileLog.log("正常信息");
        fileLog.errorLog("错误信息");
    }
}

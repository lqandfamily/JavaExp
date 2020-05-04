package com.lq.test.services;

import com.lq.exp3.db.excption.DataBaseException;
import com.lq.exp3.services.IOService;
import com.lq.exp3.services.impl.IOServiceImpl;

import java.io.IOException;

/**
 * 测试读取和订单分配
 */
public class TestReadAndDis {
    public static void main(String[] args) throws IOException, ClassNotFoundException, DataBaseException {
        IOService service = new IOServiceImpl();
        service.handlerSheet("/home/blue/Projctes/IdeaProjects/" +
                "JavaHomework/test/com/lq/test/db/textDb/Transactions.txt", "utf-8");
    }
}

package com.lq.exp3.log;

import java.io.InputStream;
import java.io.OutputStream;

public class ConsoleLog implements ILog{

    @Override
    public void log(String msg) {
        System.out.println("***LOG***" + msg);
    }

    @Override
    public void errorLog(String msg) {
        System.err.println("***ERROR LOG***" + msg);
    }
}

package com.lq.exp3.db;

import com.lq.exp3.excption.DataBaseException;

import java.io.*;

public class TextConnection implements IConnection {
    private File file;
    private IStatement statement;

    public TextConnection(File file) {
        this.file = file;
    }

    @Override
    public synchronized IStatement createStatement() throws DataBaseException {
        try {
            if (statement == null) {
                statement = new TextStatement(file);
            }
            return statement;
        } catch (FileNotFoundException e) {
            throw new DataBaseException("数据库打开IO失败!");
        }
    }

    @Override
    public void close() {
        //todo close方法没有完善
    }
}

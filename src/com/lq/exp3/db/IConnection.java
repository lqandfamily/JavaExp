package com.lq.exp3.db;

import com.lq.exp3.excption.DataBaseException;

/**
 * 数据库连接接口
 *
 */
public interface IConnection<E> {
    IStatement<E> createStatement() throws DataBaseException;

    void close();
}

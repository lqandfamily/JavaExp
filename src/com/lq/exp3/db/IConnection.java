package com.lq.exp3.db;

import com.lq.exp3.db.excption.DataBaseException;

/**
 * 数据库连接接口
 *
 */
public interface IConnection {
    /**
     * 从数据库中获得指定表的连接
     * @param tableName 表名
     * @param eClass　泛型
     * @param <E>　泛型
     * @return IStatement
     * @throws DataBaseException e
     */
    <E> IStatement<E> createStatementByTableName(String tableName, Class<E> eClass) throws DataBaseException;

    /**
     * 标准数据库中获得连接，弃用
     * @return
     * @throws DataBaseException
     */
    IStatement createStatement() throws DataBaseException;

    void close();
}

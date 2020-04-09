package com.lq.exp3.db;

import com.lq.exp3.excption.DataBaseException;

/**
 * 数据库接口
 */
public interface  IDataBaseManager<E> {
    /**
     * 连接到数据库并返回数据库连接对象
     * @param host　主机
     * @param port　端口
     * @param username　用户名
     * @param password　密码
     * @return 数据库连接对象
     * @throws DataBaseException 连接错误时抛出异常
     */
   IConnection<E>  getConnection(String host,int port,String username,String password) throws DataBaseException;

    /**
     * 关闭前务必保证所有未提交数据保存到数据库
     */
  void close();
}

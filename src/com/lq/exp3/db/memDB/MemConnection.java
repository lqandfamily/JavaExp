package com.lq.exp3.db.memDB;

import com.lq.exp3.db.IConnection;
import com.lq.exp3.db.IStatement;
import com.lq.exp3.db.excption.DataBaseException;

import java.util.HashMap;
import java.util.Map;

/**
 * 内存型数据库的连接
 */
public class MemConnection implements IConnection {
    /**
     * 保存所有的statement
     */
    private Map<String,MemStatement> statementMap;

    //核心数据库
    private MemDB db;

    public MemConnection(MemDB db) {
        this.db = db;
        statementMap = new HashMap<>();
    }

    @Override
    public <E> MemStatement<E> createStatementByTableName(String tableName, Class<?> eClass) throws DataBaseException {
        MemStatement<E> statement = null;

        //todo:测试反射获取类型
//        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
//        System.out.println(parameterizedType.getClass());


        if((statement = (MemStatement<E>)statementMap.get(tableName))!=null)     {
            return statement;
        }
        MemTable table;
        if((table = db.getTables().get(tableName))!=null){
            statement = new MemStatement<E>(table);
            statementMap.put(tableName,statement);
        }else {
            throw new DataBaseException("不存在该表: " + tableName);
        }
        return statement;
    }

    /***
     * 该方法已经弃用
     * @return null
     */
    @Override
    public synchronized IStatement createStatement() {
        return null;
    }

    @Override
    public void close() {
        //todo close方法没有完善
    }
}

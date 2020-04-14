package com.lq.exp3.db.memDB;

import com.lq.exp3.db.IResult;
import com.lq.exp3.db.IStatement;
import com.lq.exp3.db.IWhereCallback;
import com.lq.exp3.entity.Inventory;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class MemStatement<E> extends IStatement<E> {
    private MemTable table;

    public MemStatement(MemTable table) {
        this.table = table;
    }


    @Override
    public IResult<E> executeSelAll() {
        List<String> rowData = table.getRowData();
        //做一个简单测试
        for (String str: rowData){
            System.out.println("***********: " + str);
        }

        /*
         * 反射装配数据
         */

        for (String str: rowData){
//            Class <E> entityClass = (Class <E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//             获取 Main 的超类 SuperClass 的签名(携带泛型). 这里为: xxx.xxx.xxx.SuperClass<xxx.xxx.xxx.User>
            Class<? extends Type[]> aClass = MemStatement.class.getGenericInterfaces().getClass();
//             强转成 参数化类型 实体.
            Class<MemStatement> memStatementClass = MemStatement.class;
            System.out.println(memStatementClass.getSimpleName());
        }
        return null;
    }

    @Override
    public IResult<E> executeSel(IWhereCallback where) {
        return null;
    }

    @Override
    public IResult<E> executeAdd(E obj) {
        return null;
    }

    @Override
    public IResult<E> executeUpd(IWhereCallback where) {
        return null;
    }

    @Override
    public IResult<E> executeDel(IWhereCallback where) {
        return null;
    }
}

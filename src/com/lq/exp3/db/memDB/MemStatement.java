package com.lq.exp3.db.memDB;

import com.lq.exp3.db.IResult;
import com.lq.exp3.db.IStatement;
import com.lq.exp3.db.IWhereCallback;
import com.lq.exp3.entity.Inventory;

import java.io.*;
import java.util.List;

public class MemStatement<E> implements IStatement<E> {
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

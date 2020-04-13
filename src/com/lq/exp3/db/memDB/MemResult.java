package com.lq.exp3.db.memDB;

import com.lq.exp3.db.IResult;

import java.util.ArrayList;
import java.util.List;

public class MemResult<E> implements IResult<E> {
    private List<E> dbList;
    //todo next方法的必要性?
    private int i;

    public MemResult() {
        this.dbList = new ArrayList<>();
    }

    @Override
    public E next() {
        if(i < dbList.size()){
            return dbList.get(i++);
        }
        return null;
    }

    @Override
    public void add(E obj) {
        dbList.add(obj);
    }
}

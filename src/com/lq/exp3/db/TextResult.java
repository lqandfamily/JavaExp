package com.lq.exp3.db;

import java.util.ArrayList;
import java.util.List;

public class TextResult<E> implements IResult<E>{
    private List<E> dbList;
    //todo next方法的必要性?
    private int i;

    public TextResult() {
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

package com.lq.exp3.db.memDB;

import com.lq.exp3.db.IResult;
import com.lq.exp3.db.IStatement;
import com.lq.exp3.db.IWhereCallback;
import com.lq.exp3.entity.Inventory;

import java.util.List;
import java.util.zip.ZipInputStream;


public class MemStatement<E> extends IStatement<E> {
    private MemTable table;


    public MemStatement(MemTable table) {
        this.table = table;
    }


    @Override
    public IResult<E> executeSelAll() {
        List<List<String>> rowData = table.getRowData();
        /*
         * todo:反射装配数据
         */
        IResult<Inventory> result = new MemResult<>();

        for (List<String> rowList : rowData) {
            for (String str : rowList) {
                String[] split = str.split("\\s+");
                Inventory inventory = new Inventory(split[0], Integer.parseInt(split[1]), split[2], split[3]);
                result.add(inventory);
            }
        }
        return (IResult<E>) result;
    }

    @Override
    public IResult<E> executeSel(IWhereCallback where) {
        IResult<E> result = new MemResult<>();
        IResult<E> all = executeSelAll();
        E next = null;
        while ((next = all.next()) != null) {
            if (where.where(next)) {
                result.add(next);
            }
        }
        return result;
    }

    @Override
    public IResult<E> executeAdd(E obj) {
        return null;
    }


    @Override
    public IResult<E> executeUpd(IWhereCallback where, E obj) {
        IResult<E> result = executeSelAll();
        //把所有的数据记录回调给用户
        if (table.getTbName().equals("Inventory")) {
            Inventory inventory = (Inventory) obj;
            IResult<Inventory> all = (IResult<Inventory>) executeSelAll();
            //通过条件进行对比
            Inventory item = null;
            while ((item = all.next()) != null) {
                if (where.where(item)) {
                    //更新记录


/*
                    String row = inventory.getItemNumber() + "      " + inventory.getQuantity() + "      " +
                            inventory.getSupplier() + "      " + (inventory.getDescription() == null ?
                            rowData.get(i).split("\\s+")[3] : inventory.getDescription());
*/
                }
            }
        }

        return null;
    }

    @Override
    public IResult<E> executeDel(IWhereCallback where) {
        return null;
    }


}

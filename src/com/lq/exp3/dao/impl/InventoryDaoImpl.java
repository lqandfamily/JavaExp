package com.lq.exp3.dao.impl;

import com.lq.exp3.dao.InventoryDao;
import com.lq.exp3.db.IConnection;
import com.lq.exp3.db.IResult;
import com.lq.exp3.db.IStatement;
import com.lq.exp3.db.IWhereCallback;
import com.lq.exp3.db.excption.DataBaseException;
import com.lq.exp3.db.memDB.MemDBManager;
import com.lq.exp3.entity.Inventory;

import java.util.ArrayList;
import java.util.List;

public class InventoryDaoImpl implements InventoryDao {
    MemDBManager dbManager = null;
    IConnection connection = null;
    IStatement<Inventory> statement = null;


    public InventoryDaoImpl() throws DataBaseException {
        this.dbManager = new MemDBManager();
        dbManager.getConnection("", "utf-8").createStatementByTableName("Inventory", Inventory.class);
    }

    @Override
    public List<Inventory> selAll() {
        List<Inventory> list = new ArrayList<>();
        Inventory inventory = null;
        IResult<Inventory> result = statement.executeSelAll();
        while ((inventory = result.next()) != null) {
            list.add(inventory);
        }
        return list;
    }

    @Override
    public Inventory selOne(String itemId) {
        //检查库存
        IResult<Inventory> inventoryIResult = statement.executeSel(new IWhereCallback() {
            @Override
            public boolean where(Object item) {
                return ((Inventory) item).getItemNumber().equals(itemId);
            }
        });
        return inventoryIResult.next();
    }

    @Override
    public boolean upd(Inventory inventory, String itemId) {
        statement.executeUpd(new IWhereCallback() {
            @Override
            public boolean where(Object item) {
                return false;
            }
        }, null);
//        new Inventory(itemId, newQuantity, customId, null)
        return false;
    }

    @Override
    public boolean add(Inventory inventory) {
        return false;
    }

    @Override
    public boolean del(String itemId) {
        return false;
    }
}

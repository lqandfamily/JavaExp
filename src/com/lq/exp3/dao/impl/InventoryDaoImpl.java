package com.lq.exp3.dao.impl;

import com.lq.exp3.dao.InventoryDao;
import com.lq.exp3.db.IConnection;
import com.lq.exp3.db.IStatement;
import com.lq.exp3.db.excption.DataBaseException;
import com.lq.exp3.db.memDB.MemDBManager;
import com.lq.exp3.entity.Inventory;

public class InventoryDaoImpl implements InventoryDao {
    MemDBManager dbManager = null;
    IConnection connection = null;
    IStatement<Inventory> statement = null;


    public InventoryDaoImpl() throws DataBaseException {
        this.dbManager = new MemDBManager();
        dbManager.getConnection("","utf-8").createStatementByTableName("Inventory", Inventory.class);
    }

    @Override
    public Inventory selOne(String itemId) {
        return null;
    }

    @Override
    public boolean upd(Inventory inventory, String itemId) {
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

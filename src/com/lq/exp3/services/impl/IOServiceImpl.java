package com.lq.exp3.services.impl;

import com.lq.exp3.dao.InventoryDao;
import com.lq.exp3.dao.impl.InventoryDaoImpl;
import com.lq.exp3.db.IConnection;
import com.lq.exp3.db.IResult;
import com.lq.exp3.db.IStatement;
import com.lq.exp3.db.IWhereCallback;
import com.lq.exp3.db.excption.DataBaseException;
import com.lq.exp3.db.memDB.MemDBManager;
import com.lq.exp3.entity.Inventory;
import com.lq.exp3.services.IOService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOServiceImpl implements IOService {
    //数据库访问对象
    InventoryDao inventoryDao = new InventoryDaoImpl();

    public IOServiceImpl() throws DataBaseException, ClassNotFoundException {
    }

    @Override
    public int outCargo(String itemId, String customId, int quantity) throws DataBaseException {
        System.out.println("outCargo:" + itemId + ":" + customId + ":" + quantity);
        //测试
        List<Inventory> all = inventoryDao.selAll();
        for (Inventory tmp : all) {
            System.out.println(tmp);
        }

        //检查库存
        Inventory storeInventory =  inventoryDao.selOne(itemId);
        int newQuantity = 0;
        if(storeInventory != null){
            newQuantity = storeInventory.getQuantity() - quantity;
        }

        //货物出仓
        if(newQuantity < 0){
            throw new DataBaseException("库存不足");
        }
        System.out.println("newQuantity: " + newQuantity);
//        inventoryIStatement.executeUpdOne(new Inventory(itemId, newQuantity, customId, null));

        //测试
        all = inventoryDao.selAll();
        for (Inventory tmp : all) {
            System.out.println(tmp);
        }
        return 0;
    }

    @Override
    public int inCargo(String itemId, int quantity) {
        System.out.println("inCargo:" + itemId + ":" + quantity);

        return 0;
    }


    @Override
    public int newCargo(Inventory inventory) {
        System.out.println("newCargo:" + inventory.toString());
        return 0;
    }

    @Override
    public int delCargo(String itemId) {
        System.out.println("delCargo:" + itemId);
        return 0;
    }

    @Override
    public List<String> readOfferSheet(String filePath, String encode) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), encode));

        List<String> list = new ArrayList<>();
        String line = "";
        while ((line = br.readLine()) != null) {
            list.add(line);
        }
        return list;
    }

    @Override
    public int disSheet(List<String> list) throws DataBaseException {
        for (String row : list) {
            String[] split = row.split("\\s+");
            //发货
            if ("O".equals(split[0])) {
                outCargo(split[1], split[2], Integer.parseInt(split[3]));
            }
            //货物进仓
            if ("R".equals(split[0])) {
                inCargo(split[1], Integer.parseInt(split[2]));
            }
            //新增货物
            if ("A".equals(split[0])) {
                newCargo(new Inventory(split[1], 0, split[2], split[3]));
            }
            //删除货物
            if ("D".equals(split[0])) {
                delCargo(split[1]);
            }
        }
        return SUCCESS;
    }

    @Override
    public int handlerSheet(String filePath, String encode) throws IOException, DataBaseException {
        List<String> list = readOfferSheet(filePath, encode);
        disSheet(list);
        return 0;
    }
}

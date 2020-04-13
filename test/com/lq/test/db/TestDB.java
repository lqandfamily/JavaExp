package com.lq.test.db;

import com.lq.exp3.db.*;
import com.lq.exp3.db.memDB.MemDBManager;
import com.lq.exp3.entity.Inventory;
import com.lq.exp3.excption.DataBaseException;

import java.io.IOException;

public class TestDB {
    public static void main(String[] args) throws DataBaseException, IOException {
        MemDBManager manager = new MemDBManager();
        IConnection connection = manager.getConnection("/home/blue/Projctes/IdeaProjects/JavaHomework/test/com/lq/test/db/textDb/", "UTF-8");
        IStatement<Inventory> statement = connection.createStatementByTableName("Inventory.txt",Inventory.class);
        //测试读取所有
        IResult<Inventory> result = statement.executeSelAll();

//        Inventory inventory;
//        while ((inventory = result.next())!=null)
//        {
//            System.out.println(inventory.getItemNumber());
//        }
        //测试选择性读取
//       result =  statement.executeSel(Inventory.class, new IWhereCallback() {
//            @Override
//            public boolean where(Object item) {
//                Inventory inventory = (Inventory) item;
//                return inventory.getItemNumber().equals("1234");
//            }
//        });
//        while ((inventory = result.next())!=null)
//        {
//            System.out.println(inventory.getItemNumber());
//        }


    }
}

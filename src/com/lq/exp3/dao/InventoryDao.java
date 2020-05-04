package com.lq.exp3.dao;

import com.lq.exp3.entity.Inventory;

import java.util.List;

public interface InventoryDao {

    /**
     * 查找所有
     * @return list
     */
    List<Inventory> selAll();

    /**
     * 根据itemId查询
     * @param itemId id
     * @return 不存在返回null
     */
    Inventory selOne(String itemId);

    /**
     * 根据itemId更新记录
     * @param inventory 待更新数据
     * @param itemId　id
     * @return 失败返回false
     */
    boolean upd(Inventory inventory,String itemId);

    /**
     * 新增记录
     * @param inventory　待新增数据
     * @return 失败返回返回false
     */
    boolean add(Inventory inventory);

    /**
     * 根据itemId删除记录
     * @param itemId　id
     * @return 失败返回返回false
     */
    boolean del(String itemId);
}

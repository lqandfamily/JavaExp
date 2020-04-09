package com.lq.exp3.entity;

/**
 * 仓库货物数据模型
 */
public class Inventory {
    //货物编号
    private String ItemNumber;
    //货物数量
    private int Quantity;
    //供应商编号
    private String supplier;
    //货物描述
    private String description;

    public Inventory() {
    }

    public Inventory(String itemNumber, int quantity, String supplier, String description) {
        ItemNumber = itemNumber;
        Quantity = quantity;
        this.supplier = supplier;
        this.description = description;
    }

    public String getItemNumber() {
        return ItemNumber;
    }

    public void setItemNumber(String itemNumber) {
        ItemNumber = itemNumber;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

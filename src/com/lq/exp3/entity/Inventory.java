package com.lq.exp3.entity;

/**
 * 仓库货物数据模型
 */
public class Inventory {
    //货物编号
    private String itemNumber;
    //货物数量
    private int quantity;
    //供应商编号
    private String supplier;
    //货物描述
    private String description;

    public Inventory() {
    }

    public Inventory(String itemNumber, int quantity, String supplier, String description) {
        this.itemNumber = itemNumber;
        this.quantity = quantity;
        this.supplier = supplier;
        this.description = description;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    @Override
    public String toString() {
        return "Inventory{" +
                "itemNumber='" + itemNumber + '\'' +
                ", quantity=" + quantity +
                ", supplier='" + supplier + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

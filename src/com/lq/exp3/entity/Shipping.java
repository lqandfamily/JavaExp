package com.lq.exp3.entity;

public class Shipping {
    //客户编号
    private String customId;
    //货物编号
    private String itemId;
    //货物数量
    private int quantity;

    public Shipping() {
    }

    public Shipping(String customId, String itemId, int quantity) {
        this.customId = customId;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Shipping{" +
                "customId='" + customId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

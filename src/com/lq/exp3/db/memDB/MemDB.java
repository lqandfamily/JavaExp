package com.lq.exp3.db.memDB;

import java.util.*;

/**
 * 内存型数据库的核心存储
 */
class MemDB {
    private String dbName;

    private Map<String,MemTable> tables;


    public MemDB(String dbName) {
        this.dbName = dbName;
        this.tables = new HashMap<>();
    }

    /**
     * 添加表到数据库中
     * @param table 表
     */
    public void addTable(MemTable table){
        this.tables.put(table.getTbName(),table);
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public Map<String, MemTable> getTables() {
        return tables;
    }

    public void setTables(Map<String, MemTable> tables) {
        this.tables = tables;
    }

}

/**
 * 内存型数据库的内部用表，对用户不可见
 */
class MemTable {
    //表名
    private String tbName;
    //字段
    private List<String> fieldList;
    //所有行数据
    private List<List<String>> rowData;

    public MemTable(String tbName) {
        this.tbName = tbName;
        rowData = new ArrayList<>();
        fieldList = new ArrayList<>();
    }

    /**
     * 读取并设置字段
     */
    public void readField(){
        fieldList = rowData.get(0);
        //移除在记录中的字段，避免污染数据
        rowData.remove(0);
    }

    /**
     * 添加一行数据到表中
     *
     * @param rowList 　行数据
     */
    public void addRow(List<String> rowList) {
        rowData.add(rowList);
    }

    public String getTbName() {
        return tbName;
    }

    public List<String> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<String> fieldList) {
        this.fieldList = fieldList;
    }

    public List<List<String>> getRowData() {
        return rowData;
    }

    public void setRowData(List<List<String>> rowData) {
        this.rowData = rowData;
    }

    public void setTbName(String tbName) {
        this.tbName = tbName;
    }
}


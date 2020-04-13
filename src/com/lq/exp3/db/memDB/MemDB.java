package com.lq.exp3.db.memDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 添加保到数据库中
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
    //所有行数据
    private List<String> rowData;


    public MemTable(String tbName) {
        this.tbName = tbName;
        rowData = new ArrayList<>();
    }

    /**
     * 添加一行数据到表中
     *
     * @param rowStr 　行数据
     */
    public void addRow(String rowStr) {
        rowData.add(rowStr);
    }

    public List<String> getRowData() {
        return rowData;
    }

    public void setRowData(List<String> rowData) {
        this.rowData = rowData;
    }

    public String getTbName() {
        return tbName;
    }

    public void setTbName(String tbName) {
        this.tbName = tbName;
    }
}


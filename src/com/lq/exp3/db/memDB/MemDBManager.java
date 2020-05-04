package com.lq.exp3.db.memDB;

import com.lq.exp3.db.IConnection;
import com.lq.exp3.db.IDataBaseManager;
import com.lq.exp3.db.excption.DataBaseException;

import java.io.*;
import java.sql.Connection;
import java.util.*;

/**
 * 内存型数据库管理器
 * 所有数据读入内存进行管理
 * 数据操作完毕后再写回数据库
 */
public class MemDBManager implements IDataBaseManager {
    private File dbFile;

    private String charset;

    private MemConnection con;

    /**
     * 数据库中表的存储
     * 每个项代表一张表
     * todo:对一个数据库中存在很多表的情况会有性能问题
     */
    private MemDB db;



    /*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
    //todo 由于文件读写的不确定性，本版所有的读写暂时使用单例模式
    /*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/

    public MemDBManager() {
    }

    /**
     * 从数据库中读取所有的表到内存中
     *
     * @param db     内部数据库表
     * @param dbFile 数据库文件夹
     * @throws DataBaseException 　e
     */
    private void readTables(MemDB db, File dbFile, String charset) throws DataBaseException {
        File[] childList = dbFile.listFiles((file, s) -> s.endsWith(DBConfig.DB_FILE_SUFFIX));
        //读取存在的所有表
        if (childList != null) {
            BufferedReader br = null;
            try {
                for (File child : childList) {
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(child), charset));
                    MemTable table = new MemTable(child.getName().replace(DBConfig.DB_FILE_SUFFIX, ""));
                    String line = "";

                    while ((line = br.readLine()) != null) {
                        String[] split = line.split("\\s+");
                        List<String> rowList = new ArrayList<>(Arrays.asList(split));
                        table.addRow(rowList);
                    }
                    this.db.addTable(table);
                    /*
                     * 添加字段
                     */
                    table.readField();
                }
                //子文件 --> 表
            } catch (FileNotFoundException e) {
                throw new DataBaseException("ERROR: 数据库路径不存在!");
            } catch (IOException e) {
                throw new DataBaseException("ERROR: 数据库读取失败!");
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 将内存中的数据写入到db文件中
     *
     * @param db      　db
     * @param dbFile  数据库文件夹
     * @param charset 　编码
     * @throws DataBaseException 　e
     */
    private void writeTables(MemDB db, File dbFile, String charset) throws DataBaseException {
        //所有的表　--> 子文件
        if (!db.getTables().isEmpty()) {
            Map<String, MemTable> tables = db.getTables();
            for (String tbName : tables.keySet()) {
                BufferedWriter bw = null;
                try {
                    File child = new File(dbFile, tbName);
                    bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(child), charset));
                    List<List<String>> rowData = tables.get(tbName).getRowData();
                    if (!rowData.isEmpty()) {
                        for (List<String> rowList : rowData) {
                            for (String row : rowList) {
                                bw.write(row + "\n");
                                bw.flush();
                            }
                        }
                    }
                } catch (FileNotFoundException e) {
                    throw new DataBaseException("ERROR: 数据库路径不存在!");
                } catch (UnsupportedEncodingException e) {
                    throw new DataBaseException("ERROR: 编码不支持!");
                } catch (IOException e) {
                    throw new DataBaseException("ERROR: 数据库写入失败!");
                } finally {
                    if (bw != null) {
                        try {
                            bw.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


    /**
     * 重写方法，不建议使用，默认编码UTF-8
     *
     * @param host     　数据库文件夹路径
     * @param port     请填入null
     * @param username 　请填入null
     * @param password 　请填入null
     * @return con
     * @throws DataBaseException
     */
    @Override
    public synchronized IConnection getConnection(String host, int port, String username, String password) throws DataBaseException {
        this.charset = "UTF-8";
        return getConnection(host, charset);
    }

    /**
     * 从文本数据文件中获取连接
     *
     * @param dbDirPath 数据库文件夹全路径
     * @param charset   　数据库文件编码
     * @return MemConnection
     */

    public synchronized MemConnection getConnection(String dbDirPath, String charset) throws DataBaseException {
        if (dbFile == null) {
            dbFile = new File(dbDirPath);
            if (!dbFile.exists()) {
                throw new DataBaseException("数据库文件夹不存在!");
            }
            //读取数据到内存中
            db = new MemDB(dbFile.getName());
            this.charset = charset;
            readTables(db, dbFile, charset);
        }
        /*
         * 请务必考虑并发问题！
         * BUG，这里还不完善
         * todo 并发问题,暂时单例模式
         */
        if (con == null) {
            con = new MemConnection(db);
        }
        return con;
    }

    @Override
    public void close() throws DataBaseException {
        con.close();
        writeTables(db, dbFile, charset);
    }
}

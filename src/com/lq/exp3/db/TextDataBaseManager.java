package com.lq.exp3.db;

import com.lq.exp3.excption.DataBaseException;

import java.io.File;

public class TextDataBaseManager<E> implements IDataBaseManager<E> {
    private TextConnection con;
    /*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
    //todo 由于文件读写的不确定性，本版所有的读写暂时使用单例模式
    /*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/

    public TextDataBaseManager() {
    }

    /**
     * 重写方法，不建议使用，默认编码UTF-8
     *
     * @param host     　数据库文件路径
     * @param port     请填入null
     * @param username 　请填入null
     * @param password 　请填入null
     * @return
     * @throws DataBaseException
     */
    @Override
    public synchronized IConnection<E>  getConnection(String host, int port, String username, String password) throws DataBaseException {
        return getConnection(host, "UTF-8");
    }

    /**
     * 从文本数据文件中获取连接
     *
     * @param dbPath   数据库文件全路径
     * @param encoding 　数据库文件编码
     * @return TextConnection
     */

    public synchronized TextConnection getConnection(String dbPath, String encoding) throws DataBaseException {
        File file = new File(dbPath);
        if (!file.exists()) {
            throw new DataBaseException("数据库文件不存在!");
        }
        /*
         * 这里通过IO的方式实现数据库的读写操作
         */

        /*
         * 请务必考虑并发问题！
         * BUG，这里还不完善
         */
        //todo 并发问题
        if (con == null) {
            con = new TextConnection(file);
        }
        return con;
    }

    @Override
    public void close() {
        con.close();
    }
}

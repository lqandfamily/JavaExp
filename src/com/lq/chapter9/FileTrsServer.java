package com.lq.chapter9;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Socket通信机制传输文件的服务端
 */
public class FileTrsServer implements Runnable {
    private ServerSocket ss = null;
    private Socket newSocket = null;
    private ExecutorService pool;
    private int port;

    public FileTrsServer(int port) throws IOException {
        this.port = port;
        ss = new ServerSocket(port);
        pool = Executors.newCachedThreadPool();
    }

    /**
     * 启动服务
     *
     * @throws IOException 　异常
     */
    public void start() throws IOException {
        System.out.println("Server启动成功...");
        while (true){
            newSocket = ss.accept();
            pool.execute(this);
        }
    }


    /**
     * 多线程
     */
    @Override
    public void run() {
        System.out.println("Server线程:" + Thread.currentThread().getName());
        Socket socket = newSocket;
        InputStream is = null;
        OutputStream os = null;
        BufferedInputStream bi = null;
        BufferedReader br = null;
        BufferedOutputStream bo = null;
        BufferedWriter bw = null;
        try {
            is = socket.getInputStream();
            os = socket.getOutputStream();
            br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            bw = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));

            String pathStr = br.readLine();
            int indexEnd;
            if (pathStr.startsWith("<path>") && (indexEnd = pathStr.indexOf("</path>")) != -1) {
                pathStr = pathStr.substring(6, indexEnd);
            }
            System.out.println("Server: Path " + pathStr);

            File file = new File(pathStr);
            System.out.println("Server: Path " + file.getParentFile().getPath());
            //穿件文件夹
            if (!file.getParentFile().exists()) {
                if (!file.getParentFile().mkdirs()) {
                    bw.write("ERROR:目标文件无法创建!");
                    bw.newLine();
                    bw.flush();
                }
            }
            bw.write("SUCCESS:目标文件已经创建!");
            bw.newLine();
            bw.flush();
            bi = new BufferedInputStream(is);
            bo = new BufferedOutputStream(new FileOutputStream(file));
            byte[] buff = new byte[1048576];
            int len;
            while ((len = bi.read(buff)) != -1) {
                bo.write(buff, 0, len);
                bo.flush();
            }
            System.out.println("Server:传输完毕");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
                if (bw != null) bw.close();
                if (bi != null) bi.close();
                if (bo != null) bo.close();
                if (is != null) is.close();
                if (os != null) os.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 关闭服务
     *
     * @throws IOException 异常
     */
    public void close() throws IOException {
        ss.close();
    }

    /**
     * 获取端口
     *
     * @return 端口号
     */
    public int getPort() {
        return port;
    }
}

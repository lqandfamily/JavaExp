package com.lq.chapter9;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Socket通信机制传输文件的服务端
 */
public abstract  class FileTrsClient implements Runnable {
    private Socket socket = null;
    private int port;
    private String host;
    private String desPathStr;
    private File file;

    public FileTrsClient(int port, String host, String desPathStr, File file) throws IOException {
        this.host = host;
        this.port = port;
        this.desPathStr = desPathStr;
        this.file = file;
    }

    public void transport() throws IOException {
        socket = new Socket(host, port);
        new Thread(this).start();
    }


    public abstract void fail(String msg);

    public abstract void success();
    /**
     * 多线程
     */
    @Override
    public void run() {
        System.out.println("client:" + Thread.currentThread().getName());
        InputStream is = null;
        OutputStream os = null;
        BufferedInputStream bi = null;
        BufferedReader br = null;
        BufferedOutputStream bo = null;
        BufferedWriter bw = null;
        try {
            is = this.socket.getInputStream();
            os = this.socket.getOutputStream();
            br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            bw = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));

            //写入目标文件路径
            bw.write("<path>" + desPathStr + "</path>");
            bw.newLine();
            bw.flush();
            Thread.sleep(50);
            String returnMsg = br.readLine();
            System.out.println("client MSG: " + returnMsg);
            if ("ERROR:目标文件无法创建!".equals(returnMsg)){
                //失败回调
                fail(returnMsg);
            } else {
                bo = new BufferedOutputStream(os);
                bi = new BufferedInputStream(new FileInputStream(file));
                byte[] buff = new byte[1048576];
                int len;
                System.out.println("Client Write");
                while ((len = bi.read(buff)) != -1) {
                    bo.write(buff, 0, len);
                    bo.flush();
                }
                System.out.println("Client End");
                //成功回调
                success();
                socket.close();
            }

        } catch (IOException | InterruptedException e) {
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
     * 获取端口
     *
     * @return 端口号
     */
    public int getPort() {
        return port;
    }
}

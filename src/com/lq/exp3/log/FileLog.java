package com.lq.exp3.log;

import java.io.*;

public class FileLog implements ILog {
    File logFile = null;
    BufferedWriter bw;

    public FileLog(File logFile, String charset) throws FileNotFoundException, UnsupportedEncodingException {
        this.logFile = logFile;
        if(!logFile.getParentFile().exists()){
            if(!logFile.getParentFile().mkdirs()){
                throw new FileNotFoundException("Log日志文件创建失败!");
            }
        }
        bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logFile),charset));
    }

    public FileLog(String logFilePath, String charset) throws Exception {
        this(new File(logFilePath), charset);
    }

    @Override
    public void log(String msg) throws IOException {
        bw.write(msg);
        bw.newLine();
        bw.flush();
    }

    @Override
    public void errorLog(String msg) throws IOException {
        bw.write("***ERROR LOG***: " + msg);
        bw.newLine();
        bw.flush();
    }

    public void close(){
        if(bw != null){
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

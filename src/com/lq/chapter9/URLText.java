package com.lq.chapter9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 通过URL连续从服务器上读取一个文本文件，并显示该文本文件的内容
 */
public class URLText {

    /**
     * 通过ＵＲＬ读取文本
     *
     * @param spec 　完整URL路径:协议 + 主机 + 端口 + 资源路径
     * @return 读取到的文本
     * @throws IOException 异常
     */
    public String readStr(String spec) throws IOException {
        //构建URL
        URL url = new URL(spec);
        //获取流并包装为缓冲字符流
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();
        String line = "";
        //读取
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        //关闭流
        br.close();
        return sb.toString();
    }
}

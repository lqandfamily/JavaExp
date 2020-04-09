package com.lq.chapter9;

import java.io.File;
import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws IOException {
//        URLText urlText = new URLText();
//        String str = urlText.readStr("https://www.baidu.com/index.html");
//        System.out.println(str);

        FileTrsServer server = new FileTrsServer(8888);
        server.start();


    }
}

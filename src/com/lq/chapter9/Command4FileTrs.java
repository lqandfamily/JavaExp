package com.lq.chapter9;

import java.io.File;
import java.io.IOException;

/**
 * 文件传输的命令行工具
 */
public class Command4FileTrs {
    /**
     * 接受命令行参数
     * @param args　参数数组
     */
    public static void main(String[] args) throws IOException {
        for (String s :
                args) {
            System.out.println(s);
        }


        //参数个数校验
        if(args.length < 1 ){
            System.out.println("请输入参数:\n-h  帮助\n或者file_source file_target");
        }

        //帮助命令
        if(args[0].equals("-h")){
            System.out.println("mcp file_source file_target\nfile_source：本机资源全路径\nfile_target：目标存储资源全路径\n");
            return;
        }

        //传输命令
        System.out.println("开始传输");
        FileTrsClient client = new FileTrsClient(8888,"127.0.0.1",args[1],new File(args[0])) {
            @Override
            public void fail(String msg) {
                System.out.println("传输失败: " + msg);
            }

            @Override
            public void success() {
                System.out.println("传输成功!");
            }
        };
        client.transport();
    }
}

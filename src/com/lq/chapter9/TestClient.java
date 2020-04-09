package com.lq.chapter9;

import java.io.File;
import java.io.IOException;

public class TestClient {
    public static void main(String[] args) throws IOException {
        File file = new File("/home/blue/Projctes/IdeaProjects/JavaHomework/ori.txt");
        FileTrsClient client = new FileTrsClient(8888, "127.0.0.1", "/home/blue/Projctes/IdeaProjects/JavaHomework/dd/cop.txt", file) {
            @Override
            public void fail(String msg) {
                System.err.println(msg);
            }

            @Override
            public void success() {
                System.out.println("SUCCESS!");
            }
        };
        client.transport();
    }
}

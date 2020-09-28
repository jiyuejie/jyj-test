package com.jyj.practice.io;

import java.io.*;

public class IOTest {


    public static void main(String[] args) throws Exception {
        RandomAccessFile r = new RandomAccessFile("E:\\JYJ\\LID10  001.dt", "r");
        byte[] bytes = new byte[1024];
        r.read(bytes,0,1024);


        for (int i = 0 ; i < 1000 ; i ++ ){
            System.out.print(r.readLong() + " " + r.readInt() + "\t" );
            if (i % 10 == 0) {
                System.out.println();
            }
        }




    }
}

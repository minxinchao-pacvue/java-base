package com.minxc.baseUse;

import org.junit.Assert;

import java.nio.ByteBuffer;

public class TestBuffer {

    @org.junit.Test
    public void test1() {
        String str = "abcde";

//1.分配一个指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        System.out.println("--------------allocate()----------------");
        System.out.println(buf.position());//0
        System.out.println(buf.limit());//1024
        System.out.println(buf.capacity());//1024

//2.利用put()存放数据到缓冲区中
        buf.put(str.getBytes());

        System.out.println("-------------put()-------------");
        System.out.println(buf.position());//5
        System.out.println(buf.limit());//1024
        System.out.println(buf.capacity());//1024

//3.切换读取数据模式
        buf.flip();
        System.out.println("--------------flip()------------");
        System.out.println(buf.position());//0
        System.out.println(buf.limit());//5
        System.out.println(buf.capacity());//1024

//4.利用get()读取缓冲区中的数据
        byte[] dst = new byte[buf.limit()];
        buf.get(dst);
        System.out.println(new String(dst, 0, dst.length));//abcd

        System.out.println("--------------get()------------");
        System.out.println(buf.position());//5
        System.out.println(buf.limit());//5
        System.out.println(buf.capacity());//1024

//5.rewind():可重复读
        buf.rewind();

        System.out.println("--------------rewind()------------");
        System.out.println(buf.position());//0
        System.out.println(buf.limit());//5
        System.out.println(buf.capacity());//1024

//6.clear():清空缓冲区。但是缓冲区中的数据依然存在，但是处在“被遗忘”状态
        buf.clear();

        System.out.println("--------------clear()------------");
        System.out.println(buf.position());//0
        System.out.println(buf.limit());//1024
        System.out.println(buf.capacity());//1024

        System.out.println((char) buf.get());
    }


    @org.junit.Test
    public void test2() {
        String str = "abcde";

        ByteBuffer buf = ByteBuffer.allocate(1024);

        buf.put(str.getBytes());


        buf.flip();

        byte[] dst = new byte[buf.limit()];
        buf.get(dst, 0, 2);
        System.out.println(new String(dst, 0, 2));//ab
        System.out.println(buf.position());//2

//mark():标记
        buf.mark();

        buf.get(dst, 2, 2);//再读两个位置
        System.out.println(new String(dst, 2, 2));//cd
        System.out.println(buf.position());//4

//reset():恢复到mark的位置
        buf.reset();
        System.out.println(buf.position());//2

//判断缓冲区中是否还有剩余数据
        if (buf.hasRemaining()) {
//获取缓冲区中可以操作的数量
            System.out.println(buf.remaining());//3
        }
    }

    @org.junit.Test
    public void test3() {
//分配直接缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        System.out.println(buf.isDirect());//false
    }
}
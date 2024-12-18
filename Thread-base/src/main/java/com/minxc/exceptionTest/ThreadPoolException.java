package com.minxc.exceptionTest;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolException {

    private Runnable getRunnable() {
        return () -> {
            Thread thread = Thread.currentThread();
            System.out.println("thread name:" + thread.getName() + " is running");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        };
    }


    @Test
    public void testPoolScale() {
        ArrayBlockingQueue<Runnable> objects = new ArrayBlockingQueue<>(1);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,
                3, 1000, TimeUnit.MILLISECONDS,
                objects);
        System.out.println("thread pool size:" + threadPoolExecutor.getPoolSize());
        Assert.assertEquals(threadPoolExecutor.getPoolSize(),0);
        threadPoolExecutor.submit(getRunnable());
        Assert.assertEquals(threadPoolExecutor.getPoolSize(),1);
        threadPoolExecutor.submit(getRunnable());
        Assert.assertEquals(objects.size(),1);
        Assert.assertEquals(threadPoolExecutor.getPoolSize(),1);
        threadPoolExecutor.submit(getRunnable());
        Assert.assertEquals(threadPoolExecutor.getPoolSize(),2);
        threadPoolExecutor.shutdown();
    }


    @Test
    public void testPoolStartWith0() {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0,
                3, 1000, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1));
        Assert.assertEquals(threadPoolExecutor.getPoolSize(),0);
        threadPoolExecutor.submit(getRunnable());
        Assert.assertEquals(threadPoolExecutor.getPoolSize(),1);
        threadPoolExecutor.submit(getRunnable());
        Assert.assertEquals(threadPoolExecutor.getPoolSize(),2);
        threadPoolExecutor.submit(getRunnable());
        Assert.assertEquals(threadPoolExecutor.getPoolSize(),3);
        threadPoolExecutor.shutdown();
    }


    @Test
    public void testPoolReject() {
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,
//                3, 1000, TimeUnit.MILLISECONDS,
//                new ArrayBlockingQueue<>(1),new ThreadPoolExecutor.AbortPolicy());
//        threadPoolExecutor.submit(getRunnable());
//        threadPoolExecutor.submit(getRunnable());
//        threadPoolExecutor.submit(getRunnable());
//        threadPoolExecutor.submit(getRunnable());
//
//
//        threadPoolExecutor.submit(getRunnable());

    }
}

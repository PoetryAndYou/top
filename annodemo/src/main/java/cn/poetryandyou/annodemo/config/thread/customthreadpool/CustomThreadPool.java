package cn.poetryandyou.annodemo.config.thread.customthreadpool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomThreadPool {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch c =new CountDownLatch(15);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4,10,100L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        long sta = System.currentTimeMillis();
        for (int i = 0; i < 15; i++) {
            threadPoolExecutor.execute(new test(String.valueOf(i),c));
        }
        c.await();
        threadPoolExecutor.shutdown();
        System.out.println("运行时间： "+ (System.currentTimeMillis() - sta)/1000 + "s");
    }

}

class test implements Runnable{

    private String name;
    CountDownLatch c;
    public test(String name,CountDownLatch c) {
        this.name = name;
        this.c = c;
    }

    @Override
    public void run() {
        System.out.println(name);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            c.countDown();
        }
    }
}
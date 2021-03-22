package cn.poetryandyou.annodemo.config.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 线程池使用
 */
@Component
public class AsyncTask2 {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Async
    public void doTask1(int i) throws InterruptedException {
        logger.info("Task" + i + " started.");
    }
}
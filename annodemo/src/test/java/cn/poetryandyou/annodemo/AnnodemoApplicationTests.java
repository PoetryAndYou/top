package cn.poetryandyou.annodemo;

import cn.poetryandyou.annodemo.anno.Log;
import cn.poetryandyou.annodemo.config.thread.AsyncTask;
import cn.poetryandyou.annodemo.config.thread.AsyncTask2;
import cn.poetryandyou.annodemo.service.TargetclassTest;
import cn.poetryandyou.annodemo.study.getpropertesvalue.GetPropValue;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

@SpringBootTest
class AnnodemoApplicationTests {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AsyncTask asyncTask;

    @Autowired
    private AsyncTask2 asyncTask2;

    @Autowired
    private TargetclassTest targetclassTest;

    /**
     * 自定义线程池
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test
    public void AsyncTaskTest() throws InterruptedException, ExecutionException {

        for (int i = 0; i < 100; i++) {
            asyncTask.doTask1(i);
        }
        logger.info("All tasks finished.");
    }

    /**
     * 默认线程池
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test
    public void AsyncTaskTest2() throws InterruptedException, ExecutionException {

        for (int i = 0; i < 100; i++) {
            asyncTask2.doTask1(i);
        }
        logger.info("All tasks finished.");
    }

    /**
     * 自定义注解 + ASP
     */
    @Test
    public void annoAspTest() throws UnsupportedEncodingException {
        char[] chars = new char[]{'\u0097'};
        String str = new String(chars);
        byte[] bytes = str.getBytes();
        System.out.println(Arrays.toString(bytes));
        targetclassTest.getMoney("小明", "23");
    }

    /**
     * 通过反射获取方法上的注解
     */
    @Test
    public void reflectionAnnotationMethod() throws NoSuchMethodException {
        Class clazz = TargetclassTest.class;
        Method getMoney = clazz.getDeclaredMethod("getMoney", String.class, String.class);
        if (getMoney.isAnnotationPresent(Log.class)) {
            Log declaredAnnotation = getMoney.getDeclaredAnnotation(Log.class);
            System.out.println(declaredAnnotation.value());
        }
    }

    /**
     * 通过反射获取类上的注解
     */
    @Test
    public void reflectionAnnotationClass() throws NoSuchMethodException {
        Class clazz = TargetclassTest.class;
        if (clazz.isAnnotationPresent(Log.class)) {
            Log declaredAnnotation = (Log) clazz.getDeclaredAnnotation(Log.class);
            System.out.println(declaredAnnotation.value());
        }
    }

    /**
     * 反射
     */
    @Test
    public void testRef() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class clazz = TargetclassTest.class;
        Method getMoney = clazz.getDeclaredMethod("getMoney", String.class, String.class);
        getMoney.invoke(clazz.newInstance(), "小红", "22");
    }

    /**
     * 使用lombok
     */
    @Autowired
    private GetPropValue getPropValue;

    @Test
    public void testLombok() throws Exception {
        String name = getPropValue.getName();
        String age = getPropValue.getAge();
        System.out.println(name + ":" + age);
    }
}

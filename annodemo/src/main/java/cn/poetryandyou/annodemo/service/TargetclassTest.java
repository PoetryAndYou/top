package cn.poetryandyou.annodemo.service;

import cn.poetryandyou.annodemo.anno.Log;
import org.springframework.stereotype.Component;

@Component
@Log("小明呀")
public class TargetclassTest {

    @Log("小明")
    public void getMoney(String name, String money) {
        //int i = 1/0;
        System.out.println(name + "有" + money + "元");
    }
}
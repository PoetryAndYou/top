package cn.poetryandyou.annodemo.study.getpropertesvalue;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 获取配置文件的值
 */

@Getter
@Setter
@Component

@ConfigurationProperties(prefix="student")
public class GetPropValue {

    private String name;

    private String age;


}

package cn.poetryandyou.annodemo;

import cn.poetryandyou.annodemo.config.TaskThreadPoolConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
//使用异步
@EnableAsync
//开启事务
@EnableTransactionManagement
//开启配置属性支持
@EnableConfigurationProperties({TaskThreadPoolConfig.class} )
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class AnnodemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnnodemoApplication.class, args);
	}

}

package com.yanxisir.fetealarm;

import com.yanxisir.neb.EnableNebHttp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author YanxiSir
 * @since 2018/6/14
 */
@PropertySource({
        "classpath:application.yml",
        "classpath:config.properties"
})
@EnableNebHttp
@SpringBootApplication
public class FeteAlarmApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeteAlarmApplication.class, args);
    }
}

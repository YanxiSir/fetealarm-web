package com.yanxisir.fetealarm.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author YanxiSir
 * @since 2018/6/14
 */
@Data
@Component
@ConfigurationProperties(prefix = "email")
public class EmailProperties {

    private String host;
    private Integer port;
    private String username;
    private String password;

    private String from;
    private String nick;
    private String contact;
}

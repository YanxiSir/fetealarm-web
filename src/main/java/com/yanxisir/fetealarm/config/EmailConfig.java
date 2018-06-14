package com.yanxisir.fetealarm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * @author YanxiSir
 * @since 2018/6/14
 */
@Configuration
public class EmailConfig {

    @Autowired
    private EmailProperties properties;

    @Bean
    public JavaMailSender create() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(properties.getHost());
        sender.setPort(properties.getPort());
        sender.setUsername(properties.getUsername());
        sender.setPassword(properties.getPassword());

        Properties mailProperties = new Properties();
        // 验证身份
        mailProperties.put("mail.smtp.auth", true);
        sender.setJavaMailProperties(mailProperties);
        return sender;
    }
}

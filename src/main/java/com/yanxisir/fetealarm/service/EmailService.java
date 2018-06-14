package com.yanxisir.fetealarm.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @author YanxiSir
 * @since 2018/4/28
 */
@Slf4j
@Service
public class EmailService {


    @Autowired
    private JavaMailSender javaMailSender;


    public boolean send(String title, String content, String nick, String from, String[] tos) {
        return send(title, content, nick, from, tos, true, null);
    }

    /**
     * 最基本发邮件方法
     *
     * @param title   邮件名
     * @param content 邮件内容
     * @param nick    发件人昵称
     * @param from    发件人
     * @param tos     收件人数组
     * @param isHtml  是否是html
     */
    public boolean send(String title, String content, String nick, String from, String[] tos, boolean isHtml, File file) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper;
            if (isHtml) {
                helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
                helper.setText(content, true);
            } else {
                helper = new MimeMessageHelper(mimeMessage, true);
                helper.setText(content);
            }
            try {
                nick = MimeUtility.encodeText(nick);
            } catch (UnsupportedEncodingException e) {
                log.error("mail send ,nick encode error", e);
            }
            InternetAddress address = new InternetAddress(nick + "<" + from + ">");
            helper.setFrom(address);
            helper.setSubject(title);
            helper.setTo(tos);
            if (file != null) {
                FileSystemResource fileSystemResource = new FileSystemResource(file);
                String fileName = MimeUtility.encodeWord(fileSystemResource.getFilename());
                helper.addAttachment(fileName, fileSystemResource);
            }
            javaMailSender.send(mimeMessage);
            log.info("mail send success. title:{} , tos:{}", title, tos);
        } catch (Exception e) {
            log.error("mail send error. title:" + title + " , tos:" + Arrays.toString(tos), e);
            // retry one time
            try {
                javaMailSender.send(mimeMessage);
            } catch (Exception e1) {
                log.error("mail send error again.  title:" + title + " , tos:" + Arrays.toString(tos), e1);
                return false;
            }
        }
        return true;
    }
}


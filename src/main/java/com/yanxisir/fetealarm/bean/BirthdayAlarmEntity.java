package com.yanxisir.fetealarm.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author YanxiSir
 * @since 2018/6/15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BirthdayAlarmEntity {

    private Integer id;
    private String fromAddress;
    private String fromEmail;
    private String fromName;
    private String toEmail;
    private String toName;
    private String content;
    @JSONField(format = "yyyy-MM-dd")
    private Date birthday;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;
    private Integer kind;
    private String imageUrl;


}

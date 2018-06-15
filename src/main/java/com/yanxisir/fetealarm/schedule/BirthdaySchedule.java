package com.yanxisir.fetealarm.schedule;

import com.alibaba.fastjson.JSON;
import com.yanxisir.fetealarm.bean.BirthdayAlarmEntity;
import com.yanxisir.fetealarm.bean.enums.ECardType;
import com.yanxisir.fetealarm.config.EmailProperties;
import com.yanxisir.fetealarm.service.EmailService;
import com.yanxisir.fetealarm.service.FreeMarkerService;
import com.yanxisir.fetealarm.service.NebUserService;
import com.yanxisir.fetealarm.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author YanxiSir
 * @since 2018/6/14
 */
@Slf4j
@Component
public class BirthdaySchedule {

    @Value("${neb.bitthday.contract}")
    private String contractAddress;

    @Autowired
    private NebUserService userService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private FreeMarkerService freeMarkerService;
    @Autowired
    private EmailProperties emailProperties;

    ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Scheduled(cron = "0 0/1 * * * ?")
    public void executeFileDownLoadTask() {
        log.info("任务开始。");
        String result = userService.callContract(contractAddress,
                "getAll",
                JSON.toJSONString(new String[]{}));
        if (StringUtils.isEmpty(result)) {
            log.info("链上数据为空字符串");
            return;
        }
        List<BirthdayAlarmEntity> entityList = JSON.parseArray(result, BirthdayAlarmEntity.class);
        if (CollectionUtils.isEmpty(entityList)) {
            log.info("任务为空");
            return;
        }
        log.info("所有数据：{}", JSON.toJSONString(entityList));
        final Date now = LocalDate.now().toDate();
        final Date oneMinuteAgo = LocalDateTime.now().plusMinutes(-1).toDate();
        entityList.stream().filter(entity -> {
            // 发送时间改为今年
            entity.setSendTime(DateUtils.setCurYear(entity.getSendTime()));
            Date sendDate = entity.getSendTime();
            // 发送当前时间到前一分钟内的数据
            if (sendDate.compareTo(oneMinuteAgo) > 0
                    && sendDate.compareTo(now) <= 0) {
                return true;
            }
            return false;
        }).forEach(entity -> executorService.execute(() -> asyncSendEmail(entity)));

        log.info("任务结束.");

    }

    @Async
    void asyncSendEmail(BirthdayAlarmEntity entity) {
        log.info("=======开始发送邮件:{}=======", entity.getToEmail());
        ECardType type = ECardType.of(entity.getKind()) == null ? ECardType.CARD_0 : ECardType.of(entity.getKind());
        entity.setImageUrl(type.getImage());
        String html = freeMarkerService.html("birthday/card_1.ftl", entity);
        if (StringUtils.isEmpty(html) || entity == null) {
            log.info("模板渲染未空，注意排查问题...");
            return;
        }
        boolean result = emailService.send("星云生日提醒", html, emailProperties.getNick(),
                emailProperties.getFrom(), new String[]{entity.getToEmail()});
        log.info("result:{}, entity:{}", result, JSON.toJSONString(entity));
    }
}

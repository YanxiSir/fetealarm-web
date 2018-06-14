package com.yanxisir.fetealarm.controller;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;
import com.yanxisir.fetealarm.ApiResult;
import com.yanxisir.fetealarm.service.EmailService;
import com.yanxisir.fetealarm.service.FreeMarkerService;
import com.yanxisir.fetealarm.service.NebUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YanxiSir
 * @since 2018/6/14
 */
@RestController
@RequestMapping("/fetealarm/birthday")
public class BirthdayController {

    @Value("${neb.bitthday.contract}")
    private String contractAddress;

    @Autowired
    private NebUserService userService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private FreeMarkerService freeMarkerService;

    @GetMapping("/all")
    public Object allRecord() {
        return ApiResult.success(userService.callVoteContract(contractAddress,
                "getAll",
                JSON.toJSONString(new String[]{})));
    }

    @GetMapping("/send")
    public Object testEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return ApiResult.fail();
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user", email);
        String html = freeMarkerService.html("birthday.ftl", jsonObject);
        return ApiResult.success(emailService.send("Just A Test Email", html, "support",
                "support@yanxisir.com", new String[]{email}));
    }
}
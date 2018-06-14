package com.yanxisir.fetealarm;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @GetMapping("/all")
    public Object allRecord() {
        return userService.callVoteContract(contractAddress, "getAll", JSON.toJSONString(new String[]{}));
    }
}

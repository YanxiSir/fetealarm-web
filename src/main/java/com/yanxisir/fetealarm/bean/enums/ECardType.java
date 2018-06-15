package com.yanxisir.fetealarm.bean.enums;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;

/**
 * @author YanxiSir
 * @since 2018/6/15
 */
public enum ECardType {

    CARD_0(0, "http://birthday.fetealarm.cn/img/card0.jpg"),
    CARD_1(1, "http://birthday.fetealarm.cn/img/card1.jpg"),
    CARD_2(2, "http://birthday.fetealarm.cn/img/card2.jpg"),
    CARD_3(3, "http://birthday.fetealarm.cn/img/card3.jpg"),
    CARD_4(4, "http://birthday.fetealarm.cn/img/card4.jpg");
//    CARD_0(0, "../img/card0.jpg"),
//    CARD_1(1, "../img/card1.jpg"),
//    CARD_2(2, "../img/card2.jpg"),
//    CARD_3(3, "../img/card3.jpg"),
//    CARD_4(4, "../img/card4.jpg");


    private Integer value;
    private String image;

    ECardType(Integer value, String image) {
        this.value = value;
        this.image = image;
    }

    public Integer getValue() {
        return value;
    }

    public String getImage() {
        return image;
    }

    private static Map<Integer, ECardType> valueMap = Maps.newHashMap();

    static {
        Arrays.asList(ECardType.values()).forEach(item -> valueMap.put(item.getValue(), item));
    }

    public static ECardType of(Integer value) {
        return valueMap.get(value);
    }
}

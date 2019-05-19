package com.hackday.securekeyboard.vo;

import lombok.Getter;

@Getter
public enum CardCompanyInfo {
    WOORI(1, "http://101.101.166.145:38080", "woori"),
    SINHAN(2, "www.sinhan.com", "shinhan");
//    KEB(3, "www.keb.com", "keb"),
//    NH(4, "www.nh.com", "nh"),
//    IBK(5, "www.ibk.com", "ibk"),
//    KB(6, "www.kb.com", "kb");

    private final int value;
    private final String url;
    private final String name;
//    private final String publicKey;

    CardCompanyInfo(int value, String url, String name) {
        this.value = value;
        this.url = url;
        this.name = name;
    }

    public static CardCompanyInfo valueOf(int value) {
        switch (value) {
            case 1: return WOORI;
            case 2: return SINHAN;
//            case 3: return KEB;
//            case 4: return NH;
//            case 5: return IBK;
//            case 6: return KB;
            default: throw new AssertionError("Unknown value: " + value);
        }
    }

    public static CardCompanyInfo valueOfName(String name){
        switch (name) {
            case "woori": return WOORI;
            case "shinhan": return SINHAN;
//            case "keb": return KEB;
//            case "nh": return NH;
//            case "ibk": return IBK;
//            case "kb": return KB;
            default: throw new AssertionError("Unknow name: " + name);
        }
    }
}

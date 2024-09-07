package com.automation.utils;

import io.github.bonigarcia.wdm.config.DriverManagerType;

import java.util.*;

public class Constants {
    public static final int MILLIS = 2000;

    public static List<String> MOBILE_RESOLUTIONS = Arrays.asList("360x640", "414x896", "375x667");

    public static List<String> DESKTOP_RESOLUTIONS = Arrays.asList("1920x1080", "1366x768", "1536x864");
    public static List<String> TEST_CASE_ONE_ULRS = Arrays.asList("https://www.getcalley.com/" , "https://www.getcalley.com/calley-lifetime-offer/", "https://www.getcalley.com/see-a-demo/", "https://www.getcalley.com/calley-teams-features/","https://www.getcalley.com/calley-personal-features/");
    public static Map<String, List<String>> DEVICES_AND_RES_MAP = new HashMap<>();
    public static String DEAL_URL = "https://demo.dealsdray.com/";

    static {
        DEVICES_AND_RES_MAP.put("Desktop", DESKTOP_RESOLUTIONS);
        DEVICES_AND_RES_MAP.put("Mobile", MOBILE_RESOLUTIONS);
    }
}

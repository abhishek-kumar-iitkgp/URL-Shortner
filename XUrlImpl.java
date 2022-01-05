package com.crio.shorturl;

import java.util.*;

public class XUrlImpl implements XUrl {
    private static final String aplhaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int count = 9;
    private HashMap<String, String> mapShortToLong = new HashMap<String, String>();
    private HashMap<String, Integer> mapLongToCount = new HashMap<String, Integer>();

    public static String randomAlphaNumeric() {
        StringBuilder builder = new StringBuilder();
        int temp = count;
        while (temp-- != 0) {
            int character = (int) (Math.random() * aplhaNumericString.length());
            builder.append(aplhaNumericString.charAt(character));
        }
        return builder.toString();
    }

    public String registerNewUrl(String longUrl) {

        String domain = "http://short.url/";
        String random, shortUrl;

        for (Map.Entry<String, String> entry : mapShortToLong.entrySet()) {
            if (longUrl == entry.getValue()) {
                return entry.getKey();
            }
        }

            random = randomAlphaNumeric();
            shortUrl = domain + random;
        

        mapShortToLong.put(shortUrl, longUrl);
        mapLongToCount.put(longUrl, 0);
        return shortUrl;
    }

    public String registerNewUrl(String longUrl, String shortUrl) {

        if (mapShortToLong.get(shortUrl) != null) {
            return null;
        }
        mapShortToLong.put(shortUrl, longUrl);
        mapLongToCount.put(longUrl, 0);
        return shortUrl;
    }

    public String getUrl(String shortUrl) {
        boolean isKeyPresent = mapShortToLong.containsKey(shortUrl);
        if(isKeyPresent==false)
        return null;
        mapLongToCount.put(mapShortToLong.get(shortUrl),mapLongToCount.get(mapShortToLong.get(shortUrl))+1);
        return mapShortToLong.get(shortUrl);
    }

    public Integer getHitCount(String longUrl) {
        boolean isKeyPresent = mapLongToCount.containsKey(longUrl);
        if(isKeyPresent==true)
        return mapLongToCount.get(longUrl);
        return 0;
    }

    public void delete(String longUrl) {
        for (Map.Entry<String, String> entry : mapShortToLong.entrySet()) {
            if (entry.getValue() == longUrl) {
                mapShortToLong.remove(entry.getKey());
                break;
            }
        }
    }
}
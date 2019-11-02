package com.mage.crm.util;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {
    public static String encode(String id) {
        String encodeString = Base64.encodeBase64String(id.getBytes());
        String timeString = Base64.encodeBase64String((System.currentTimeMillis() + "").getBytes());
        String encode = timeString + encodeString + timeString.substring(4, 8);
        encode = new StringBuilder(encode).reverse().toString();
        return encode.replaceAll("=", "#");
    }

    public static String decode(String encodeId) {
        String encode = encodeId.replaceAll("#", "=");
        encode = new StringBuilder(encode).reverse().toString();
        byte[] idBytes = Base64.decodeBase64(encode.split("==")[1]);
        return new String(idBytes);
    }

    public static void main(String[] args) {
        System.out.println(decode(encode("15465sa465df46a5s4df")));
    }
}

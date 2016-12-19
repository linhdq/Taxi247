package com.example.linhdq.taxi.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 * Created by LinhDQ on 12/19/16.
 */

public class StringUtil {

    public static String unAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replaceAll("đ", "d");
    }

    public static boolean validatePhoneNumber(String phone) {
        phone = unAccent(phone);
        if (phone == null || phone.trim().toCharArray().length < 10) return false;

        for (char c : phone.toCharArray()) {
            if (!Character.isDigit(c) && c != ' ') return false;
        }

        if (phone.trim().charAt(0) != '0') return false;
        return true;
    }
}

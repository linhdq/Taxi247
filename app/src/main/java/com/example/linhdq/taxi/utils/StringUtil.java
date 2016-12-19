package com.example.linhdq.taxi.utils;

import android.util.Patterns;

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

    public static boolean validateEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean validateName(String name) {
        if (name == null || name.toCharArray().length == 0) return false;
        for (char c : name.toCharArray()) {
            if (!Character.isLetter(c) && c != ' ') return false;
        }
        return true;
    }
}

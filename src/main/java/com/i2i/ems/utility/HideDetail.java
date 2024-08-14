package com.i2i.ems.utility;

public class HideDetail {

    public static String showDetails(String context) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < context.length(); i++) {
            if (i > 5) {
                stringBuilder.append(context.charAt(i));
            } else {
                stringBuilder.append("*");
            }
        }
        return stringBuilder.toString();
    }

}

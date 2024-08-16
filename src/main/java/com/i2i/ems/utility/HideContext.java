package com.i2i.ems.utility;

/**
 * Utility class for hide the context partially.
 */
public class HideContext {

    /**
     * This method is responsible for hide the context partially.
     *
     * @param context - accept the String literal
     * @return partially hidden string like ex : muza****
     */
    public static String hideContext(String context) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < context.length(); i++) {
            if (i < 5) {
                stringBuilder.append(context.charAt(i));
            } else {
                stringBuilder.append("*");
            }
        }
        return stringBuilder.toString();
    }

}

/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev;

/**
 *
 * @author MixaDev
 */
public class Utils {
    public static String escapeString(String originalString) {
        return originalString.replaceAll("\\\\", "\\\\u005C").replaceAll("\"", "\\\\u0022").replaceAll(",", "\\\\u002C");
    }

    public static String unescapeString(String escapedString) {
        return escapedString.replaceAll("u0022", "\"").replaceAll("u002C", ",").replaceAll("u005C", "\\\\");
    }
}

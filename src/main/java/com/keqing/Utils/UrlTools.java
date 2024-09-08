package com.keqing.Utils;

public class UrlTools {

    public static String convertToJdbcUrl(String url) {
        // Check if the input URL starts with "jdbc:mysql://", if not add the prefix
        if (!url.startsWith("jdbc:mysql://")) {
            url = "jdbc:mysql://" + url;
        }
        return url;
    }

}

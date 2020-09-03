package com.jasonfeist.rocket.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     * Converts a string with format yyyy-MM-dd into a java.sql.Timestamp.
     * @param dateString The string to be parsed, allowed to be null
     * @return The java.sql.Timestamp representing this string, may be null
     * @throws ParseException if the string is not in a valid date/time format
     */
    public static Timestamp parseStringDate(String dateString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (dateString != null) {
            Date parsedDate = dateFormat.parse(dateString);
            return new Timestamp(parsedDate.getTime());
        }
        return null;
    }
}

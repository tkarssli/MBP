package com.anony.mybudgetpal.util;

import android.content.res.Resources;
import android.util.TypedValue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Natalie on 9/1/2014.
 */
public class Formatter {
    private static Formatter s_instance = new Formatter();

    private SimpleDateFormat m_dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private Formatter(){
        m_dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    /**
     * Gets the Formatter singleton instance.
     *
     * @return The singleton instance of the formatter.
     */
    public static Formatter getInstance(){
        return s_instance;
    }

    /**
     * Formats a date into a string.
     *
     * @param date The date object to format.
     *
     * @return The string representation of the date.
     */
    public String format(Date date){
        return m_dateFormat.format(date);
    }

    /**
     * Parses a date string into the represented date.
     *
     * @throws ParseException If dateString is not in the format expected for a date.
     *
     * @param dateString The date string to parse.
     *
     * @return The date represented in the given string.
     */
    public Date parseDate(String dateString) throws ParseException {
        return m_dateFormat.parse(dateString);
    }

    /**
     * Converts a dp unit to device-specific pixels.
     *
     * @param dp The device-independent pixel count to convert.
     *
     * @return The number of pixels represented by the given dimension.
     */
    public int dpToPx(int dp){
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    /**
     * Converts a px unit to device-independent pixels.
     *
     * @param px The device-dependent pixel count to convert.
     *
     * @return The number of dp equivalent to the given dimension.
     */
    public int pxToDp(int px){
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, Resources.getSystem().getDisplayMetrics());
    }
}

package com.phistream.practicecode.architecture;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * <pre>
 * author : Manish Verma
 * email : manish.verma@eventzonli.com
 * Date :  5/15/2018
 * </pre>
 */
public class DateConverter {


    @TypeConverter
    public static Date toDate(Long timestamp) {
        return (timestamp == null) ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}

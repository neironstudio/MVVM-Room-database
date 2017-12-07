package com.example.den.mvvm;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * SQL не может хранить типы данных, такие как Date по умолчанию. Вот почему нам нужен способ конвертировать его в совместимый тип данных
 * для его хранения в базе данных. Мы используем @TypeConverters для указания конвертера для атрибута borrowDate .
 * Поэтому, чтобы помочь нам с этим преобразованием, мы создадим класс DateConverter
 * Как вы можете видеть, класс просто преобразует Date в Long и наоборот.
 * Created by Den on 29.09.2017.
 */

public class DateConverter {
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}

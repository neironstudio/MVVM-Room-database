package com.example.den.mvvm;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**Мы аннотируем класс с помощью @Database который принимает два аргумента:
 Массив классов Entity (таблицы)
 Версия базы данных, которая представляет собой целое число.
 Этот класс используется для создания базы данных и получения ее экземпляра. Мы создаем базу данных, используя
 Room.databaseBuilder (context.getApplicationContext (), AppDatabase.class, "loan_db").build ();
 Аргументами являются:
 Context
 Класс базы данных
 Имя, присвоенное базе данных

 * Мы должны создать абстрактный метод для каждого создаваемого нами класса DAO. Это действительно важно.
 Вот и все. Наша база данных готова к работе.
 */
@Database(entities = {BorrowModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "borrow_db")
                            .build();
        }
        return INSTANCE;
    }

    public abstract BorrowModelDao itemAndPersonModel();

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
}

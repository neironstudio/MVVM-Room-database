package com.example.den.mvvm;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**нам нужно создать класс DAO - Data Access Object. Этот класс будет использоваться для определения всех запросов, которые мы будем выполнять в нашей базе данных.
 *
 *
 Мы определяем наши запросы как строки и передаем их как параметр @Query . Каждая аннотация @Query сопряжена с методом.
 Когда вызывается парный метод, запрос выполняется.
 Затем мы используем аннотацию @Insert для методов, которые вставляют записи в таблицу. Аналогичным образом мы можем использовать @Delete и @Update для метода удаления и обновления.
 В случае возникновения конфликтов при таких манипуляционных операциях мы должны также указать стратегию конфликта. В нашем примере мы используем REPLACE .
 Это означает, что конфликтная запись будет заменена текущей записью.
 */
@Dao //Мы используем @Dao чтобы сообщить Room, что это класс DAO.
@TypeConverters(DateConverter.class)
public interface BorrowModelDao {
    @Query("select * from BorrowModel")
    LiveData<List<BorrowModel>> getAllBorrowedItems();

    @Query("select * from BorrowModel where id = :id")
    BorrowModel getItembyId(String id);

    @Insert(onConflict = REPLACE)
    void addBorrow(BorrowModel borrowModel);

    @Delete
    void deleteBorrow(BorrowModel borrowModel);
}

package com.example.den.mvvm;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;


@Entity // @Entity - чтобы сообщить Room использовать текущий класс в качестве таблицы базы данных.
public class BorrowModel {
    //Любой атрибут, которому предшествует аннотация @PrimaryKey будет служить в качестве первичного ключа для таблицы. Здесь мы используем 'autoGenerate
    // = true' так что ключ автоматически генерируется каждый раз, когда делается запись.

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String itemName;
    private String personName;
    @TypeConverters(DateConverter.class)
    private Date borrowDate;

    public BorrowModel(String itemName, String personName, Date borrowDate) {
        this.itemName = itemName;
        this.personName = personName;
        this.borrowDate = borrowDate;
    }

    public String getItemName() {
        return itemName;
    }

    public String getPersonName() {
        return personName;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }
}

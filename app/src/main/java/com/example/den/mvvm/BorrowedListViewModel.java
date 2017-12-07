package com.example.den.mvvm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * ViewModel
 Ранее в сообщении мы упоминали ViewModel . ViewModels - это объекты, которые не имеют жизненного цикла Activity / Fragment .
 Например, они могут сохранять свое состояние / данные даже во время изменения ориентации.
 ViewModels не содержат код, связанный с пользовательским интерфейсом. Это помогает в развязывании наших компонентов приложения.
 В Room экземпляр базы данных в идеале должен содержаться в ViewModel, а не в Activity / Fragment .
 * Created by Den on 29.09.2017.
 */

/*Каждый класс ViewModel должен расширять класс ViewModel . Если вашему ViewModel нужен контекст приложения, он должен расширить AndroidViewModel.
ViewModel будет содержать все данные, необходимые для нашей Activity . В нашем примере мы используем что-то, называемое LiveData .
LiveData - это оболочка, которая позволяет заинтересованным классам наблюдать за изменениями данных внутри оболочки.
        Мы переносим наш список заемных элементов внутри LiveData, чтобы Activity могла наблюдать изменения в данных и обновлять пользовательский интерфейс.
        В нашей модели ViewModel мы сначала получаем экземпляр нашей базы данных, используя AppDatabase.getDatabase(this.getApplication())
        Во-первых, нам нужно загрузить список заемных предметов из базы данных. Для этого мы должны использовать запрос, который мы определили в классе
        DAO, getAllBorrowedItems() .
        Затем вызовите абстрактный метод, который мы создали для DAO, а затем вызовите метод запроса. См. Этот фрагмент в классе BorrowedListViewModel .
        appDatabase.itemAndPersonModel () getAllBorrowedItems ().*/

public class BorrowedListViewModel extends AndroidViewModel {

    private final LiveData<List<BorrowModel>> itemAndPersonList;
    private AppDatabase appDatabase;

    public BorrowedListViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

        itemAndPersonList = appDatabase.itemAndPersonModel().getAllBorrowedItems();
    }


    public LiveData<List<BorrowModel>> getItemAndPersonList() {
        return itemAndPersonList;
    }

    public void deleteItem(BorrowModel borrowModel) {
        new deleteAsyncTask(appDatabase).execute(borrowModel);
    }

    private static class deleteAsyncTask extends AsyncTask<BorrowModel, Void, Void> {

        private AppDatabase db;

        deleteAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final BorrowModel... params) {
            db.itemAndPersonModel().deleteBorrow(params[0]);
            return null;
        }

    }
}

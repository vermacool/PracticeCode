package com.phistream.practicecode.architecture;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * <pre>
 * author : Manish Verma
 * email : manish.verma@eventzonli.com
 * Date :  5/15/2018
 * </pre>
 */
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

    public void deleteItem(BorrowModel model) {
        new deleteAsyncTask(appDatabase).execute(model);
    }

    public static class deleteAsyncTask extends AsyncTask<BorrowModel, Void, Void> {
        private AppDatabase db;

        deleteAsyncTask(AppDatabase database) {
            db = database;
        }

        @Override
        protected Void doInBackground(BorrowModel... borrowModels) {
            db.itemAndPersonModel().deleteBorrow(borrowModels[0]);
            return null;
        }
    }
}

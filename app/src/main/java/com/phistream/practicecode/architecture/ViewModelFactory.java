package com.phistream.practicecode.architecture;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

/**
 * <pre>
 * author : Manish Verma
 * email : manish.verma@eventzonli.com
 * Date :  5/15/2018
 * </pre>
 */
public class ViewModelFactory implements ViewModelProvider.Factory {
    private BorrowedListViewModel viewModel;


    public ViewModelFactory(BorrowedListViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BorrowedListViewModel.class)) {
            return (T) viewModel;
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

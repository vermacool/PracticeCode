package com.phistream.practicecode.architecture.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.phistream.practicecode.R;
import com.phistream.practicecode.architecture.BorrowModel;
import com.phistream.practicecode.architecture.BorrowedListViewModel;
import com.phistream.practicecode.architecture.ViewModelFactory;
import com.phistream.practicecode.architecture.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * author : Manish Verma
 * email : manish.verma@eventzonli.com
 * Date :  5/15/2018
 * </pre>
 */
public class ArchitectureActivity extends AppCompatActivity implements View.OnLongClickListener {

    private BorrowedListViewModel viewModel;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<BorrowModel>(), this);
        recyclerView.setAdapter(recyclerViewAdapter);



        viewModel = ViewModelProviders.of(this).get(BorrowedListViewModel.class);
        viewModel.getItemAndPersonList().observe(ArchitectureActivity.this, new Observer<List<BorrowModel>>() {
            @Override
            public void onChanged(@Nullable List<BorrowModel> itemAndPeople) {
                recyclerViewAdapter.addItems(itemAndPeople);
            }
        });

    }

    @Override
    public boolean onLongClick(View v) {
        BorrowModel mModel = (BorrowModel) v.getTag();
        Snackbar.make(v, "Delete", Snackbar.LENGTH_INDEFINITE).setAction("Delete", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.deleteItem(mModel);
            }
        }).show();
        return true;
    }
}

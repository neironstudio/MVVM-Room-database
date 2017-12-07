package com.example.den.mvvm;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
/*Всякий раз, когда нам нужно использовать ViewModels внутри нашей Activity , Activity должен расширять LifecycleActivity .
Создание ViewModel прост.
        viewModel = ViewModelProviders.of (this) .get (BorrowedListViewModel.class);
        Эти два параметра:
        контекст
        Класс ViewModel*/
public class MainActivity extends LifecycleActivity implements View.OnLongClickListener  {

    private BorrowedListViewModel viewModel;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
                startActivity(intent);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<BorrowModel>(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(recyclerViewAdapter);

        viewModel = ViewModelProviders.of(this).get(BorrowedListViewModel.class);

        viewModel.getItemAndPersonList().observe(MainActivity.this, new Observer<List<BorrowModel>>() {

            //Всякий раз, когда происходит изменение данных, onChanged() обратный вызов onChanged() и мы получаем новые данные.
            // Мы можем соответствующим образом обновить интерфейс.
            @Override
            public void onChanged(@Nullable List<BorrowModel> itemAndPeople) {
                recyclerViewAdapter.addItems(itemAndPeople);
            }
        });
    }

    @Override
    public boolean onLongClick(View view) {

        BorrowModel borrowModel = (BorrowModel) view.getTag();
        viewModel.deleteItem(borrowModel);
        return true;
    }
}

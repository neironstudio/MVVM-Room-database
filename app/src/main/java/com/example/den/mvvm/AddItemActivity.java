package com.example.den.mvvm;

import android.app.DatePickerDialog;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

public class AddItemActivity extends LifecycleActivity implements DatePickerDialog.OnDateSetListener{
  EditText name;
  EditText pname;
  Button btn;
  FloatingActionButton fab;
    private Date date;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;


    private AddBorrowViewModel addBorrowViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, AddItemActivity.this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        name = (EditText)findViewById(R.id.editTextName);
        pname = (EditText)findViewById(R.id.editTextPName);
        btn = (Button)findViewById(R.id.button);
        fab = (FloatingActionButton)findViewById(R.id.floatingActionButton2);
        addBorrowViewModel = ViewModelProviders.of(this).get(AddBorrowViewModel.class);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  Date date  = new Date(10102017);
                BorrowModel borrowModel = new BorrowModel(name.getText().toString(),pname.getText().toString(),date);
                addBorrowViewModel.addBorrow(borrowModel);
                finish();
            }
        });


        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        date = calendar.getTime();
    }
}

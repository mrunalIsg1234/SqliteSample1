package com.sqlitesample1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.sqlitesample1.database.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    @BindView(R.id.toolbar) Toolbar toolbar;
//    @BindView(R.id.empListRecyclerView)RecyclerView empListRecyclerView;
//    @BindView(R.id.submitButton1)TextView submitButton1;
//
     Toolbar toolbar;
    RecyclerView empListRecyclerView;
    TextView submitButton1;


    EmployeeListAdapter employeeListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        empListRecyclerView = (RecyclerView) findViewById(R.id.empListRecyclerView);
        submitButton1 = (TextView) findViewById(R.id.submitButton1);

        setSupportActionBar(toolbar);


        submitButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddEmployeeActivity.class));
                finish();

            }
        });


        List<EmployeeModel> employeeModelList = new ArrayList<>();

        employeeModelList= DatabaseHandler.getInstance(MainActivity.this).getAllEmployee();

        if(!employeeModelList.isEmpty() && employeeModelList !=null)
            fillRecyclerView(employeeModelList);

    }

    /*@OnClick(submitButton1)
    public void onSubmitClick(View v){


    }*/

    public void fillRecyclerView(List<EmployeeModel> employeeModelList){

        employeeListAdapter = new EmployeeListAdapter(employeeModelList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        empListRecyclerView.setLayoutManager(mLayoutManager);
        empListRecyclerView.setItemAnimator(new DefaultItemAnimator());
        empListRecyclerView.setAdapter(employeeListAdapter);
        employeeListAdapter.notifyDataSetChanged();
        empListRecyclerView.setNestedScrollingEnabled(true);
    }

}

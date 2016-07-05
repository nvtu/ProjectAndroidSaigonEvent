package com.example.tuvanninh.hcmcevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {

    ListView listView;
    CommonAdapter foodAdapter;
    ArrayList<Info> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        loadContent();
        initComponent();
    }

    private void loadContent() {
        foodList = new ArrayList<>();

    }

    private void initComponent() {
        listView = (ListView) findViewById(R.id.listFood);
        foodAdapter = new CommonAdapter(this, R.layout.content_list);
        foodAdapter.addAll(foodList);
        listView.setAdapter(foodAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });
    }
}

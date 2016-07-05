package com.example.tuvanninh.hcmcevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class CafeActivity extends AppCompatActivity {

    ListView listView;
    CommonAdapter cafeAdapter;
    ArrayList<Info> cafeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        loadContent();
        initComponent();
    }

    private void loadContent() {
        cafeList = new ArrayList<>();

    }

    private void initComponent() {
        listView = (ListView) findViewById(R.id.listCafe);
        cafeAdapter = new CommonAdapter(this, R.layout.content_list);
        cafeAdapter.addAll(cafeList);
        listView.setAdapter(cafeAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });
    }
}

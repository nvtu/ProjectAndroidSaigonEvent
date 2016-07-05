package com.example.tuvanninh.hcmcevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Search_List extends AppCompatActivity {

    ListView listView;
    CommonAdapter resultAdapter;
    ArrayList<Info> resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__list);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        loadData();
        initComponent();
    }

    private void loadData() {
        Intent intent = getIntent();
        resultList = (ArrayList<Info>) intent.getSerializableExtra("resultList");
    }

    private void initComponent() {
        listView = (ListView) findViewById(R.id.searchListView);
        resultAdapter = new CommonAdapter(this, R.layout.content_list);
        resultAdapter.addAll(resultList);
        listView.setAdapter(resultAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Info info = resultList.get(position);
                Intent intent = new Intent(Search_List.this, DetailActivity.class);
                intent.putExtra("name", info.getName());
                intent.putExtra("description", info.getDescription());
                intent.putExtra("phoneNo", info.getPhoneNo());
                intent.putExtra("url", info.getUrl());
                intent.putExtra("address", info.getAddress());
                intent.putExtra("imageId", info.getBmpId());
                startActivity(intent);
            }
        });
    }


}

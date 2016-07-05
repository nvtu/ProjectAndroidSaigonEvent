package com.example.tuvanninh.hcmcevent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class LocationActivity extends AppCompatActivity {

    ListView listView;
    CommonAdapter locationAdapter;
    static ArrayList<Info> locationList;

    Content content = new Content();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        loadContent();
        initComponent();

    }



    private void loadContent() {
        locationList = content.getLocationList();
    }

    private void initComponent() {
        listView = (ListView) findViewById(R.id.listLocation);
        locationAdapter = new CommonAdapter(this, R.layout.content_list);
        locationAdapter.addAll(locationList);
        listView.setAdapter(locationAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Info info = locationList.get(position);
                Intent intent = new Intent(LocationActivity.this, DetailActivity.class);
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

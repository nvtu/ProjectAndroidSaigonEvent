package com.example.tuvanninh.hcmcevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {

    ListView listView;
    CommonAdapter eventAdapter;
    ArrayList<Info> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        loadContent();
        initComponent();
    }

    private void loadContent() {
        eventList = new ArrayList<>();

    }

    private void initComponent() {
        listView = (ListView) findViewById(R.id.listEvent);
        eventAdapter = new CommonAdapter(this, R.layout.content_list);
        eventAdapter.addAll(eventList);
        listView.setAdapter(eventAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }


}

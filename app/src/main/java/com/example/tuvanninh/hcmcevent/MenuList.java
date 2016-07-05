package com.example.tuvanninh.hcmcevent;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuList extends AppCompatActivity {

    LinearLayout location, event, food, cafe;
    EditText searchTxt;
    ImageView searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initComponent();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    private void initComponent() {
        location = (LinearLayout) findViewById(R.id.locationBut);
        event = (LinearLayout) findViewById(R.id.eventBut);
        food = (LinearLayout) findViewById(R.id.foodBut);
        cafe = (LinearLayout) findViewById(R.id.cafeBut);



        searchTxt = (EditText) findViewById(R.id.searchTxt);
        searchButton = (ImageView) findViewById(R.id.searchButton);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MenuList.this, LocationActivity.class);
                        startActivity(intent);
                    }
                });

            }
        });

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuList.this, EventActivity.class);
                startActivity(intent);
            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuList.this, FoodActivity.class);
                startActivity(intent);
            }
        });

        cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuList.this, CafeActivity.class);
                startActivity(intent);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Content content = new Content();
                ArrayList<Info> locationList = content.getLocationList();
                ArrayList<Info> resultList = new ArrayList<Info>();
                String query = searchTxt.getText().toString().toLowerCase();

                for (int i=0; i<locationList.size(); i++){
                    Info info = locationList.get(i);
                    if (info.getName().toLowerCase().contains(query) || info.getDescription().toLowerCase().contains(query)
                            || info.getAddress().toLowerCase().contains(query)){
                        resultList.add(info);
                    }
                }
                if (resultList.size()==0){
                    Toast.makeText(MenuList.this, "No matching", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(MenuList.this, Search_List.class);
                intent.putExtra("resultList", resultList);
                startActivity(intent);
            }
        });

    }
}

package com.example.nat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Main32Activity extends AppCompatActivity {
    Button fin,semifinal,quarter,practice;
    String type;
    public String day1,month1,year1,hr,min,ap,day,month,match;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main32);
        fin=findViewById(R.id.finaL);
        semifinal=findViewById(R.id.semi);
        quarter=findViewById(R.id.quarter);
        practice=findViewById(R.id.practice);
        Intent i=getIntent();
        day1=i.getStringExtra("day1");
        month1=i.getStringExtra("month1");
        year1=i.getStringExtra("year1");
        hr=i.getStringExtra("hr");
        min=i.getStringExtra("min");
        ap=i.getStringExtra("ap");
        day=i.getStringExtra("day");
        month=i.getStringExtra("month");
        match=i.getStringExtra("match");
        semifinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Semi final";
                Intent intent;
                intent = new Intent(Main32Activity.this, Main6Activity.class);
                intent.putExtra("day1",day1);
                intent.putExtra("month1",month1);
                intent.putExtra("month",month);
                intent.putExtra("year1",year1);
                intent.putExtra("hr",hr);
                intent.putExtra("min",min);
                intent.putExtra("ap",ap);
                intent.putExtra("day",day);
                intent.putExtra("match",match);
                intent.putExtra("type",type);
                startActivity(intent);
            }

        });
        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Final";
                Intent intent;
                intent = new Intent(Main32Activity.this, Main6Activity.class);
                intent.putExtra("day1",day1);
                intent.putExtra("month1",month1);
                intent.putExtra("month",month);
                intent.putExtra("year1",year1);
                intent.putExtra("hr",hr);
                intent.putExtra("min",min);
                intent.putExtra("ap",ap);
                intent.putExtra("day",day);
                intent.putExtra("match",match);
                intent.putExtra("type",type);
                startActivity(intent);
            }

        });
        quarter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Quarter Final";
                Intent intent;
                intent = new Intent(Main32Activity.this, Main6Activity.class);
                intent.putExtra("day1",day1);
                intent.putExtra("month1",month1);
                intent.putExtra("month",month);
                intent.putExtra("year1",year1);
                intent.putExtra("hr",hr);
                intent.putExtra("min",min);
                intent.putExtra("ap",ap);
                intent.putExtra("day",day);
                intent.putExtra("match",match);
                intent.putExtra("type",type);
                startActivity(intent);
            }

        });
        practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Practice";
                Intent intent;
                intent = new Intent(Main32Activity.this, Main6Activity.class);
                intent.putExtra("day1",day1);
                intent.putExtra("month1",month1);
                intent.putExtra("month",month);
                intent.putExtra("year1",year1);
                intent.putExtra("hr",hr);
                intent.putExtra("min",min);
                intent.putExtra("ap",ap);
                intent.putExtra("day",day);
                intent.putExtra("match",match);
                intent.putExtra("type",type);
                startActivity(intent);
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logoutmenu: {
                Intent intent = new Intent(Main32Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(Main32Activity.this, "Succesfully Logout", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.homemenu: {
                Intent intent = new Intent(Main32Activity.this, Main7Activity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}

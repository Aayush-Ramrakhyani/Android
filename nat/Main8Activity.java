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

public class Main8Activity extends AppCompatActivity {
    Button boys;
    Button girls;
    String match;
    public String day1,month1,year1,hr,min,ap,day,month;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        boys=findViewById(R.id.boys);
        girls=findViewById(R.id.girls);
        Intent i=getIntent();
        day1=i.getStringExtra("day1");
        month1=i.getStringExtra("month1");
        year1=i.getStringExtra("year1");
        hr=i.getStringExtra("hr");
        min=i.getStringExtra("min");
        ap=i.getStringExtra("ap");
        day=i.getStringExtra("day");
        month=i.getStringExtra("month");
        boys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                match="Boys Match";
                Intent intent;
                intent = new Intent(Main8Activity.this, Main32Activity.class);
                intent.putExtra("day1",day1);
                intent.putExtra("month1",month1);
                intent.putExtra("month",month);
                intent.putExtra("year1",year1);
                intent.putExtra("hr",hr);
                intent.putExtra("min",min);
                intent.putExtra("ap",ap);
                intent.putExtra("day",day);
                intent.putExtra("match",match);
                startActivity(intent);
            }

        });
        girls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                match="Girls Match";
                Intent intent;
                intent = new Intent(Main8Activity.this, Main32Activity.class);
                intent.putExtra("day1",day1);
                intent.putExtra("month1",month1);
                intent.putExtra("year1",year1);
                intent.putExtra("hr",hr);
                intent.putExtra("min",min);
                intent.putExtra("ap",ap);
                intent.putExtra("day",day);
                intent.putExtra("month",month);
                intent.putExtra("match",match);
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
                Intent intent = new Intent(Main8Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(Main8Activity.this, "Succesfully Logout", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.homemenu: {
                Intent intent = new Intent(Main8Activity.this, Main7Activity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}

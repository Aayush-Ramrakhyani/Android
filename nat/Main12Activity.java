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

public class Main12Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);
        Button c,d,e;
            c = findViewById(R.id.cricket);
            d=findViewById(R.id.football);
            e= findViewById(R.id.baseketball);
            c.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)   {
                    Intent intent;
                    intent =new Intent(Main12Activity.this,Main13Activity.class);
                    startActivity(intent);
                }

            });
            d.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    intent =new Intent(Main12Activity.this,Main21Activity.class);
                    startActivity(intent);
                }
            });
            e.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    intent =new Intent(Main12Activity.this,Main28Activity.class);
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
                Intent intent = new Intent(Main12Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(Main12Activity.this, "Succesfully Logout", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.homemenu: {
                Intent intent = new Intent(Main12Activity.this, Main7Activity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    }


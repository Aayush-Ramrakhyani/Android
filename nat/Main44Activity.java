package com.example.nat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
public class Main44Activity extends AppCompatActivity {
    GridView list;
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main44);
        Bundle extras = getIntent().getExtras();
        String count1=extras.getString("count1");
        int count=Integer.parseInt(count1);
        String[] players=new String[count];
        String[] score=new String[count];
        players = extras.getStringArray("players");
        score = extras.getStringArray("score");
        String[] players2=new String[count];
        String[] score2=new String[count];
        int[] p=new int[count];
        for(int j=0;j<count;j++)
        {
            p[j]=Integer.parseInt(score[j]);
        }
        for(int j=0;j<count-1;j++)
        {
            for(int k=0;k<count-j-1;k++)
            {

                if(p[k]<p[k+1]) {
                    int temp = p[k];
                    p[k] = p[k + 1];
                    p[k + 1] = temp;
                    String a=players[k];
                    players[k]=players[k+1];
                    players[k+1]=a;
                }
            }
        }
        for(int j=0;j<count;j++)
        {
            String a=String.valueOf(p[j]);
            score2[j]=a;
        }
        int k=0,l=0;
        count=count+count;
        String[] both=new String[count];
        for(int j=0;j<count;j++)
        {
            if(j%2==0)
            {
                both[j]=players[k];
                k++;
            }
            else
            {
                both[j]=score2[l];
                l++;
            }
        }
        list=(GridView) findViewById(R.id.listtext);
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, both);
        list.setAdapter(arrayAdapter);
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
                Intent intent = new Intent(Main44Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(Main44Activity.this, "Succesfully Logout", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.homemenu: {
                Intent intent = new Intent(Main44Activity.this, Main3Activity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}

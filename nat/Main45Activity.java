package com.example.nat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main45Activity extends AppCompatActivity {


    int j=0;
    String k,id,id2;
    String item1[]=new String[50];
    ListView list;
    ArrayList<String> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main45);
        Bundle extras = getIntent().getExtras();
        id = extras.getString("teamw");
        id2 = extras.getString("teaml");
        item1=extras.getStringArray("item1");
        k=extras.getString("len");
        int l=Integer.parseInt(k);
        list = (ListView) findViewById(R.id.listview);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        //Toast.makeText(Main25Activity.this,k,Toast.LENGTH_SHORT).show();
        String c[] = new String[l];
        for (int j = 0; j < l; j++) {
            c[j] = item1[j];
        }
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.layout, R.id.layout, c);
        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            String selectedItem = ((TextView) view).getText().toString();
                                            if (arrayList.contains(selectedItem)) {
                                                arrayList.remove(selectedItem);
                                            } else {
                                                arrayList.add(selectedItem);
                                            }
                                        }


                                    }
        );
    }

    public void show(View view) {
        String i="";
        for(String item:arrayList)
        {
            i=item;
        }
        //Toast.makeText(Main25Activity.this,i,Toast.LENGTH_SHORT).show();
        Intent intent;
        intent=new Intent(Main45Activity.this,Main20Activity.class);
        intent.putExtra("i",i);
        setResult(RESULT_OK,intent);
        finish();
    }
}


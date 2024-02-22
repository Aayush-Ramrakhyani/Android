package com.example.nat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class Main15Activity extends AppCompatActivity {
    ListView list;
    ArrayList<String> arrayList = new ArrayList<>();
    DatabaseReference reff2;
    Member4 member;
    String key,t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        String players;
        String[] a = new String[10];
        a = extras.getStringArray("a");
        String d = extras.getString("d");
        key = extras.getString("key");
        t=extras.getString("t");
        setContentView(R.layout.activity_main15);
        list = (ListView) findViewById(R.id.listview);
        int len = Integer.parseInt(d);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        String c[] = new String[len];
        for (int i = 0; i < len; i++) {
            c[i] = a[i];
        }
        reff2 = FirebaseDatabase.getInstance().getReference().child("data5");
        member = new Member4();
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
        String items="";
        for(String item:arrayList)
        {
            items+="-"+item+"\n";
        }
        member.setItems(items);
        member.setKey(key);
        member.setT(t);
        member.setTossl("");
        member.setTossw("");
        member.setRole("");
        member.setScore("");
        member.setBall("");
        member.setOver("");
        member.setWicket("");
        member.setMstatus("running");
        member.setInning("1st inning");
        member.setMatch("Cricket");
        reff2.push().setValue(member);
        finish();
    }
}


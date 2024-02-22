package com.example.nat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main13Activity extends AppCompatActivity{
    FirebaseDatabase Frefernce;
    String[] c =new String[100];
    String[] c2 =new String[100];
    String[] c3=new String[100];
    int i;
    public String team,team1,match;
    DatabaseReference reference;
    ListView list;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main13);
        Frefernce=FirebaseDatabase.getInstance();
        reference=Frefernce.getReference().child("data2");
        list=(ListView) findViewById(R.id.listtext);
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        list.setAdapter(arrayAdapter);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    String day1=ds.child("day1").getValue().toString();
                    String hr=ds.child("hr").getValue().toString();
                    match=ds.child("match").getValue().toString();
                    team=ds.child("team").getValue().toString();
                    team1=ds.child("team1").getValue().toString();
                    String day=ds.child("day").getValue().toString();
                    String team3=team+" V/S "+team1;
                    if(match.contains("Cricket")) {
                        c[i] = team;
                        c2[i] = team1;
                        c3[i]=match;
                        i++;
                        String Display = match + "\n" + day1 + "\n" + team3 + "\n" + hr;
                        arrayList.add(Display);
                        arrayAdapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myintent = new Intent(view.getContext(),Main14Activity.class);
                String ok=c[position];
                String ok2=c2[position];
                String ok3=c3[position];
                myintent.putExtra("ok",ok);
                myintent.putExtra("ok2",ok2);
                myintent.putExtra("ok3",ok3);
                startActivityForResult(myintent,0);
            }
        });

    }
}

package com.example.nat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;


public class Main20Activity extends AppCompatActivity {
    int j=0,c = 0;
    String it,it2,key;
    String id,id2,team1,team2;
    FirebaseDatabase Frefernce,reff;
    DatabaseReference reference,reff1;
    Button striker,nonstriker,bowler,start;
    String n;
    int m=-1;
    Member5 member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main20);
        Intent i = getIntent();
        id = i.getStringExtra("teamw");
        id2 = i.getStringExtra("teaml");
        team1 = i.getStringExtra("team1");
        team2 = i.getStringExtra("team2");
        Frefernce = FirebaseDatabase.getInstance();
        reference = Frefernce.getReference().child("data5");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                j=0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key1=ds.getKey();
                    if(id.equals(key1))
                    {
                        String items=ds.child("items").getValue().toString();
                        String item1=items;
                        it=item1;
                        Log.e("hii",""+item1);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        striker=findViewById(R.id.striker);
        striker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] k=it.split("\n-");
                int l=k.length;
                String len=Integer.toString(l);
                Intent intent;
                intent = new Intent(Main20Activity.this,Main45Activity.class);
                intent.putExtra("teamw", id);
                intent.putExtra("teaml", id2);
                intent.putExtra("item1",k);
                intent.putExtra("len",len);
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        start = findViewById(R.id.start);
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
        {

            if(resultCode==RESULT_OK)
            {
                String i=data.getStringExtra("i");
                n = i;
                reff= FirebaseDatabase.getInstance();
                reff1 = reff.getReference().child("data5").child(id).child("player");
                reff1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String name = (String) ds.child("name").getValue();
                            if (name.equals(n)){
                                c = 1;
                            }
                        }
                        if(c==0){
                            key = id;
                            member = new Member5();
                            member.setName(n);
                            member.setRun("0");
                            member.setBall("0");
                            member.setWicket("0");
                            member.setRun2("0");
                            member.setBall2("0");
                            member.setOver("0");
                            member.setOutby("0");
                            member.setStatus("not_out");
                            reff1.push().setValue(member);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        }
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent=new Intent(Main20Activity.this,Main19Activity.class);
                intent.putExtra("i",n);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}

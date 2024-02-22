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


public class Main18Activity extends AppCompatActivity {
    int j=0,c;
    String it,it2,key;
    String id,id2,team1,team2;
    FirebaseDatabase Frefernce,reff;
    DatabaseReference reference,reff1;
    Button striker,nonstriker,bowler,start;
    String[] n =new String[3];
    int m=-1;
    Member5 member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main18);
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
                    if(id.contains(key1))
                    {
                        String items=ds.child("items").getValue().toString();
                        String item1=items;
                        it=item1;
                        Log.e("hii",""+item1);
                    }
                    if(id2.contains(key1))
                    {
                        String items2=ds.child("items").getValue().toString();
                        String item2=items2;
                        it2=item2;
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        Toast.makeText(Main18Activity.this,it,Toast.LENGTH_SHORT);
        striker=findViewById(R.id.striker);
        striker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m++;
                String[] k=it.split("\n-");
                int l=k.length;
                String len=Integer.toString(l);
                Intent intent;
                intent = new Intent(Main18Activity.this,Main25Activity.class);
                intent.putExtra("teamw", id);
                intent.putExtra("teaml", id2);
                intent.putExtra("item1",k);
                intent.putExtra("len",len);
                c=1;
                startActivityForResult(intent,1);
            }
        });
        nonstriker=findViewById(R.id.non_striker);
        nonstriker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m++;
                String[] k=it.split("\n-");
                int l=k.length;
                String len=Integer.toString(l);
                Intent intent;
                intent = new Intent(Main18Activity.this,Main25Activity.class);
                intent.putExtra("teamw", id);
                intent.putExtra("teaml", id2);
                intent.putExtra("item1",k);
                intent.putExtra("len",len);
                c=1;
                startActivityForResult(intent,1);
            }
        });
        bowler=findViewById(R.id.bowl);
        bowler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m++;
                String[] k=it2.split("\n-");
                int l=k.length;
                String len=Integer.toString(l);
                Intent intent;
                intent = new Intent(Main18Activity.this,Main25Activity.class);
                intent.putExtra("teamw", id);
                intent.putExtra("teaml", id2);
                intent.putExtra("item1",k);
                intent.putExtra("len",len);
                c=2;
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
                n[m]=i;
                if(c==1){
                    key = id;
                }
                if(c==2){
                    key =id2;
                }
                reff= FirebaseDatabase.getInstance();
                reff1 = reff.getReference().child("data5").child(key).child("player");
                member = new Member5();
                member.setName(n[m]);
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

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Main18Activity.this,Main19Activity.class);
                intent.putExtra("player1", n[0]);
                intent.putExtra("player2", n[1]);
                intent.putExtra("player3", n[2]);
                intent.putExtra("Team1",team1);
                intent.putExtra("Team2",team2);
                intent.putExtra("key",id);
                intent.putExtra("key1",id2);
                startActivity(intent);
            }
        });
    }
}

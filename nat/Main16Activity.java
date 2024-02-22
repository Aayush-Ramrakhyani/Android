package com.example.nat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main16Activity extends AppCompatActivity {
    Button team1,team2;
    FirebaseDatabase Frefernce;
    DatabaseReference reference;
    int j=0,i=0;
    String k[]=new String[50];
    String team;
    Member5 member;
    int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main16);
        team1 = findViewById(R.id.t1);
        team2 = findViewById(R.id.t2);
        Intent i=getIntent();
        final String te=i.getStringExtra("te");
        final String te1=i.getStringExtra("te1");
        final String key=i.getStringExtra("key");
        team1.setText(te);
        team2.setText(te1);
        Frefernce = FirebaseDatabase.getInstance();
        reference = Frefernce.getReference().child("data5");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                j=0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key1 = ds.child("key").getValue().toString();
                    if(key.equals(key1))
                    {
                        String it= ds.child("items").getValue().toString();
                        String it1 = it;
                        team = it1;
                        k[j]=ds.getKey();
                        j++;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        team1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Frefernce=FirebaseDatabase.getInstance();
                Frefernce.getReference().child("data5").child(k[0]).child("tossw").setValue(te);
                Frefernce.getReference().child("data5").child(k[0]).child("tossl").setValue(te1);
                Frefernce.getReference().child("data5").child(k[1]).child("tossw").setValue(te);
                Frefernce.getReference().child("data5").child(k[1]).child("tossl").setValue(te1);
                Intent intent;
                intent = new Intent(Main16Activity.this,Main17Activity.class);
                intent.putExtra("teamw",te);
                intent.putExtra("teaml",te1);
                intent.putExtra("key",key);
                intent.putExtra("id",k[0]);
                intent.putExtra("id2",k[1]);
                startActivity(intent);
            }
        });
        team2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                Frefernce.getReference().child("data5").child(k[1]).child("tossw").setValue(te1);
                Frefernce.getReference().child("data5").child(k[1]).child("tossl").setValue(te);
                Frefernce.getReference().child("data5").child(k[0]).child("tossw").setValue(te1);
                Frefernce.getReference().child("data5").child(k[0]).child("tossl").setValue(te);
                intent = new Intent(Main16Activity.this,Main17Activity.class);
                intent.putExtra("teamw",te1);
                intent.putExtra("teaml",te);
                intent.putExtra("key",key);
                intent.putExtra("id",k[1]);
                intent.putExtra("id2",k[0]);
                startActivity(intent);
            }
        });

    }
}

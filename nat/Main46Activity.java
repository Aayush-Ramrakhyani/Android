package com.example.nat;

import androidx.annotation.NonNull;
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

public class Main46Activity extends AppCompatActivity {
    Button next;
    String id,id2,team1,team2,tscore;
    FirebaseDatabase Frefernce,reff;
    DatabaseReference reference,reff1;
    Member4 member;
    String key3,key4;
    String i2,i1,k,k1;
    int c=0,a=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main46);
        Intent i = getIntent();
        id = i.getStringExtra("id");
        id2 = i.getStringExtra("id2");
        team1 = i.getStringExtra("Team1");
        team2 = i.getStringExtra("Team2");
        tscore =i.getStringExtra("tscore");
        next = findViewById(R.id.next);

        Frefernce = FirebaseDatabase.getInstance();
        reference = Frefernce.getReference().child("data5");

        reff= FirebaseDatabase.getInstance();
        reff1 = reff.getReference().child("data5");
        member = new Member4();



        reference.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                                    String key1 = ds.getKey();
                                                    if (id.contains(key1)) {
                                                        String items = ds.child("items").getValue().toString();
                                                        String key = ds.child("key").getValue().toString();
                                                        i2=items;
                                                        k=key;
                                                        c=1;
                                                    }
                                                    if(id2.contains(key1)){
                                                        String items1 = ds.child("items").getValue().toString();
                                                        String key2 = ds.child("key").getValue().toString();
                                                        i1=items1;
                                                        k1=key2;
                                                        a=1;
                                                    }
                                                }


                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c==1){
                    member.setItems(i2);
                    member.setKey(k);
                    member.setT("");
                    member.setTossl("");
                    member.setTossw("");
                    member.setRole("Bat");
                    member.setScore("");
                    member.setBall("");
                    member.setOver("");
                    member.setWicket("");
                    member.setMstatus("running");
                    member.setInning("2nd inning");
                    member.setMatch("Cricket");
                    member.setLoss("");
                    member.setWon("");
                    reff1.push().setValue(member);

                }
                if(a==1){
                    member.setItems(i1);
                    member.setKey(k1);
                    member.setT("");
                    member.setTossl("");
                    member.setTossw("");
                    member.setRole("Bowl");
                    member.setScore("");
                    member.setBall("");
                    member.setOver("");
                    member.setWicket("");
                    member.setLoss("");
                    member.setWon("");
                    member.setMatch("Cricket");
                    reff1.push().setValue(member);

                }
                Intent intent;
                intent = new Intent(Main46Activity.this, Main47Activity.class);
                intent.putExtra("teamw", k);
                intent.putExtra("teamw1",i2);
                intent.putExtra("teaml", k1);
                intent.putExtra("teaml1", i1);
                intent.putExtra("team1", team1);
                intent.putExtra("team2", team2);
                intent.putExtra("tscore",tscore);
                intent.putExtra("id",id2);
                intent.putExtra("id2",id);
                startActivity(intent);
            }
        });
    }
}

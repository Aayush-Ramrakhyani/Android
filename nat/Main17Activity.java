package com.example.nat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;
import java.util.*;
public class Main17Activity extends AppCompatActivity {
    TextView t;
    Button bat,ball;
    FirebaseDatabase Frefernce;
    DatabaseReference reference;
    int j=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main17);
        Intent i=getIntent();
        final String te=i.getStringExtra("teamw");
        final String te1=i.getStringExtra("teaml");
        final String key=i.getStringExtra("key");
        final String id=i.getStringExtra("id");
        final String id2=i.getStringExtra("id2");
        t =(TextView)findViewById(R.id.text1);
        t.setText(te);
        bat = findViewById(R.id.bat);
        ball = findViewById(R.id.ball);
        bat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Main17Activity.this,Main18Activity.class);
                Frefernce=FirebaseDatabase.getInstance();
                Frefernce.getReference().child("data5").child(id).child("role").setValue("Bat");
                Frefernce.getReference().child("data5").child(id2).child("role").setValue("Bowl");

                intent.putExtra("teamw",id);
                intent.putExtra("teaml",id2);
                intent.putExtra("team1",te);
                intent.putExtra("team2",te1);
                intent.putExtra("elected","BAT");
                intent.putExtra("key",key);
                startActivity(intent);
            }
        });
        ball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Main17Activity.this,Main18Activity.class);
                Frefernce=FirebaseDatabase.getInstance();
                Frefernce.getReference().child("data5").child(id).child("role").setValue("Bowl");
                Frefernce.getReference().child("data5").child(id2).child("role").setValue("Bat");
                intent.putExtra("teamw",id2);
                intent.putExtra("teaml",id);
                intent.putExtra("team1",te1);
                intent.putExtra("team2",te);
                intent.putExtra("elected","BOWL");
                intent.putExtra("key",key);
                startActivity(intent);
            }
        });
    }
}

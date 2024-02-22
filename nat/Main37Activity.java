package com.example.nat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main37Activity extends AppCompatActivity {
    String ok,ok1,ok2,key,key1,goal;
    TextView t,t1,t2,t3,t4;
    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference ref;
    String score="0",name="FCAIT";
    Button exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main37);
        Intent i = getIntent();
        ok = i.getStringExtra("ok");
        ok1 = i.getStringExtra("ok2");
        ok2=i.getStringExtra("ok3");
        key=i.getStringExtra("key");
        key1=i.getStringExtra("key1");
        t=(TextView)findViewById(R.id.team1);
        t1=(TextView)findViewById(R.id.team2);
        t2=(TextView)findViewById(R.id.match);
        t3=(TextView)findViewById(R.id.score1);
        t4=(TextView)findViewById(R.id.score2);
        exit=findViewById(R.id.exit);
        t.setText(ok);
        t1.setText(ok1);
        t2.setText(ok2);
        firebaseDatabase=FirebaseDatabase.getInstance();
        ref=firebaseDatabase.getReference().child("data4");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String k=ds.getKey();
                    String goal1=(String)ds.child("score").getValue();
                    if(k.equals(key))
                    {
                        goal=goal1;
                        t3.setText(goal);
                    }
                    if(k.equals(key1))
                    {
                        goal=goal1;
                        t4.setText(goal);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent=new Intent(Main37Activity.this,Main10Activity.class);
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
                Intent intent = new Intent(Main37Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(Main37Activity.this, "Succesfully Logout", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.homemenu: {
                Intent intent = new Intent(Main37Activity.this, Main7Activity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.example.nat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main34Activity extends AppCompatActivity {
    FirebaseDatabase Frefernce;
    DatabaseReference reference;
    String a[]=new String[100];
    String b[]=new String[100];
    String j[]=new String[3];
    String d,key,team,team1;
    String match,g,e;
    int c=0,k=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main34);
        final Button t1=findViewById(R.id.team1);
        final Button t2=findViewById(R.id.team2);
        Button start = findViewById(R.id.start);
        Button cancel = findViewById(R.id.cancel);
        Frefernce = FirebaseDatabase.getInstance();
        reference = Frefernce.getReference().child("data2");
        Intent i=getIntent();
        final String te=i.getStringExtra("ok");
        final String te1=i.getStringExtra("ok2");
        final String te2=i.getStringExtra("ok3");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String day1 = ds.child("day1").getValue().toString();
                    String hr = ds.child("hr").getValue().toString();
                    match = ds.child("match").getValue().toString();
                    team = ds.child("team").getValue().toString();
                    team1 = ds.child("team1").getValue().toString();
                    String day = ds.child("day").getValue().toString();
                    String team3 = team + " V/S " + team1;
                    if(te.equals(team) && te1.equals(team1) && te2.equals(match)) {
                        t1.setText(team);
                        t2.setText(team1);
                        key = ds.getKey();
                        return;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        FirebaseDatabase Frefernce2;
        DatabaseReference reference2;
        Frefernce2 = FirebaseDatabase.getInstance();
        reference2 = Frefernce2.getReference().child("data3");
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(te2.contains("Boys"))
                {
                    g="M";
                }
                else
                {
                    g="F";
                }

                int i = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String clg1 = ds.child("clg1").getValue().toString();
                    String text = ds.child("text").getValue().toString();
                    String gender=ds.child("gender").getValue().toString();
                    if (text.contains("Volleyball") && gender.equals(g)) {
                        if (clg1.contains(te)) {

                            String fname = ds.child("fname").getValue().toString();
                            String lname = ds.child("lname").getValue().toString();
                            String name = fname + " " + lname;
                            a[i] = name;
                            i++;
                            c++;
                        }
                        if (clg1.contains(te1)) {

                            String fname = ds.child("fname").getValue().toString();
                            String lname = ds.child("lname").getValue().toString();
                            String name = fname + " " + lname;
                            b[k] = name;
                            k++;
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }});
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)   {
                String d=Integer.toString(c);
                Intent intent;
                intent =new Intent(Main34Activity.this,Main35Activity.class);
                intent.putExtra("a",a);
                intent.putExtra("d",d);
                intent.putExtra("t",team);
                intent.putExtra("key",key);
                startActivityForResult(intent,1);
            }

        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)   {
                String e=Integer.toString(k);
                Intent intent;
                intent =new Intent(Main34Activity.this,Main35Activity.class);
                intent.putExtra("a",b);
                intent.putExtra("key",key);
                intent.putExtra("t",team1);
                intent.putExtra("d",e);
                startActivityForResult(intent,1);
            }

        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Main34Activity.this, Main36Activity.class);
                intent.putExtra("te", te);
                intent.putExtra("te1", te1);
                intent.putExtra("key",key);
                intent.putExtra("id",j[0]);
                intent.putExtra("id2",j[1]);
                startActivity(intent);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Main34Activity.this, Main35Activity.class);
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
                Intent intent = new Intent(Main34Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(Main34Activity.this, "Succesfully Logout", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.homemenu: {
                Intent intent = new Intent(Main34Activity.this, Main7Activity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
        {
            if(resultCode==RESULT_OK)
            {
                Frefernce = FirebaseDatabase.getInstance();
                reference = Frefernce.getReference().child("data4");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int m=0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String key1 = ds.child("key").getValue().toString();
                            if(key.equals(key1))
                            {
                                j[m]=ds.getKey();
                                m++;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });

            }
        }

    }
}

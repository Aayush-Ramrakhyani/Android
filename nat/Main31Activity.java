package com.example.nat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class Main31Activity extends AppCompatActivity {
    Button ok,team1,team2,timer;
    TextView t1,t2;
    EditText time;
    Button a,b,c,d,e,f;
    int ts1,ts2;
    CountDownTimer mtimer;
    boolean timerrunning;
    long START_TIME;
    String it,it2,id,id2,i,pid,id3,te,te1;
    FirebaseDatabase Frefernce;
    DatabaseReference reference;
    long timeleft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main31);
        Intent i = getIntent();
        te = i.getStringExtra("te");
        te1 = i.getStringExtra("te1");
        final String key = i.getStringExtra("key");
        time = findViewById(R.id.time);
        Frefernce = FirebaseDatabase.getInstance();
        reference = Frefernce.getReference().child("data6");
        id=i.getStringExtra("id");
        id2=i.getStringExtra("id2");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key1 = ds.getKey();
                    if(id.contains(key1))
                    {
                        String items= (String) ds.child("items").getValue();
                        String item1=items;
                        it=item1;
                    }
                    if(id2.contains(ds.getKey()))
                    {
                        String items2= (String) ds.child("items").getValue();
                        String item2=items2;
                        it2=item2;
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        t1 =(TextView) findViewById(R.id.team1);
        t2 =(TextView) findViewById(R.id.team2);
        t1.setText(te);
        t2.setText(te1);
        team1 = findViewById(R.id.score1);
        team2 = findViewById(R.id.score2);
        timer = findViewById(R.id.timer);
        ok = findViewById(R.id.ok);
        a=findViewById(R.id.one);
        b=findViewById(R.id.two);
        c=findViewById(R.id.three);
        d=findViewById(R.id.one1);
        e=findViewById(R.id.two2);
        f=findViewById(R.id.three3);
        team1.setEnabled(false);
        team2.setEnabled(false);
        ok.setEnabled(false);
        time.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ok.setEnabled(true);
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time2= time.getText().toString();
                Long l = new Long(time2);
                START_TIME = l*60*1000;
                timeleft = START_TIME;
                int min = (int)(timeleft / 1000 )/ 60 ;
                int sec = (int)(timeleft / 1000 )% 60 ;
                String timeleftformat =  String.format(Locale.getDefault(),"%02d:%02d",min,sec);
                timer.setText(timeleftformat);
                ok.setVisibility(View.INVISIBLE);
                time.setVisibility(View.INVISIBLE);
                timer.setVisibility(View.VISIBLE);
            }
        });
        team1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.setVisibility(View.VISIBLE);
                b.setVisibility(View.VISIBLE);
                c.setVisibility(View.VISIBLE);
                team1.setEnabled(false);
                team2.setEnabled(false);
                pauseTimer();
                a.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ts1++;
                        String o=Integer.toString(ts1);
                        team1.setText(o);
                        a.setVisibility(View.INVISIBLE);
                        b.setVisibility(View.INVISIBLE);
                        c.setVisibility(View.INVISIBLE);
                        team1.setEnabled(true);
                        team2.setEnabled(true);
                        String[] k=it.split("\n-");
                        int l=k.length;
                        String len=Integer.toString(l);
                        Intent i=new Intent(Main31Activity.this,Main27Activity.class);
                        i.putExtra("te",te);
                        i.putExtra("key",key);
                        i.putExtra("item1",k);
                        i.putExtra("len",len);
                        i.putExtra("id",id);
                        i.putExtra("o","1");
                        i.putExtra("sc",o);
                        startTimer();
                        startActivity(i);
                    }
                });
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ts1=ts1+2;
                        String o=Integer.toString(ts1);
                        team1.setText(o);
                        a.setVisibility(View.INVISIBLE);
                        b.setVisibility(View.INVISIBLE);
                        c.setVisibility(View.INVISIBLE);
                        team1.setEnabled(true);
                        team2.setEnabled(true);
                        String[] k=it.split("\n-");
                        int l=k.length;
                        String len=Integer.toString(l);
                        Intent i=new Intent(Main31Activity.this,Main27Activity.class);
                        i.putExtra("te",te);
                        i.putExtra("key",key);
                        i.putExtra("item1",k);
                        i.putExtra("len",len);
                        i.putExtra("sc",o);
                        i.putExtra("id",id);
                        i.putExtra("o","2");
                        startTimer();
                        startActivity(i);
                    }
                });
                c.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ts1=ts1+3;
                        String o=Integer.toString(ts1);
                        team1.setText(o);
                        a.setVisibility(View.INVISIBLE);
                        b.setVisibility(View.INVISIBLE);
                        c.setVisibility(View.INVISIBLE);
                        team1.setEnabled(true);
                        team2.setEnabled(true);
                        String[] k=it.split("\n-");
                        int l=k.length;
                        String len=Integer.toString(l);
                        Intent i=new Intent(Main31Activity.this,Main27Activity.class);
                        i.putExtra("te",te);
                        i.putExtra("key",key);
                        i.putExtra("sc",o);
                        i.putExtra("item1",k);
                        i.putExtra("len",len);
                        i.putExtra("id",id);
                        i.putExtra("o","3");
                        startTimer();
                        startActivity(i);
                    }
                });

            }
        });
        team2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.setVisibility(View.VISIBLE);
                e.setVisibility(View.VISIBLE);
                f.setVisibility(View.VISIBLE);
                team1.setEnabled(false);
                team2.setEnabled(false);
                pauseTimer();
                d.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ts2++;
                        String o=Integer.toString(ts2);
                        team2.setText(o);
                        d.setVisibility(View.INVISIBLE);
                        e.setVisibility(View.INVISIBLE);
                        f.setVisibility(View.INVISIBLE);
                        team1.setEnabled(true);
                        team2.setEnabled(true);
                        String[] k=it2.split("\n-");
                        int l=k.length;
                        String len=Integer.toString(l);
                        Intent i=new Intent(Main31Activity.this,Main27Activity.class);
                        i.putExtra("te",te);
                        i.putExtra("key",key);
                        i.putExtra("sc",o);
                        i.putExtra("item1",k);
                        i.putExtra("len",len);
                        i.putExtra("id",id2);
                        i.putExtra("o","1");
                        startTimer();
                        startActivity(i);
                    }
                });
                e.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ts2=ts2+2;
                        String o=Integer.toString(ts2);
                        team2.setText(o);
                        d.setVisibility(View.INVISIBLE);
                        e.setVisibility(View.INVISIBLE);
                        f.setVisibility(View.INVISIBLE);
                        team1.setEnabled(true);
                        team2.setEnabled(true);
                        String[] k=it2.split("\n-");
                        int l=k.length;
                        String len=Integer.toString(l);
                        Intent i=new Intent(Main31Activity.this,Main27Activity.class);
                        i.putExtra("te",te);
                        i.putExtra("key",key);
                        i.putExtra("item1",k);
                        i.putExtra("len",len);
                        i.putExtra("sc",o);
                        i.putExtra("id",id2);
                        i.putExtra("o","2");
                        startTimer();
                        startActivity(i);
                    }
                });
                f.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ts2=ts2+3;
                        String o=Integer.toString(ts2);
                        team2.setText(o);
                        d.setVisibility(View.INVISIBLE);
                        e.setVisibility(View.INVISIBLE);
                        f.setVisibility(View.INVISIBLE);
                        team1.setEnabled(true);
                        team2.setEnabled(true);
                        String[] k=it2.split("\n-");
                        int l=k.length;
                        String len=Integer.toString(l);
                        Intent i=new Intent(Main31Activity.this,Main27Activity.class);
                        i.putExtra("te",te);
                        i.putExtra("key",key);
                        i.putExtra("item1",k);
                        i.putExtra("len",len);
                        i.putExtra("id",id2);
                        i.putExtra("o","3");
                        i.putExtra("sc",o);
                        startTimer();
                        startActivity(i);
                    }
                });
            }
        });

        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(timerrunning){
                    pauseTimer();
                }
                else{
                    startTimer();
                }

            }
        });

        updatetimer();

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
                Intent intent = new Intent(Main31Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(Main31Activity.this, "Succesfully Logout", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.homemenu: {
                Intent intent = new Intent(Main31Activity.this, Main7Activity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    public void startTimer(){
        team1.setEnabled(true);
        team2.setEnabled(true);

        mtimer = new CountDownTimer(timeleft,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                team1.setEnabled(true);
                team2.setEnabled(true);
                timeleft = millisUntilFinished;
                updatetimer();
            }

            @Override
            public void onFinish() {
                timerrunning = false;
                int min = (int)(timeleft / 1000 )/ 60 ;
                int sec = (int)(timeleft / 1000 )% 60 ;
                String timeleftformat =  String.format(Locale.getDefault(),"%02d:%02d",min,sec);
                team1.setEnabled(false);
                team2.setEnabled(false);
                timer.setText(timeleftformat);
                reset();
            }
        }.start();
        timerrunning = true;
    }
    public void updatetimer(){
        int min = (int)(timeleft / 1000 )/ 60 ;
        int sec = (int)(timeleft / 1000 )% 60 ;
        String timeleftformat =  String.format(Locale.getDefault(),"%02d:%02d",min,sec);
        timer.setText(timeleftformat);
    }
    public void pauseTimer(){
        mtimer.cancel();
        timerrunning = false;
        int min1 = (int)(timeleft / 1000 )/ 60 ;
        int sec1 = (int)(timeleft / 1000 )% 60 ;
        String timeleftformat =  String.format(Locale.getDefault(),"%02d:%02d",min1,sec1);
        timer.setText(timeleftformat);
        team1.setEnabled(false);
        team2.setEnabled(false);

    }
    public void reset(){
        timeleft = START_TIME;
        Frefernce=FirebaseDatabase.getInstance();
        if(ts1>ts2) {
            Frefernce.getReference().child("data6").child(id).child("won").setValue(te);
            Frefernce.getReference().child("data6").child(id).child("loss").setValue(te1);
            Frefernce.getReference().child("data6").child(id2).child("won").setValue(te);
            Frefernce.getReference().child("data6").child(id2).child("loss").setValue(te1);
        }
        if(ts1<ts2)
        {
            Frefernce.getReference().child("data6").child(id).child("won").setValue(te1);
            Frefernce.getReference().child("data6").child(id).child("loss").setValue(te);
            Frefernce.getReference().child("data6").child(id2).child("won").setValue(te1);
            Frefernce.getReference().child("data6").child(id2).child("loss").setValue(te);
        }
        if(ts1==ts2)
        {
            Frefernce.getReference().child("data6").child(id).child("won").setValue("Tie");
            Frefernce.getReference().child("data6").child(id2).child("won").setValue("Tie");
        }
        updatetimer();
    }
}
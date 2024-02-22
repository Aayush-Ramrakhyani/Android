package com.example.nat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main19Activity extends AppCompatActivity {
    TextView t, p, p1, b, score, strikerrun, nonstrikerrun, overs, over;
    int srun = 0, nsrun = 0, tscore = 0, tball = 0, sball = 0, nsball = 0, twicket = 0, tover = 0, bover = 0, bball = 0, bwicket = 0, brun = 0;
    Button zero, one, two, three, four, six, out, wd, nb, lb, bye;
    FirebaseDatabase Frefernce, Frefernce1,Frefernce2,Frefernce3, f, f1,f2;
    DatabaseReference reference, reference1,reference2,reference3;
    String ball, bat1, bat2;
    Member5 member;
    String s = "s";
    String batsman1,batsman2,bowler,team1,team2,id,id2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main19);
        Intent i = getIntent();
        batsman1 = i.getStringExtra("player1");
        batsman2 = i.getStringExtra("player2");
        bowler = i.getStringExtra("player3");
        team1 = i.getStringExtra("Team1");
        team2 = i.getStringExtra("Team2");
        id = i.getStringExtra("key");
        id2 = i.getStringExtra("key1");
        Frefernce = FirebaseDatabase.getInstance();
        reference = Frefernce.getReference().child("data5").child(id).child("player");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String name = (String) ds.child("name").getValue();
                    if (name.equals(batsman1)) {
                        bat1 = ds.getKey();
                    }
                    if (name.equals(batsman2)) {
                        bat2 = ds.getKey();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Frefernce1 = FirebaseDatabase.getInstance();
        reference1 = Frefernce1.getReference().child("data5").child(id2).child("player");
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String name = (String) ds.child("name").getValue();
                    if (name.equals(bowler)) {
                        ball = ds.getKey();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        t = (TextView) findViewById(R.id.batting_team);
        t.setText(team1);

        p = (TextView) findViewById(R.id.striker);
        p.setText(batsman1);

        p1 = (TextView) findViewById(R.id.non_striker);
        p1.setText(batsman2);

        b = (TextView) findViewById(R.id.bowler);
        b.setText(bowler);

        score = findViewById(R.id.score);
        String o = Integer.toString(tscore);
        String o1 = Integer.toString(twicket);
        score.setText(o + "/" + o1);

        overs = findViewById(R.id.overs);
        String t = Integer.toString(tover);
        String t1 = Integer.toString(tball);
        overs.setText(t + "." + t1);

        over = findViewById(R.id.over);
        String g = Integer.toString(bover);
        String g1 = Integer.toString(bball);
        String g2 = Integer.toString(brun);
        String g3 = Integer.toString(bwicket);
        over.setText(g + "." + g1 + "-" + g2 + "-" + g3);

        strikerrun = findViewById(R.id.strikerrun);
        String u = Integer.toString(srun);
        String u1 = Integer.toString(sball);
        strikerrun.setText(u + "(" + u1 + ")");

        nonstrikerrun = findViewById(R.id.non_strikerrun);
        String y = Integer.toString(nsrun);
        String y1 = Integer.toString(nsball);
        nonstrikerrun.setText(y + "(" + y1 + ")");

        zero = findViewById(R.id.zero);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        six = findViewById(R.id.six);
        out = findViewById(R.id.out);
        wd = findViewById(R.id.wd);
        nb = findViewById(R.id.nb);
        lb = findViewById(R.id.lb);
        bye = findViewById(R.id.bye);
        f = FirebaseDatabase.getInstance();
        f1 = FirebaseDatabase.getInstance();
        f2 = FirebaseDatabase.getInstance();
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (s == "s") {
                    sball++;
                    String u = Integer.toString(srun);
                    String u1 = Integer.toString(sball);
                    strikerrun.setText(u + "(" + u1 + ")");
                    f.getReference().child("data5").child(id).child("player").child(bat1).child("ball").setValue(sball);

                } else {
                    nsball++;
                    String u = Integer.toString(nsrun);
                    String u1 = Integer.toString(nsball);
                    nonstrikerrun.setText(u + "(" + u1 + ")");
                    f.getReference().child("data5").child(id).child("player").child(bat2).child("ball").setValue(nsball);

                }
                if (tball == 5) {
                    tover++;
                    tball = 0;
                } else {
                    tball++;
                }
                overs = findViewById(R.id.overs);
                String t = Integer.toString(tover);
                String t1 = Integer.toString(tball);
                overs.setText(t + "." + t1);

                if (bball == 5) {
                    bover++;
                    bball = 0;
                    if (s == "s") {
                        s = "ns";
                    } else {
                        s = "s";
                    }
                    Intent intent;
                    intent = new Intent(Main19Activity.this, Main20Activity.class);
                    intent.putExtra("teamw", id2);
                    intent.putExtra("teaml", id);
                    intent.putExtra("team1", team1);
                    intent.putExtra("team2", team2);
                    startActivityForResult(intent, 3);
                }
                else {
                    bball++;
                }
                String g = Integer.toString(bover);
                String g1 = Integer.toString(bball);
                String g2 = Integer.toString(brun);
                String g3 = Integer.toString(bwicket);
                over.setText(g + "." + g1 + "-" + g2 + "-" + g3);
                f1.getReference().child("data5").child(id2).child("player").child(ball).child("ball2").setValue(bball);
                f1.getReference().child("data5").child(id2).child("player").child(ball).child("over").setValue(bover);

                f2.getReference().child("data5").child(id).child("score").setValue(tscore);
                f2.getReference().child("data5").child(id).child("ball").setValue(tball);
                f2.getReference().child("data5").child(id).child("over").setValue(tover);
                f2.getReference().child("data5").child(id).child("wicket").setValue(twicket);

                if(tover == 12){
                    String o = Integer.toString(tscore);
                    Intent intent;
                    intent = new Intent(Main19Activity.this,Main46Activity.class);
                    intent.putExtra("id",id2);
                    intent.putExtra("id2",id);
                    intent.putExtra("Team1",team2);
                    intent.putExtra("Team2",team1);
                    intent.putExtra("tscore",o);
                    startActivity(intent);

                }
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (s == "s") {
                    sball++;
                    srun++;
                    String u = Integer.toString(srun);
                    String u1 = Integer.toString(sball);
                    strikerrun.setText(u + "(" + u1 + ")");
                    f.getReference().child("data5").child(id).child("player").child(bat1).child("ball").setValue(sball);
                    f.getReference().child("data5").child(id).child("player").child(bat1).child("run").setValue(srun);
                    s = "ns";
                } else {
                    nsball++;
                    nsrun++;
                    String u = Integer.toString(nsrun);
                    String u1 = Integer.toString(nsball);
                    nonstrikerrun.setText(u + "(" + u1 + ")");
                    f.getReference().child("data5").child(id).child("player").child(bat2).child("ball").setValue(nsball);
                    f.getReference().child("data5").child(id).child("player").child(bat2).child("run").setValue(nsrun);
                    s = "s";
                }

                tscore++;
                brun++;
                if (tball == 5) {
                    tover++;
                    tball = 0;
                } else {
                    tball++;
                }
                String o = Integer.toString(tscore);
                String o1 = Integer.toString(twicket);
                score.setText(o + "/" + o1);

                overs = findViewById(R.id.overs);
                String t = Integer.toString(tover);
                String t1 = Integer.toString(tball);
                overs.setText(t + "." + t1);


                if (bball == 5) {
                    bball = 0;
                    bover++;
                    if (s == "s") {
                        s = "ns";
                    }
                    else {
                        s = "s";
                    }
                    Intent intent;
                    intent = new Intent(Main19Activity.this, Main20Activity.class);
                    intent.putExtra("teamw", id2);
                    intent.putExtra("teaml", id);
                    intent.putExtra("team1", team1);
                    intent.putExtra("team2", team2);
                    startActivityForResult(intent, 3);
                }
                else {
                    bball++;
                }
                String g = Integer.toString(bover);
                String g1 = Integer.toString(bball);
                String g2 = Integer.toString(brun);
                String g3 = Integer.toString(bwicket);
                over.setText(g + "." + g1 + "-" + g2 + "-" + g3);
                f1.getReference().child("data5").child(id2).child("player").child(ball).child("ball2").setValue(bball);
                f1.getReference().child("data5").child(id2).child("player").child(ball).child("over").setValue(bover);
                f1.getReference().child("data5").child(id2).child("player").child(ball).child("run2").setValue(brun);

                f2.getReference().child("data5").child(id).child("score").setValue(tscore);
                f2.getReference().child("data5").child(id).child("ball").setValue(tball);
                f2.getReference().child("data5").child(id).child("over").setValue(tover);
                f2.getReference().child("data5").child(id).child("wicket").setValue(twicket);
                if(tover == 12){

                    Intent intent;
                    intent = new Intent(Main19Activity.this,Main46Activity.class);
                    intent.putExtra("id",id2);
                    intent.putExtra("id2",id);
                    intent.putExtra("Team1",team2);
                    intent.putExtra("Team2",team1);
                    intent.putExtra("tscore",o);
                    startActivity(intent);

                }
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (s == "s") {
                    sball++;
                    srun = srun + 2;
                    String u = Integer.toString(srun);
                    String u1 = Integer.toString(sball);
                    strikerrun.setText(u + "(" + u1 + ")");
                    f.getReference().child("data5").child(id).child("player").child(bat1).child("ball").setValue(sball);
                    f.getReference().child("data5").child(id).child("player").child(bat1).child("run").setValue(srun);
                } else {
                    nsball++;
                    nsrun = nsrun + 2;
                    String u = Integer.toString(nsrun);
                    String u1 = Integer.toString(nsball);
                    nonstrikerrun.setText(u + "(" + u1 + ")");
                    f.getReference().child("data5").child(id).child("player").child(bat2).child("ball").setValue(nsball);
                    f.getReference().child("data5").child(id).child("player").child(bat2).child("run").setValue(nsrun);
                }

                tscore = tscore + 2;
                brun = brun + 2;
                if (tball == 5) {
                    tover++;
                    tball = 0;
                } else {
                    tball++;
                }
                String o = Integer.toString(tscore);
                String o1 = Integer.toString(twicket);
                score.setText(o + "/" + o1);

                overs = findViewById(R.id.overs);
                String t = Integer.toString(tover);
                String t1 = Integer.toString(tball);
                overs.setText(t + "." + t1);


                if (bball == 5) {
                    bball = 0;
                    bover++;
                    if (s == "s") {
                        s = "ns";
                    }
                    else {
                        s = "s";
                    }
                    Intent intent;
                    intent = new Intent(Main19Activity.this, Main20Activity.class);
                    intent.putExtra("teamw", id2);
                    intent.putExtra("teaml", id);
                    intent.putExtra("team1", team1);
                    intent.putExtra("team2", team2);
                    startActivityForResult(intent, 3);
                }
                else {
                    bball++;
                }
                String g = Integer.toString(bover);
                String g1 = Integer.toString(bball);
                String g2 = Integer.toString(brun);
                String g3 = Integer.toString(bwicket);
                over.setText(g + "." + g1 + "-" + g2 + "-" + g3);
                f1.getReference().child("data5").child(id2).child("player").child(ball).child("ball2").setValue(bball);
                f1.getReference().child("data5").child(id2).child("player").child(ball).child("over").setValue(bover);
                f1.getReference().child("data5").child(id2).child("player").child(ball).child("run2").setValue(brun);


                f2.getReference().child("data5").child(id).child("score").setValue(tscore);
                f2.getReference().child("data5").child(id).child("ball").setValue(tball);
                f2.getReference().child("data5").child(id).child("over").setValue(tover);
                f2.getReference().child("data5").child(id).child("wicket").setValue(twicket);

                if(tover == 12){

                    Intent intent;
                    intent = new Intent(Main19Activity.this,Main46Activity.class);
                    intent.putExtra("id",id2);
                    intent.putExtra("id2",id);
                    intent.putExtra("Team1",team2);
                    intent.putExtra("Team2",team1);
                    intent.putExtra("tscore",o);
                    startActivity(intent);

                }
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (s == "s") {
                    sball++;
                    srun = srun + 3;
                    String u = Integer.toString(srun);
                    String u1 = Integer.toString(sball);
                    strikerrun.setText(u + "(" + u1 + ")");
                    f.getReference().child("data5").child(id).child("player").child(bat1).child("ball").setValue(sball);
                    f.getReference().child("data5").child(id).child("player").child(bat1).child("run").setValue(srun);
                    s = "ns";
                }
                else {
                    nsball++;
                    nsrun = nsrun + 3;
                    String u = Integer.toString(nsrun);
                    String u1 = Integer.toString(nsball);
                    nonstrikerrun.setText(u + "(" + u1 + ")");
                    f.getReference().child("data5").child(id).child("player").child(bat2).child("ball").setValue(nsball);
                    f.getReference().child("data5").child(id).child("player").child(bat2).child("run").setValue(nsrun);
                    s = "s";
                }

                tscore = tscore + 3;
                brun = brun + 3;
                if (tball == 5) {
                    tover++;
                    tball = 0;
                }
                else {
                    tball++;
                }
                String o = Integer.toString(tscore);
                String o1 = Integer.toString(twicket);
                score.setText(o + "/" + o1);

                overs = findViewById(R.id.overs);
                String t = Integer.toString(tover);
                String t1 = Integer.toString(tball);
                overs.setText(t + "." + t1);


                if (bball == 5) {
                    bball = 0;
                    bover++;
                    if (s == "s") {
                        s = "ns";
                    }
                    else {
                        s = "s";
                    }
                    Intent intent;
                    intent = new Intent(Main19Activity.this, Main20Activity.class);
                    intent.putExtra("teamw", id2);
                    intent.putExtra("teaml", id);
                    intent.putExtra("team1", team1);
                    intent.putExtra("team2", team2);
                    startActivityForResult(intent, 3);
                }
                else {
                    bball++;
                }
                String g = Integer.toString(bover);
                String g1 = Integer.toString(bball);
                String g2 = Integer.toString(brun);
                String g3 = Integer.toString(bwicket);
                over.setText(g + "." + g1 + "-" + g2 + "-" + g3);
                f1.getReference().child("data5").child(id2).child("player").child(ball).child("ball2").setValue(bball);
                f1.getReference().child("data5").child(id2).child("player").child(ball).child("over").setValue(bover);
                f1.getReference().child("data5").child(id2).child("player").child(ball).child("run2").setValue(brun);

                f2.getReference().child("data5").child(id).child("score").setValue(tscore);
                f2.getReference().child("data5").child(id).child("ball").setValue(tball);
                f2.getReference().child("data5").child(id).child("over").setValue(tover);
                f2.getReference().child("data5").child(id).child("wicket").setValue(twicket);
                if(tover == 12){

                    Intent intent;
                    intent = new Intent(Main19Activity.this,Main46Activity.class);
                    intent.putExtra("id",id2);
                    intent.putExtra("id2",id);
                    intent.putExtra("Team1",team2);
                    intent.putExtra("Team2",team1);
                    intent.putExtra("tscore",o);
                    startActivity(intent);

                }
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (s == "s") {
                    sball++;
                    srun = srun + 4;
                    String u = Integer.toString(srun);
                    String u1 = Integer.toString(sball);
                    strikerrun.setText(u + "(" + u1 + ")");
                    f.getReference().child("data5").child(id).child("player").child(bat1).child("ball").setValue(sball);
                    f.getReference().child("data5").child(id).child("player").child(bat1).child("run").setValue(srun);
                }
                else {
                    nsball++;
                    nsrun = nsrun + 4;
                    String u = Integer.toString(nsrun);
                    String u1 = Integer.toString(nsball);
                    nonstrikerrun.setText(u + "(" + u1 + ")");
                    f.getReference().child("data5").child(id).child("player").child(bat2).child("ball").setValue(nsball);
                    f.getReference().child("data5").child(id).child("player").child(bat2).child("run").setValue(nsrun);
                }

                tscore = tscore + 4;
                brun = brun + 4;
                if (tball == 5) {
                    tover++;
                    tball = 0;
                }
                else {
                    tball++;
                }
                String o = Integer.toString(tscore);
                String o1 = Integer.toString(twicket);
                score.setText(o + "/" + o1);

                overs = findViewById(R.id.overs);
                String t = Integer.toString(tover);
                String t1 = Integer.toString(tball);
                overs.setText(t + "." + t1);


                if (bball == 5) {
                    bball = 0;
                    bover++;
                    if (s == "s") {
                        s = "ns";
                    }
                    else {
                        s = "s";
                    }
                    Intent intent;
                    intent = new Intent(Main19Activity.this, Main20Activity.class);
                    intent.putExtra("teamw", id2);
                    intent.putExtra("teaml", id);
                    intent.putExtra("team1", team1);
                    intent.putExtra("team2", team2);
                    startActivityForResult(intent, 3);
                }
                else {
                    bball++;
                }
                String g = Integer.toString(bover);
                String g1 = Integer.toString(bball);
                String g2 = Integer.toString(brun);
                String g3 = Integer.toString(bwicket);
                over.setText(g + "." + g1 + "-" + g2 + "-" + g3);
                f1.getReference().child("data5").child(id2).child("player").child(ball).child("ball2").setValue(bball);
                f1.getReference().child("data5").child(id2).child("player").child(ball).child("over").setValue(bover);
                f1.getReference().child("data5").child(id2).child("player").child(ball).child("run2").setValue(brun);

                f2.getReference().child("data5").child(id).child("score").setValue(tscore);
                f2.getReference().child("data5").child(id).child("ball").setValue(tball);
                f2.getReference().child("data5").child(id).child("over").setValue(tover);
                f2.getReference().child("data5").child(id).child("wicket").setValue(twicket);
                if(tover == 12){

                    Intent intent;
                    intent = new Intent(Main19Activity.this,Main46Activity.class);
                    intent.putExtra("id",id2);
                    intent.putExtra("id2",id);
                    intent.putExtra("Team1",team2);
                    intent.putExtra("Team2",team1);
                    intent.putExtra("tscore",o);
                    startActivity(intent);

                }
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (s == "s") {
                    sball++;
                    srun = srun + 6;
                    String u = Integer.toString(srun);
                    String u1 = Integer.toString(sball);
                    strikerrun.setText(u + "(" + u1 + ")");
                    f.getReference().child("data5").child(id).child("player").child(bat1).child("ball").setValue(sball);
                    f.getReference().child("data5").child(id).child("player").child(bat1).child("run").setValue(srun);
                }
                else {
                    nsball++;
                    nsrun = nsrun + 6;
                    String u = Integer.toString(nsrun);
                    String u1 = Integer.toString(nsball);
                    nonstrikerrun.setText(u + "(" + u1 + ")");
                    f.getReference().child("data5").child(id).child("player").child(bat2).child("ball").setValue(nsball);
                    f.getReference().child("data5").child(id).child("player").child(bat2).child("run").setValue(nsrun);
                }

                tscore = tscore + 6;
                brun = brun + 6;
                if (tball == 5) {
                    tover++;
                    tball = 0;
                }
                else {
                    tball++;
                }
                String o = Integer.toString(tscore);
                String o1 = Integer.toString(twicket);
                score.setText(o + "/" + o1);

                overs = findViewById(R.id.overs);
                String t = Integer.toString(tover);
                String t1 = Integer.toString(tball);
                overs.setText(t + "." + t1);


                if (bball == 5) {
                    bball = 0;
                    bover++;
                    if (s == "s") {
                        s = "ns";
                    }
                    else {
                        s = "s";
                    }
                    Intent intent;
                    intent = new Intent(Main19Activity.this, Main20Activity.class);
                    intent.putExtra("teamw", id2);
                    intent.putExtra("teaml", id);
                    intent.putExtra("team1", team1);
                    intent.putExtra("team2", team2);
                    startActivityForResult(intent, 3);
                }
                else {
                    bball++;
                }
                String g = Integer.toString(bover);
                String g1 = Integer.toString(bball);
                String g2 = Integer.toString(brun);
                String g3 = Integer.toString(bwicket);
                over.setText(g + "." + g1 + "-" + g2 + "-" + g3);
                f1.getReference().child("data5").child(id2).child("player").child(ball).child("ball2").setValue(bball);
                f1.getReference().child("data5").child(id2).child("player").child(ball).child("over").setValue(bover);
                f1.getReference().child("data5").child(id2).child("player").child(ball).child("run2").setValue(brun);


                f2.getReference().child("data5").child(id).child("score").setValue(tscore);
                f2.getReference().child("data5").child(id).child("ball").setValue(tball);
                f2.getReference().child("data5").child(id).child("over").setValue(tover);
                f2.getReference().child("data5").child(id).child("wicket").setValue(twicket);
                if(tover == 12){

                    Intent intent;
                    intent = new Intent(Main19Activity.this,Main46Activity.class);
                    intent.putExtra("id",id2);
                    intent.putExtra("id2",id);
                    intent.putExtra("Team1",team2);
                    intent.putExtra("Team2",team1);
                    intent.putExtra("tscore",o);
                    startActivity(intent);

                }
            }
        });
        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (s == "s") {
                    f.getReference().child("data5").child(id).child("player").child(bat1).child("status").setValue("out");
                    f.getReference().child("data5").child(id).child("player").child(bat1).child("outby").setValue(bowler);
                    Intent intent;
                    intent = new Intent(Main19Activity.this, Main20Activity.class);
                    intent.putExtra("teamw", id);
                    intent.putExtra("teaml", id2);
                    intent.putExtra("team1", team1);
                    intent.putExtra("team2", team2);
                    startActivityForResult(intent, 1);
                }
                else {
                    f.getReference().child("data5").child(id).child("player").child(bat2).child("status").setValue("out");
                    f.getReference().child("data5").child(id).child("player").child(bat2).child("outby").setValue(bowler);
                    Intent intent;
                    intent = new Intent(Main19Activity.this, Main20Activity.class);
                    intent.putExtra("teamw", id);
                    intent.putExtra("teaml", id2);
                    intent.putExtra("team1", team1);
                    intent.putExtra("team2", team2);

                    startActivityForResult(intent, 2);
                }

                twicket++;
                bwicket++;
                if (tball == 5) {
                    tover++;
                    tball = 0;
                }
                else {
                    tball++;
                }
                String o = Integer.toString(tscore);
                String o1 = Integer.toString(twicket);
                score.setText(o + "/" + o1);

                overs = findViewById(R.id.overs);
                String t = Integer.toString(tover);
                String t1 = Integer.toString(tball);
                overs.setText(t + "." + t1);


                if (bball == 5) {
                    bball = 0;
                    bover++;
                    if (s == "s") {
                        s = "ns";
                    }
                    else {
                        s = "s";
                    }
                    Intent intent;
                    intent = new Intent(Main19Activity.this, Main20Activity.class);
                    intent.putExtra("teamw", id2);
                    intent.putExtra("teaml", id);
                    intent.putExtra("team1", team1);
                    intent.putExtra("team2", team2);
                    startActivityForResult(intent, 3);
                }
                else {
                    bball++;
                }
                String g = Integer.toString(bover);
                String g1 = Integer.toString(bball);
                String g2 = Integer.toString(brun);
                String g3 = Integer.toString(bwicket);
                over.setText(g + "." + g1 + "-" + g2 + "-" + g3);
                f1.getReference().child("data5").child(id2).child("player").child(ball).child("wicket").setValue(bwicket);

                f2.getReference().child("data5").child(id).child("score").setValue(tscore);
                f2.getReference().child("data5").child(id).child("ball").setValue(tball);
                f2.getReference().child("data5").child(id).child("over").setValue(tover);
                f2.getReference().child("data5").child(id).child("wicket").setValue(twicket);
                if(tover == 12){

                    Intent intent;
                    intent = new Intent(Main19Activity.this,Main46Activity.class);
                    intent.putExtra("id",id2);
                    intent.putExtra("id2",id);
                    intent.putExtra("Team1",team2);
                    intent.putExtra("Team2",team1);
                    intent.putExtra("tscore",o);
                    startActivity(intent);

                }
            }
        });
        wd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brun++;
                tscore++;
                String g = Integer.toString(bover);
                String g1 = Integer.toString(bball);
                String g2 = Integer.toString(brun);
                String g3 = Integer.toString(bwicket);
                over.setText(g + "." + g1 + "-" + g2 + "-" + g3);
                f1.getReference().child("data5").child(id2).child("player").child(ball).child("run2").setValue(brun);

                f2.getReference().child("data5").child(id).child("score").setValue(tscore);
                f2.getReference().child("data5").child(id).child("ball").setValue(tball);
                f2.getReference().child("data5").child(id).child("over").setValue(tover);
                f2.getReference().child("data5").child(id).child("wicket").setValue(twicket);
            }
        });
        nb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brun++;
                tscore++;
                String g = Integer.toString(bover);
                String g1 = Integer.toString(bball);
                String g2 = Integer.toString(brun);
                String g3 = Integer.toString(bwicket);
                over.setText(g + "." + g1 + "-" + g2 + "-" + g3);
                f1.getReference().child("data5").child(id2).child("player").child(ball).child("run2").setValue(brun);

                f2.getReference().child("data5").child(id).child("score").setValue(tscore);
                f2.getReference().child("data5").child(id).child("ball").setValue(tball);
                f2.getReference().child("data5").child(id).child("over").setValue(tover);
                f2.getReference().child("data5").child(id).child("wicket").setValue(twicket);


            }
        });
        lb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tscore++;
                if (tball == 5) {
                    tover++;
                    tball = 0;
                }
                else {
                    tball++;
                }
                String o = Integer.toString(tscore);
                String o1 = Integer.toString(twicket);
                score.setText(o + "/" + o1);

                overs = findViewById(R.id.overs);
                String t = Integer.toString(tover);
                String t1 = Integer.toString(tball);
                overs.setText(t + "." + t1);


                if (bball == 5) {
                    bball = 0;
                    bover++;
                    if (s == "s") {
                        s = "ns";
                    }
                    else {
                        s = "s";
                    }
                    Intent intent;
                    intent = new Intent(Main19Activity.this, Main20Activity.class);
                    intent.putExtra("teamw", id2);
                    intent.putExtra("teaml", id);
                    intent.putExtra("team1", team1);
                    intent.putExtra("team2", team2);
                    startActivityForResult(intent, 3);
                }
                else {
                    bball++;
                }
                String g = Integer.toString(bover);
                String g1 = Integer.toString(bball);
                String g2 = Integer.toString(brun);
                String g3 = Integer.toString(bwicket);
                over.setText(g + "." + g1 + "-" + g2 + "-" + g3);
                f1.getReference().child("data5").child(id2).child("player").child(ball).child("ball2").setValue(bball);
                f1.getReference().child("data5").child(id2).child("player").child(ball).child("over").setValue(bover);


                f2.getReference().child("data5").child(id).child("score").setValue(tscore);
                f2.getReference().child("data5").child(id).child("ball").setValue(tball);
                f2.getReference().child("data5").child(id).child("over").setValue(tover);
                f2.getReference().child("data5").child(id).child("wicket").setValue(twicket);

                if(tover == 12){

                    Intent intent;
                    intent = new Intent(Main19Activity.this,Main46Activity.class);
                    intent.putExtra("id",id2);
                    intent.putExtra("id2",id);
                    intent.putExtra("Team1",team2);
                    intent.putExtra("Team2",team1);
                    intent.putExtra("tscore",o);
                    startActivity(intent);

                }
            }
        });
        bye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tscore++;
                if (tball == 5) {
                    tover++;
                    tball = 0;
                }
                else {
                    tball++;
                }
                String o = Integer.toString(tscore);
                String o1 = Integer.toString(twicket);
                score.setText(o + "/" + o1);

                overs = findViewById(R.id.overs);
                String t = Integer.toString(tover);
                String t1 = Integer.toString(tball);
                overs.setText(t + "." + t1);


                if (bball == 5) {
                    bball = 0;
                    bover++;
                    if (s == "s") {
                        s = "ns";
                    }
                    else {
                        s = "s";
                    }
                    Intent intent;
                    intent = new Intent(Main19Activity.this, Main20Activity.class);
                    intent.putExtra("teamw", id2);
                    intent.putExtra("teaml", id);
                    intent.putExtra("team1", team1);
                    intent.putExtra("team2", team2);
                    startActivityForResult(intent, 3);
                }
                else {
                    bball++;
                }
                String g = Integer.toString(bover);
                String g1 = Integer.toString(bball);
                String g2 = Integer.toString(brun);
                String g3 = Integer.toString(bwicket);
                over.setText(g + "." + g1 + "-" + g2 + "-" + g3);
                f1.getReference().child("data5").child(id2).child("player").child(ball).child("ball2").setValue(bball);
                f1.getReference().child("data5").child(id2).child("player").child(ball).child("over").setValue(bover);

                f2.getReference().child("data5").child(id).child("score").setValue(tscore);
                f2.getReference().child("data5").child(id).child("ball").setValue(tball);
                f2.getReference().child("data5").child(id).child("over").setValue(tover);
                f2.getReference().child("data5").child(id).child("wicket").setValue(twicket);
                if(tover == 12){

                    Intent intent;
                    intent = new Intent(Main19Activity.this,Main46Activity.class);
                    intent.putExtra("id",id2);
                    intent.putExtra("id2",id);
                    intent.putExtra("Team1",team2);
                    intent.putExtra("Team2",team1);
                    intent.putExtra("tscore",o);
                    startActivity(intent);

                }
            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3) {
            if (resultCode == RESULT_OK){
                Frefernce2 = FirebaseDatabase.getInstance();
                reference2 = Frefernce2.getReference().child("data5").child(id2).child("player");
                bowler = data.getStringExtra("i");
                reference2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String name = (String) ds.child("name").getValue();
                            String over = ds.child("over").getValue().toString();
                            String wicket = ds.child("wicket").getValue().toString();
                            String run = ds.child("run2").getValue().toString();
                            if (name.equals(bowler)) {
                                ball = ds.getKey();
                                String over2 = over;
                                int over1 = Integer.parseInt(over2);
                                bover = over1;

                                String wicket2 = wicket;
                                int wicket1 = Integer.parseInt(wicket2);
                                bwicket = wicket1;

                                String run2 = run;
                                int run1 = Integer.parseInt(run2);
                                brun = run1;
                            }
                        }
                        b.setText(bowler);
                        String g = Integer.toString(bover);
                        String g1 = Integer.toString(bball);
                        String g2 = Integer.toString(brun);
                        String g3 = Integer.toString(bwicket);
                        over.setText(g + "." + g1 + "-" + g2 + "-" + g3);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

        }
        if (requestCode == 1) {
            if (resultCode == RESULT_OK){
                Frefernce3 = FirebaseDatabase.getInstance();
                reference3 = Frefernce3.getReference().child("data5").child(id).child("player");
                batsman1 = data.getStringExtra("i");
                reference3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String name = (String) ds.child("name").getValue();
                            String run =  ds.child("run").getValue().toString();
                            String ball = ds.child("ball").getValue().toString();
                            if (name.equals(batsman1)) {
                                bat1 = ds.getKey();
                                String run2 = run;
                                int run1 = Integer.parseInt(run2);
                                srun = run1;

                                String ball2 = ball;
                                int ball1 = Integer.parseInt(ball2);
                                sball = ball1;
                            }
                        }
                        p.setText(batsman1);
                        String u = Integer.toString(srun);
                        String u1 = Integer.toString(sball);
                        strikerrun.setText(u + "(" + u1 + ")");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

        }
        if (requestCode == 2) {
            if (resultCode == RESULT_OK){
                Frefernce3 = FirebaseDatabase.getInstance();
                reference3 = Frefernce3.getReference().child("data5").child(id).child("player");
                batsman2 = data.getStringExtra("i");
                reference3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String name = (String) ds.child("name").getValue();
                            String run = ds.child("run").getValue().toString();
                            String ball =  ds.child("ball").getValue().toString();
                            if (name.equals(batsman2)) {
                                bat2 = ds.getKey();

                                String run2 = run;
                                int run1 = Integer.parseInt(run2);
                                nsrun = run1;

                                String ball2 = ball;
                                int ball1 = Integer.parseInt(ball2);
                                nsball = ball1;
                            }
                        }
                        p1.setText(batsman2);
                        String y = Integer.toString(nsrun);
                        String y1 = Integer.toString(nsball);
                        nonstrikerrun.setText(y + "(" + y1 + ")");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

        }
    }
}
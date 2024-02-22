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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main43Activity extends AppCompatActivity {
    DatabaseReference reference,ref2,ref3;
    DatabaseReference reference1,ref21;
    FirebaseDatabase Frefernce;
    String a,key,match;
    String a1,key1,match1;
    int i=0,l=0,loo=0,count=0;
    int i1=0,l1=0,loo1=0,count1=0;
    String [] n=new String[100];
    String [] players=new String[100];
    String [] score=new String[100];
    String [] n1=new String[100];
    String [] players1=new String[100];
    String [] score1=new String[100];
    String a2,key2,match2;
    int i2=0,l2=0,loo2=0,count2=0;
    String [] n2=new String[100];
    String [] players2=new String[100];
    String [] score2=new String[100];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main43);
        Button c,d;
        d=findViewById(R.id.pa);
        c=findViewById(R.id.ta);
        Frefernce = FirebaseDatabase.getInstance();
        reference = Frefernce.getReference().child("data6");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    a = ds.getKey();
                    key = (String) ds.child("key").getValue();
                    match=(String)ds.child("match").getValue();
                    if(match.contains("Basketball"))
                    {
                        n[i]=a;
                        i++;
                    }
                }
                for(int j=0;j<i;j++)
                {
                    ref2=Frefernce.getReference().child("data6").child(n[j]).child("players");
                    ref2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds2 : dataSnapshot.getChildren()) {
                                String g=(String)ds2.child("goal").getValue();
                                String m=(String)ds2.child("name").getValue();
                                if(count==0)
                                {
                                    players[0]=m;
                                    score[0]=g;
                                    count++;
                                }
                                else
                                {
                                    for(int j=0;j<count;j++)
                                    {
                                        if(players[j].equals(m))
                                        {
                                            int a=Integer.parseInt(score[j]);
                                            int b=Integer.parseInt(g);
                                            int sc=a+b;
                                            String sco=String.valueOf(sc);
                                            score[j]=sco;
                                            loo++;
                                            break;
                                        }
                                    }
                                    if(loo==0)
                                    {
                                        players[count]=m;
                                        score[count]=g;
                                        count++;
                                    }
                                    else
                                    {
                                        loo=0;
                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        reference1 = Frefernce.getReference().child("data6");
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String t=(String)ds.child("t").getValue();
                    //Toast.makeText(Main42Activity.this,t,Toast.LENGTH_SHORT).show();
                    if(count1==0)
                    {
                        players1[0]=t;
                        count1++;
                    }
                    else
                    {
                        for(int j1=0;j1<count1;j1++)
                        {
                            if(players1[j1].equals(t))
                            {
                                loo1++;
                                break;
                            }
                        }
                        if(loo1==0)
                        {
                            players1[count1]=t;
                            count1++;
                        }
                        else
                        {
                            loo1=0;
                        }
                    }
                }
                ref3=Frefernce.getReference().child("data6");
                ref3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds2 : dataSnapshot.getChildren()) {
                            String won=(String)ds2.child("won").getValue();
                            if(count2==0)
                            {
                                players2[0]=won;
                                score1[0]="1";
                                count2++;
                            }
                            else
                            {
                                for(int j1=0;j1<count2;j1++)
                                {
                                    if(players2[j1].equals(won))
                                    {
                                        int a=Integer.parseInt(score1[j1]);
                                        int sc=a+1;
                                        String sco=String.valueOf(sc);
                                        score1[j1]=sco;
                                        loo2++;
                                        break;
                                    }
                                }
                                if(loo2==0)
                                {
                                    players2[count2]=won;
                                    score1[count2]="1";
                                    count2++;
                                }
                                else
                                {
                                    loo2=0;
                                }
                            }


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent =new Intent(Main43Activity.this,Main44Activity.class);
                String[] a=new String[count];
                String[] b=new String[count];
                a=score;
                b=players;
                String count1=String.valueOf(count);
                intent.putExtra("score",a);
                intent.putExtra("players",b);
                intent.putExtra("count1",count1);
                startActivity(intent);
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<count1;i++)
                {
                    for(int j=0;j<count2;j++)
                    {
                        if(players1[i].equals(players2[j]))
                        {
                            loo2=1;
                            int a=Integer.parseInt(score1[j]);
                            a=a/2;
                            String b=String.valueOf(a);
                            score2[i]=b;
                            break;
                        }
                    }
                    if(loo2==0)
                    {
                        score2[i]="0";
                    }
                    else
                    {
                        loo2=0;
                    }
                }
                Intent intent;
                intent =new Intent(Main43Activity.this,Main44Activity.class);
                String[] a=new String[count1];
                String[] b=new String[count1];
                a=score2;
                b=players1;
                String count=String.valueOf(count1);
                intent.putExtra("score",a);
                intent.putExtra("players",b);
                intent.putExtra("count1",count);
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
                Intent intent = new Intent(Main43Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(Main43Activity.this, "Succesfully Logout", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.homemenu: {
                Intent intent = new Intent(Main43Activity.this, Main3Activity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}

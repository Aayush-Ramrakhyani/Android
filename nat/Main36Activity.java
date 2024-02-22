package com.example.nat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class Main36Activity extends AppCompatActivity{
    FirebaseDatabase Frefernce;
    String[] c =new String[100];
    String[] c2 =new String[100];
    String[] c3=new String[100];
    String[] a=new String[2];
    String day1;
    String[] key=new String[100];
    int i;
    public String team,team1,match;
    DatabaseReference reference;
    ListView list;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main36);
        Frefernce=FirebaseDatabase.getInstance();
        reference=Frefernce.getReference().child("data2");
        list=(ListView) findViewById(R.id.listtext);
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        list.setAdapter(arrayAdapter);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    day1=(String)ds.child("day1").getValue();
                    String hr=(String)ds.child("hr").getValue();
                    match=(String)ds.child("match").getValue();
                    team=(String)ds.child("team").getValue();
                    team1=(String)ds.child("team1").getValue();
                    String key1=ds.getKey();
                    String day=(String)ds.child("day").getValue();
                    String team3=team+" V/S "+team1;
                    if(match.contains("Football")) {
                        c[i] = team;
                        c2[i] = team1;
                        c3[i]=match;
                        key[i]=key1;
                        i++;
                        String Display = match + "\n" + day1 + "\n" + team3 + "\n" + hr;
                        arrayList.add(Display);
                        arrayAdapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                Frefernce=FirebaseDatabase.getInstance();
                reference=Frefernce.getReference().child("data4");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String kp=key[position];
                        i=0;
                        int c1=0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String k = (String) ds.child("key").getValue();
                            String o= ds.getKey();
                            if(kp.equals(k))
                            {
                                a[i]=o;
                                i++;
                                c1=1;
                            }

                        }
                        if(c1==1)
                        {
                            //Toast.makeText(Main36Activity.this,a[0],Toast.LENGTH_LONG).show();
                            Intent myintent = new Intent(view.getContext(),Main37Activity.class);
                            String ok=c[position];
                            String ok2=c2[position];
                            String ok3=c3[position];
                            String ok4=a[0];
                            String ok6=a[1];
                            String ok5=key[0];
                            myintent.putExtra("ok",ok);
                            myintent.putExtra("ok2",ok2);
                            myintent.putExtra("ok3",ok3);
                            myintent.putExtra("key",ok4);
                            myintent.putExtra("key1",ok6);
                            startActivity(myintent);
                        }
                        else
                        {
                            AlertDialog.Builder a=new AlertDialog.Builder(Main36Activity.this);
                            a.setMessage("NO DATA FOUND").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                            AlertDialog alertDialog=a.create();
                            alertDialog.setTitle("Schedule Information");
                            alertDialog.show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });
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
                Intent intent = new Intent(Main36Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(Main36Activity.this, "Succesfully Logout", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.homemenu: {
                Intent intent = new Intent(Main36Activity.this, Main7Activity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.example.nat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main26Activity extends AppCompatActivity {
    TextView t, t1;
    String i="",te,key;
    int j=0;
    String k,id,id2,pid,goal,o;
    String item1[]=new String[50];
    ListView list;
    FirebaseDatabase f;
    DatabaseReference reference,reference2;
    Member5 member;
    ArrayList<String> arrayList = new ArrayList<>();
    public void show(View view) {
        for (String item : arrayList) {
            i = item;
        }
        reference = FirebaseDatabase.getInstance().getReference().child("data3");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int m = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String fname = ds.child("fname").getValue().toString();
                    String lname = ds.child("lname").getValue().toString();
                    String name = fname + "" + lname;
                    String id1 = ds.child("i").getValue().toString();
                    if (i.contains(fname)) {
                        pid = id1;
                        break;
                    }
                }
                f=FirebaseDatabase.getInstance();
                f.getReference().child("data4").child(id).child("score").setValue(o);
                reference2=FirebaseDatabase.getInstance().getReference().child("data4").child(id).child("players");
                member=new Member5();
                member.setName(pid);
                member.setGoal("1");
                reference2.push().setValue(member);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //Toast.makeText(Main26Activity.this,i,Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main26);
        Bundle extras = getIntent().getExtras();
        item1=extras.getStringArray("item1");
        k=extras.getString("len");
        int l=Integer.parseInt(k);
        id=extras.getString("id");
        o=extras.getString("o");
        te=extras.getString("te");
        key=extras.getString("key");
        list = (ListView) findViewById(R.id.listview);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        //Toast.makeText(Main25Activity.this,k,Toast.LENGTH_SHORT).show();
        String c[] = new String[l];
        for (int j = 0; j < l; j++) {
            c[j] = item1[j];
        }
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.layout, R.id.layout, c);
        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            String selectedItem = ((TextView) view).getText().toString();
                                            if (arrayList.contains(selectedItem)) {
                                                arrayList.remove(selectedItem);
                                            } else {
                                                arrayList.add(selectedItem);
                                            }
                                        }


                                    }
        );
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
                Intent intent = new Intent(Main26Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(Main26Activity.this, "Succesfully Logout", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.homemenu: {
                Intent intent = new Intent(Main26Activity.this, Main7Activity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}

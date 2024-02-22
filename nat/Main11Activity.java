package com.example.nat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main11Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    DatabaseReference reff2;
    Member member;
    int c=0;
    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference ref;
    public ArrayList<String> arrayList;
    public RadioGroup radioGroup;
    public RadioButton sel;
    String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);

        Spinner spinner = findViewById(R.id.sports1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.choice,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        firebaseDatabase=FirebaseDatabase.getInstance();
        ref=firebaseDatabase.getReference().child("data");
        arrayList=new ArrayList<String>();
        final String text = parent.getItemAtPosition(position).toString();
        final EditText fname1,lname1,id1;
        fname1=findViewById(R.id.fname1);
        lname1=findViewById(R.id.lname1);
        id1=findViewById(R.id.id1);
        final TextView bo=findViewById(R.id.bos);
        final TextView pos=findViewById(R.id.position);
        final TextView b=findViewById(R.id.bs);
        final TextView ro=findViewById(R.id.role);
        final EditText clg1=findViewById(R.id.clg1);
        final EditText pos1=findViewById(R.id.position1);
        final EditText bo1=findViewById(R.id.bos1);
        final EditText b1=findViewById(R.id.bs1);
        final EditText ro1=findViewById(R.id.role1);
        radioGroup = findViewById(R.id.g);
        if(text.equals("Cricket"))
        {
            pos.setVisibility(View.INVISIBLE);
            pos1.setVisibility(View.INVISIBLE);
            b.setVisibility(View.VISIBLE);
            b1.setVisibility(View.VISIBLE);
            bo.setVisibility(View.VISIBLE);
            bo1.setVisibility(View.VISIBLE);
            ro.setVisibility(View.VISIBLE);
            ro1.setVisibility(View.VISIBLE);
        }
        else if(text.equals("Football"))
        {
            pos.setVisibility(View.VISIBLE);
            pos1.setVisibility(View.VISIBLE);
            b.setVisibility(View.INVISIBLE);
            b1.setVisibility(View.INVISIBLE);
            bo.setVisibility(View.INVISIBLE);
            bo1.setVisibility(View.INVISIBLE);
            ro.setVisibility(View.INVISIBLE);
            ro1.setVisibility(View.INVISIBLE);
        }
        else if(text.equals("Basketball"))
        {
            pos.setVisibility(View.VISIBLE);
            pos1.setVisibility(View.VISIBLE);
            b.setVisibility(View.INVISIBLE);
            b1.setVisibility(View.INVISIBLE);
            bo.setVisibility(View.INVISIBLE);
            bo1.setVisibility(View.INVISIBLE);
            ro.setVisibility(View.INVISIBLE);
            ro1.setVisibility(View.INVISIBLE);
        }
        else if(text.equals("Volleyball"))
        {
            pos.setVisibility(View.VISIBLE);
            pos1.setVisibility(View.VISIBLE);
            b.setVisibility(View.INVISIBLE);
            b1.setVisibility(View.INVISIBLE);
            bo.setVisibility(View.INVISIBLE);
            bo1.setVisibility(View.INVISIBLE);
            ro.setVisibility(View.INVISIBLE);
            ro1.setVisibility(View.INVISIBLE);
        }
        Button bq=findViewById(R.id.but1);
        bq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sel=findViewById(radioGroup.getCheckedRadioButtonId());
                gender=sel.getText().toString();
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String fname,lname,i,role,bs,bos,po,clg;
                        lname=lname1.getText().toString().trim();
                        i=id1.getText().toString().trim();
                        fname=fname1.getText().toString().trim();
                        role=ro1.getText().toString().trim();
                        bs=b1.getText().toString().trim();
                        bos=bo1.getText().toString().trim();
                        po=pos1.getText().toString().trim();
                        clg=clg1.getText().toString().trim();
                        int a=0;
                        for(DataSnapshot ds:dataSnapshot.getChildren()) {
                            String Login_Id = ds.child("Login_Id").getValue().toString();
                            String g=ds.child("Gender").getValue().toString();
                            c=1;
                            if(Login_Id.equals(i) && g.equals(gender))
                            {
                                a=1;
                                break;
                            }
                        }
                        if(c!=1)
                        {
                            Toast.makeText(Main11Activity.this,"Login Id doesn't exists",Toast.LENGTH_LONG).show();
                        }
                        if(a!=1)
                        {
                            Toast.makeText(Main11Activity.this,"Gender doesn't match",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            reff2= FirebaseDatabase.getInstance().getReference().child("data3");
                            member=new Member();
                            member.setFname(fname);
                            member.setLname(lname);
                            member.setI(i);
                            member.setRole(role);
                            member.setBs(bs);
                            member.setBos(bos);
                            member.setPo(po);
                            member.setText(text);
                            member.setClg1(clg);
                            member.setGender(gender);
                            reff2.push().setValue(member);
                            Intent intent=new Intent(Main11Activity.this,Main10Activity.class);
                            startActivity(intent);

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
    public void onNothingSelected(AdapterView<?> parent) {

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
                Intent intent = new Intent(Main11Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(Main11Activity.this, "Succesfully Logout", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.homemenu: {
                Intent intent = new Intent(Main11Activity.this, Main7Activity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}

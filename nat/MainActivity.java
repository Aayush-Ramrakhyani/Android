package com.example.nat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity {
    int c=0;
    EditText log,pass;
    Button but,but1;
    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference ref;
    public ArrayList<String> arrayList;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(!isConnected(MainActivity.this)) buildDialog(MainActivity.this).show();
        else {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        log = findViewById(R.id.login);
        but = findViewById(R.id.but);
        pass=findViewById(R.id.password);
        but1 = findViewById(R.id.but1);
        arrayList=new ArrayList<String>();
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDatabase=FirebaseDatabase.getInstance();
                ref=firebaseDatabase.getReference().child("data");
                ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds:dataSnapshot.getChildren())
                    {
                        String s1=log.getText().toString();
                        String s2=pass.getText().toString();
                        String Login_Id=ds.child("Login_Id").getValue().toString();
                        String Password=ds.child("Password").getValue().toString();
                        if(s1.equals("NehaA"))
                        {
                            if(s2.equals("neharsha"))
                            {
                                Toast.makeText(MainActivity.this,"Welcome",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(MainActivity.this, Main7Activity.class);
                                startActivity(intent);
                                c=1;
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this,"Incorrect Password",Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            if(Login_Id.equals(s1))
                            {
                                if(Password.equals(s2))
                                {
                                    Toast.makeText(MainActivity.this,"Welcome",Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                                    startActivity(intent);
                                    c=1;

                                }
                                else
                                {
                                    Toast.makeText(MainActivity.this, "Incorrect Password", Toast.LENGTH_LONG).show();
                                    return;
                                }
                            }
                        }


                    }
                    if(c==0)
                    {
                        Toast.makeText(MainActivity.this, "Incorrect Login Id", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    });
}
    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
        else return false;
        } else
        return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection!");
        builder.setMessage("Turn on Mobile Data or Wifi to access this.\n Press ok to Exit.");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }

}

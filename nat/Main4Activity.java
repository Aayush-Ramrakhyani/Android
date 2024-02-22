package com.example.nat;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AlertDialogLayout;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Main4Activity extends AppCompatActivity {

    CompactCalendarView compactCalendar;
    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference ref;
    public ArrayList<String> arrayList=new ArrayList<>();
    public ListView l;
    public String cd,data;
    int c=0,a=0;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(null);
        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);
        Event ev1 = new Event(Color.BLUE,1575829800000l,"Teachers' Professional Day");
        compactCalendar.addEvent(ev1);
        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                firebaseDatabase=FirebaseDatabase.getInstance();
                ref=firebaseDatabase.getReference().child("data2");

                cd=dateClicked.toString();
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds:dataSnapshot.getChildren())
                        {
                            String day1=ds.child("day1").getValue().toString();
                            String hr=ds.child("hr").getValue().toString();
                            String match=ds.child("match").getValue().toString();
                            String team=ds.child("team").getValue().toString();
                            String team1=ds.child("team1").getValue().toString();
                            String day=ds.child("day").getValue().toString();
                            String team3=team+" V/S "+team1;
                            if (cd.compareTo(day) == 0) {
                                a=1;
                                data=match+"\n"+day1+"\n"+hr+"\n"+team3;
                                AlertDialog.Builder a=new AlertDialog.Builder(Main4Activity.this);
                                a.setMessage(data).setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
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
                                c=1;
                            }
                        }
                        if(c==0)
                        {
                            Toast.makeText(Main4Activity.this,"No events planned for that day",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                }) ;

                //Context context = getApplicationContext();
               //Toast.makeText(context,""+dateClicked.toString(),Toast.LENGTH_LONG).show();


            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));
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
                Intent intent = new Intent(Main4Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(Main4Activity.this, "Succesfully Logout", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.homemenu: {
                Intent intent = new Intent(Main4Activity.this, Main3Activity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
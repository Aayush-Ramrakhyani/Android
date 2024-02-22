package com.example.nat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main9Activity extends AppCompatActivity implements SingleChoice3.SingleChoice3Listener , SingleChoice4.SingleChoice4Listener {
    public TextView dc,dc2;
    public int i,j;
    public String day1,month1,year1,hr,min,ap,match,game,team,team1,day,month;
    DatabaseReference reff2;
    Member3 member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        dc=findViewById(R.id.button_choice1);
        dc2=findViewById(R.id.button_choice2);
        final Button button_choice1=findViewById(R.id.button_choice1);
        final Button button_choice2=findViewById(R.id.button_choice2);
        Intent i=getIntent();
        day1=i.getStringExtra("day1");
        month1=i.getStringExtra("month1");
        year1=i.getStringExtra("year1");
        hr=i.getStringExtra("hr");
        min=i.getStringExtra("min");
        ap=i.getStringExtra("ap");
        match=i.getStringExtra("match");
        game=i.getStringExtra("game");
        day=i.getStringExtra("day");
        month=i.getStringExtra("month");
        int len=min.length();
        if(len==1)
        {
            min="0"+min;
        }
        hr=hr+":"+min+" "+ap;
        match=game+" "+match;
        day=day+" "+month+" "+day1+" "+"00:00:00 GMT+05:30 "+year1;
        day1=day1+"-"+month+"-"+year1;
        button_choice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment singleChoice3Dialog;
                singleChoice3Dialog = new SingleChoice3();
                singleChoice3Dialog.setCancelable(false);
                singleChoice3Dialog.show(getSupportFragmentManager(),"Single Choice3 Dialog");

            }
        });
        button_choice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment singleChoice4Dialog;
                singleChoice4Dialog = new SingleChoice4();
                singleChoice4Dialog.setCancelable(false);
                singleChoice4Dialog.show(getSupportFragmentManager(),"Single Choice4 Dialog");
            }
        });
    }

    @Override
    public void onPositiveButtonClicked(String[] list, int position) {
        Button button=findViewById(R.id.button_choice1);
        button.setText(list[position]);
        team=list[position];
        i=position;
    }

    @Override
    public void onPositiveButtonClicked2(final String[] list2, final int position2)
    {

        Button button2=findViewById(R.id.button_choice2);
        button2.setText(list2[position2]);
        j=position2;
        Button button3=findViewById(R.id.sub);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i>=0 && j>=0)
                {
                    team1=list2[position2];
                    reff2= FirebaseDatabase.getInstance().getReference().child("data2");
                    member=new Member3();
                    member.setDay(day);
                    member.setDay1(day1);
                    member.setHr(hr);
                    member.setMatch(match);
                    member.setTeam(team);
                    member.setTeam1(team1);
                    reff2.push().setValue(member);
                    Intent intent=new Intent(Main9Activity.this,Main7Activity.class);

                    startActivity(intent);
                }
            }
        });
    }


    @Override
    public void onNegativeButtonClicked() {
        dc.setText("Dialog cancel");
    }
    @Override
    public void onNegativeButtonClicked2() {
        dc2.setText("Dialog cancel");
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
                Intent intent = new Intent(Main9Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(Main9Activity.this, "Succesfully Logout", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.homemenu: {
                Intent intent = new Intent(Main9Activity.this, Main7Activity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}

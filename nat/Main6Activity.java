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

public class Main6Activity extends AppCompatActivity implements SingleChoice.SingleChoiceListener {
    private TextView dc;
    String match;
    public String day1,month1,year1,hr,min,ap,day,month,type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        dc=findViewById(R.id.button_choice);
        final Button button_choice=findViewById(R.id.button_choice);
        Intent i=getIntent();
        day1=i.getStringExtra("day1");
        month1=i.getStringExtra("month1");
        year1=i.getStringExtra("year1");
        hr=i.getStringExtra("hr");
        min=i.getStringExtra("min");
        ap=i.getStringExtra("ap");
        match=i.getStringExtra("match");
        day=i.getStringExtra("day");
        month=i.getStringExtra("month");
        type=i.getStringExtra("type");
        button_choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment singleChoiceDialog;
                singleChoiceDialog = new SingleChoice();
                singleChoiceDialog.setCancelable(false);
                singleChoiceDialog.show(getSupportFragmentManager(),"Single Choice Dialog");
            }
        });
    }

    @Override
    public void onPositiveButtonClicked(final String[] list, final int position) {
        Button button=findViewById(R.id.button_choice);
        button.setText(list[position]);
        if(position>=0) {
            Button ok=findViewById(R.id.ok);
            System.out.println(day1);
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    intent = new Intent(Main6Activity.this, Main9Activity.class);
                    intent.putExtra("day1",day1);
                    intent.putExtra("month",month);
                    intent.putExtra("month1",month1);
                    intent.putExtra("year1",year1);
                    intent.putExtra("hr",hr);
                    intent.putExtra("min",min);
                    intent.putExtra("ap",ap);
                    intent.putExtra("match",match);
                    intent.putExtra("day",day);
                    intent.putExtra("type",type);
                    intent.putExtra("game",list[position]);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onNegativeButtonClicked() {
        dc.setText("Dialog cancel");
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
                Intent intent = new Intent(Main6Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(Main6Activity.this, "Succesfully Logout", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.homemenu: {
                Intent intent = new Intent(Main6Activity.this, Main7Activity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}

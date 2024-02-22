package com.example.nat;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Main5Activity extends AppCompatActivity implements
        View.OnClickListener {

    Button btnDatePicker, btnTimePicker,btn_event;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute,mDay1,m,d,y;
    public String day1,month1,year1,hr,min,ap,day,month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        btn_event=(Button)findViewById(R.id.btn_event);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        btn_event.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            monthOfYear=monthOfYear+1;
                            d=dayOfMonth;
                            m=monthOfYear;
                            y=year;
                            day1=Integer.toString(dayOfMonth);
                            month1=Integer.toString(monthOfYear);
                            year1=Integer.toString(year);
                            if(day1.length()==1) {
                                day1 = "0" + day1;
                            }
                            if(m==1)
                            {
                                month="Jan";
                            }
                            else if(m==2)
                            {
                                month="Feb";
                            }
                            else if(m==3)
                            {
                                month="Mar";
                            }
                            else if(m==4)
                            {
                                month="Apr";
                            }
                            else if(m==5)
                            {
                                month="May";
                            }
                            else if(m==6)
                            {
                                month="Jun";
                            }
                            else if(m==7)
                            {
                                month="Jul";
                            }
                            else if(m==8)
                            {
                                month="Aug";
                            }
                            else if(m==9)
                            {
                                month="Sep";
                            }
                            else if(m==10)
                            {
                                month="Oct";
                            }
                            else if(m==11)
                            {
                                month="Nov";
                            }
                            else if(m==12)
                            {
                                month="Dec";
                            }
                            int t[]={0,3,2,5,0,3,5,1,4,6,2,4};
                            y-=(m<3)?1:0;
                            mDay1=(y+y/4-y/100+y/400+t[m-1]+d)%7;
                            if(mDay1==1)
                            {
                                day="Mon";
                            }
                            if(mDay1==2)
                            {
                                day="Tue";
                            }
                            if(mDay1==3)
                            {
                                day="Wed";
                            }
                            if(mDay1==4)
                            {
                                day="Thu";
                            }
                            if(mDay1==5)
                            {
                                day="Fri";
                            }
                            if(mDay1==6)
                            {
                                day="Sat";
                            }
                            if(mDay1==0)
                            {
                                day="Sun";
                            }
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time

            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            String AM_PM;
                            if(hourOfDay>=12) {
                                AM_PM="PM";
                            }
                            else
                            {
                                AM_PM="AM";
                            }
                            if(hourOfDay==0 && hourOfDay!=12)
                            {
                                hourOfDay=12;
                            }
                            else if(hourOfDay==12 && hourOfDay!=0)
                            {
                                hourOfDay=12;
                            }
                            else if(hourOfDay<12 && hourOfDay!=0)
                            {
                                hourOfDay=hourOfDay;
                            }
                            else if(hourOfDay>12 && hourOfDay!=0)
                            {
                                hourOfDay=hourOfDay-12;
                            }
                            txtTime.setText(hourOfDay + ":" + minute + " " + AM_PM);
                            hr=Integer.toString(hourOfDay);
                            min=Integer.toString(minute);
                            ap=AM_PM;
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

        if(v==btn_event)
        {
            Intent intent;
            intent = new Intent(Main5Activity.this, Main8Activity.class);
            intent.putExtra("day1",day1);
            intent.putExtra("month1",month1);
            intent.putExtra("year1",year1);
            intent.putExtra("hr",hr);
            intent.putExtra("min",min);
            intent.putExtra("ap",ap);
            intent.putExtra("day",day);
            intent.putExtra("month",month);
            startActivity(intent);
        }
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
                Intent intent = new Intent(Main5Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(Main5Activity.this, "Succesfully Logout", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.homemenu: {
                Intent intent = new Intent(Main5Activity.this, Main7Activity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}


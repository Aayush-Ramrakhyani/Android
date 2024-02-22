package com.example.nat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;


public class Main2Activity extends AppCompatActivity {
    public EditText fname1, lname1, email1, phone1, pass1, id1;
    public Button but1;
    public AwesomeValidation awesomeValidation;
    public RadioGroup radioGroup;
    public RadioButton sel;
    final Calendar c = Calendar.getInstance();
    int maxYear = c.get(Calendar.YEAR) - 11;
    int maxMonth = c.get(Calendar.MONTH);
    int maxDay = c.get(Calendar.DAY_OF_MONTH);
    int minYear = 1960;
    int minMonth = 0;
    int minDay = 31;
    int day, month, year;
    String gender;
    public DatePicker d;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        d = (DatePicker) findViewById(R.id.dob1);
        d.init(maxYear - 19, maxMonth, maxDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if (year < minYear)
                    view.updateDate(minYear, minMonth, minDay);
                if (monthOfYear < minMonth && year == minYear)
                    view.updateDate(minYear, minMonth, minDay);
                if (dayOfMonth < minDay && year == minYear && monthOfYear == minMonth)
                    view.updateDate(minYear, minMonth, minDay);

                if (year > maxYear)
                    view.updateDate(maxYear, maxMonth, maxDay);

                if (monthOfYear > maxMonth && year == maxYear)
                    view.updateDate(maxYear, maxMonth, maxDay);

                if (dayOfMonth > maxDay && year == maxYear && monthOfYear == maxMonth)
                    view.updateDate(maxYear, maxMonth, maxDay);
            }
        });
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        fname1 = findViewById(R.id.fname1);
        radioGroup = findViewById(R.id.g);
        lname1 = findViewById(R.id.lname1);
        email1 = findViewById(R.id.email1);
        phone1 = findViewById(R.id.phone1);
        pass1 = findViewById(R.id.pass1);
        but1 = findViewById(R.id.but1);
        id1 = findViewById(R.id.id1);
        day = d.getDayOfMonth();
        month = d.getMonth();
        year = d.getYear();
        awesomeValidation.addValidation(this, R.id.fname1, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{3,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.lname1, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{3,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.email1, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        awesomeValidation.addValidation(this, R.id.pass1, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{6,}$", R.string.passworderror);
        awesomeValidation.addValidation(this, R.id.phone1, "^[0-9]{2}[0-9]{8}$", R.string.mobileerror);
        awesomeValidation.addValidation(this, R.id.id1, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{3,}$", R.string.iderror);
        reff = FirebaseDatabase.getInstance().getReference().child("data");
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sel=findViewById(radioGroup.getCheckedRadioButtonId());
                gender=sel.getText().toString();
                submitform();
            }
        });

    }

    public void submitform() {
        if (awesomeValidation.validate()) {
            String day1 = Integer.toString(day);
            String month1 = Integer.toString(month);
            String year1 = Integer.toString(year);
            DatabaseReference fuName = reff.child(id1.getText().toString().trim());

            DatabaseReference fName = fuName.child("Fname");
            fName.setValue(fname1.getText().toString().trim());
            DatabaseReference lName = fuName.child("Lname");
            lName.setValue(lname1.getText().toString().trim());

            DatabaseReference iD = fuName.child("Login_Id");
            iD.setValue(id1.getText().toString().trim());

            DatabaseReference pNo = fuName.child("Phone_No");
            pNo.setValue(phone1.getText().toString().trim());


            DatabaseReference pass = fuName.child("Password");
            pass.setValue(pass1.getText().toString().trim());


            DatabaseReference email = fuName.child("Email");
            email.setValue(email1.getText().toString().trim());

            DatabaseReference gender1 = fuName.child("Gender");
            gender1.setValue(gender);

            DatabaseReference Date = fuName.child("Date");
            Date.setValue(day1 + "/" + month1 + "/" + year1);
            Toast.makeText(Main2Activity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Main2Activity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}

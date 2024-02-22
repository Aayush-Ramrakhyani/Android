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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class Main30Activity extends AppCompatActivity {
    ListView list;
    ArrayList<String> arrayList = new ArrayList<>();
    DatabaseReference reff2;
    Member4 member;
    String key,t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        String players;
        String[] a = new String[10];
        a = extras.getStringArray("a");
        String d = extras.getString("d");
        key = extras.getString("key");
        t=extras.getString("t");
        setContentView(R.layout.activity_main30);
        list = (ListView) findViewById(R.id.listview);
        int len = Integer.parseInt(d);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        String c[] = new String[len];
        for (int i = 0; i < len; i++) {
            c[i] = a[i];
        }
        reff2 = FirebaseDatabase.getInstance().getReference().child("data6");
        member = new Member4();
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
                Intent intent = new Intent(Main30Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(Main30Activity.this, "Succesfully Logout", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.homemenu: {
                Intent intent = new Intent(Main30Activity.this, Main7Activity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    public void show(View view) {
        String items="";
        for(String item:arrayList)
        {
            items+="-"+item+"\n";
        }
        member.setItems(items);
        member.setKey(key);
        member.setT(t);
        member.setPlayers("");
        member.setScore("");
        member.setMatch("Basketball");
        reff2.push().setValue(member);
        Intent intent;
        intent=new Intent(Main30Activity.this,Main29Activity.class);
        intent.putExtra("i",items);
        setResult(RESULT_OK,intent);
        finish();
    }
}


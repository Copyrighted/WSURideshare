package com.example.rideshare_00.PostList;

import android.content.Intent;
import java.lang.*;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.CalendarView;

import com.example.rideshare_00.GetList.getList;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.example.rideshare_00.R;

public class postTrip extends AppCompatActivity {
    private DatabaseReference mDatabase;
    Button confrim_btn;
    String from,dst,driver,date;
    CalendarView calendar;
    String selectedDate;
    Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_trip);
        confrim_btn= findViewById(R.id.confrim);
        intent = new Intent(this, getList.class);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        confrim_btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                String timestap = String.valueOf(System.currentTimeMillis());
                from= ((EditText)findViewById(R.id.frmTxt)).getText().toString();
                dst = ((EditText)findViewById(R.id.dstTxt)).getText().toString();
                driver = ((EditText)findViewById(R.id.D_Name)).getText().toString();
                calendar = (CalendarView) findViewById(R.id.calendarView);

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                selectedDate= sdf.format(new Date(calendar.getDate()));
                System.out.println(selectedDate);
                mDatabase.child("Trip").child(timestap).child("Date").setValue(selectedDate);
                mDatabase.child("Trip").child(timestap).child("From").setValue(from);
                mDatabase.child("Trip").child(timestap).child("Dst").setValue(dst);
                mDatabase.child("Trip").child(timestap).child("Driver").setValue(driver);
                startActivity(intent);
            }
        });
    }
}

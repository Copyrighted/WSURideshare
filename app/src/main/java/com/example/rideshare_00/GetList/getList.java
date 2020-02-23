package com.example.rideshare_00.GetList;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.rideshare_00.PostList.postTrip;
import com.example.rideshare_00.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class getList extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState ){
        setContentView(R.layout.get_list);
        ListView list = findViewById(R.id.show_list);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        list.setAdapter(adapter);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference contacts = database.getReference("Trip");
        contacts.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                adapter.add((String)dataSnapshot.child("From").getValue()+ "->"+ (String)dataSnapshot.child("Dst").getValue());


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                adapter.remove((String) dataSnapshot.child("From").getValue()+ "->" +(String) dataSnapshot.child("Dst").getValue());

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onCreate(savedInstanceState);
        Button create_btn= findViewById(R.id.create_btn);
        create_btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getList.this, postTrip.class);
                startActivity(intent);
            }
        });
    }
    public void PostTrip(){

    }

}

package com.example.rideshare_00.GetList;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.rideshare_00.PostList.postTrip;
import com.example.rideshare_00.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.os.Handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class getList extends AppCompatActivity {

    DatabaseReference contacts;
    FirebaseDatabase database;
    ArrayList<String> a_list;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        contacts= database.getReference("Trip");
        database.getReference("Trip").keepSynced(true);
        a_list= new ArrayList<>();
        setContentView(R.layout.get_list);
        ListView list = findViewById(R.id.show_list);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        list.setAdapter(adapter);
        contacts.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.child("From").getValue()!=null && dataSnapshot.child("Dst").getValue()!=null) {
                    String key = dataSnapshot.getKey().toString();
                    a_list.add(key);
                    adapter.add((String) dataSnapshot.child("From").getValue() + "->" + (String) dataSnapshot.child("Dst").getValue()+"  Driver Name: "+(String) dataSnapshot.child("Driver").getValue()+"   "+(String) dataSnapshot.child("Date").getValue());
                    adapter.notifyDataSetChanged();
                }

            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.child("From").getValue()!=null && dataSnapshot.child("Dst").getValue()!=null && i==2){
                    adapter.add((String) dataSnapshot.child("From").getValue() + "->" + (String) dataSnapshot.child("Dst").getValue()+"  Driver Name: "+(String) dataSnapshot.child("Driver").getValue()+"   "+(String) dataSnapshot.child("Date").getValue());
                    i=0;
                }
                adapter.notifyDataSetChanged();
                i++;
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                adapter.remove((String) dataSnapshot.child("From").getValue() + "->" + (String) dataSnapshot.child("Dst").getValue()+"  Driver Name: "+(String) dataSnapshot.child("Driver").getValue()+"   "+(String) dataSnapshot.child("Date").getValue());

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Button create_btn= findViewById(R.id.create_btn);
        create_btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getList.this, postTrip.class);
                startActivity(intent);
            }
        });

        list.setOnItemClickListener( new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println(a_list.get(i));
                contacts.child(a_list.get(i)).removeValue();
                for( int j =i; j +1< a_list.size();j++){
                    a_list.set(j,a_list.get(j+1));
                }
                new AlertDialog.Builder(getList.this).setTitle("Confirm?").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int  i ) {
                    }
                }).setNegativeButton("cancle", null).create().show();
            }
        });
    }

}

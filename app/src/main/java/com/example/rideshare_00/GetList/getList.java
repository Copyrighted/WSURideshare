package com.example.rideshare_00.GetList;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.example.rideshare_00.PostList.postTrip;
import com.example.rideshare_00.R;

public class getList extends AppCompatActivity {

   //
    @Override
    protected void onCreate(Bundle savedInstanceState ){
        setContentView(R.layout.get_list);
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

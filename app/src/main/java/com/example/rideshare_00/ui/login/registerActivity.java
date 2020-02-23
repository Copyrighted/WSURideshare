package com.example.rideshare_00.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.example.rideshare_00.R;

public class registerActivity extends AppCompatActivity {

    private Button button;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        button = (Button) findViewById(R.id.register2Btn);
        button.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goBacktoMeanActivity();
            }

        });

    }
    //function to go back to Main View after registering
    public void goBacktoMeanActivity(){
        Intent intent = new Intent (this, LoginActivity.class);
        startActivity(intent);
    }
}



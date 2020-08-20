package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class ControlClass extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        if (sharedPreferences.getString("user", "").isEmpty() ||
        sharedPreferences.getString("user", "").equalsIgnoreCase("")){
            //login dulu
            startActivity(new Intent(getApplicationContext(),Login.class));
        }else{
            startActivity(new Intent(getApplicationContext(), MainMenu.class));

        }
    }
}
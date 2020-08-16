package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;


public class Login extends AppCompatActivity {
    EditText txtuser;
    EditText txtpass;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void bounce(View view) {
        Button button = (Button)findViewById(R.id.btnlogin);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        button.startAnimation(animation);
    }
}

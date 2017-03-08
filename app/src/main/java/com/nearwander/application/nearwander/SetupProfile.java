package com.nearwander.application.nearwander;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SetupProfile extends AppCompatActivity {

    private TextView tvEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_profile);

        tvEmail = (TextView) findViewById(R.id.emailProfile);
        tvEmail.setText(getIntent().getExtras().getString("Email"));

    }
}

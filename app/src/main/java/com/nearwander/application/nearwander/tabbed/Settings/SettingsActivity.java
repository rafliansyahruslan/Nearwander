package com.nearwander.application.nearwander.tabbed.Settings;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.nearwander.application.nearwander.R;
import com.nearwander.application.nearwander.userprofile.SetupProfile;

public class SettingsActivity extends AppCompatActivity {

    private TextView textView;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Drawable drwA = getDrawable(R.drawable.ic_arrow_back_white_24dp);
        Bitmap bitmap = ((BitmapDrawable) drwA).getBitmap();
        Drawable newDrw = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 100, 100, true));

        Toolbar toolbar = (Toolbar) findViewById(R.id.settings_toolbar);
        toolbar.setNavigationIcon(newDrw);
        toolbar.setTitleTextColor(R.color.white);
        toolbar.setTitle("Settings");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        textView = (TextView) findViewById(R.id.edit_profile);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"fonts/JosefinSans-Bold.ttf");
        textView.setTypeface(myCustomFont);

        textView2 = (TextView) findViewById(R.id.about_us);
        Typeface myCustomFont1 = Typeface.createFromAsset(getAssets(),"fonts/JosefinSans-Bold.ttf");
        textView2.setTypeface(myCustomFont1);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SetupProfile.class);
                startActivity(i);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent
            }
        });
    }


}

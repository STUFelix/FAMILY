package com.example.kaixuan.family;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.kaixuan.family.Circle.HisCircleActivity;
import com.example.kaixuan.family.Contacts.chatInterfaceActivity;

public class MeIntentActivity extends AppCompatActivity {
    private TextView toChattextView;
    private TextView HisCircletextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meintent_activity);
        initView();
    }
    private void initView(){
        toChattextView = findViewById(R.id.toChat);
        toChattextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeIntentActivity.this,chatInterfaceActivity.class);
                startActivity(intent);
            }
        });
        HisCircletextView = findViewById(R.id.HisCircle);
        HisCircletextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeIntentActivity.this,HisCircleActivity.class);
                startActivity(intent);
            }
        });
    }
}

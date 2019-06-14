package com.example.kaixuan.family.Tree;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.kaixuan.family.R;

public class AddMemberActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_back;
    private Button btn_comfirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmember_activity);

        btn_back = findViewById(R.id.addmember_back);
        btn_comfirm = findViewById(R.id.addmember_comfirm);
        btn_back.setOnClickListener(this);
        btn_comfirm.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addmember_comfirm:
                finish();
                break;
            case R.id.addmember_back:
                finish();
                break;
        }

    }
}

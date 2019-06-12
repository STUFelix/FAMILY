package com.example.kaixuan.family.Circle;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.kaixuan.family.R;


public class HisCircleActivity extends AppCompatActivity {
    private RecyclerView mRvTestList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_hiscircle);
        mRvTestList = (RecyclerView) findViewById(R.id.rv_hiscircle_list);
        mRvTestList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvTestList.setAdapter(new HisTestListAdapter(this));
    }


}

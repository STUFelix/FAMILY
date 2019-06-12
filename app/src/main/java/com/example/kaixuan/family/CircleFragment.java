package com.example.kaixuan.family;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kaixuan.family.Circle.TestListAdapter;

public class CircleFragment extends Fragment {
    private View mView;
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if(mView == null){
            mView = inflater.inflate(R.layout.circle_fragment, container, false);
            init();
        }
        return mView;
    }
    private void init(){
        recyclerView = (RecyclerView) mView.findViewById(R.id.rv_test_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new TestListAdapter(getContext()));
    }



}

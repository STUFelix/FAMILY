package com.example.kaixuan.family;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kaixuan.family.Me.MyLifeActivity;
import com.example.kaixuan.family.login.ToSignInActivity;

public class MeFragment extends Fragment {
    private View mView;
    private TextView MyLifeTextView;
    private TextView LogOut;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if(mView == null){
            mView = inflater.inflate(R.layout.me_fragment, container, false);
            init();
        }

        return mView;
    }

    private void init(){
        MyLifeTextView = mView.findViewById(R.id.mylife);
        MyLifeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MyLifeActivity.class);
                startActivity(intent);

            }
        });
        LogOut = mView.findViewById(R.id.log_out);
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(getActivity(),ToSignInActivity.class);
                startActivity(intent);
            }
        });


    }
}

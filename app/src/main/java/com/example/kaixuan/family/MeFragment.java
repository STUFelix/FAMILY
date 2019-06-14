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
import com.example.kaixuan.family.login.ToUpdateActivity;

public class MeFragment extends Fragment {
    private View mView;
    private TextView MyLifeTextView;
    private TextView LogOut;
    private TextView updatePassword;
    private TextView TVcontents;
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
        updatePassword = mView.findViewById(R.id.update_password);
        updatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(getActivity(),ToUpdateActivity.class);
                startActivity(intent);
            }
        });
        TVcontents = mView.findViewById(R.id.metv_contents);
        TVcontents.setText("国籍：美国                |       出生地：美国纽约\n"+
                "家族：史塔克家族  |        年龄：35\n"+
                "主要成就L:           ---------------------------------\n"+
                "1.守夜人，有效阻止夜王\n"+
        "2.公元前500年创立阿里巴巴\n"+
        "3.公元221年统一六国\n");

    }
}

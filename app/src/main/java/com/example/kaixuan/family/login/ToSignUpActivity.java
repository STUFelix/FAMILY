package com.example.kaixuan.family.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kaixuan.family.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import static cn.bmob.v3.b.From.e;

public class ToSignUpActivity  extends AppCompatActivity implements View.OnClickListener{

    EditText et_account,et_password;
    Button  bt_back,bt_confirm;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_uplayout);

        Bmob.initialize(this,"5842011c2e483aac62ac4d8ccc7a9150");

        et_account=(EditText)findViewById(R.id.sign_up_account);
        et_password=(EditText)findViewById(R.id.sign_up_password);
        bt_back=(Button)findViewById(R.id.sign_up_back);
        bt_confirm=(Button)findViewById(R.id.sign_up_confirm);

        bt_back.setOnClickListener(this);
        bt_confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.sign_up_confirm:
                String the_account =et_account.getText().toString();
                String the_password =et_password.getText().toString();

                if(TextUtils.isEmpty(the_account)||TextUtils.isEmpty(the_password)){
                    Toast.makeText(this,"账号和密码都不能为空",Toast.LENGTH_SHORT).show();
                }

                BmobUser user = new BmobUser();
                user.setUsername(the_account);
                user.setPassword(the_password);
                user.signUp(new SaveListener<theUserImformation>() {
                    public void done(theUserImformation s, BmobException e){
                        Toast.makeText(ToSignUpActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            case R.id.sign_up_back:
                finish();
                break;
            default:
                break;
        }

    }
}

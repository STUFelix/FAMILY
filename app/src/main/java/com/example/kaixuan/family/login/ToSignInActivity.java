package com.example.kaixuan.family.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kaixuan.family.MainActivity;
import com.example.kaixuan.family.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class ToSignInActivity  extends AppCompatActivity implements View.OnClickListener {

    EditText Login_et_account,Login_et_password;
    Button Login_bt_SignUp,Login_bt_confirm;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_inlayout);

        Bmob.initialize(this,"5842011c2e483aac62ac4d8ccc7a9150");

        Login_et_account=(EditText)findViewById(R.id.sign_in_account);
        Login_et_password=(EditText)findViewById(R.id.sign_in_password);
        Login_bt_SignUp=(Button)findViewById(R.id.sign_in_sign_up);
        Login_bt_confirm=(Button)findViewById(R.id.sign_in_sign_in);

        Login_bt_SignUp.setOnClickListener(this);
        Login_bt_confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.sign_in_sign_in:
                String the_account =Login_et_account.getText().toString();
                String the_password =Login_et_password.getText().toString();

                if(TextUtils.isEmpty(the_account)||TextUtils.isEmpty(the_password)){
                    Toast.makeText(this,"账号和密码都不能为空",Toast.LENGTH_SHORT).show();
                }



                //user.loginByAccount(the_account,the_password,new LogInListener<String>
                BmobUser user = new BmobUser();
                user.setUsername(the_account);
                user.setPassword(the_password);
                user.login(new SaveListener<BmobUser>(){
                    public void done(BmobUser user,BmobException e){
                        if(e == null){
                            Toast.makeText(ToSignInActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setClass(ToSignInActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(ToSignInActivity.this,"用户或密码错误",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;


            case R.id.sign_in_sign_up:
                Intent intent = new Intent(ToSignInActivity.this,ToSignUpActivity.class);
                startActivity(intent);
                break;


            default:
                break;
        }

    }




}
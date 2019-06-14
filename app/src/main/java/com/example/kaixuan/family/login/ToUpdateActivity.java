package com.example.kaixuan.family.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kaixuan.family.MainActivity;
import com.example.kaixuan.family.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class ToUpdateActivity extends AppCompatActivity  implements View.OnClickListener{

    EditText old_password,new_password,new_password_again;
    Button btn_back,btn_comfirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatepassword_layout);
        init();
    }

    private void init() {
        old_password = findViewById(R.id.old_password);
        new_password = findViewById(R.id.new_password);
        new_password_again = findViewById(R.id.new_password_again);

        btn_back = findViewById(R.id.update_back);
        btn_comfirm = findViewById(R.id.update_confirm);
        btn_back.setOnClickListener(this);
        btn_comfirm.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.update_confirm:
                String oldPassword = old_password.getText().toString();
                String newPassword = new_password.getText().toString();
                String newPasswordAgain = new_password_again.getText().toString();
                if(TextUtils.isEmpty(oldPassword)||TextUtils.isEmpty(newPassword)||TextUtils.isEmpty(newPasswordAgain)){
                    Toast.makeText(this,"不能为空",Toast.LENGTH_SHORT).show();
                }
                if(newPassword.equals(newPasswordAgain)){

                    BmobUser.updateCurrentUserPassword(oldPassword, newPassword, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            Toast.makeText(ToUpdateActivity.this,"密码修改成功",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setClass(ToUpdateActivity.this,ToSignInActivity.class);
                            startActivity(intent);
                        }
                    });

                }else {
                    Toast.makeText(this,"新密码和确认密码值得相同",Toast.LENGTH_SHORT).show();
                }




                break;

            case R.id.update_back:
                finish();
                break;

                default:
                    break;

        }
    }
}

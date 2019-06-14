package com.example.kaixuan.family.Tree;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.example.kaixuan.family.AssetsUtil;
import com.example.kaixuan.family.FamilyLiteOrm;
import com.example.kaixuan.family.FamilyMember;
import com.example.kaixuan.family.FamilyTreeView;
import com.example.kaixuan.family.MeIntentActivity;
import com.example.kaixuan.family.OnFamilySelectListener;
import com.example.kaixuan.family.R;

import java.util.List;

public class TREEFragment extends BaseTREEFragment{

    private static final String MY_ID = "601";

    private Button btnEnlarge;

    private Button btnShrinkDown;

    private Button btnAddmember;

    private Button btngetPermission;

    private Button btnDeletemember;

    private FamilyTreeView ftvTree;

    private FamilyLiteOrm mDatabase;

    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if(mView == null){
            mView = inflater.inflate(R.layout.activity_family_tree, container, false);
            initView();

            String appName = getString(R.string.app_name);

            permissions = new String[]{

                    Manifest.permission.READ_EXTERNAL_STORAGE,

            };

            refuseTips = new String[]{

                    String.format("在设置-应用-%1$s-权限中开启存储权限，以正常使用该功能", appName),

            };

            setPermissions();
        }
        return mView;
    }
    @Override

    public void onDestroy() {

        super.onDestroy();

        if (mDatabase != null) {

            mDatabase.closeDB();

        }

    }

    @Override

    public void onPermissionSuccess() {

        setData();

    }


    private void initView() {

        btnEnlarge = (Button) mView.findViewById(R.id.btn_enlarge);

        btnShrinkDown = (Button) mView.findViewById(R.id.btn_shrink_down);

        ftvTree = (FamilyTreeView) mView.findViewById(R.id.ftv_tree); //原来括号里面竟然是个类名，强制转换。

        btnAddmember = (Button) mView.findViewById(R.id.addmember);

        btnDeletemember = (Button) mView.findViewById(R.id.deletemember);

        btngetPermission = (Button) mView.findViewById(R.id.get_permission);

    }



    private void setData() {


        ftvTree.setVisibility(View.VISIBLE);

        mDatabase = new FamilyLiteOrm(getContext());
        Log.i("一1：mDatabase", String.valueOf(mDatabase));//有数据

        String json = AssetsUtil.getAssetsTxtByName(getContext(), "family_tree.txt");

        List<FamilyMember> mList = JSONArray.parseArray(json, FamilyMember.class);
        Log.i("mList", String.valueOf(mList));//有数据

        mDatabase.deleteTable();

        mDatabase.save(mList);
        Log.i("二2：mDatabase", String.valueOf(mDatabase));

        btnEnlarge.setOnClickListener(click);

        btnShrinkDown.setOnClickListener(click);

        btnAddmember.setOnClickListener(click);
        btnDeletemember.setOnClickListener(click);
        btngetPermission.setOnClickListener(click);


        FamilyMember mFamilyMember = mDatabase.getFamilyTreeById(MY_ID);

        Log.i("mFamilyMember", String.valueOf(mFamilyMember));

        if (mFamilyMember != null) {

            ftvTree.setFamilyMember(mFamilyMember);

        }

        ftvTree.setOnFamilySelectListener(familySelect);

    }

    private OnFamilySelectListener familySelect = new OnFamilySelectListener() {

        @Override

        public void onFamilySelect(FamilyMember family) {

            if (family.isSelect()) {
                /**点击已经选中的头像跳转到"我的"activity*/
                Intent intent = new Intent(getActivity(),MeIntentActivity.class);
                startActivity(intent);
            } else {

                String currentFamilyId = family.getMemberId();

                FamilyMember currentFamily = mDatabase.getFamilyTreeById(currentFamilyId);

                if (currentFamily != null) {

                    ftvTree.setFamilyMember(currentFamily);

                }

            }

        }

    };


    private static int famaly_permission = -1;

    private View.OnClickListener click = new View.OnClickListener() {

        @Override

        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.btn_enlarge:

                    ftvTree.doEnlarge();

                    break;

                case R.id.btn_shrink_down:

                    ftvTree.doShrinkDown();

                    break;

                case R.id.addmember:
                    if (famaly_permission == 1){
                        Intent intent = new Intent(getActivity(),AddMemberActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getActivity(),"请先获得权限",Toast.LENGTH_LONG).show();
                    }
                    break;

                case R.id.get_permission:
                    final EditText editText = new EditText(mView.getContext());
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(mView.getContext());

                    alertDialog.setTitle("请输入秘钥").setView(editText).setPositiveButton("验证", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String ET_String = editText.getText().toString();
                                    if(ET_String.equals("mima")){
                                        Toast.makeText(getActivity(),"成功获得权限",Toast.LENGTH_LONG).show();
                                        famaly_permission = 1;
                                    }
                                }
                            }).setNegativeButton("取消",null).show();


                    famaly_permission = 1;

                    break;

                case R.id.deletemember:

                    if (famaly_permission == 1){
                    final EditText delete_EditText = new EditText(mView.getContext());
                    AlertDialog.Builder delete_alertDialog = new AlertDialog.Builder(mView.getContext());

                    delete_alertDialog.setTitle("请输入要删除的id号").setView(delete_EditText).setPositiveButton("确认删除", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String Delete_ET_String = delete_EditText.getText().toString();
                            Toast.makeText(getActivity(),"成功删除"+Delete_ET_String,Toast.LENGTH_LONG).show();

                        }
                    }).setNegativeButton("取消",null).show();
                    }else {
                        Toast.makeText(getActivity(),"请先获得权限",Toast.LENGTH_LONG).show();

                    }

                    break;

                    default:
                        break;
            }
        }
    };


}

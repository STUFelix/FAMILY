package com.example.kaixuan.family.Tree;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;

import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.kaixuan.family.AppManager;
import com.example.kaixuan.family.DefaultAlertDialog;
import com.example.kaixuan.family.DisplayUtil;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class BaseTREEFragment extends Fragment implements TREEListener{

    private static final int REQUEST_TO_SETTING = 0;//跳转到系统设置权限页面



    protected String[] permissions = {};//需要请求的权限

    protected String[] refuseTips = {};//拒绝请求后的对话框提示



    private InputMethodManager manager;



    private boolean curIsShow = false;



    private DefaultAlertDialog permissionDialog;//获取权限对话框

    private int permissionPosition = 0;//当前请求的权限

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i(getActivity().getLocalClassName());



        manager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);



        setStatusBar();
    }

    @Override

    public void onDestroy() {

        //移除布局变化监听

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

            getActivity().getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(mLayoutChangeListener);

        } else {

            getActivity(). getWindow().getDecorView().getViewTreeObserver().removeGlobalOnLayoutListener(mLayoutChangeListener);

        }

        if (permissionDialog != null) {

            permissionDialog.dismissDialog();

        }

        super.onDestroy();

    }



    //沉浸式

    private void setStatusBar() {

        Window window = getActivity().getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            // 状态栏透明

            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // 导航栏透明

//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        }

        window.getDecorView().setFitsSystemWindows(true);

    }



    //隐藏键盘

    protected void hideKeyboard() {

        if (getActivity().getCurrentFocus() != null && getActivity().getCurrentFocus().getWindowToken() != null) {

            manager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        }

    }



    //隐藏虚拟按键，并且全屏

    protected void hideBottomUIMenu() {

        //隐藏虚拟按键，并且全屏

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB && Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {

            View v = getActivity().getWindow().getDecorView();

            v.setSystemUiVisibility(View.GONE);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            //for new api versions.

            View decorView = getActivity().getWindow().getDecorView();

            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;

            decorView.setSystemUiVisibility(uiOptions);

        }

    }



    //键盘显示隐藏回调

    protected void setOnKeyboardChangeListener() {

        getActivity().getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(mLayoutChangeListener);

    }



    //layout改变监听

    private ViewTreeObserver.OnGlobalLayoutListener mLayoutChangeListener = new ViewTreeObserver.OnGlobalLayoutListener() {

        @Override

        public void onGlobalLayout() {

            Rect r = new Rect();

            getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(r);



            int screenHeight = DisplayUtil.getScreenHeight();

            int heightDifference = screenHeight - (r.bottom - r.top);



            boolean isShow = heightDifference > screenHeight / 3;



            if (((!curIsShow && isShow) || curIsShow && !isShow)) {

                onkeyboardChange(isShow);

                curIsShow = isShow;

            }

        }

    };



    protected void setPermissions() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {

            onPermissionSuccess();

        } else {

            List<String> pTemp = new ArrayList<>();

            List<String> tTemp = new ArrayList<>();

            for (int i = 0; i < permissions.length; i++) {

                String permission = permissions[i];

                if (ContextCompat.checkSelfPermission(getContext(), permission) != PackageManager.PERMISSION_GRANTED) {

                    pTemp.add(permission);

                    tTemp.add(refuseTips[i]);

                }

            }



            permissions = pTemp.toArray(new String[pTemp.size()]);

            refuseTips = tTemp.toArray(new String[tTemp.size()]);



            requestPermissions(0);

        }

    }



    private void requestPermissions(int index) {

        if (permissions.length > 0 && index >= 0 && index < permissions.length) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{permissions[index]}, index);

        } else if (permissions.length == 0 || index >= permissions.length) {

            onPermissionSuccess();

        }

    }



    @Override

    public void onRequestPermissionsResult(final int requestCode, String[] p, int[] grantResults) {

        if (grantResults != null && grantResults.length > 0) {

            permissionPosition = requestCode;

            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                if (requestCode < refuseTips.length) {

                    permissionDialog = new DefaultAlertDialog(getContext());

                    permissionDialog.setTitle("权限申请");

                    permissionDialog.setMessage(refuseTips[requestCode]);

                    permissionDialog.setConfirmButton("去设置", new DialogInterface.OnClickListener() {

                        @Override

                        public void onClick(DialogInterface dialog, int which) {

                            AppManager.showInstalledAppDetails(getActivity(), getActivity().getPackageName(), REQUEST_TO_SETTING);

                        }

                    });

                    permissionDialog.setCancelButton("取消", new DialogInterface.OnClickListener() {

                        @Override

                        public void onClick(DialogInterface dialog, int which) {

                            Toast.makeText(getActivity().getApplicationContext(),"没有权限不能使用该功能",Toast.LENGTH_LONG);

                        }

                    });

                } else {

                    Toast.makeText(getActivity().getApplicationContext(),"没有权限不能使用该功能",Toast.LENGTH_LONG);

                }

                permissionDialog.showDialog();

            } else {

                int nextRequest = permissionPosition + 1;

                requestPermissions(nextRequest);

            }

        }

    }



    @Override

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (REQUEST_TO_SETTING == requestCode) {

            if (permissionPosition < permissions.length) {

                if (ContextCompat.checkSelfPermission(getContext(), permissions[permissionPosition]) != PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(getActivity().getApplicationContext(),"没有权限不能使用该功能",Toast.LENGTH_LONG);

                } else {

                    onPermissionSuccess();

                }

            }

        }

    }



    @Override

    public void onkeyboardChange(boolean isShow) {

        //键盘显示隐藏后的操作

    }



    @Override

    public void onPermissionSuccess() {

        //请求权限成功后的操作

    }
}

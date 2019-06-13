package com.example.kaixuan.family;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.kaixuan.family.Tree.TREEFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup navigationBar;
    private RadioButton btnTree, btnContacts, btnCircle, btnMe;
    private Fragment treeFragment, contactsFragment, circleFragment, meFragment;

    private Fragment mFragment;//当前显示的Fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.main_fragment, treeFragment).commit();
        mFragment = treeFragment;
    }

    private void initViews() {
        navigationBar = (RadioGroup) findViewById(R.id.main_radiogroup);
        btnTree = (RadioButton) findViewById(R.id.main_familytree);
        btnContacts = (RadioButton) findViewById(R.id.main_familycontacts);
        btnCircle = (RadioButton) findViewById(R.id.main_familycircle);
        btnMe = (RadioButton) findViewById(R.id.main_familyme);
        fitDrawable();
        navigationBar.setOnCheckedChangeListener(this);
        treeFragment = new TREEFragment();
        contactsFragment = new ContactsFragment();
        circleFragment = new CircleFragment();
        meFragment = new MeFragment();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        btnTree.setChecked(false);
        btnContacts.setChecked(false);
        btnCircle.setChecked(false);
        btnMe.setChecked(false);
        switch (checkedId) {
            case R.id.main_familytree:
                btnTree.setChecked(true);
                switchFragment(treeFragment);
                break;
            case R.id.main_familycontacts:
                btnContacts.setChecked(true);
                switchFragment(contactsFragment);
                break;
            case R.id.main_familycircle:
                btnCircle.setChecked(true);
                switchFragment(circleFragment);
                break;
            case R.id.main_familyme:
                btnMe.setChecked(true);
                switchFragment(meFragment);
                break;

            default:
                break;
        }
    }

    private void switchFragment(Fragment fragment) {
        //判断当前显示的Fragment是不是切换的Fragment
        if (mFragment != fragment) {
            //判断切换的Fragment是否已经添加过
            if (!fragment.isAdded()) {
                //如果没有，则先把当前的Fragment隐藏，把切换的Fragment添加上
                getSupportFragmentManager().beginTransaction().hide(mFragment)
                        .add(R.id.main_fragment, fragment).commit();
            } else {
                //如果已经添加过，则先把当前的Fragment隐藏，把切换的Fragment显示出来
                getSupportFragmentManager().beginTransaction().hide(mFragment).show(fragment).commit();
            }
            mFragment = fragment;
        }
    }

    /**
     * 令底部导航栏的图标大小合适**/
    public void fitDrawable() {

        Drawable drawable_tree =getResources().getDrawable(R.drawable.rb_drawable_tree);
        drawable_tree.setBounds(0, 0, 40, 40);
        btnTree.setCompoundDrawables(null, drawable_tree, null, null);

        Drawable drawable_contacts =getResources().getDrawable(R.drawable.rb_drawable_contacts);
        drawable_contacts.setBounds(0, 0, 40, 40);
        btnContacts.setCompoundDrawables(null, drawable_contacts, null, null);

        Drawable drawable_circle =getResources().getDrawable(R.drawable.rb_drawable_circle);
        drawable_circle.setBounds(0, 0, 40, 40);
        btnCircle.setCompoundDrawables(null, drawable_circle, null, null);

        Drawable drawable_me =getResources().getDrawable(R.drawable.rb_drawable_me);
        drawable_me.setBounds(0, 0, 40, 40);
        btnMe.setCompoundDrawables(null, drawable_me, null, null);
    }
}

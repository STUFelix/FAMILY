package com.example.kaixuan.family;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.kaixuan.family.module.PersonalInfo;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class TryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try);

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("try.realm")
                .build();
        Realm familyRealm = Realm.getInstance(config);

        familyRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                PersonalInfo pi=realm.createObject(PersonalInfo.class);
                pi.setName("WWW");
                pi.setAge(10);
                pi.setBirthday("2009/02/22");
                pi.setAchievement("无所事事");
            }
        });
        familyRealm.commitTransaction();
    }
}

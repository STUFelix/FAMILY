package com.example.kaixuan.family;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.*;

import com.example.kaixuan.family.Contacts.ContactAdapter;
import com.example.kaixuan.family.Contacts.DividerItemDecoration;
import com.example.kaixuan.family.Contacts.LetterView;

public class ContactsFragment extends Fragment {
    private View mView;
    private RecyclerView contactList;
    private String[] contactNames;
    private LinearLayoutManager layoutManager;
    private LetterView letterView;
    private ContactAdapter adapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (mView == null){
            mView = inflater.inflate(R.layout.contacts_fragment,container,false);
            init();
        }

        return mView;
    }
    private void init(){
        contactNames = new String[] {"张三丰", "郭靖", "黄蓉", "黄老邪", "赵敏", "123", "天山童姥", "任我行", "于万亭", "陈家洛", "韦小宝", "$6", "穆人清", "陈圆圆", "郭芙", "郭襄", "穆念慈", "东方不败", "梅超风", "林平之", "林远图", "灭绝师太", "段誉", "鸠摩智"};
        contactList = (RecyclerView) mView.findViewById(R.id.contact_list);
        letterView = (LetterView) mView.findViewById(R.id.letter_view);
        layoutManager = new LinearLayoutManager(getContext());
        adapter = new ContactAdapter(getContext(), contactNames);

        contactList.setLayoutManager(layoutManager);
        contactList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        contactList.setAdapter(adapter);

        letterView.setCharacterListener(new LetterView.CharacterClickListener() {
            @Override
            public void clickCharacter(String character) {
                layoutManager.scrollToPositionWithOffset(adapter.getScrollPosition(character), 0);
            }

            @Override
            public void clickArrow() {
                layoutManager.scrollToPositionWithOffset(0, 0);
            }
        });

    }

}

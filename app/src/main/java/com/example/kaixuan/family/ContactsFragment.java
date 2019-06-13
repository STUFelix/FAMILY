package com.example.kaixuan.family;


import android.content.Intent;
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
import com.example.kaixuan.family.Contacts.chatInterfaceActivity;

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
        contactNames = new String[] {"张三丰", "郭靖", "黄蓉", "黄老邪", "赵敏", "123", "天山童姥", "任我行",
                "于万亭", "陈家洛", "韦小宝", "$6", "穆人清", "陈圆圆", "郭芙", "郭襄", "穆念慈", "东方不败", "梅超风", "林平之", "林远图", "灭绝师太", "段誉", "鸠摩智",
                "吴亦凡","王尼美","Kyana Poppy Downs","萧剑","王尼玛","都敏俊","迪丽热巴","福尔康","唐马儒","张全蛋","赫敏·格兰杰","夏紫薇","刘醒","非凡哥","教皇","金馆长","梁逸峰",
                "van样", "劳拉·金尼","郑九妹","赵冬妮","Ricardo Milos", "金星","嘿我的小可爱","比利·海灵顿","塘主张翰","伊藤诚","乌蝇哥","洛丽塔","小S","PaPi酱","亨伯特","罗柏儿子",
                "罗柏女儿","琼恩·雪诺","耶哥蕊特","艾莉亚·史塔克","珊莎·史塔克","布兰·史塔克","罗柏·史塔克","瑞肯·史塔克","乔佛里·拜拉席恩","弥赛菈·拜拉席恩","托曼·拜拉席恩",
                "波澜哥","高音哥", "姑姑女儿","姑姑儿子", "布兰登女","艾德·史塔克","维拉","凯瑟琳·徒利·史塔克","布兰登·史塔克","班扬·史塔克","莱安娜·史塔克","莱莎·徒利","艾德慕·徒利",
                "萝丝琳·佛雷","琼恩·艾林", "劳勃·拜拉席恩","瑟曦·兰尼斯特","詹姆·兰尼斯特","提利昂·兰尼斯特","维拉哥哥","维拉姐姐", "维拉妹妹","维拉妹夫","布兰登妻","瑞卡德·史塔克",
                "霍斯特·徒利","米妮莎·河安","泰温·兰尼斯特", "索非亚·罗丝·史泰龙","西斯廷·史泰龙","斯嘉丽·罗丝·史泰龙","维拉母亲","维拉父亲","小姨女儿", "小姨女婿","西尔维斯特·史泰龙",
                "詹妮弗·菲拉文","弗兰克·史泰龙"};
        contactList = (RecyclerView) mView.findViewById(R.id.contact_list);
        letterView = (LetterView) mView.findViewById(R.id.letter_view);
        layoutManager = new LinearLayoutManager(getContext());
        adapter = new ContactAdapter(getContext(), contactNames);
        adapter.setOnItemSelectedListener(new ContactAdapter.OnItemSelectedListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(),MeIntentActivity.class);
                startActivity(intent);
            }
        });
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

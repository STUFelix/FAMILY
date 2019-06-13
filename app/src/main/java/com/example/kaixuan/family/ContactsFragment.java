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

import java.util.ArrayList;
import java.util.List;

public class ContactsFragment extends Fragment {
    private View mView;
    private RecyclerView contactList;
    private String[] contactNames;
    private LinearLayoutManager layoutManager;
    private LetterView letterView;
    private ContactAdapter adapter;
    private List<String> imageViewURL;

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

        initURL();

        contactList = (RecyclerView) mView.findViewById(R.id.contact_list);
        letterView = (LetterView) mView.findViewById(R.id.letter_view);
        layoutManager = new LinearLayoutManager(getContext());
        adapter = new ContactAdapter(getContext(), contactNames,imageViewURL);
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

    private void initURL() {
        imageViewURL = new ArrayList<String>();
        for(int i=0;i<10;i++){
        imageViewURL.add("https://imgsa.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=1e90a9f32f34349b600b66d7a8837eab/7e3e6709c93d70cf007ae2e4ffdcd100bba12b52.jpg");
        imageViewURL.add("http://upload.ct.youth.cn/2016/0518/1463564738559.png");
        imageViewURL.add("http://a1.att.hudong.com/02/99/20300543116259143460994521443_s.jpg");
        imageViewURL.add("http://img003.21cnimg.com/photos/album/20141211/m600/7C0FDC909057A2795A2781CE0AEB2DAC.jpeg");
        imageViewURL.add("https://imgsa.baidu.com/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=512a8fa1546034a83defb0d3aa7a2231/cefc1e178a82b901829fc5937b8da9773912ef64.jpg");
        imageViewURL.add( "https://imgsa.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=bc341a50b2119313d34ef7e2045167b2/9345d688d43f87944bb59cbcd21b0ef41ad53ade.jpg");
        imageViewURL.add("https://imgsa.baidu.com/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=74efdf659f3df8dcb23087c3ac7819ee/9f510fb30f2442a7dd132c37d143ad4bd1130206.jpg");
        imageViewURL.add("https://imgsa.baidu.com/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=c7c8c204d033c895b2739029b07a1895/d8f9d72a6059252d1cc6a8973d9b033b5ab5b954.jpg");
        imageViewURL.add("http://img3.duitang.com/uploads/item/201607/12/20160712195039_cuwke.thumb.224_0.jpeg");
        imageViewURL.add("https://imgsa.baidu.com/baike/crop%3D0%2C26%2C600%2C395%3Bc0%3Dbaike80%2C5%2C5%2C80%2C26/sign=ca3c7bb1f0dcd100d9d3a2614fbb6b22/38dbb6fd5266d016f8df4f949f2bd40734fa354c.jpg");
        imageViewURL.add( "http://ztd00.photos.bdimg.com/ztd/w=350;q=70/sign=2692ce938013632715edc436a1b4d1d1/d009b3de9c82d158fbf20bfe8a0a19d8bc3e4208.jpg");
        imageViewURL.add("http://p1.pccoo.cn/bbs/20130701/201307011820469316.jpg");
        imageViewURL.add("http://i2.sinaimg.cn/ent/2011/0617/U4099P28DT20110617142147.jpg");
        imageViewURL.add("http://a4.att.hudong.com/71/34/01300542899749141759349066580.jpg");
        imageViewURL.add("http://a0.att.hudong.com/38/23/01300543669348145318234441231_s.jpg");
        imageViewURL.add("https://imgsa.baidu.com/baike/w%3D268/sign=f3e36c68c3cec3fd8b3ea073ee89d4b6/d043ad4bd11373f0c8f3fe3ba40f4bfbfaed0441.jpg");
        imageViewURL.add("https://imgsa.baidu.com/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=726ea6a7a86eddc432eabca958b2dd98/472309f7905298220e94b181d2ca7bcb0b46d4c9.jpg");
        imageViewURL.add("https://imgsa.baidu.com/baike/c0%3Dbaike72%2C5%2C5%2C72%2C24/sign=07d9444851da81cb5aeb8b9f330fbb73/0df3d7ca7bcb0a4658a827856b63f6246a60af80.jpg");
        imageViewURL.add("https://imgsa.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=0a5ea0b7cefc1e17e9b284632bf99d66/0dd7912397dda144e4c37b13b3b7d0a20df4864c.jpg");
        imageViewURL.add( "http://i2.hoopchina.com.cn/blogfile/201504/13/BbsImg142885460329459_415*337.jpg");
        imageViewURL.add( "http://imgsrc.baidu.com/forum/w%3D580/sign=0c685117be3eb13544c7b7b3961fa8cb/f55cbb18972bd407b998ae0279899e510eb3096e.jpg");
        imageViewURL.add( "https://imgsa.baidu.com/baike/c0%3Dbaike72%2C5%2C5%2C72%2C24/sign=e89629bd5c6034a83defb0d3aa7a2231/b8014a90f603738dd8c4ac9eb31bb051f919ec84.jpg");
        imageViewURL.add("https://imgsa.baidu.com/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=f9675d12845494ee932f074b4c9c8b9b/aec379310a55b319e17d5ed84ba98226cefc1792.jpg");
        imageViewURL.add("https://imgsa.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=a45185575c4e9258b2398ebcfdebba3d/8718367adab44aed8301d3d7b91c8701a18bfbb2.jpg");
        imageViewURL.add("http://c1.haibao.cn/img/600_0_100_0/1463119532.3054/a3a0d6272250a8481a5940a9fe7d9979.jpg");
        }
    }

}

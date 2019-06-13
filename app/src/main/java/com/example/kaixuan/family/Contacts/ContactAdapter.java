package com.example.kaixuan.family.Contacts;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kaixuan.family.ContactsFragment;
import com.example.kaixuan.family.GlideCircleTransform;
import com.example.kaixuan.family.R;
import com.example.kaixuan.family.Tree.TREEFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private String[] mContactNames; // 联系人名称字符串数组
    private List<String> mContactList; // 联系人名称List（转换成拼音）
    private List<Contact> resultList; // 最终结果（包含分组的字母）
    private List<String> characterList; // 字母List
    private List<String> imageViewURL;

    public enum ITEM_TYPE {
        ITEM_TYPE_CHARACTER,
        ITEM_TYPE_CONTACT
    }

    public ContactAdapter(Context context, String[] contactNames, List<String>  imageViewURL) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mContactNames = contactNames;
        this.imageViewURL =imageViewURL;
        handleContact();
    }

    private void handleContact() {
        mContactList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < mContactNames.length; i++) {
            String pinyin = Utils.getPingYin(mContactNames[i]);
            map.put(pinyin, mContactNames[i]);
            mContactList.add(pinyin);
        }
        Collections.sort(mContactList, new ContactComparator());

        resultList = new ArrayList<>();
        characterList = new ArrayList<>();

        for (int i = 0; i < mContactList.size(); i++) {
            String name = mContactList.get(i);
            String character = (name.charAt(0) + "").toUpperCase(Locale.ENGLISH);
            if (!characterList.contains(character)) {
                if (character.hashCode() >= "A".hashCode() && character.hashCode() <= "Z".hashCode()) { // 是字母
                    characterList.add(character);
                    resultList.add(new Contact(character, ITEM_TYPE.ITEM_TYPE_CHARACTER.ordinal()));
                } else {
                    if (!characterList.contains("#")) {
                        characterList.add("#");
                        resultList.add(new Contact("#", ITEM_TYPE.ITEM_TYPE_CHARACTER.ordinal()));
                    }
                }
            }

            resultList.add(new Contact(map.get(name), ITEM_TYPE.ITEM_TYPE_CONTACT.ordinal()));
        }
    }

    @Nullable
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_CHARACTER.ordinal()) {
            return new CharacterHolder(mLayoutInflater.inflate(R.layout.item_character, parent, false));
        } else {
            return new ContactHolder(mLayoutInflater.inflate(R.layout.item_contact, parent, false));
        }
    }

    private ContactAdapter.OnItemSelectedListener onItemSelectedListener;

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CharacterHolder) {
            ((CharacterHolder) holder).mTextView.setText(resultList.get(position).getmName());
        } else if (holder instanceof ContactHolder) {
            final ContactHolder contactHolder = (ContactHolder)holder;
            contactHolder.mTextView.setText(resultList.get(position).getmName());
            contactHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = contactHolder.getLayoutPosition();
                    onItemSelectedListener.onItemClick(contactHolder.itemView,pos);
                }
            });
            Glide.with(mContext)
                    .load(imageViewURL.get(position))
                    .centerCrop()
                    .placeholder(R.drawable.family_avatar)
                    .error(R.drawable.family_avatar)
                    .transform(new GlideCircleTransform(mContext))
                    .into(contactHolder.imageView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return resultList.get(position).getmType();
    }

    @Override
    public int getItemCount() {
        return resultList == null ? 0 : resultList.size();
    }

    public class CharacterHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        CharacterHolder(View view) {
            super(view);

            mTextView = (TextView) view.findViewById(R.id.character);
        }
    }

    public class ContactHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        ImageView imageView;
        ContactHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.contact_imageView);
            mTextView = (TextView) view.findViewById(R.id.contact_name);
        }
    }

    public int getScrollPosition(String character) {
        if (characterList.contains(character)) {
            for (int i = 0; i < resultList.size(); i++) {
                if (resultList.get(i).getmName().equals(character)) {
                    return i;
                }
            }
        }

        return -1; // -1不会滑动
    }

    public interface OnItemSelectedListener{
        void onItemClick(View view,int position);
    }

    public void setOnItemSelectedListener(ContactAdapter.OnItemSelectedListener listener){
        this.onItemSelectedListener = listener;
    }

}


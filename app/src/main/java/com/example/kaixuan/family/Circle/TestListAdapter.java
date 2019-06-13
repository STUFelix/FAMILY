package com.example.kaixuan.family.Circle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kaixuan.family.GlideCircleTransform;
import com.example.kaixuan.family.R;

import java.util.List;

public class TestListAdapter extends ExpandableTextAdapter<TestListAdapter.TestHolder> {

    private Context mContext;
    private List<String> imageViewURL;

    public TestListAdapter(Context context,List<String> imageViewURL) {
        mContext = context;
        this.imageViewURL = imageViewURL;
    }

    @Override
    public TestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TestHolder(LayoutInflater.from(mContext).inflate(R.layout.item_test_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final TestHolder holder, final int position) {
        super.onBindViewHolder(holder,position);
        Glide.with(mContext)
                .load(imageViewURL.get(position))
                .centerCrop()
                .placeholder(R.drawable.family_avatar)
                .error(R.drawable.family_avatar)
                .transform(new GlideCircleTransform(mContext))
                .into(holder.head);
        holder.name.setText(Util.getName(position));

    }

    @Override
    protected int getExpandableTextViewId() {
        return R.id.tv_content;
    }

    @Override
    protected int getTriggerViewId() {
        return R.id.tv_expand_or_collapse;
    }

    @Override
    protected int getMaxLineCount() {
        return 3;
    }

    @Override
    protected String getItemDataId(int position) {
        return String.valueOf(position);
    }

    @Override
    protected String getExpandText() {
        return "全文";
    }

    @Override
    protected String getCollapseText() {
        return "收起";
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class TestHolder extends RecyclerView.ViewHolder {

        public ImageView head;

        public TextView name;

        public TestHolder(View itemView) {
            super(itemView);
            head =  (ImageView) itemView.findViewById(R.id.tv_head);
            name = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}

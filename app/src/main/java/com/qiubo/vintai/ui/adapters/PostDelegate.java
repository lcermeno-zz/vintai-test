package com.qiubo.vintai.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qiubo.vintai.R;
import com.qiubo.vintai.Vintai;
import com.qiubo.vintai.ui.viewholders.PostViewHolder;
import com.qiubo.vintai.ui.viewmodels.BaseVM;
import com.qiubo.vintai.widgets.ViewPagerDelegate;

import java.util.List;

public class PostDelegate extends ViewPagerDelegate<BaseVM> {

    public static final int DELEGATE_TYPE = 200;

    @Override
    public void onBindViewHolder(BaseVM item, Object holder) {
        PostViewHolder viewHolder = (PostViewHolder) holder;
        viewHolder.setValues(item);
    }

    @Override
    public Object buildViewHolder(View itemView) {
        return new PostViewHolder(itemView);
    }

    @Override
    public int getDelegateType() {
        return DELEGATE_TYPE;
    }

    @Override
    public Object instantiateItem(List<BaseVM> items, ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) Vintai.getInstance().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.vp_post_image, container, false);
        container.addView(itemView);
        BaseVM vm = items.get(position);
        itemView.setTag(vm.getVmId());
        onCreateViewHolder(itemView, position, vm);
        return itemView;
    }
}

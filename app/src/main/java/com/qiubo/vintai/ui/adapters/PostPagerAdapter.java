package com.qiubo.vintai.ui.adapters;

import android.view.View;
import android.view.ViewGroup;

import com.qiubo.vintai.ui.viewmodels.BaseVM;
import com.qiubo.vintai.widgets.ViewPagerDelegateManager;

import java.util.List;

import androidx.viewpager.widget.PagerAdapter;

public class PostPagerAdapter extends PagerAdapter {

    private ViewPagerDelegateManager<BaseVM> mDelegateManager;
    private List<BaseVM> mItems;

    public PostPagerAdapter(List<BaseVM> items) {
        mItems = items;
        mDelegateManager = new ViewPagerDelegateManager<>();
        mDelegateManager.addDelegate(new PostDelegate());
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return mDelegateManager.instantiateItem(mItems, container, position);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        mDelegateManager.destroyViewHolder(position);
        container.removeView((View) object);
    }
}

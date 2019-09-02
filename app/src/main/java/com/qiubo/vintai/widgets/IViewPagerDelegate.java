package com.qiubo.vintai.widgets;

import android.view.ViewGroup;

interface IViewPagerDelegate<T> {

    int getDelegateType();

    Object instantiateItem(T items, ViewGroup container, int position);

    void destroyViewHolder(int position);
}

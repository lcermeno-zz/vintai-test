package com.qiubo.vintai.widgets;

import android.view.View;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.collection.SparseArrayCompat;

public abstract class ViewPagerDelegate<T> implements IViewPagerDelegate<List<T>> {

    protected SparseArrayCompat<Object> mViewHolders;

    public ViewPagerDelegate() {
        mViewHolders = new SparseArrayCompat<>();
    }

    @NonNull
    protected void onCreateViewHolder(View itemView, int position, T item) {
        Object viewHolder = mViewHolders.get(position);

        if (viewHolder == null) {
            viewHolder = buildViewHolder(itemView);
            mViewHolders.put(position, viewHolder);
        }
        onBindViewHolder(item, viewHolder);
    }

    public abstract void onBindViewHolder(T item, Object holder);

    public abstract Object buildViewHolder(View itemView);

    public void destroyViewHolder(int position) {
        mViewHolders.remove(position);
    }
}

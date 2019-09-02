package com.qiubo.vintai.widgets;

import android.view.ViewGroup;

import com.qiubo.vintai.ui.viewmodels.BaseVM;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.collection.SparseArrayCompat;

/**
 * Created by Lawrence Cermeño on 13/05/18.
 */
public class ViewPagerDelegateManager<T extends BaseVM> {

    private SparseArrayCompat<IViewPagerDelegate> mDelegateTypes;

    public ViewPagerDelegateManager() {
        mDelegateTypes = new SparseArrayCompat<>();
    }

    public void addDelegate(@NonNull IViewPagerDelegate<List<T>> delegate) {
        if (mDelegateTypes.get(delegate.getDelegateType()) == null) {
            mDelegateTypes.put(delegate.getDelegateType(), delegate);
        } else {
            throw new IllegalArgumentException("Delegate already " + delegate.getDelegateType() + " registered");
        }
    }

    public Object instantiateItem(@NonNull List<T> items, ViewGroup container, int position) {
        int delegateType = items.get(position).getType();
        IViewPagerDelegate delegate = mDelegateTypes.get(delegateType);
        if (delegate == null) {
            throw new NullPointerException(
                    "No AdapterDelegate added that matches position=" + position + " in data source");
        }
        return delegate.instantiateItem(items, container, position);
    }

    public void destroyViewHolder(int position) {
        if (mDelegateTypes != null && mDelegateTypes.size() > 0) {
            for (int i = 0; i < mDelegateTypes.size(); i++) {
                int key = mDelegateTypes.keyAt(i);
                IViewPagerDelegate delegate = mDelegateTypes.get(key);
                if (delegate != null) {
                    delegate.destroyViewHolder(position);
                }
            }
        }
    }
}

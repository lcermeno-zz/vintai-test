package com.qiubo.vintai.ui.viewholders;

import android.view.View;

import com.qiubo.vintai.R;
import com.qiubo.vintai.ui.viewmodels.BaseVM;

import androidx.appcompat.widget.AppCompatImageView;

public class PostViewHolder {

    private AppCompatImageView mImageView;

    public PostViewHolder(View itemView) {
        mImageView = itemView.findViewById(R.id.img_post);
    }

    public void setValues(BaseVM item) {
        mImageView.setImageResource(item.getResourceId());
    }
}

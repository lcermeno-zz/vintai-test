package com.qiubo.vintai.ui.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qiubo.vintai.R;
import com.qiubo.vintai.ui.adapters.PostDelegate;
import com.qiubo.vintai.ui.adapters.PostPagerAdapter;
import com.qiubo.vintai.ui.viewmodels.BaseVM;
import com.qiubo.vintai.widgets.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;


public class PostsFragment extends Fragment {


    public PostsFragment() {
        // Required empty public constructor
    }


    public static PostsFragment newInstance() {
        PostsFragment fragment = new PostsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<BaseVM> vms = new ArrayList<>();
        vms.add(new BaseVM(PostDelegate.DELEGATE_TYPE, 1, R.drawable.img_1));
        vms.add(new BaseVM(PostDelegate.DELEGATE_TYPE, 2, R.drawable.img_2));
        vms.add(new BaseVM(PostDelegate.DELEGATE_TYPE, 3, R.drawable.img_3));

        PostPagerAdapter adapter = new PostPagerAdapter(vms);
        VerticalViewPager verticalViewPager = view.findViewById(R.id.vp_posts);
        verticalViewPager.setAdapter(adapter);
    }
}

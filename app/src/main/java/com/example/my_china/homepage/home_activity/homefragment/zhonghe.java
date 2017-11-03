package com.example.my_china.homepage.home_activity.homefragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.my_china.R;
import com.example.my_china.homepage.home_activity.adapter.ZhongHeFragmentPagerAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Zhonghe extends Fragment {


    private TabLayout tab_ZhongHe;
    private ViewPager pager_ZhongHe;
    private ArrayList<String> title;
    private ArrayList<Fragment> fragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_zhonghe, container, false);
        initView(inflate);
        initAddPagerTitle();
        initSetAdapter();
        return inflate;
    }

    private void initSetAdapter() {
        ZhongHeFragmentPagerAdapter adapter=new ZhongHeFragmentPagerAdapter(getActivity().getSupportFragmentManager(),title,fragment);
        pager_ZhongHe.setOffscreenPageLimit(1);
        pager_ZhongHe.setAdapter(adapter);
        tab_ZhongHe.setupWithViewPager(pager_ZhongHe);
    }

    private void initAddPagerTitle() {
        title = new ArrayList<>();
        title.add("开源咨询");
        title.add("推荐博客");
        title.add("技术问答");
        title.add("每日一搏");

        fragment = new ArrayList<>();

        fragment.add(new Zhonghe_Copy("action/api/news_list"));
        fragment.add(new Zhonghe_Copy("action/api/blog_list"));
        fragment.add(new Zhonghe_Copy("action/api/tweet_list"));
        fragment.add(new Zhonghe_Copy("action/api/blog_list"));


    }

    private void initView(View inflate) {
        tab_ZhongHe = (TabLayout) inflate.findViewById(R.id.tab_ZhongHe);
        pager_ZhongHe = (ViewPager) inflate.findViewById(R.id.pager_ZhongHe);
    }

}

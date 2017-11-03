package com.example.my_china.homepage.home_activity.homefragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.my_china.R;
import com.example.my_china.homepage.home_activity.activity.ZhongHe_Details;
import com.example.my_china.homepage.home_activity.adapter.ZhongHeFragmentBoKeListAdapter;
import com.example.my_china.homepage.home_activity.adapter.ZhongHeFragmentDongTaiListAdapter;
import com.example.my_china.homepage.home_activity.adapter.ZhongHeFragmentZiXuListAdapter;
import com.example.my_china.homepage.home_activity.bean.ZhengHe_DongTai;
import com.example.my_china.homepage.home_activity.bean.ZhongHe_BoKe;
import com.example.my_china.homepage.home_activity.bean.ZhongHe_ZiXun;
import com.example.my_china.homepage.home_activity.presenter.Presenter;
import com.example.my_china.homepage.home_activity.presenter.PresenterInt;
import com.example.my_china.homepage.home_activity.view.ViewInf;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Zhonghe_Copy extends Fragment implements ViewInf {


    private String url;
    private ListView lv;
    private Handler handler = new Handler();
    int x = 0;
    int y=0;
    private List list;
    private View view;
    private PresenterInt presenter;
    List<ZhongHe_ZiXun.NewsBean> newslists;
    List<ZhongHe_BoKe.BlogBean>  newsBoke;
    List<ZhengHe_DongTai.TweetBean>   newsdongtai;
    private SmartRefreshLayout refreshLayout;
    private ZhongHeFragmentBoKeListAdapter adapter;
    private ZhongHeFragmentZiXuListAdapter adapter1;
    private ZhongHeFragmentDongTaiListAdapter adapter2;

    public Zhonghe_Copy(String url) {
        this.url = url;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_zhonghe__copy, container, false);
        list = new ArrayList<>();
        list.add(R.mipmap.haha);
        list.add(R.mipmap.hehe);
        list.add(R.mipmap.meme);
        list.add(R.mipmap.x);
        list.add(R.mipmap.y);
        list.add(R.mipmap.z);

        initView(inflate);

        if (url.equals("action/api/news_list")) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.imagepager, null);
            Banner banner = (Banner) view.findViewById(R.id.banner);
            banner.setIndicatorGravity(BannerConfig.RIGHT);
            //设置图片加载器
            banner.setImageLoader(new GlideImageLoader());
            //设置图片集合
            banner.setImages(list);
            //banner设置方法全部调用完毕时最后调用
            banner.start();

        }
        return inflate;
    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //调用p层
            presenter = new Presenter(Zhonghe_Copy.this);
            presenter.getUtl(url);
        }
    };

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            newsdongtai=new ArrayList<>();
            newsBoke=new ArrayList<>();
            newslists=new ArrayList<>();
            handler.postDelayed(runnable, 500);
        } else {
            x = 1;
            handler.removeCallbacks(runnable);
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onResume() {
        x = 0;
        super.onResume();
    }

    @Override
    public void getMyBean(ZhongHe_ZiXun zhongHe_ziXun, ZhongHe_BoKe zhongHe_boKe, ZhengHe_DongTai zhongHe_dongtai) {

        if (zhongHe_ziXun != null) {
            List<ZhongHe_ZiXun.NewsBean> newslist = zhongHe_ziXun.getNewslist();
            if(newslists.size()==0){
                Log.i("login","xxxx");
                newslists.addAll(newslist);
                adapter1 = new ZhongHeFragmentZiXuListAdapter(newslists, getActivity());
                lv.setAdapter(adapter1);
            }else{
                Log.i("login","vvvvvv");
                newslists.addAll(newslist);
                adapter1.notifyDataSetChanged();
            }
            if (x == 0 && lv.getHeaderViewsCount() == 0) {
                newslists.addAll(newslist);
                adapter1 = new ZhongHeFragmentZiXuListAdapter(newslists, getActivity());
                lv.setAdapter(adapter1);
                lv.addHeaderView(view);
            }


            //上下拉取
//
            refreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshlayout) {
                    PresenterInt presenter = new Presenter(Zhonghe_Copy.this);
                    refreshlayout.finishRefresh(2000);
                    newslists.clear();
                    presenter.getUtl(url);
                }
            });
//
            refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
                @Override
                public void onLoadmore(RefreshLayout refreshlayout) {
                    PresenterInt presenter = new Presenter(Zhonghe_Copy.this);
                    int i = y++;
                    presenter.getUtl(url+"?pageIndex=\n"+i+"");
                    refreshlayout.finishLoadmore(2000);
                }
            });


            //lv点击跳转
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), ZhongHe_Details.class);
                    intent.putExtra("webView", "https://www.oschina.net/news/90190/gitlab-10-dot-1-dot-1-released");
                    startActivity(intent);
                }
            });

        } else if (zhongHe_boKe != null) {
            final List<ZhongHe_BoKe.BlogBean> blogs = zhongHe_boKe.getBlogs();
            if(newsBoke.size()==0){
                newsBoke.addAll(blogs);
                adapter = new ZhongHeFragmentBoKeListAdapter(newsBoke, getActivity());
                lv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }else{
                newsBoke.addAll(blogs);
                adapter.notifyDataSetChanged();
            }


            //上下拉取
//
            refreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshlayout) {
                    PresenterInt presenter = new Presenter(Zhonghe_Copy.this);
                    refreshlayout.finishRefresh(2000);
                    newsBoke.clear();
                    presenter.getUtl(url);
                }
            });
//
            refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
                @Override
                public void onLoadmore(RefreshLayout refreshlayout) {
                    PresenterInt presenter = new Presenter(Zhonghe_Copy.this);
                    int i = y++;
                    presenter.getUtl(url+"?pageIndex=\n"+i+"");
                    refreshlayout.finishLoadmore(2000);
                }
            });


            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), ZhongHe_Details.class);
                    intent.putExtra("webView", blogs.get(position).getUrl());
                    startActivity(intent);
                }
            });
        } else if (zhongHe_dongtai != null) {
            List<ZhengHe_DongTai.TweetBean> tweets = zhongHe_dongtai.getTweets();
            if(newsdongtai.size()==0){
                newsdongtai.addAll(tweets);
                adapter2 = new ZhongHeFragmentDongTaiListAdapter(newsdongtai, getActivity());
                lv.setAdapter(adapter2);
                adapter2.notifyDataSetChanged();
            }else{
                newsdongtai.addAll(tweets);
                adapter2.notifyDataSetChanged();
            }
            refreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshlayout) {
                    PresenterInt presenter = new Presenter(Zhonghe_Copy.this);
                    refreshlayout.finishRefresh(2000);
                    newsdongtai.clear();
                    presenter.getUtl(url);
                }
            });
//
            refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
                @Override
                public void onLoadmore(RefreshLayout refreshlayout) {
                    PresenterInt presenter = new Presenter(Zhonghe_Copy.this);
                    int i = y++;
                    presenter.getUtl(url+"?pageIndex=\n"+i+"");
                    refreshlayout.finishLoadmore(2000);
                }
            });
        }
    }

    private void initView(View inflate) {
        refreshLayout = (SmartRefreshLayout) inflate.findViewById(R.id.refreshLayout);
        lv= (ListView) inflate.findViewById(R.id.lv);
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}

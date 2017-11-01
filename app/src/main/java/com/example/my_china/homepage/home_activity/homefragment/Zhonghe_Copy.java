package com.example.my_china.homepage.home_activity.homefragment;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.my_china.R;
import com.example.my_china.homepage.home_activity.adapter.ZhongHeFragmentZiXuListAdapter;
import com.example.my_china.homepage.home_activity.bean.ZhongHe_BoKe;
import com.example.my_china.homepage.home_activity.bean.ZhongHe_ZiXun;
import com.example.my_china.homepage.home_activity.presenter.Presenter;
import com.example.my_china.homepage.home_activity.presenter.PresenterInt;
import com.example.my_china.homepage.home_activity.view.ViewInf;
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
    private TextView text;
    private ListView lv;
    private Handler handler=new Handler();
    private View inflate;
    int x=0;

    public Zhonghe_Copy(String url) {
        this.url = url;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_zhonghe__copy, container, false);
        initView(inflate);
        return inflate;
    }


    private void initView(View inflate) {
        text = (TextView) inflate.findViewById(R.id.text);
        lv = (ListView) inflate.findViewById(R.id.lv);
    }

    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            //调用p层
            PresenterInt presenter=new Presenter(Zhonghe_Copy.this);
            presenter.getUtl(url);
        }
    };
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if(isVisibleToUser){
            handler.postDelayed(runnable,500);
        }else{
            handler.removeCallbacks(runnable);
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void getMyBean(ZhongHe_ZiXun zhongHe_ziXun, ZhongHe_BoKe zhongHe_boKe) {
        List list=new ArrayList<>();
        list.add(R.mipmap.haha);
        list.add(R.mipmap.hehe);
        list.add(R.mipmap.meme);
        list.add(R.mipmap.x);
        list.add(R.mipmap.y);
        list.add(R.mipmap.z);

        if(zhongHe_ziXun!=null){

            List<ZhongHe_ZiXun.NewsBean> newslist = zhongHe_ziXun.getNewslist();
            ZhongHeFragmentZiXuListAdapter adapter=new ZhongHeFragmentZiXuListAdapter(newslist,getActivity());
            lv.setAdapter(adapter);
            inflate = LayoutInflater.from(getActivity()).inflate(R.layout.imagepager, null);
            Banner banner = (Banner) inflate.findViewById(R.id.banner);
            banner.setIndicatorGravity(BannerConfig.RIGHT);
            //设置图片加载器
            banner.setImageLoader(new GlideImageLoader());
            //设置图片集合
            banner.setImages(list);
            //banner设置方法全部调用完毕时最后调用
            banner.start();
            if(x==0){
                lv.addHeaderView(inflate);
                x=1;
            }
//            text.setText("第一个"+zhongHe_ziXun.getNewslist().get(0).getAuthor());
//            Toast.makeText(getActivity(), "diyige", Toast.LENGTH_SHORT).show();

        }else{
            x=0;
            lv.removeFooterView(inflate);
            List<ZhongHe_BoKe.BlogBean> blogs = zhongHe_boKe.getBlogs();
            text.setText("第二个"+zhongHe_boKe.getBlogs().get(0).getAuthorname());
            Toast.makeText(getActivity(), "dierge", Toast.LENGTH_SHORT).show();
        }
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);
        }
    }
}

package com.example.my_china.homepage.home_activity.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.my_china.R;
import com.example.my_china.homepage.home_activity.bean.ZhengHe_DongTai;

import java.util.List;

/**
 * Created by 吴肖光 on 2017/11/2.
 */
public class ZhongHeFragmentDongTaiListAdapter extends BaseAdapter {

    List<ZhengHe_DongTai.TweetBean> list;
    Context context;

    public ZhongHeFragmentDongTaiListAdapter(List<ZhengHe_DongTai.TweetBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.gonttaiitem, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.DongTai_Appclient.setText(list.get(position).getAppclient());
        viewHolder.DongTai_Author.setText(list.get(position).getAuthor());
        viewHolder.DongTai_Body.setText(list.get(position).getBody());
        viewHolder.DongTai_AuthorCopy.setHint(list.get(position).getAuthor());
        viewHolder.DongTai_PubDate.setText(list.get(position).getPubDate());
        Log.i("login", list.get(position).getPortrait());
        Glide.with(context).load(list.get(position).getImgSmall()).into(viewHolder.DongTai_Body_Image);
        Glide.with(context).load(list.get(position).getPortrait()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(viewHolder.DongTai_Portrait);

        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public ImageView DongTai_Portrait;
        public TextView DongTai_Author;
        public TextView DongTai_Body;
        public ImageView DongTai_Body_Image;
        public TextView DongTai_AuthorCopy;
        public TextView DongTai_PubDate;
        public TextView DongTai_Appclient;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.DongTai_Portrait = (ImageView) rootView.findViewById(R.id.DongTai_Portrait);
            this.DongTai_Author = (TextView) rootView.findViewById(R.id.DongTai_Author);
            this.DongTai_Body = (TextView) rootView.findViewById(R.id.DongTai_Body);
            this.DongTai_Body_Image = (ImageView) rootView.findViewById(R.id.DongTai_Body_Image);
            this.DongTai_AuthorCopy = (TextView) rootView.findViewById(R.id.DongTai_AuthorCopy);
            this.DongTai_PubDate = (TextView) rootView.findViewById(R.id.DongTai_PubDate);
            this.DongTai_Appclient = (TextView) rootView.findViewById(R.id.DongTai_Appclient);
        }

    }
}

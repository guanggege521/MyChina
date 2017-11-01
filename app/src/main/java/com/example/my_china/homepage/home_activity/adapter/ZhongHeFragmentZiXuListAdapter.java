package com.example.my_china.homepage.home_activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.my_china.R;
import com.example.my_china.homepage.home_activity.bean.ZhongHe_ZiXun;

import java.util.List;

/**
 * Created by 吴肖光 on 2017/11/1.
 */
public class ZhongHeFragmentZiXuListAdapter extends BaseAdapter {

    List<ZhongHe_ZiXun.NewsBean> list;
    Context context;

    public ZhongHeFragmentZiXuListAdapter(List<ZhongHe_ZiXun.NewsBean> list, Context context) {
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

        ViewHolder holder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.zixunitem, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.ZiXunTitle.setText(list.get(position).getTitle());
        holder.ZiXunBody.setText(list.get(position).getBody());
        holder.ZiXunAuthor.setText(list.get(position).getAuthor());
        holder.ZiXunPubDate.setText(list.get(position).getPubDate());
        holder.ZiXunCommentCount.setText(list.get(position).getCommentCount());

        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView ZiXunTitle;
        public TextView ZiXunBody;
        public TextView ZiXunAuthor;
        public TextView ZiXunPubDate;
        public TextView ZiXunCommentCount;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.ZiXunTitle = (TextView) rootView.findViewById(R.id.ZiXunTitle);
            this.ZiXunBody = (TextView) rootView.findViewById(R.id.ZiXunBody);
            this.ZiXunAuthor = (TextView) rootView.findViewById(R.id.ZiXunAuthor);
            this.ZiXunPubDate = (TextView) rootView.findViewById(R.id.ZiXunPubDate);
            this.ZiXunCommentCount = (TextView) rootView.findViewById(R.id.ZiXunCommentCount);
        }

    }
}

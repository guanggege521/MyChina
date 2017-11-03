package com.example.my_china.homepage.home_activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.my_china.R;
import com.example.my_china.homepage.home_activity.bean.ZhongHe_BoKe;

import java.util.List;

/**
 * Created by 吴肖光 on 2017/11/1.
 */
public class ZhongHeFragmentBoKeListAdapter extends BaseAdapter {
    List<ZhongHe_BoKe.BlogBean> list;
    Context context;

    public ZhongHeFragmentBoKeListAdapter(List<ZhongHe_BoKe.BlogBean> list, Context context) {
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

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.bokeitem, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.BoKeBody.setText(list.get(position).getBody());
        holder.BoKeTitle.setText(list.get(position).getTitle());
        holder.BoKeCommentCount.setText(list.get(position).getCommentCount());
        holder.BoKeAuthor.setText(list.get(position).getAuthorname());
        holder.BoKePubDate.setText(list.get(position).getPubDate());

        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView BoKeTitle;
        public TextView BoKeBody;
        public TextView BoKeAuthor;
        public TextView BoKePubDate;
        public TextView BoKeCommentCount;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.BoKeTitle = (TextView) rootView.findViewById(R.id.BoKeTitle);
            this.BoKeBody = (TextView) rootView.findViewById(R.id.BoKeBody);
            this.BoKeAuthor = (TextView) rootView.findViewById(R.id.BoKeAuthor);
            this.BoKePubDate = (TextView) rootView.findViewById(R.id.BoKePubDate);
            this.BoKeCommentCount = (TextView) rootView.findViewById(R.id.BoKeCommentCount);
        }

    }
}

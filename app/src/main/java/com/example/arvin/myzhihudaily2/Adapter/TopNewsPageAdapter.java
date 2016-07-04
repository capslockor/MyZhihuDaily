package com.example.arvin.myzhihudaily2.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arvin.myzhihudaily2.Models.News;
import com.example.arvin.myzhihudaily2.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by arvin on 16-7-4.
 */
public class TopNewsPageAdapter extends PagerAdapter {
    List<News> list;
    LayoutInflater myInflater;
    View view;
    public TopNewsPageAdapter (LayoutInflater inflater, List<News> list)
    {
        this.myInflater = inflater;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        view = myInflater.inflate(R.layout.top_news,null);
        TextView text = (TextView) view.findViewById(R.id.top_news_title);
        SimpleDraweeView image = (SimpleDraweeView) view.findViewById(R.id.top_news_image);
        text.setText(list.get(position).getTitle());
        image.setImageURI(list.get(position).getImage());
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

}

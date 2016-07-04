package com.example.arvin.myzhihudaily2.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.arvin.myzhihudaily2.Models.News;
import com.example.arvin.myzhihudaily2.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by arvin on 16-6-23.
 */
public class NewsListAdapter extends ArrayAdapter<News> {
    private int ResourceId ;

    public NewsListAdapter(Context context, int resource){
        super(context, resource);
        ResourceId = resource;
    }
    public NewsListAdapter(Context context, int resource, List<News> objects) {
        super(context, resource, objects);
        ResourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News news = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(ResourceId,null);

        TextView textView = (TextView) view.findViewById(R.id.news_title);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.news_image);
        textView.setText(news.getTitle());
        if ( news.getImage()!="") {
            Uri imageUri = Uri.parse(news.getImage());
            simpleDraweeView.setImageURI(imageUri);

        }else
        {
            simpleDraweeView.setVisibility(View.GONE);
        }
        return view;
    }

    public void reFreshNewsList(List<News> newsList){
        clear();
        addAll(newsList);
        notifyDataSetChanged();
    }

}

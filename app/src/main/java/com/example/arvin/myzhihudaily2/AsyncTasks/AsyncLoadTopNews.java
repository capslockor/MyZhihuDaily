package com.example.arvin.myzhihudaily2.AsyncTasks;

import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;

import com.example.arvin.myzhihudaily2.Adapter.TopNewsPageAdapter;
import com.example.arvin.myzhihudaily2.Models.News;
import com.example.arvin.myzhihudaily2.NetWork.Http;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by arvin on 16-7-4.
 */
public class AsyncLoadTopNews extends AsyncTask<Void,Void,List<News>> {
    private TopNewsPageAdapter adapter;
    private LayoutInflater inflater;
    private  ViewPager myPager;
    private CircleIndicator indicator;

    public AsyncLoadTopNews(LayoutInflater inflater, ViewPager myPager, CircleIndicator indicator)
    {
        super();
        this.inflater = inflater;
        this.myPager = myPager;
        this.indicator = indicator;
    }
    @Override
    protected List<News> doInBackground(Void... params) {
        List<News> newsList = null;
        newsList = new Http().getTopNews();

        return newsList;
    }

    @Override
    protected void onPostExecute(List<News> newses) {
        adapter = new TopNewsPageAdapter(inflater,newses);
        myPager.setAdapter(adapter);
        indicator.setViewPager(myPager);
        adapter.registerDataSetObserver(indicator.getDataSetObserver());
    }
}

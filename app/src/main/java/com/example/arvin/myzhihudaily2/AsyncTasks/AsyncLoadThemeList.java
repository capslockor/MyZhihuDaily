package com.example.arvin.myzhihudaily2.AsyncTasks;

import android.os.AsyncTask;

import com.example.arvin.myzhihudaily2.Adapter.NewsListAdapter;
import com.example.arvin.myzhihudaily2.Models.News;
import com.example.arvin.myzhihudaily2.NetWork.Http;
import java.util.List;

/**
 * Created by arvin on 16-6-23.
 */
public class AsyncLoadThemeList extends AsyncTask<Void,Void,List<News>> {

    private NewsListAdapter adapter;
    private int id;

    public AsyncLoadThemeList(NewsListAdapter adapter,int id){
        super();
        this.adapter = adapter;
        this.id = id;

    }
    @Override
    protected List<News> doInBackground(Void... params) {
        List<News> newsList = null;

        newsList = new Http().getThemeNews(id);

        return newsList;
    }

    @Override
    protected void onPostExecute(List<News> newses) {
        adapter.reFreshNewsList(newses);
    }

}
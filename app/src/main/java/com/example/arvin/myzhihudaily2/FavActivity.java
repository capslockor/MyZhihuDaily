package com.example.arvin.myzhihudaily2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.arvin.myzhihudaily2.Adapter.NewsListAdapter;
import com.example.arvin.myzhihudaily2.Db.DailyNewsDB;
import com.example.arvin.myzhihudaily2.Models.News;

import java.util.List;

/**
 * Created by arvin on 16-6-26.
 */
public class FavActivity extends AppCompatActivity {
    private NewsListAdapter adapter;
    private List<News> newsList;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        newsList = DailyNewsDB.getInstance(FavActivity.this).loadFavourite();
        listView = (ListView) findViewById(R.id.fav_list);
        adapter = new NewsListAdapter(FavActivity.this,R.layout.list_view,newsList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News news = adapter.getItem(position);
                Intent intent = new Intent(FavActivity.this, WebActivity.class);
                intent.putExtra("id",  news.getId());
                intent.putExtra("title",news.getTitle());
                intent.putExtra("image",news.getImage());
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

    }

}

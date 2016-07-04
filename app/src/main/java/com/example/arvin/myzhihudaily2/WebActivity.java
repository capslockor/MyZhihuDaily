package com.example.arvin.myzhihudaily2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.example.arvin.myzhihudaily2.AsyncTasks.AsyncLoadNewsDetail;
import com.example.arvin.myzhihudaily2.Db.DailyNewsDB;

/**
 * Created by arvin on 16-6-24.
 */
public class WebActivity extends AppCompatActivity {

    private WebView webView;
    private int newsId;
    private String newsTitle = "";
    private String newsImage = "";
    private boolean isFavourite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        newsId = intent.getIntExtra("id",0);
        newsTitle = intent.getStringExtra("title");
        newsImage = intent.getStringExtra("image");
        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);
        new AsyncLoadNewsDetail(webView).execute(newsId);
        isFavourite = DailyNewsDB.getInstance(WebActivity.this).isFavourite(newsId);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.left_in,R.anim.right_out);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.web,menu);
        if (isFavourite)
        {
            menu.findItem(R.id.action_favour).setIcon(R.drawable.ic_isfavourite);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_in,R.anim.right_out);
                return true;
            case R.id.action_favour:
                if (!isFavourite)
                {
                    DailyNewsDB.getInstance(WebActivity.this).saveFavourite(newsId,newsTitle,newsImage);

                    item.setIcon(R.drawable.ic_isfavourite);
                    isFavourite = true;
                }else {
                    DailyNewsDB.getInstance(WebActivity.this).deleteFavourite(newsId);
                    item.setIcon(R.drawable.ic_notfavourite);
                    isFavourite = false;
                }

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

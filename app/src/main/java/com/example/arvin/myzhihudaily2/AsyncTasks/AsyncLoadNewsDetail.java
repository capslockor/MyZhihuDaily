package com.example.arvin.myzhihudaily2.AsyncTasks;

import android.os.AsyncTask;
import android.webkit.WebView;

import com.example.arvin.myzhihudaily2.Models.NewsDetail;
import com.example.arvin.myzhihudaily2.NetWork.Http;

/**
 * Created by arvin on 16-6-24.
 */
public class AsyncLoadNewsDetail extends AsyncTask<Integer,Void,NewsDetail> {

    private WebView webView;

    public  AsyncLoadNewsDetail(WebView webView)
    {
        this.webView = webView;
    }

    @Override
    protected NewsDetail doInBackground(Integer... params) {
        NewsDetail newsDetail = null;
        newsDetail = new Http().getNewsDetail(params[0]);
        return newsDetail;
    }

    @Override
    protected void onPostExecute(NewsDetail newsDetail) {
        String headerImage;
        if (newsDetail.getImage() == null || newsDetail.getImage() == "")
        {
            headerImage = "file:///android_asset/news_detail_header_image.jpg";
        }else {
            headerImage = newsDetail.getImage();
        }
        StringBuilder myweb = new StringBuilder();
        myweb.append("<div class=\"img-wrap\">")
                .append("<h1 class=\"headline-title\">")
                .append(newsDetail.getTitle()).append("</h1>")
                .append("<span class=\"img-source\">")
                .append(newsDetail.getImagesource()).append("</span>")
                .append("<img src=\"").append(headerImage)
                .append("\" alt=\"\">")
                .append("<div class=\"img-mask\"></div>");

        String mNewsContent = "<link rel=\"stylesheet\" type=\"text/css\" href=\"news_content_style.css\"/>"
                + "<link rel=\"stylesheet\" type=\"text/css\" href=\"news_header_style.css\"/>"
                + newsDetail.getBody().replace("<div class=\"img-place-holder\">", myweb.toString());
        webView.loadDataWithBaseURL("file:///android_asset/",mNewsContent,"text/html","UTF-8",null);



    }
}

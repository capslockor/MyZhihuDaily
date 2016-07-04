package com.example.arvin.myzhihudaily2.NetWork;

import com.example.arvin.myzhihudaily2.Models.News;
import com.example.arvin.myzhihudaily2.Models.NewsDetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by arvin on 16-6-23.
 */
public class Http {
    private OkHttpClient myHttpClient = new OkHttpClient();
    private final String apiLatestNews = "http://news-at.zhihu.com/api/4/news/latest";
    private final String apiNewsDetail = "http://news-at.zhihu.com/api/4/news/";
    private final String apiThemeList = "http://news-at.zhihu.com/api/4/themes";
    private final String apiThemeKind = "http://news-at.zhihu.com/api/4/theme/";



    public List<News> getLatestNews(){
        final List<News> newsList = new ArrayList<News>();
        Request request = new Request.Builder()
                .url(apiLatestNews)
                .build();

        try {
            Response response = myHttpClient.newCall(request).execute();
            JSONObject newContent = new JSONObject(response.body().string());
            JSONArray newsArray = newContent.getJSONArray("stories");

            for (int i = 0; i < newsArray.length(); i++) {
                String image="";
                JSONObject newsInJson = newsArray.getJSONObject(i);
                int id = newsInJson.getInt("id");
                String title = newsInJson.getString("title");
                if (newsInJson.has("images"))
                {
                    image = (String) newsInJson.getJSONArray("images").get(0);
                } else{
                    System.out.println("No Image");
                }
                News news = new News(id, title, image);
                newsList.add(news);

            }
        }catch (IOException e)
        {
        }catch (JSONException e)
        {

        }
        return newsList;
    }
    public List<News> getTopNews(){
        List<News> topNews = new ArrayList<News>();
        Request request = new Request.Builder()
                .url(apiLatestNews)
                .build();
        try{
            Response response = myHttpClient.newCall(request).execute();
            JSONObject topNewsContent = new JSONObject(response.body().string());
            JSONArray topNewsArray = topNewsContent.getJSONArray("top_stories");
            for (int i = 0 ; i < topNewsArray.length(); i++)
            {
                String image="";
                JSONObject topNewsInJson = (JSONObject) topNewsArray.get(i);
                int id = topNewsInJson.getInt("id");
                String title = topNewsInJson.getString("title");
                image = topNewsInJson.getString("image");
                News news = new News(id,title,image);
                topNews.add(news);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return topNews;
    }

    public List<News> getThemeNews(int themeId){
        final List<News> themeList = new ArrayList<News>();
        News news;
        Request request =new  Request.Builder()
                .url(apiThemeKind + themeId)
                .build();

        try {
            Response response = myHttpClient.newCall(request).execute();
            JSONObject newContent = new JSONObject(response.body().string());
            JSONArray newsArray = newContent.getJSONArray("stories");

            for (int i = 0; i < newsArray.length(); i++) {
                String image = "";
                JSONObject newsInJson = newsArray.getJSONObject(i);
                int id = newsInJson.getInt("id");
                String title = newsInJson.getString("title");
                if (newsInJson.has("images"))
                {
                    image = (String) newsInJson.getJSONArray("images").get(0);
                }
                news = new News(id,title,image);
                themeList.add(news);

            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        return themeList;
    }
    public NewsDetail getNewsDetail(int newsId){

        final Request request = new Request.Builder()
                .url(apiNewsDetail + newsId)
                .build();

        try {
            String image = "";
            String image_source = "";
            String body = "";
            Response response = myHttpClient.newCall(request).execute();
            JSONObject newContent = new JSONObject(response.body().string());
            if (newContent.has("body")) {
                body = newContent.getString("body");
            }
            if (newContent.has("image_source"))
            {
                image_source = newContent.getString("image_source");
            }
            String title = newContent.getString("title");
            if (newContent.has("image")) {
                image = newContent.getString("image");
            }
            String share_url = newContent.getString("share_url");
            int type = newContent.getInt("type");
            int id = newContent.getInt("id");
            String css = (String) newContent.getJSONArray("css").get(0);
            NewsDetail newsDetail = new NewsDetail(id,type,title,image,body,image_source,share_url,css);
            return newsDetail;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}

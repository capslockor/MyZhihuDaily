package com.example.arvin.myzhihudaily2.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.arvin.myzhihudaily2.Models.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arvin on 16-7-4.
 */
public class DailyNewsDB {
    private SaveDataBase newsDateBase;
    private SQLiteDatabase db;
    private static DailyNewsDB mDailyNewsDB;

    private DailyNewsDB(Context context){
        newsDateBase = new SaveDataBase(context);
        db = newsDateBase.getWritableDatabase();
    }

    public synchronized static DailyNewsDB getInstance(Context context)
    {
        if (mDailyNewsDB == null)
        {
            mDailyNewsDB = new DailyNewsDB(context);
        }
        return mDailyNewsDB;
    }

    public void saveFavourite(int newsId, String newsTitle, String newsImage)
    {

        ContentValues contentValues = new ContentValues();
        contentValues.put(newsDateBase.COLUMN_NEWS_ID, newsId);
        contentValues.put(newsDateBase.COLUMN_NEWS_TITLE, newsTitle);
        contentValues.put(newsDateBase.COLUMN_NEWS_IMAGE, newsImage);
        db.insert(newsDateBase.TABLE_NAME,null,contentValues);

    }

    public List<News> loadFavourite()
    {
        List<News> listFavourite = new ArrayList<>();
        Cursor cursor = db.query(newsDateBase.TABLE_NAME,null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                News news = new News(cursor.getInt(1), cursor.getString(2), cursor.getString(3));
                listFavourite.add(news);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return listFavourite;
    }

    public boolean isFavourite(int newsId)
    {
        Cursor cursor = db.query(newsDateBase.TABLE_NAME, null, newsDateBase.COLUMN_NEWS_ID + " = ?", new String[]{newsId + ""}, null, null, null);
        if (cursor.moveToNext())
        {
            cursor.close();
            return true;
        }else {
            return false;
        }

    }

    public void deleteFavourite(int newsId)
    {

        db.delete(newsDateBase.TABLE_NAME, newsDateBase.COLUMN_NEWS_ID + " = ?",new String[]{newsId + ""});

    }

    public synchronized void closeDB(){
        if (mDailyNewsDB != null)
        {
            db.close();
        }
    }
}

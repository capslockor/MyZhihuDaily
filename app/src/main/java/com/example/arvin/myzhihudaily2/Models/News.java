package com.example.arvin.myzhihudaily2.Models;

/**
 * Created by arvin on 16-6-23.
 */
public class News {
    private int id;
    private String title;
    private String image;

    public News(int id, String title, String image)
    {
        this.id = id;
        this.title = title;
        this.image = image;
    }
    public News(int id, String title)
    {
        this.id = id;
        this.title = title;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }
}

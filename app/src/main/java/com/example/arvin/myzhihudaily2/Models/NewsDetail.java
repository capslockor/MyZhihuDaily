package com.example.arvin.myzhihudaily2.Models;

/**
 * Created by arvin on 16-6-23.
 */
public class NewsDetail {
    private String body;
    private String imagesource;
    private String title;
    private String image;
    private String share_url;
    private int type;
    private int id;

    private String css;

    public NewsDetail(int id, int type, String title, String image, String body, String imagesource,String share_url , String css){
        this.id = id;
        this.type = type;
        this.title = title;
        this.image = image;
        this.body = body;
        this.imagesource = imagesource;
        this.share_url = share_url;
        this.css = css;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setImagesource(String imagesource) {
        this.imagesource = imagesource;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public void setCss(String css) {
        this.css = css;
    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getBody() {
        return body;
    }

    public String getImagesource() {
        return imagesource;
    }

    public String getShare_url() {
        return share_url;
    }

    public String getCss() {
        return css;
    }
}

package com.ctwl.lzq.howmuchanimation.Model.Bean;

import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.AVObject;

import java.util.List;
import java.util.Map;

/**
 * Created by B41-80 on 2016/6/28.
 */
public class News {
    String channelId;
    String channelName;
    String desc;
    List<ImageUrls> imageurls;
    String link;
    String pubDate;
    String source;
    String title;
    int imagesNumber;


    public int getImagesNumber() {
            return imageurls.size();
    }

    public void setImagesNumber(int imagesNumber) {
        this.imagesNumber = imagesNumber;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<ImageUrls> getImageurls() {
        return imageurls;
    }

    public void setImageurls(List<ImageUrls> imageurls) {
        this.imageurls = imageurls;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

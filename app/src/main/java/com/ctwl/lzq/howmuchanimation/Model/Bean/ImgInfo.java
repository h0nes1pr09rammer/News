package com.ctwl.lzq.howmuchanimation.Model.Bean;

/**
 * Created by h0nes1pr09rammer on 2016/8/10.
 */
public class ImgInfo {
    String path;
    boolean isSelected;

    public ImgInfo(String path, boolean isSelected) {
        this.path = path;
        this.isSelected = isSelected;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

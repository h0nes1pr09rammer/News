package com.ctwl.lzq.howmuchanimation.Model.Data;

/**
 * Created by B41-80 on 2016/7/5.
 */
public class Dialogue {
    /**"id": "1572",//台词id
            "taici": "即使无法实现愿望，在某个地方也会有其他小小的幸福，等待著我们吧。",//台词内容
            "cat": "a",//台词分类，具体请参考手册
            "catcn": "动画",//台词分类，具体请参考手册
            "show": null,//台词角色
            "source": "蜂蜜与四叶草"//台词出处
     */
    String id;
    String taici;
    String cat;
    String catcn;
    String show;
    String source;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaici() {
        return taici;
    }

    public void setTaici(String taici) {
        this.taici = taici;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getCatcn() {
        return catcn;
    }

    public void setCatcn(String catcn) {
        this.catcn = catcn;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}

package com.ctwl.lzq.howmuchanimation.Model;

import com.ctwl.lzq.howmuchanimation.Callback.JsonCallBack;
import com.ctwl.lzq.howmuchanimation.Model.Data.Dialogue;

/**
 * Created by B41-80 on 2016/7/5.
 */
public interface DialogueDataSource {
    Dialogue getDialogue();
    void loadingData(JsonCallBack jsonCallBack);
    void loadConstellData(JsonCallBack jsonCallBack);
}

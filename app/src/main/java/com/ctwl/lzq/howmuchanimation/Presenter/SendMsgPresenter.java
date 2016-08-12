package com.ctwl.lzq.howmuchanimation.Presenter;

import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVUser;
import com.ctwl.lzq.howmuchanimation.Callback.LeancloudSaveCallback;
import com.ctwl.lzq.howmuchanimation.Contract.SendContract;
import com.ctwl.lzq.howmuchanimation.Utils.BitmapUtils;
import com.ctwl.lzq.howmuchanimation.Utils.ImageLoaderUtils;
import com.ctwl.lzq.howmuchanimation.Utils.LeanCloudUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by h0nes1pr09rammer on 2016/8/12.
 */
public class SendMsgPresenter implements SendContract.Presenter{
    SendContract.View mView;
    public SendMsgPresenter(SendContract.View view) {
        this.mView = view;
    }
    @Override
    public void send(String content,List<String> mList) {
        List<AVFile> mAvFiles = new ArrayList<>();
        Map<String,Object> map = new HashMap<String, Object>();
        for (String s:mList){
            AVFile avFile = new AVFile(""+System.currentTimeMillis(), BitmapUtils.Bitmap2Bytes(ImageLoaderUtils.getInstance().decodeSampledBitmapFromNative(s,100,100)));
            mAvFiles.add(avFile);
        }
        map.put("content",content);
        map.put("imgList", mAvFiles);
        map.put("name", LeanCloudUtils.getCurrentUser().getString("name"));
        map.put("username", LeanCloudUtils.getCurrentUser().getUsername());
        LeanCloudUtils.saveInBackground("share", map, new LeancloudSaveCallback() {
            @Override
            public void onSaveSuccess() {
                mView.sendSuccess();
            }

            @Override
            public void onSaveFaile(String eor) {
                mView.showErrorMsg(eor);
            }
        });
    }

    @Override
    public void start() {

    }
}

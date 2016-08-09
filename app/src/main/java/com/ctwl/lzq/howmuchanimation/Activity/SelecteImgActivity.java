package com.ctwl.lzq.howmuchanimation.Activity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ctwl.lzq.howmuchanimation.Adapter.SelecteImgAdapter;
import com.ctwl.lzq.howmuchanimation.Diy.DividerGridItemDecoration;
import com.ctwl.lzq.howmuchanimation.R;
import com.ctwl.lzq.howmuchanimation.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by h0nes1pr09rammer on 2016/8/9.
 */
public class SelecteImgActivity extends AppCompatActivity{
    ArrayList<String> mImgList;
    private static final int SCAN_OK = 0x11;
    @BindView(R.id.select_img_recyclerview)
    RecyclerView mRecyclerView;
    SelecteImgAdapter mSelecteImgAdapter;
    Handler mHandler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_img);
        ButterKnife.bind(this);
        mImgList = new ArrayList<>();
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        mSelecteImgAdapter = new SelecteImgAdapter(mImgList,this);
        mRecyclerView.setAdapter(mSelecteImgAdapter);
        getImg();
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case SCAN_OK:
                        mImgList.addAll(msg.getData().getStringArrayList("img_list"));
                        mSelecteImgAdapter.notifyDataSetChanged();
                        break;
                }
            }
        };
    }

    private void getImg() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = getContentResolver();
                //只查询jpeg和png的图片
                Cursor mCursor = mContentResolver.query(mImageUri, null, null, null,null);
                while (mCursor.moveToNext()){
                    String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    mImgList.add(path);
                    Log.v("SelecteImgActivity",path);
                }
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("img_list",mImgList);
                message.what = SCAN_OK;
                message.setData(bundle);
                mHandler.sendMessage(message);
            }
        }).start();
    }
}

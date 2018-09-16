package com.example.xutils.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.xutils.R;
import com.example.xutils.adapter.MyAdapter;
import com.example.xutils.bean.InfoBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

@ContentView(R.layout.activity_image)
public class ImageActivity extends AppCompatActivity {

    private List<InfoBean.InfoListBean> datas;

    private String url ="http://www.moviebase.cn/uread/app/recommend/recommend?platform=2&deviceId=A8C73E0D1ED1A2BED491C9238C8FD8A0&pageContext=1&appVersion=1.7.0";

    private MyAdapter adapter;

    @ViewInject(R.id.lv_showImage)
    ListView lv_showImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        init();
    }

    private void init() {
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                InfoBean infoBean = gson.fromJson(result,InfoBean.class);
                datas = infoBean.getInfoList();
                adapter = new MyAdapter(ImageActivity.this,datas);
                lv_showImage.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(ImageActivity.this,"数据错误"+ex.getMessage(),Toast.LENGTH_SHORT).show();
                Log.e("TAG",ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }
}

package com.example.xutils.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xutils.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.btn_getPic)
    Button btn_getPic;

//    @ViewInject(R.id.iv_showImage)
//    ImageView iv_showImage;

    @ViewInject(R.id.btn_getText)
    Button btn_getText;

    @ViewInject(R.id.btn_downLoad)
    Button btn_downLoad;

    @ViewInject(R.id.tv_showText)
    TextView tv_showText;

    @ViewInject(R.id.btn_database)
    Button btn_database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }

    @Event(value = {R.id.btn_getPic,R.id.btn_getText,R.id.btn_downLoad,R.id.btn_database})
    private void onClick(View v){
        switch (v.getId()){
            case R.id.btn_getPic:
                Intent intent3 =new Intent(MainActivity.this,ImageActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_getText:
                showText();
                break;
            case R.id.btn_downLoad:
                Intent intent = new Intent(MainActivity.this,DownLoadActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_database:
                Intent intent2 = new Intent(MainActivity.this,DatabaseActivity.class);
                startActivity(intent2);
                break;
        }
    }

    private void showText() {
        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("TAG","onSuccess"+result);
                tv_showText.setText("onSuccess----->"+result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(MainActivity.this,"加载失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

   /* private void showImage() {
        String url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536748154272&di=dbaea8aca35b83867afdec462bdcf3ac&imgtype=0&src=http%3A%2F%2Fi1.umei.cc%2Fuploads%2Ftu%2F201807%2F9999%2F95ec4d7f64.jpg";
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setFadeIn(true) //淡入效果
                .build();
        x.image().bind(iv_showImage, url, imageOptions, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {
                iv_showImage.setImageDrawable(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(MainActivity.this,"加载失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }*/

}

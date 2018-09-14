package com.example.xutils.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.xutils.R;

import org.xutils.common.Callback;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

@ContentView(R.layout.activity_download)
public class DownLoadActivity extends AppCompatActivity {

    @ViewInject(R.id.btn_getFile)
    Button btn_getFile;

    @ViewInject(R.id.progress)
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }

    @Event(value = {R.id.btn_getFile})
    private void onClick(View v){
        switch (v.getId()){
            case R.id.btn_getFile:
                downLoadFile();
                break;
        }
    }

    private void downLoadFile() {
        String url = "http://vfx.mtime.cn/Video/2018/09/07/mp4/180907100129653266.mp4";
        RequestParams params = new RequestParams(url);
        //设置保存数据
//        params.setSaveFilePath(Environment.getExternalStorageState()+"/atguigu/480.mp4");
        //设置是否可以取消下载
        params.setCancelFast(true);
        //设置是否自动根据头信息命名
        params.setAutoRename(false);
        //设置断点续传
        params.setAutoRename(true);

        params.setExecutor(new PriorityExecutor(3,true)); //开启线程池
        x.http().get(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onSuccess(File file) {
                Log.d("TAG",file.toString());
                Toast.makeText(DownLoadActivity.this,"result"+file.toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("TAG","onError"+ex.toString());
                Toast.makeText(DownLoadActivity.this,ex.toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.d("TAG","onCancelled"+cex.toString());
            }

            @Override
            public void onFinished() {
                Log.d("TAG","onFinished");
            }

            @Override
            public void onWaiting() {
                Log.d("TAG","onWaiting");
            }

            @Override
            public void onStarted() {
                Log.d("TAG","onStarted");
            }

            /**
             * 进度设置
             * */
            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                progress.setMax((int) total);
                progress.setProgress((int) current);
                Log.i("TAG","onLoading----->"+current+"/"+total+"--"+isDownloading);
            }
        });
    }
}
